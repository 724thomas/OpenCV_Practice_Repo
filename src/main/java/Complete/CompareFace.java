package Complete;

import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

public class CompareFace {
    public static void main(String[] args) {
        System.setProperty("java.library.path", "C:/opencv/build/java/x64");
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME); // Load the OpenCV library
        CascadeClassifier faceClassifier = new CascadeClassifier("C:/opencv/build/etc/haarcascades/haarcascade_frontalface_default.xml"); // Load the face classifier
        VideoCapture videoCapture = new VideoCapture(0); // Create a VideoCapture object to access the video stream
        if (!videoCapture.isOpened()) { // Check if the video stream is open
            System.out.println("Unable to open the video stream.");
            return;
        }

        while (true){
            Mat frame = new Mat(); // Create a Mat object to store the frame from the video stream
            videoCapture.read(frame);

            // Detect faces in the frame
            MatOfRect faces = new MatOfRect();
            faceClassifier.detectMultiScale(frame, faces);

            // Iterate over the detected faces and draw a rectangle around each face
            for (Rect rect : faces.toArray()) {
                Imgproc.rectangle(frame, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0), 3);
            }

            HighGui.imshow("Video", frame); // Display the frame in a window
            // Wait for a key press before continuing to the next frame
            int key = HighGui.waitKey(1);
            if (key == 27) { // Escape key
                break;
            }
        }
    }
}
