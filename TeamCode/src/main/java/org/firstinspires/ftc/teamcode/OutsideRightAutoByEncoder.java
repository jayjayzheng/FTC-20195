package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Auto By Encoder: Outside-right", group="Team 20195")
public class OutsideRightAutoByEncoder extends LinearOpMode {

    @Override
    public void runOpMode() {
        AutonomousHelper auto = new AutonomousHelper();
        auto.runOpMode("OutsideRight");
    }
}
