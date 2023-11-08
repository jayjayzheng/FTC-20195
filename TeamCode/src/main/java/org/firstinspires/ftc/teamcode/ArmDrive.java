package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Arm Mode", group="Team 20195")
public class ArmDrive extends LinearOpMode {
    private DcMotor arm = null;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        arm = hardwareMap.get(DcMotor.class, "arm");
        //armMotor setting
        arm.setDirection(DcMotor.Direction.FORWARD);
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm.setTargetPosition(0);   //initial position
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        waitForStart();
        runtime.reset();
        while (opModeIsActive()) {
            if(gamepad1.a) {
                arm.setTargetPosition(0);
            } else if(gamepad1.b) {
                arm.setTargetPosition(800);
            } else if (gamepad1.y) {
                arm.setTargetPosition(1000);
            }

            // Send telemetry message to indicate successful Encoder reset
            telemetry.addData("Starting at",  "%7d",
                    arm.getCurrentPosition());
            telemetry.update();

            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm.setPower(Math.abs(0.6));

            // Send telemetry message to indicate successful Encoder reset
            telemetry.addData("Starting at",  "%7d",
                    arm.getCurrentPosition());
            telemetry.update();

//            arm.setPower(0);
//            arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            sleep(250);   // optional pause after each move.
        }
    }

}
