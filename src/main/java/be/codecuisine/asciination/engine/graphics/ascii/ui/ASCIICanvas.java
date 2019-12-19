/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.codecuisine.asciination.engine.graphics.ascii.ui;

import be.codecuisine.asciination.engine.graphics.ascii.ASCII;
import be.codecuisine.asciination.engine.graphics.ascii.ASCIIColor;
import be.codecuisine.asciination.engine.math.Vector;
import be.codecuisine.asciination.playground.Demo;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import javax.swing.JTextPane;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 *
 * @author kenne
 */
public class ASCIICanvas extends JTextPane {

    private int[] pixels;
    private double[] zBuffer;
    int maxWidth;
    int maxHeight;
    int rowHeight;
    private Font monoFont;
    private final int rows;
    private final int columns;
    private StringBuilder sb;

    public static ASCIICanvas INSTANCE;
    private int fontWidth;
    private int fontHeight;
    private int fontAR;

    public ASCIICanvas(int rows, int columns, int fontSize) {
        INSTANCE.INSTANCE = this;
        //CREATE A MONOSPACE CANVAS
        this.rows = rows;
        this.columns = columns;
        this.pixels = new int[rows * columns];
        initMonoSpacedScreen(fontSize);
        initStringBuilder();
        initZBuffer();

        flush();
    }

    private void initMonoSpacedScreen(int fontSize) {

        MutableAttributeSet set = new SimpleAttributeSet();
        StyleConstants.setLineSpacing(set, -.21f);
        setParagraphAttributes(set, false);
        //Making font monospaced
        monoFont = new Font("Monospaced", Font.PLAIN, fontSize);
        setFont(monoFont);
        setBackground(Color.BLACK);
        setForeground(Color.GREEN);
        setEditable(false);

        FontMetrics fontMetrics = getFontMetrics(monoFont);
        fontWidth = fontMetrics.charWidth('W');
        fontHeight = fontMetrics.getHeight();
        fontAR = fontHeight / fontWidth;
        maxWidth = fontMetrics.charWidth('M') * columns;
        rowHeight = fontMetrics.getHeight();
        maxHeight = (int) (rows * (rowHeight - .67f));

    }

    private void initZBuffer() {
        zBuffer = new double[pixels.length];
        for (int q = 0; q < zBuffer.length; q++) {
            zBuffer[q] = Double.NEGATIVE_INFINITY;
        }
    }

    @Override
    public int getWidth() {
        return maxWidth;
    }

    @Override
    public int getHeight() {
        return maxWidth;//maxHeight;
    }

    private void initStringBuilder() {
        sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                sb.append(' ');
            }
            sb.append("\n");
        }
    }

    public void flush() {
        ASCII.convert(pixels, rows, columns, sb);
        setText(sb.toString());
        clear();
    }

    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = ASCIIColor.getEmptyPixel();
            zBuffer[i] = Double.NEGATIVE_INFINITY;
        }
    }

    @Override
    public void setFont(Font font) {
        super.setFont(font);
    }

    @Override
    public Dimension getPreferredScrollableViewportSize() {
        return new Dimension(maxWidth, maxWidth);
    }

    //DRAWING METHODS
    public void drawPoint(int x, int y, int myPixel) {
        putPixel(x, y, myPixel);
    }

    public void drawPoint(Vector point, int pixel) {
        drawPoint((int) point.x, (int) point.y, pixel);
    }

    public void drawLine(Vector p1, Vector p2, int myPixel) {
        drawLine((int) p1.x, (int) p1.y, (int) p2.x, (int) p2.y, myPixel);
    }

    public void drawCircle(int x, int y, double radius, int myPixel) {

        y = Math.min(rows, rows - y);

        double i, angle, x1, y1;
        for (i = 0; i < 360; i += 0.1) {
            angle = i;
            x1 = radius * Math.cos(angle * Math.PI / 180);
            y1 = radius * Math.sin(angle * Math.PI / 180);
            putPixel((int) (x + x1), (int) (y - y1), myPixel);
        }
    }

    public void drawCircle(Vector p1, double radius, int myPixel) {
        drawCircle((int) p1.x, (int) p1.y, radius, myPixel);
    }

    public void drawLine(int x1, int y1, int x2, int y2, int myPixel) {

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = (x1 < x2) ? 1 : -1;
        int sy = (y1 < y2) ? 1 : -1;
        int err = dx - dy;

        putPixel(x1, y1, myPixel);
        while ((x1 != x2) || (y1 != y2)) {
            int err2 = err << 1;
            if (err2 > -dy) {
                err -= dy;
                x1 += sx;
            }
            if (err2 < dx) {
                err += dx;
                y1 += sy;
            }
            putPixel(x1, y1, myPixel);
        }
    }

    public void fillTriangle(Vector v1, Vector v2, Vector v3, int pixel) {
        if (pixel == ASCIIColor.getEmptyPixel()) {
            return;
        }

        fillBaryCentric(v1, v2, v3, pixel);
    }

    public void fillBaryCentric(Vector v1, Vector v2, Vector v3, int pixel) {
        // since we are not using Graphics2D anymore,
        // we have to do translation manually
        /*   v1.x += columns / 2;
        v1.y += rows / 2;
        v2.x += columns / 2;
        v2.y += rows / 2;
        v3.x += columns / 2;
        v3.y += rows / 2;*/
        // compute rectangular bounds for triangle
        int minX = (int) Math.max(-columns / 2, Math.ceil(Math.min(v1.x, Math.min(v2.x, v3.x))));
        int maxX = (int) Math.min(columns / 2 - 1,
                Math.floor(Math.max(v1.x, Math.max(v2.x, v3.x))));
        int minY = (int) Math.max(-rows / 2, Math.ceil(Math.min(v1.y, Math.min(v2.y, v3.y))));
        int maxY = (int) Math.min(rows / 2 - 1,
                Math.floor(Math.max(v1.y, Math.max(v2.y, v3.y))));

        double triangleArea
                = (v1.y - v3.y) * (v2.x - v3.x) + (v2.y - v3.y) * (v3.x - v1.x);

        for (int y = minY; y <= maxY; y++) {
            for (int x = minX; x <= maxX; x++) {
                double b1
                        = ((y - v3.y) * (v2.x - v3.x) + (v2.y - v3.y) * (v3.x - x)) / triangleArea;
                double b2
                        = ((y - v1.y) * (v3.x - v1.x) + (v3.y - v1.y) * (v1.x - x)) / triangleArea;
                double b3
                        = ((y - v2.y) * (v1.x - v2.x) + (v1.y - v2.y) * (v2.x - x)) / triangleArea;
                if (b1 >= 0 && b1 <= 1 && b2 >= 0 && b2 <= 1 && b3 >= 0 && b3 <= 1) {
                    double depth = b1 * v1.z + b2 * v2.z + b3 * v3.z;
                    putPixel(x, y, depth, pixel);
                }
            }
        }
    }

    public void putPixel(int x, int y, int pixel) {
        putPixel(x, y, -Double.MAX_VALUE, pixel);
    }

    public void putPixel(int x, int y, double z, int pixel) {
        //center is at columns/2, rows/2
        int tmpX = (x * fontAR) + columns / 2;
        int tmpY = (rows - y) - rows / 2;
        if (tmpX < columns && tmpX > 0 && tmpY < rows && tmpY > 0) {
            int index = (tmpY * columns) + tmpX;
            if (zBuffer[index] <= z) {
                pixels[index] = pixel;
                zBuffer[index] = z;
            }

        }
    }

}
