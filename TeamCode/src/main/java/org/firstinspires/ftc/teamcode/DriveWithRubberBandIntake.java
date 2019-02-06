package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/*
    Created by me ON 10/23/18
 */
@TeleOp(name="DriveWithRubberBandIntake", group="OpMode")
public class DriveWithRubberBandIntake extends OpMode{

    /* objects*/
    /**********************************/
    ElapsedTime runtime = new ElapsedTime();

    //Motors
    DcMotor leftBack = null;
    DcMotor leftFront = null;
    DcMotor rightBack = null;
    DcMotor rightFront = null;

    DcMotor rightLin = null;
    DcMotor leftLin = null;

    DcMotor intake = null;

    //Initialize the variables
    @Override
    public void init() {
        //Initialize the DcMotors
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");

        rightLin = hardwareMap.get(DcMotor.class, "rightLin");
        leftLin = hardwareMap.get(DcMotor.class, "leftLin");

        intake = hardwareMap.get(DcMotor.class, "intake");

        //Set the zero power behavior
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftLin.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightLin.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //Set the directions of the motors
        leftBack.setDirection(DcMotor.Direction.FORWARD);
        leftFront.setDirection(DcMotor.Direction.FORWARD);
        rightBack.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.REVERSE);

        rightLin.setDirection(DcMotor.Direction.REVERSE);
        leftLin.setDirection(DcMotor.Direction.FORWARD);

        intake.setDirection(DcMotor.Direction.FORWARD);

        //Tell user that initialization is complete
        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {
        //Drive the robot
        leftBack.setPower(gamepad2.left_stick_y);
        leftFront.setPower(gamepad2.left_stick_y);
        rightBack.setPower(gamepad2.right_stick_y);
        rightFront.setPower(gamepad2.right_stick_y);

        if (gamepad1.left_trigger > 0) {
            if (leftLin.getCurrentPosition() < -30) {
                leftLin.setPower(100);
            }
            else leftLin.setPower(0);
        }
        if (gamepad1.right_trigger > 0) {
            if (rightLin.getCurrentPosition() < -30) {
                rightLin.setPower(100);
            }
            else rightLin.setPower(0);
        }
        if (gamepad1.left_bumper) {
            if (leftLin.getCurrentPosition() > -4100) {
                leftLin.setPower(-100);
            }
            else leftLin.setPower(0);
        }
        if (gamepad1.right_bumper) {
            if (rightLin.getCurrentPosition() > -4100) {
                rightLin.setPower(-100);
            }
            else rightLin.setPower(0);
        }
        if (!gamepad1.left_bumper && gamepad1.left_trigger <= 0) {
            leftLin.setPower(0);
        }
        if (!gamepad1.right_bumper && gamepad1.right_trigger <= 0) {
            rightLin.setPower(0);
        }

        if (gamepad1.a) intake.setPower(100);
        else if (gamepad1.b) intake.setPower(-100);
        else intake.setPower(0);

        //Displays the runtime and debug info
        telemetry.addData("Left joystick: ", gamepad1.left_stick_y);
        telemetry.addData("right joystick: ", gamepad1.right_stick_y);
        telemetry.addData("left bumper: ", gamepad1.left_bumper);
        telemetry.addData("right bumper: ", gamepad1.right_bumper);
        telemetry.addData("left trigger: ", gamepad1.left_trigger);
        telemetry.addData("right trigger: ", gamepad1.right_trigger);
        telemetry.addData("left linear: ", leftLin.getCurrentPosition());
        telemetry.addData("right linear: ", rightLin.getCurrentPosition());
        telemetry.addData("Runtime: ", getRuntime());
    }

}