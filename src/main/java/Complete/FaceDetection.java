package Complete;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class FaceDetection {
    public static void RectangleFaceOnPicture(String fileName) {

        // Load the OpenCV library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Load the input image
        Mat image = Imgcodecs.imread(fileName);

        // Load the cascade classifier
        CascadeClassifier faceDetector = new CascadeClassifier("C:/opencv/build/etc/haarcascades/haarcascade_frontalface_default.xml");

        // Detect faces in the image
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image, faceDetections);

        if (faceDetections.toArray().length == 0) {
            System.out.println("No face detected");
        } else if (faceDetections.toArray().length == 1) {
            System.out.println("Face detected");
        } else {
            System.out.println("Multiple faces detected");
        }

        // Draw a bounding box around each face
        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 255), 3);
        }

        // Save the output image
        Imgcodecs.imwrite("output.jpg", image);
    }
}