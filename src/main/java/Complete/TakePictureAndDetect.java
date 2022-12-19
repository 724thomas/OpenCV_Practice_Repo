package Complete;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

public class TakePictureAndDetect {
    public static void TakePictureAndDetectFace(String[] args) {
        // Load the OpenCV library
//        System.setProperty("java.library.path", "C:/opencv/build/java/opencv-460.jar");
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Create a new video capture object
        VideoCapture capture = new VideoCapture(0);

        // Check if the video capture object was created successfully
        if (!capture.isOpened()) {
            System.out.println("Error: Could not access webcam.");
            return;
        }

        // Create a new matrix to store the video frame

        int detectedFaces=0;
        while( detectedFaces!=1){
            Mat frame = new Mat();
            // Read a frame from the video capture object
            capture.read(frame);
            // Save the frame to a file
            org.opencv.imgcodecs.Imgcodecs.imwrite("picture.jpg", frame);
            // Release the video capture object
            capture.release();
            // Load the input image
            Mat image = Imgcodecs.imread("picture.jpg");
            // Load the cascade classifier
            CascadeClassifier faceDetector = new CascadeClassifier("C:/opencv/build/etc/haarcascades/haarcascade_frontalface_default.xml");
            // Detect faces in the image
            MatOfRect faceDetections = new MatOfRect();
            faceDetector.detectMultiScale(image, faceDetections);

            if (faceDetections.toArray().length == 0) {
                System.out.println("No face detected");
                continue;
            } else if (faceDetections.toArray().length == 1) {
                System.out.println("Face detected");
                detectedFaces=1;
            } else {
                System.out.println("Multiple faces detected");
                continue;
            }
            // Draw a bounding box around each face
            for (Rect rect : faceDetections.toArray()) {
                Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 255), 3);
            // Save the output image
            Imgcodecs.imwrite("output.jpg", image);
            }
        }
    }
}
