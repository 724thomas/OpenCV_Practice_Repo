package Complete;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.objdetect.CascadeClassifier;

public class CutOnlyFace {
    public static void cutOnlyFace(String filename) {
        // Load the OpenCV library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Load the input image
        Mat image = Imgcodecs.imread(filename);

        // Load the cascade classifier
        CascadeClassifier faceDetector = new CascadeClassifier("C:/opencv/build/etc/haarcascades/haarcascade_frontalface_default.xml");

        // Detect faces in the image
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image, faceDetections);

        // Crop the image to just the face
        Rect faceRect = faceDetections.toArray()[0]; // assume there is only one face
        Mat face = new Mat(image, faceRect);

        // Save the output image
        Imgcodecs.imwrite("Test.jpg", face);
    }
}
