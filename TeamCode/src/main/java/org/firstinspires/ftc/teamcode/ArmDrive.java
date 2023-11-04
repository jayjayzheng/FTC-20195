package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Arm Mode", group="Team 20195")
public class ArmDrive extends LinearOpMode {
    private DcMotor armmotor;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        armmotor = hardwareMap.get(DcMotor.class, "arm");
        //armMotor setting
        armmotor.setDirection(DcMotor.Direction.FORWARD);
//        armmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        armmotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();
        runtime.reset();
        while (opModeIsActive()) {
            if(gamepad1.a) {
                armmotor.setTargetPosition(0);
            } else if(gamepad1.b) {
                armmotor.setTargetPosition(800);
            } else if (gamepad1.y) {
                armmotor.setTargetPosition(1000);
            }
            armmotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // Send telemetry message to indicate successful Encoder reset
            telemetry.addData("Starting at",  "%7d",
                    armmotor.getCurrentPosition());
            telemetry.update();

            armmotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            runtime.reset();
            armmotor.setPower(Math.abs(0.6));

            // Send telemetry message to indicate successful Encoder reset
            telemetry.addData("Starting at",  "%7d",
                    armmotor.getCurrentPosition());
            telemetry.update();

            armmotor.setPower(0);
            armmotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            sleep(250);   // optional pause after each move.
        }
    }

}
