package org.example;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

import java.util.Arrays;

public class CompareFaces {
    public static void main(String[] args) {
        // Load the OpenCV library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Create a VideoCapture object to access the video stream
        VideoCapture videoCapture = new VideoCapture(0);

        // Check if the video stream is open
        if (!videoCapture.isOpened()) {
            System.out.println("Unable to open the video stream.");
            return;
        }
        // Create a Mat object to store the frame from the video stream
        Mat frame = new Mat();

        // Read a frame from the video stream
        videoCapture.read(frame);

        // Use the Imgcodecs class to read the image file into a Mat object
        Mat image = Imgcodecs.imread("picture.jpg");

        // Use the CascadeClassifier class to detect faces in the frame and the image
        CascadeClassifier faceDetector = new CascadeClassifier("C:/opencv/build/etc/haarcascades/haarcascade_frontalface_default.xml");
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(frame, faceDetections);
        MatOfRect imageFaceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image, imageFaceDetections);

        System.out.println(Arrays.toString(faceDetections.toArray()));
        System.out.println(Arrays.toString(imageFaceDetections.toArray()));

        // Compare the faces using the compareHist() method of the Core class
        if (faceDetections.toArray().length > 0 && imageFaceDetections.toArray().length > 0) {
            Mat face = new Mat(frame, faceDetections.toArray()[0]);
            Mat imageFace = new Mat(image, imageFaceDetections.toArray()[0]);
//            double similarity = Imgproc.compareHist(face, imageFace, Imgproc.HISTCMP_CORREL);
            double similarity = Imgproc.compareHist(face, imageFace, 1);
            System.out.println("Similarity: " + similarity);
        }


        // Release the video stream
        videoCapture.release();
    }
}