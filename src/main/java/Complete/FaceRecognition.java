package Complete;

import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

import java.util.Arrays;

public class FaceRecognition {

    public static void TakePicture(String outputFileName) {
        // Load the OpenCV library
        nu.pattern.OpenCV.loadLocally();
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
        org.opencv.imgcodecs.Imgcodecs.imwrite(outputFileName, frame);

        // Release the video capture object
        capture.release();
    }


    public static void TakePictureAndDetectFace(String outputFileName) {
        // Load the OpenCV library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        // Create a new video capture object
        // Check if the video capture object was created successfully
        // Create a new matrix to store the video frame
        int detectedFaces=0;
        while( detectedFaces!=1){
            VideoCapture capture = new VideoCapture(0);
            if (!capture.isOpened()) {
                System.out.println("Error: Could not access webcam.");
                return;
            }
            Mat frame = new Mat();
            // Read a frame from the video capture object
            capture.read(frame);
            // Save the frame to a file
            org.opencv.imgcodecs.Imgcodecs.imwrite(outputFileName, frame);
            // Release the video capture object
            capture.release();
            // Load the input image
            Mat image = Imgcodecs.imread(outputFileName);
            // Load the cascade classifier
            CascadeClassifier faceDetector = new CascadeClassifier("C:/opencv/build/etc/haarcascades/haarcascade_frontalface_default.xml");
            // Detect faces in the image
            MatOfRect faceDetections = new MatOfRect();
            faceDetector.detectMultiScale(image, faceDetections);

            if (faceDetections.toArray().length == 0) {
                System.out.println("No face detected");
            } else if (faceDetections.toArray().length == 1) {
                System.out.println("Face detected");
                detectedFaces=1;
            } else {
                System.out.println("Multiple faces detected");
            }
            // Draw a bounding box around each face
//            for (Rect rect : faceDetections.toArray()) {
//                Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 255), 3);
//                // Save the output image
//                Imgcodecs.imwrite(outputFileName, image);
//            }
        }
    }

    public static void DrawRectangleOnPicture(String fileName) {
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
        Imgcodecs.imwrite("Rect"+fileName, image);
    }

    public static void FaceSimilarityVideoAndPicture(String basePicture) {
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
        CascadeClassifier faceClassifier = new CascadeClassifier("C:/opencv/build/etc/haarcascades/haarcascade_frontalface_default.xml");
        // Loop through the frames of the video stream
        while (true) {
            // Read a frame from the video stream
            Mat frame = new Mat();
            capture.read(frame);
            // Detect faces in the frame
            compareFace(basePicture,frame);
            MatOfRect faces = new MatOfRect();
            faceClassifier.detectMultiScale(frame, faces);

            // Iterate over the detected faces and draw a rectangle around each face
            for (Rect rect : faces.toArray()) {
                Imgproc.rectangle(frame, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0), 3);
            }

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

    public static double compareFace(String filename, Mat frame){
        Mat image1 = Imgcodecs.imread(filename, Imgcodecs.IMREAD_GRAYSCALE);

        Mat hist1= new Mat();
        Mat hist2= new Mat();
        Imgproc.calcHist(Arrays.asList(image1), new MatOfInt(0), new Mat(), hist1, new MatOfInt(256), new MatOfFloat(0, 256));
        Imgproc.calcHist(Arrays.asList(frame), new MatOfInt(0), new Mat(), hist2, new MatOfInt(256), new MatOfFloat(0, 256));

        double similarity = Imgproc.compareHist(hist1, hist2, Imgproc.CV_COMP_CORREL);
        System.out.println("Similarity: " + similarity);
        return similarity;
    }
}
