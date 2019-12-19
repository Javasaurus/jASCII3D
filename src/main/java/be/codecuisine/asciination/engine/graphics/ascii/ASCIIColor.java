/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.codecuisine.asciination.engine.graphics.ascii;

/**
 *
 * @author kenne
 */
public class ASCIIColor {

    public static int[] pixels;
    public static char[] characters = "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\\\|()1{}[]?-_+~<>i!lI;:,\\\"^`' .".toCharArray();
    //public static char[] characters = ".:-=+*#%@ ".toCharArray();

    private static ASCIIColor instance = new ASCIIColor();

    public static void initASCIIGrayScales() {
        instance = new ASCIIColor();
        pixels = new int[characters.length];
        for (int i = 0; i < characters.length; i++) {
            pixels[i] = i;
        }
    }

    public static int getEmptyPixel() {
        return characters.length - 1;
    }

    public static int getSolidPixel() {
        return 0;
    }

    public static int getPixel(double angleCos) {
        //exclude the empty pixel here
        //push it to become a bit biased to the left (fill)
        angleCos *= .85;
        int value = (int) ((characters.length - 2) * angleCos) + 1;
        if (value >= characters.length - 1) {
            value = characters.length - 2;
        }
        if (value < 0) {
            value = 0;
        }
        return pixels[value];
    }

    public static char getCharacter(int value) {
        if (value >= characters.length) {
            value = characters.length - 1;
        }
        if (value < 0) {
            value = 0;
        }
        return characters[value];
    }

    private ASCIIColor() {
    }

}
