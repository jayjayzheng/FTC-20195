package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Auto By Encoder: Outside-left", group="Team 20195")
public class OutsideLeftAutoByEncoder extends LinearOpMode {
    @Override
    public void runOpMode() {
        AutonomousHelper auto = new AutonomousHelper();
        auto.runOpMode("OutsideLeft");
    }
}
