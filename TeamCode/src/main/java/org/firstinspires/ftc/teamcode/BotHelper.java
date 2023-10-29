package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
import com.qualcomm.robotcore.hardware.DcMotor;

public class BotHelper {
    public void init(DcMotor BR, DcMotor BL, DcMotor FR, DcMotor FL) {

        // Initialize the drive system variables.
        BR = hardwareMap.get(DcMotor.class, "BR"); //back right motor
        BL = hardwareMap.get(DcMotor.class, "BL"); //back left motor
        FL = hardwareMap.get(DcMotor.class, "FL"); //front left motor
        FR = hardwareMap.get(DcMotor.class, "FR"); //front right motor

        // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
        // When run, this OpMode should start both motors driving forward. So adjust these two lines based on your first test drive.
        // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips
        BL.setDirection(DcMotor.Direction.REVERSE);
        FR.setDirection(DcMotor.Direction.REVERSE);
        BR.setDirection(DcMotor.Direction.FORWARD);
        FL.setDirection(DcMotor.Direction.FORWARD);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");
        telemetry.update();
    }
}
