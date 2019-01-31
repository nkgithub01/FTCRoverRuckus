package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
@TeleOp(name = "DriveWithMarker", group = "OpMode")
public class DriveWithMarker extends OpMode{

        /* objects*/
        /**********************************/
        ElapsedTime runtime = new ElapsedTime();

        //Motors
        DcMotor leftBack = null;
        DcMotor leftFront = null;
        DcMotor rightBack = null;
        DcMotor rightFront = null;

        Servo markerDeployment = null;

    //Initialize the variables
        @Override
        public void init() {
            //Initialize the DcMotors
            leftBack = hardwareMap.get(DcMotor.class, "leftBack");
            leftFront = hardwareMap.get(DcMotor.class, "leftFront");
            rightBack = hardwareMap.get(DcMotor.class, "rightBack");
            rightFront = hardwareMap.get(DcMotor.class, "rightFront");

            //Set the zero power behavior
            leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            //Set the directions of the motors
            leftBack.setDirection(DcMotor.Direction.FORWARD);
            leftFront.setDirection(DcMotor.Direction.FORWARD);
            rightBack.setDirection(DcMotor.Direction.REVERSE);
            rightFront.setDirection(DcMotor.Direction.REVERSE);

            //Tell user that initialization is complete
            telemetry.addData("Status", "Initialized");
            markerDeployment = hardwareMap.get(Servo.class, "mkDep");

        }

        @Override
        public void start() {
            runtime.reset();
        }

        @Override
        public void loop() {
            //Drive the robot
            leftBack.setPower(gamepad1.left_stick_y);
            leftFront.setPower(gamepad1.left_stick_y);
            rightBack.setPower(gamepad1.right_stick_y);
            rightFront.setPower(gamepad1.right_stick_y);

            if(gamepad1.a)
            {
                markerDeployment.setPosition(markerDeployment.getPosition() - 0.1);
            }
            else if(gamepad1.b)
            {
                markerDeployment.setPosition(markerDeployment.getPosition() + 0.1);
            }


            //Displays the runtime
            telemetry.addData("Runtime: ", getRuntime());
            telemetry.addData("deployerPos: ", markerDeployment.getPosition());
        }

    }
