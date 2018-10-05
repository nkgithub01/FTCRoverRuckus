/*
 * Code for user controlled part of the conpetition
 */
package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Hardware;

@TeleOp (name = "testDrive")
public class test extends OpMode{

    /* objects*/
    /**********************************/
    ElapsedTime runtime = new ElapsedTime();

    //Motors
    DcMotor leftBack = null;
    DcMotor leftMiddle = null;
    DcMotor leftFront = null;
    DcMotor rightBack = null;
    DcMotor rightMiddle = null;
    DcMotor rightFront = null;

    //Initialize the variables
    public void init() {
        //Initialize the DcMotors
        DcMotor leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        DcMotor leftMiddle = hardwareMap.get(DcMotor.class, "leftMiddle");;
        DcMotor leftFront = hardwareMap.get(DcMotor.class, "leftFront");;
        DcMotor rightBack = hardwareMap.get(DcMotor.class, "rightBack");;
        DcMotor rightMiddle = hardwareMap.get(DcMotor.class, "rightMiddle");;
        DcMotor rightFront = hardwareMap.get(DcMotor.class, "rightFront");;

        //Set the directions of the motors
        leftBack.setDirection(DcMotor.Direction.FORWARD);
        leftMiddle.setDirection(DcMotor.Direction.FORWARD);
        leftFront.setDirection(DcMotor.Direction.FORWARD);
        rightBack.setDirection(DcMotor.Direction.REVERSE);
        rightMiddle.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.REVERSE);

        //Tell user that initialization is complete
        telemetry.addData("Status", "Initialized");

    }

    public void start() {
        runtime.reset();
    }

    public void loop() {
        // drive the robot
        leftBack.setPower(gamepad1.left_stick_y);
        leftMiddle.setPower(gamepad1.left_stick_y);
        leftFront.setPower(gamepad1.left_stick_y);
        rightBack.setPower(gamepad1.right_stick_y);
        rightMiddle.setPower(gamepad1.right_stick_y);
        rightFront.setPower(gamepad1.right_stick_y);

        //Displays the runtime
        telemetry.addData("Runtime: ", getRuntime());
    }

}
