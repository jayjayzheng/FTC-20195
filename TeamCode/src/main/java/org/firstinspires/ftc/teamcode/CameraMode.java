package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.hardware.camera.Camera;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.matrices.OpenCvMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaSkyStone;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaSkyStone.PoseInfo;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.MatOfPoint3;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.ArrayList;
import java.util.List;

@TeleOp(name = "Sample OpMode", group = "TeleOp")
public class CameraMode extends LinearOpMode {

    private WebcamName webcam;

    @Override
    public void runOpMode() {
        // Init your hardware map and other components here
        // hardwareMap = hardwareMap.appContext.getResources().getIdentifier("HardwareMap", "xml", hardwareMap.appContext.getPackageName());

        // Initialize the camera
        initCamera();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            // Perform operations during TeleOp
            // ...

            // Example: Process the camera frame
            processCameraFrame();

            // ...

            telemetry.update();
        }
    }

    private void initCamera() {
        // Initialize Vuforia and the camera
        webcam = hardwareMap.get(WebcamName.class, "your_camera_name");
        Camera camera = new OpenCvCameraFactoryImpl().createWebcam(webcam, hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName()));
        camera.openCameraDevice();
        camera.setPipeline(new SamplePipeline()); // Set your custom image processing pipeline
        camera.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT); // Adjust resolution and rotation as needed

        // Wait for the camera to finish starting up
        sleep(1000);
    }

    private void processCameraFrame() {
        // Retrieve the latest frame from the camera pipeline
        Mat frame = SamplePipeline.latestFrame;

        // Process the frame using OpenCV
        if (frame != null) {
            // Your OpenCV processing code goes here
            // Example: Convert the frame to grayscale
            Imgproc.cvtColor(frame, frame, Imgproc.COLOR_RGBA2GRAY);
            // Example: Blur the frame
            Imgproc.GaussianBlur(frame, frame, new Size(5, 5), 0);

            // Add more OpenCV processing as needed

            // Display the processed frame on telemetry
            telemetry.addData("Processed Frame", frame.dump());
        }
    }

    // Define your custom image processing pipeline
    public static class SamplePipeline extends OpenCvPipeline {
        // Define your static variables for processed images
        public static Mat latestFrame;

        @Override
        public Mat processFrame(Mat input) {
            // Your image processing code goes here
            // Example: Save the processed frame to a static variable
            latestFrame = input.clone();

            // Add more image processing as needed

            // Return the processed frame
            return input;
        }
    }
}
