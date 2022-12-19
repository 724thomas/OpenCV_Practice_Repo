package Complete;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.Arrays;

public class CompareTwoFaces {

    static {System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}

    public static void main(String[] args) {
        // Read in the two images
        Mat image1 = Imgcodecs.imread("picture.jpg", Imgcodecs.IMREAD_GRAYSCALE);
        Mat image2 = Imgcodecs.imread("picture1.jpg", Imgcodecs.IMREAD_GRAYSCALE);
        // Convert the images to grayscale
//        Imgproc.cvtColor(image1, image1, Imgproc.COLOR_BGR2GRAY);
//        Imgproc.cvtColor(image2, image2, Imgproc.COLOR_BGR2GRAY);


        // Compute the histograms for the two images
        Mat hist1 = new Mat();
        Mat hist2 = new Mat();
        Imgproc.calcHist(Arrays.asList(image1), new MatOfInt(0), new Mat(), hist1, new MatOfInt(256), new MatOfFloat(0, 256));
        Imgproc.calcHist(Arrays.asList(image2), new MatOfInt(0), new Mat(), hist2, new MatOfInt(256), new MatOfFloat(0, 256));

        // Compare the histograms using the Chi-Squared distance
        double similarity = Imgproc.compareHist(hist1, hist2, Imgproc.CV_COMP_CORREL);

        // Print the similarity score
        System.out.println("Similarity: " + similarity);
    }
}