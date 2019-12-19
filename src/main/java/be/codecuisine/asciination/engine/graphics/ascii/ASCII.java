package be.codecuisine.asciination.engine.graphics.ascii;

public final class ASCII {

    public ASCII() {
    }

    public static void convert(final int[] pixels, final int rows, final int cols, StringBuilder sb) {
        int index = -1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                index = (i * cols) + j;
                if (index % cols == 0) {
                    sb.setCharAt(index, '\n');
                } else {
                    sb.setCharAt(index, GetAscii(pixels[index]));
                }
            }
        }    //    sb=sb.reverse();
    }

    private static char GetAscii(int pixel) {
        return ASCIIColor.getCharacter(pixel);
    }

}
