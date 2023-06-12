import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.File;
import java.io.IOException;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class ImageProcessing {

    public static void main(String[] args) throws IOException {
        String img1 = "D:\\Demo1\\Selenium.png";
        String img2 = "D:\\Demo1\\seleniumIMg.png";
        compareImage2(img1, img2);
        compareImage1(img1, img2);
    }


    public static void compareImage2(String image1, String image2) throws IOException {
        BufferedImage expectedImage = ImageIO.read(new File(image1));
        BufferedImage actualImage = ImageIO.read(new File(image2));

        ImageDiffer imgDiff = new ImageDiffer();
        ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);
        if(diff.hasDiff()) {
            System.out.println("Image is different by method 2.");
        }else{
            System.out.println("Image is same by method 2.");
        }
    }

    public static void compareImage1(String image1, String image2) throws IOException {
// TODO Auto-generated method stub

        BufferedImage img1 = ImageIO.read(new File(image1));
        BufferedImage img2 = ImageIO.read(new File(image2));
        int w1 = img1.getWidth();
        int w2 = img2.getWidth();
        int h1 = img1.getHeight();
        int h2 = img2.getHeight();
        if ((w1!=w2) || (h1!=h2)) {
            System.out.println("Both images should have same dimensions");
        } else {
            long diff = 0;
            for (int j = 0; j < h1; j++) {
                for (int i = 0; i < w1; i++) {
                    //Getting the RGB values of a pixel
                    int pixel1 = img1.getRGB(i, j);
                    Color color1 = new Color(pixel1, true);
                    int r1 = color1.getRed();
                    int g1 = color1.getGreen();
                    int b1 = color1.getBlue();
                    int pixel2 = img2.getRGB(i, j);
                    Color color2 = new Color(pixel2, true);
                    int r2 = color2.getRed();
                    int g2 = color2.getGreen();
                    int b2= color2.getBlue();
                    //sum of differences of RGB values of the two images
                    long data = Math.abs(r1-r2)+Math.abs(g1-g2)+ Math.abs(b1-b2);
                    diff = diff+data;

                }
            }
            System.out.println("diff:"+diff);
            double avg = diff/(w1*h1*3);
            double percentage = (avg/255)*100;
            System.out.println("percentage:"+percentage);
            if(percentage == 0){
                System.out.println("Image is same by method 1.");
            }else{
                System.out.println("Image is different by method 1.");
            }

        }
    }
}
