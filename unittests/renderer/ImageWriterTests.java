package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

class ImageWriterTests {

    ImageWriter imageWriter = new ImageWriter("imageTest", 800, 500);

    @Test
    void writePixel() {
        int nx;
        int ny;
        for(nx=0; nx < 800; nx++) {
            for (ny=0; ny < 500; ny++) {
                imageWriter.writePixel(nx,ny, Color.YELLOW);
            }
        }

        int row=0;
        int col=0;

        for(; row < 16; row++) {
            for(int j = 0; j < 500; j++) {
                imageWriter.writePixel(row*50, j, Color.RED);
            }
        }

        for(; col < 10; col++) {
            for(int j = 0; j < 800; j++) {
                imageWriter.writePixel(j, col*50, Color.RED);
            }
        }

        imageWriter.writeToImage();
    }
}