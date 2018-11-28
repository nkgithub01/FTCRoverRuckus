package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="DriverOp", group="OpMode")
public class test extends OpMode{

    //Objects
    /**********************************/
    ElapsedTime runtime = new ElapsedTime();

    //Motors
    DcMotor leftBack = null;
    DcMotor leftFront = null;
    DcMotor rightBack = null;
    DcMotor rightFront = null;

    DcMotor rightLin = null;
    DcMotor leftLin = null;

    //Intake
    Servo intake = null;
    boolean inForward = false;
    boolean inBackward = false;
    boolean ab2Pressed = false;

    //Initialize the variables
    public void init() {
        //Initialize the DcMotors
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");

        rightLin = hardwareMap.get(DcMotor.class, "rightLin");
        leftLin = hardwareMap.get(DcMotor.class, "leftLin");

        //Intialize the servos
        intake = hardwareMap.get(Servo.class, "intake" );


        //Set the zero power behavior
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftLin.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightLin.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //Set the directions of the motors
        leftBack.setDirection(DcMotor.Direction.FORWARD);
        leftFront.setDirection(DcMotor.Direction.FORWARD);
        rightBack.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.REVERSE);

        rightLin.setDirection(DcMotor.Direction.REVERSE);
        leftLin.setDirection(DcMotor.Direction.FORWARD);

        //Set the direction of the servo
        intake.setDirection(Servo.Direction.FORWARD);

        //Initialize servo to be not moving
        intake.setPosition(0.5);

        //Tell user that initialization is complete
        telemetry.addData("Status", "Initialized");

        intake.setPosition(0);

    }

    public void start() {
        runtime.reset();
    }

    public void loop() {
        //Drive the robot
        leftBack.setPower(gamepad1.left_stick_y);
        leftFront.setPower(gamepad1.left_stick_y);
        rightBack.setPower(gamepad1.right_stick_y);
        rightFront.setPower(gamepad1.right_stick_y);

        //Linear slide control
        if (gamepad1.left_bumper) leftLin.setPower(100);
        else if (gamepad1.left_trigger > 0) leftLin.setPower(-100);
        if (gamepad1.right_bumper) rightLin.setPower(100);
        else if (gamepad1.right_trigger > 0) rightLin.setPower(-100);

        //Toggle code for intake
        if (gamepad2.a && !ab2Pressed)
        {
            if(!inForward)
            {
                inForward = true;
                inBackward = false;
            }
            else
            {
                inForward = false;
            }
        }
        else if (gamepad2.b && !ab2Pressed)
        {
            if(!inBackward)
            {
                inForward = false;
                inBackward = true;
            }
            else
            {
                inBackward = false;
            }
        }

        if(!ab2Pressed && (gamepad2.a || gamepad2.b))
        {
            ab2Pressed = true;
        }
        else if (ab2Pressed && !(gamepad2.a || gamepad2.b))
        {
            ab2Pressed = false;
        }

        //Sets the turning for the intake servo
        if (inForward) intake.setPosition(0);
        else if (inBackward) intake.setPosition(1);
        else intake.setPosition(0.5);

        //Displays the runtime
        telemetry.addData("Runtime: ", getRuntime());
    }

}