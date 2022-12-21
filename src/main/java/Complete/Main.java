package Complete;

import org.opencv.core.Core;

public class Main {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        nu.pattern.OpenCV.loadLocally();
        System.setProperty("java.library.path", "C:/opencv/build/java/opencv-460.jar");
        FaceRecognition.TakePicture("Test.jpg");
//        FaceRecognition.TakePictureAndDetectFace("Test.jpg");
//        FaceRecognition.DrawRectangleOnPicture("Test.jpg");
        FaceRecognition.FaceSimilarityVideoAndPicture("Test.jpg");
    }
}