package be.codecuisine.asciination.playground;

import be.codecuisine.asciination.engine.graphics.ascii.ASCIIColor;
import be.codecuisine.asciination.engine.graphics.ascii.ui.ASCIICanvas;
import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public abstract class Demo extends JFrame implements Runnable {

    public static int rows = 128;
    public static int cols = 128;
    private int fontSize = 1;
    protected ASCIICanvas myASCIICanvas;

    protected boolean running;
    protected Thread thread;

    public Demo() {
        super();
        initCanvas();
        thread = new Thread(this);
        start();
    }

    public Demo(int rows, int cols, int fontSize) {
        super();
        this.fontSize = fontSize;
        this.rows = rows;
        this.cols = cols;
        initCanvas();
        thread = new Thread(this);
        start();
    }

    private void initCanvas() {
        ASCIIColor.initASCIIGrayScales();
        myASCIICanvas = new ASCIICanvas(rows, cols, fontSize);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.add(myASCIICanvas);
        add((scrollPane));
    }

    private synchronized void start() {
        running = true;
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        final double ns = 1000000000.0f / 60.0f;//60 times per second
        double delta = 0;
        while (running) {
            long now = System.nanoTime();
            delta = delta + ((now - lastTime) / ns);
            lastTime = now;
            while (delta >= 1)//Make sure update is only happening 60 times a second
            {
                //handles all of the logic restricted time
                update(delta / 1000);
                delta--;
            }
            render();//displays to the screen unrestricted time
        }
    }

    public abstract void update(double delta);

    public void render() {
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    myASCIICanvas.flush();
                }
            });
        } catch (InterruptedException | InvocationTargetException ex) {
            Logger.getLogger(Demo.class.getName()).log(Level.SEVERE, null, ex);
        }
        myASCIICanvas.setSize(myASCIICanvas.getPreferredScrollableViewportSize());
        // scrollPane.setSize(ta.getPreferredScrollableViewportSize());
        setSize(myASCIICanvas.getPreferredScrollableViewportSize());

    }

    public static void StartApp(Class demoClass) {

        try {
            Constructor<?> ctor = demoClass.getConstructor();
            Object object = ctor.newInstance();
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    JFrame frame = (Demo) object;
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    frame.setVisible(true);
                }
            });
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(Demo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
