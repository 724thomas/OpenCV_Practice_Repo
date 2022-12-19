package org.example;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class CompareTwoFaces {
    public static void main(String[] args) {
        // Load the OpenCV library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Load the images of the two faces that you want to compare
        Mat face1 = Imgcodecs.imread("picture.jpg");
        Mat face2 = Imgcodecs.imread("picture1.jpg");

        // Load the Haar cascade classifier
        CascadeClassifier faceCascade = new CascadeClassifier("C:/opencv/build/etc/haarcascades/haarcascade_frontalface_default.xml"); // Load the face classifier

        // Detect and extract the faces from the images
        MatOfRect face1Rects = new MatOfRect();
        faceCascade.detectMultiScale(face1, face1Rects);
        MatOfRect face2Rects = new MatOfRect();
        faceCascade.detectMultiScale(face2, face2Rects);

        // Extract the first face from the first image
        Mat grayFace1 = null;
        if (face1Rects.toArray().length > 0) {
            Rect rect = face1Rects.toArray()[0];
            Mat face = new Mat(face1, rect);
            // Convert the face to grayscale
            grayFace1 = new Mat();
//            Imgproc.cvtColor(face, grayFace1, Imgproc.COLOR_BGR2GRAY);
        }

        // Extract the first face from the second image
        Mat grayFace2 = null;
        if (face2Rects.toArray().length > 0) {
            Rect rect = face2Rects.toArray()[0];
            Mat face = new Mat(face2, rect);
            // Convert the face to grayscale
            grayFace2 = new Mat();
//            Imgproc.cvtColor(face, grayFace2, Imgproc.COLOR_BGR2GRAY);
        }

        // Compare the two faces using the compareHist function
        System.out.println("A");
        double similarity = Imgproc.compareHist(grayFace1, grayFace2, Imgproc.CV_COMP_CORREL);
        System.out.println("A");
        // Use a threshold value to determine whether the two faces are a match
        double threshold = 0.5;
        if (similarity > threshold) {
            System.out.println("The two faces are a match");
        } else {
            System.out.println("The two faces are not a match");

        }
    }
}