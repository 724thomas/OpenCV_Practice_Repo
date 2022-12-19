package org.example;

import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

public class FaceDetection2 {
    public static void main(String[] args) {
        // Load the OpenCV library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Create a VideoCapture object to access the video stream
        VideoCapture capture = new VideoCapture(0);

        // Check if the video stream is available
        if (!capture.isOpened()) {
            System.out.println("Error: Unable to access video stream");
            return;
        }

        // Create a CascadeClassifier object to detect faces
        CascadeClassifier faceDetector = new CascadeClassifier("C:/opencv/build/etc/haarcascades/haarcascade_frontalface_default.xml");

        // Set up a loop to process the video stream
        Mat frame = new Mat();
        while (true) {
            // Read a frame from the video stream
            capture.read(frame);

            // Detect faces in the frame
            MatOfRect faceDetections = new MatOfRect();
            faceDetector.detectMultiScale(frame, faceDetections);

            // Draw a rectangle around each detected face
            for (Rect rect : faceDetections.toArray()) {
                Imgproc.rectangle(frame, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0), 3);
            }

            // Display the frame with the detected faces
            // (You can use a GUI library like JavaFX or Swing to display the frame)
            // frame.showFrame();
            // Display the frame in a window
        HighGui.imshow("Video", frame);

// Wait for a key press before exiting
        HighGui.waitKey(0);
        }
    }
}