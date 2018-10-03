package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Hardware;

public class test extends OpMode{

    /* objects*/
    /**********************************/
    ElapsedTime runtime = new ElapsedTime();

    DcMotor leftBack = null;
    DcMotor leftMiddle = null;
    DcMotor leftFront = null;
    DcMotor rightBack = null;
    DcMotor rightMiddle = null;
    DcMotor rightFront = null;

    public void init() {

        DcMotor leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        DcMotor leftMiddle = hardwareMap.get(DcMotor.class, "leftMiddle");;
        DcMotor leftFront = hardwareMap.get(DcMotor.class, "leftFront");;
        DcMotor rightBack = hardwareMap.get(DcMotor.class, "rightBack");;
        DcMotor rightMiddle = hardwareMap.get(DcMotor.class, "rightMiddle");;
        DcMotor rightFront = hardwareMap.get(DcMotor.class, "rightFront");;
    }

    public void start() { runtime.reset(); }

    public void loop() { telemetry.addData("Runtime: ", getRuntime()); }

}
