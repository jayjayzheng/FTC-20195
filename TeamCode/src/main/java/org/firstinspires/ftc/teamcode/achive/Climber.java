package org.firstinspires.ftc.teamcode.achive;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Climber Mode", group="Team 20195")

public class Climber extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor climberMotor = null;
    private Servo climberServo = null;


    static final double INCREMENT   = 0.05;     // amount to slew servo each CYCLE_MS cycle
    static final int    CYCLE_MS    =   50;     // period of each cycle
    static final double MAX_POS     =  1.0;     // Maximum rotational position
    static final double MIN_POS     =  0.25;     // Minimum rotational position
    double  position = 0.25; // Start at halfway position
    @Override
    public void runOpMode() {

        //declare climber motor
        climberMotor  = hardwareMap.get(DcMotor.class, "climber");
        climberServo = hardwareMap.get(Servo.class, "servo0");

        // Wait for the game to start (driver presses PLAY)
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {
            double climbPower = 0.0;
            if (gamepad1.left_trigger > 0) {
                climbPower = -1.0;
            } else if (gamepad1.right_trigger > 0) {
                climbPower = 1.0;
            }
            climberMotor.setPower(climbPower);
            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Climber", "%4.2f", climbPower);
            telemetry.update();

            this.servoAction();
        }
    }

    public void servoAction() {
        climberServo = hardwareMap.get(Servo.class, "servo0");

        // slew the servo, according to the rampUp (direction) variable.
        if (gamepad1.left_bumper) {
            // Keep stepping up until we hit the max value.
            position += INCREMENT ;
            if (position >= MAX_POS ) {
                position = MAX_POS;
            }
        }
        else if (gamepad1.right_bumper) {
            // Keep stepping down until we hit the min value.
            position -= INCREMENT ;
            if (position <= MIN_POS ) {
                position = MIN_POS;
            }
        }

        // Display the current value
        telemetry.addData("Servo Position", "%5.2f", position);
        telemetry.addData(">", "Press Stop to end test." );
        telemetry.update();

        // Set the servo to the new position and pause;
        climberServo.setPosition(position);
        sleep(CYCLE_MS);
        idle();
    }
}
