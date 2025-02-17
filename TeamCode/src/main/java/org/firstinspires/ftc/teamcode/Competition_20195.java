package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Competition Mode", group="Team 20195")
public class Competition_20195 extends LinearOpMode {
    private DcMotor climber = null;
    private DcMotor arm = null;
    private DcMotor backRight = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor frontLeft = null;
    private Servo leftHand = null;
    private Servo rightHand = null;
    private Servo hook = null;
    private Servo launcher = null;
    int ArmPosition = 0;                        // arm start position
    double HookPosition = 0.0;                 // hook start position
    double LauncherPosition = 0.3;              // launcher start position
    static final boolean DRIVING_TEST_DIRECTION = false;
    static final double INCREMENT   = 0.05;     // amount to slew servo each CYCLE_MS cycle
    static final double HOOK_MAX_POS     =  0.68;     // Maximum rotational position
    static final double HOOK_MIN_POS     =  0.0;    // Minimum rotational position
    static final double LAUNCHER_MAX_POS = 0.8;
    static final double LAUNCHER_MIN_POS = 0.3;
    static final int ARM_INCREMENT  =  50;      // amount of position to rotate arm
    static final int ARM_MAX        = 1200;     // Maximum arm position
    static final int ARM_MIN        = -10;      // Minimum arm position
    /**
     * This function is executed when this OpMode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {

        this.getReady();
        waitForStart();

        while (opModeIsActive()) {
            this.driveMode();
            this.armMode();
            this.handMode();
            this.climbMode();           // contains hook mode
            this.launchMode();
            idle();
        }
    }

    private void getReady() {
        //motors
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        arm = hardwareMap.get(DcMotor.class, "arm");
        climber  = hardwareMap.get(DcMotor.class, "climber");

        //servos
        hook = hardwareMap.get(Servo.class, "servo"); //this is the climber servo
        launcher = hardwareMap.get(Servo.class, "servo5");
        leftHand = hardwareMap.get(Servo.class, "left_hand");
        rightHand = hardwareMap.get(Servo.class, "right_hand");

        //set motor reverse
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        //armMotor setting
        arm.setDirection(DcMotor.Direction.FORWARD);
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm.setTargetPosition(ArmPosition);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftHand.scaleRange(0, 1);
        rightHand.scaleRange(0, 1);

        launcher.setPosition(LauncherPosition);
        // Wait for the game to start (driver presses PLAY)
        telemetry.addData("Status", "Initialized");
        telemetry.addData("Arm Start Position", "%7d",
                arm.getCurrentPosition());
//        telemetry.addData("Hook start position", "%7d", hook.getPosition());
//        telemetry.addData("Launcher start position", "%7d", launcher.getPosition());
        telemetry.update();
    }

    private void driveMode() {
        double max;

        // POV Mode uses left joystick to go forward & strafe, and right joystick to rotate.
        double axial   = -gamepad1.left_stick_y;  // Note: pushing stick forward gives negative value
        double lateral =  gamepad1.left_stick_x;
        double yaw     =  gamepad1.right_stick_x;

        // Combine the joystick requests for each axis-motion to determine each wheel's power.
        // Set up a variable for each drive wheel to save the power level for telemetry.
        double leftFrontPower  = axial + lateral + yaw;
        double rightFrontPower = axial - lateral - yaw;
        double leftBackPower   = axial - lateral + yaw;
        double rightBackPower  = axial + lateral - yaw;

        // Normalize the values so no wheel power exceeds 100%
        // This ensures that the robot maintains the desired motion.
        max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
        max = Math.max(max, Math.abs(leftBackPower));
        max = Math.max(max, Math.abs(rightBackPower));

        if (max > 1.0) {
            leftFrontPower  /= max;
            rightFrontPower /= max;
            leftBackPower   /= max;
            rightBackPower  /= max;
        }

        // This is test code:
        //
        // Uncomment the following code to test your motor directions.
        // Each button should make the corresponding motor run FORWARD.
        //   1) First get all the motors to take to correct positions on the robot
        //      by adjusting your Robot Configuration if necessary.
        //   2) Then make sure they run in the correct direction by modifying the
        //      the setDirection() calls above.
        // Once the correct motors move in the correct direction re-comment this code.
        if (DRIVING_TEST_DIRECTION) {
            leftFrontPower  = gamepad1.x ? 1.0 : 0.0;  // X gamepad
            leftBackPower   = gamepad1.a ? 1.0 : 0.0;  // A gamepad
            rightFrontPower = gamepad1.y ? 1.0 : 0.0;  // Y gamepad
            rightBackPower  = gamepad1.b ? 1.0 : 0.0;  // B gamepad
        }

        // Send calculated power to wheels
        frontLeft.setPower(leftFrontPower);
        frontRight.setPower(rightFrontPower);
        backLeft.setPower(leftBackPower);
        backRight.setPower(rightBackPower);
    }

    private void armMode() {

        if (gamepad2.y  && !arm.isBusy()) {                   //arm goes up
            ArmPosition += ARM_INCREMENT ;
            if (ArmPosition >= ARM_MAX ) {
                ArmPosition = ARM_MAX;
            }
        } else if (gamepad2.a && !arm.isBusy()) {              //arm goes down
            ArmPosition -= ARM_INCREMENT ;
            if (ArmPosition >= ARM_MIN ) {
                ArmPosition = ARM_MIN;
            }
        }

        arm.setTargetPosition(ArmPosition);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setPower(Math.abs(0.2));
    }

    private void handMode() {
        if (gamepad1.left_bumper) {         //close hand: hold
            rightHand.setPosition(0.5);
            leftHand.setPosition(0.5);
            telemetry.addData("Hand state", "Closed");
            telemetry.update();
        } else if (gamepad1.right_bumper) {        //open hand: drop
            rightHand.setPosition(0);
            leftHand.setPosition(0);
            telemetry.addData("Hand state", "Open");
            telemetry.update();
        }
    }

    private void climbMode() {
        double climbPower = 0.0;
        if (gamepad2.dpad_down) {
            climbPower = -1.0;
            telemetry.addData("Climber direction", "DOWN");
            telemetry.update();
        } else if (gamepad2.dpad_up) {
            climbPower = 1.0;
            telemetry.addData("Climber direction", "UP");
            telemetry.update();
        }
        climber.setPower(climbPower);

        //this is the hook servo section
        // slew the servo, according to the rampUp (direction) variable.
        if (gamepad2.dpad_right) {
            // Keep stepping up until we hit the max value.
            HookPosition += INCREMENT ;
            if (HookPosition >= HOOK_MAX_POS ) {
                HookPosition = HOOK_MAX_POS;
            }
        }
        else if (gamepad2.dpad_left) {
            // Keep stepping down until we hit the min value.
            HookPosition -= INCREMENT ;
            if (HookPosition <= HOOK_MIN_POS ) {
                HookPosition = HOOK_MIN_POS;
            }
        }

        // Set the servo to the new position and pause;
        hook.setPosition(HookPosition);
    }

    private void launchMode() {
        if (gamepad2.left_trigger > 0) {
            // Keep stepping up until we hit the max value.
            LauncherPosition += INCREMENT ;
            if (LauncherPosition >= LAUNCHER_MAX_POS ) {
                LauncherPosition = LAUNCHER_MAX_POS;
            }
        }
        else if (gamepad2.right_trigger > 0) {
            // Keep stepping down until we hit the min value.
            LauncherPosition -= INCREMENT ;
            if (LauncherPosition <= LAUNCHER_MIN_POS ) {
                LauncherPosition = LAUNCHER_MIN_POS;
            }
        }

        // Set the servo to the new position and pause;
        launcher.setPosition(LauncherPosition);
    }
}
