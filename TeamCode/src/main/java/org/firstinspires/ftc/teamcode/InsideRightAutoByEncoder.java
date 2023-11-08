package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Auto By Encoder: Inside-right", group="Team 20195")
public class InsideRightAutoByEncoder extends LinearOpMode {
    @Override
    public void runOpMode() {
        AutonomousHelper auto = new AutonomousHelper();
        auto.runOpMode("InsideRight");
    }
}
