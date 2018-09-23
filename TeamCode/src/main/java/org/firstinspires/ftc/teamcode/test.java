package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

public class test extends OpMode{

    /* objects*/
    /**********************************/
    ElapsedTime runtime = new ElapsedTime();


    public void init() {}

    public void start() { runtime.reset(); }

    public void loop() { telemetry.addData("Runtime: ", getRuntime()); }

}
