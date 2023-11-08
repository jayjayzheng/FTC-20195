package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Auto By Encoder: Inside-left", group="Team 20195")
public class InsideLeftAutoByEncoder extends LinearOpMode {

    @Override
    public void runOpMode() {
        AutonomousHelper auto = new AutonomousHelper();
        auto.runOpMode("InsideLeft");
    }
}
