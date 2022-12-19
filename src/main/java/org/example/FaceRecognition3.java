//package org.example;
//
//import org.opencv.core.*;
//import org.opencv.face.FaceRecognizer;
//import org.opencv.face.LBPHFaceRecognizer;
//import org.opencv.objdetect.CascadeClassifier;
//import org.opencv.videoio.VideoCapture;
//
//public class FaceRecognition3 {
//    public static void main(String[] args) {
//        // Load the OpenCV library
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//
//        // Load the LBPH face recognition model
//        FaceRecognizer faceRecognizer = LBPHFaceRecognizer.create();
//        faceRecognizer.read("/path/to/lbph_model.xml");
//
//        // Load the Haar cascade classifier for face detection
//        CascadeClassifier faceDetector = new CascadeClassifier("C:/opencv/build/etc/haarcascades/haarcascade_frontalface_default.xml");
//
//        // Capture a video stream from the webcam
//        VideoCapture videoCapture = new VideoCapture(0);
//
//        // Loop through the video frames
//        while (true) {
//            // Read a frame from the video stream
//            Mat frame = new Mat();
//            videoCapture.read(frame);
//
//            // Detect faces in the frame
//            MatOfRect faceDetections = new MatOfRect();
//            faceDetector.detectMultiScale(frame, faceDetections);
//
//            // Loop through the detected faces
//            for (Rect rect : faceDetections.toArray()) {
//                // Crop the detected face from the frame
//                Mat face = new Mat(frame, rect);
//
//                // Compare the detected face with the known face
//                int prediction = faceRecognizer.predict(face);
//
//                if (prediction == knownFaceId) {
//                    // The detected face matches the known face
//                    System.out.println("Match found!");
//                } else {
//                    // The detected face does not match the known face
//                    System.out.println("Match not found.");
//                }
//            }
//        }
//    }
//}