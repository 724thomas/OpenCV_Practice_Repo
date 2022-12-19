package Complete;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

public class TakePicture {
    public static void TakePicture(String[] args) {
        // Load the OpenCV library
        System.out.println(System.getProperty("java.library.path"));
        System.setProperty("java.library.path", "C:/opencv/build/java/opencv-460.jar");
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Create a new video capture object
        VideoCapture capture = new VideoCapture(0);

        // Check if the video capture object was created successfully
        if (!capture.isOpened()) {
            System.out.println("Error: Could not access webcam.");
            return;
        }

        // Create a new matrix to store the video frame
        Mat frame = new Mat();

        // Read a frame from the video capture object
        capture.read(frame);

        // Save the frame to a file
        org.opencv.imgcodecs.Imgcodecs.imwrite("picture.jpg", frame);

        // Release the video capture object
        capture.release();
    }
}
