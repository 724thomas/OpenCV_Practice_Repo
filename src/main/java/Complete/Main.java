package Complete;

import org.opencv.core.Core;

public class Main {
    public static void main(String[] args) {
        nu.pattern.OpenCV.loadLocally();
        System.setProperty("java.library.path", "C:/opencv/build/java/opencv-460.jar");
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        FaceRecognition.TakePictureAndDetectFace("Test.jpg");
//        FaceRecognition.DrawRectangleOnPicture("Test.jpg");
//        FaceRecognition.FaceSimilarityVideoAndPicture("Test.jpg");
    }
}