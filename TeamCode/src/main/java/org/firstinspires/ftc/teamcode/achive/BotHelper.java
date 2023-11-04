package org.firstinspires.ftc.teamcode.achive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class BotHelper {

    public static final double     FORWARD_SPEED = 0.6;
    public static final double     TURN_SPEED    = 0.5;
    public static final double     OUTSIDE_FORWARD_TIME = 1.0;
    public static final double     OUTSIDE_HOME_TIME = 2.8;
    public static final double     TURN_TIME = 1.9;
    public static final double     INSIDE_FORWARD_TIME = 0.2;
    public static final double     INSIDE_HOME_TIME = 0.3;


    private ElapsedTime runtime = new ElapsedTime();

    public void drive(DcMotor BL, DcMotor FR, DcMotor FL, DcMotor BR, double speed) {
        BL.setPower(speed);
        FR.setPower(speed);
        FL.setPower(speed);
        BR.setPower(speed);
    }

    public void turnLeft (DcMotor BL, DcMotor FR) {
        BL.setPower(TURN_SPEED);
        FR.setPower(-TURN_SPEED);
    }

    public void turnRight (DcMotor BL, DcMotor FR) {
        BL.setPower(-TURN_SPEED);
        FR.setPower(TURN_SPEED);
    }

    public void stop(DcMotor BL, DcMotor FR, DcMotor FL, DcMotor BR) {
        this.drive(BL, FR, FL, BR, 0);
    }
}
