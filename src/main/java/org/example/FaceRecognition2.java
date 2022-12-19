package org.example;

import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

public class FaceRecognition2 {
    public static void main(String[] args) {
        // Load the OpenCV library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Create a video capture object
        VideoCapture capture = new VideoCapture(0);

        // Check if video capture is opened successfully
        if (!capture.isOpened()) {
            System.out.println("Error opening video capture device");
            return;
        }

        // Load the classifier model
        CascadeClassifier faceDetector = new CascadeClassifier("C:/opencv/build/etc/haarcascades/haarcascade_frontalface_default.xml");

        // Create a frame for displaying the video
        Mat frame = new Mat();

        // Loop until the user closes the window
        while (true) {
            // Read a frame from the video capture device
            capture.read(frame);

            // Detect faces in the frame
            MatOfRect faceDetections = new MatOfRect();
            faceDetector.detectMultiScale(frame, faceDetections);

            // Draw a bounding box around each face
            for (Rect rect : faceDetections.toArray()) {
                Imgproc.rectangle(frame, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 255));
            }

            // Show the frame in a window
            HighGui.imshow("Video", frame);

            // Check if the user pressed the 'q' key to quit
            if (HighGui.waitKey(1) == 'q') {
                break;
            }
        }

        // Release the video capture device
        capture.release();
    }
}