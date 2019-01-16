package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="AutonomousShort", group="OpMode")
public class AutonomousShort extends LinearOpMode{

    //Objects
    /*********************************************************************************/
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

    //Sensors
    ColorSensor color = null;

    //Constants
    final double ticksPerCm = 1;
    final double ticksPerDegree = 1;

    //running
    /*********************************************************************************/

    //Initialize the variables
    public void runOpMode() {
        //Initialize the DcMotors
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");

        rightLin = hardwareMap.get(DcMotor.class, "rightLin");
        leftLin = hardwareMap.get(DcMotor.class, "leftLin");

        //Initialize the servos
        intake = hardwareMap.get(Servo.class, "intake" );

        //init sensors
        color = hardwareMap.get(ColorSensor.class, "color");


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

        //Set the directions of the motors
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftLin.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightLin.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //Set the direction of the servo
        intake.setDirection(Servo.Direction.FORWARD);

        //Initialize servo to be not moving
        intake.setPosition(0.5);

        //Tell user that initialization is complete
        telemetry.addData("Status", "Initialized");

        intake.setPosition(0);

        waitForStart();

        //Release from hanging

        //Move to silver/gold
        run(90);

        //Knock the gold off
        int goldIndex = -2;
        rotate(-23.56);
        run(90);
        /*sense*/
        if (goldIndex == -1) {
            /*knock off*/
            rotate(68.56);
        } else {
            rotate(113.56);
            run(40);
            /*sense*/
        }
        if (goldIndex == 0) {
            /*knock off*/
            rotate(-90);
        } else {
            run(40);
            /*knock off*/
            rotate(-113.56);
        }

        //Drive to depot
        if (goldIndex == 1) run(45);
        else run(60);

        //Place team marker

        //Drive to crater
        if (goldIndex == 0) rotate(60);
        else if (goldIndex == 1) rotate(45);
        else rotate(20);
        run(220);
    }

    /**
     * Rotates right by a number of degrees, negative degrees will rotate left
     * @param degrees the number of degrees to rotate right
     */
    public void rotate(double degrees) {
        int deg = (int) (degrees * ticksPerDegree);
        leftBack.setTargetPosition(leftBack.getTargetPosition() + deg);
        leftFront.setTargetPosition(leftBack.getTargetPosition() + deg);
        rightBack.setTargetPosition(leftBack.getTargetPosition() + deg);
        rightFront.setTargetPosition(leftBack.getTargetPosition() + deg);
        setPowerAllMotors(100);
    }

    /**
     * Go forward a number of centimeters
     * @param cm the distance to travel
     */
    public void run(double cm) {
        setAllMotors((int) (cm * ticksPerCm));
        setPowerAllMotors(100);
    }

    /**
     * Set how much to go forward on all motors
     * @param dist the amount of encoder ticks
     */
    public void setAllMotors(int dist) {
        leftBack.setTargetPosition(leftBack.getTargetPosition() + dist);
        rightBack.setTargetPosition(leftBack.getTargetPosition() - dist);
        leftFront.setTargetPosition(leftBack.getTargetPosition() + dist);
        rightFront.setTargetPosition(leftBack.getTargetPosition() - dist);
    }

    /**
     * Set power to all motors
     * @param power power to be set for all motors, must be between -100 and 100
     */
    public void setPowerAllMotors(int power) {
        if (Math.abs(power) > 100) return;
        leftBack.setPower(power);
        rightBack.setPower(power);
        leftFront.setPower(power);
        rightFront.setPower(power);
    }
    
}