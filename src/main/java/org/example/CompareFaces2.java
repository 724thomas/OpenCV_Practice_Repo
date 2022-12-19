package org.example;

import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

public class CompareFaces2 {
    public static void main(String[] args) {
        System.setProperty("java.library.path", "C:/opencv/build/java/x64");
        // Load the OpenCV library
        System.loadLibrary("opencv_java460");

        // Create a new VideoCapture object
        VideoCapture capture = new VideoCapture(0);

        // Check if the video stream is open
        if (!capture.isOpened()) {
            System.out.println("Error opening video stream");
            return;
        }



        // Load the face classifier
        CascadeClassifier faceDetector = new CascadeClassifier("C:/opencv/build/etc/haarcascades/haarcascade_frontalface_default.xml");


        Mat image = Imgcodecs.imread("picture.jpg");
        MatOfRect imageFaceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image, imageFaceDetections);
        Mat imageFace = new Mat(image,imageFaceDetections.toArray()[0]);

        // Loop through the frames of the video stream
        while (true) {
            // Read a frame from the video stream
            Mat frame = new Mat();
            capture.read(frame);

            // Detect faces in the frame
            MatOfRect faces = new MatOfRect();
            faceDetector.detectMultiScale(frame, faces);

            // Iterate over the detected faces and draw a rectangle around each face
            for (Rect rect : faces.toArray()) {
                Imgproc.rectangle(frame, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0), 3);
            }
            double similarity = org.opencv.imgproc.Imgproc.compareHist(imageFace, frame, Imgproc.CV_COMP_CORREL);
            System.out.println(similarity);
            // Display the frame in a window
            HighGui.imshow("Video", frame);

            // Wait for a key press before continuing to the next frame
            int key = HighGui.waitKey(1);
            if (key == 27) { // Escape key
                break;
            }
        }

        // Release the video capture resources
        capture.release();
    }
}