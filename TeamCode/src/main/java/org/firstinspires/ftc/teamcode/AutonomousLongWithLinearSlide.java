package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="AutonomousLongWithLinearSlide", group="OpMode")
public class AutonomousLongWithLinearSlide extends LinearOpMode{

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

    //Servos
    Servo markerDeployment = null;

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

        //Initialize the Servos
        markerDeployment = hardwareMap.get(Servo.class, "mkDep");

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

        //Set the direction of the servos
        markerDeployment.setDirection(Servo.Direction.FORWARD);

        //Set the modes of the motors
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftLin.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightLin.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //Tell user that initialization is complete
        telemetry.addData("Status", "Initialized");

        waitForStart();

        //Release from hanging
        extendLinearSlide(12);
        /*Release hook*/
        extendLinearSlide(-12);

        //Move to silver/gold
        run(80);

        //Drive to depot
        rotate(-90);
        run(78);
        rotate(-45);
        run(170);

        //Place team marker
        rotate(-135);
        markerDeployment.setPosition(0.35);
        try {
            Thread.sleep(1000);
        } catch (Exception e) { }
        run(-10);
        markerDeployment.setPosition(0.95);
        run(10);

        //Drive to crater
        rotate(-45);
        run(240);
    }

    /**
     * Extends the linear slide a certain distance
     * @param cm the distance to extend
     */
    public void extendLinearSlide(double cm) {
        leftLin.setTargetPosition(leftLin.getTargetPosition() + (int) (cm * ticksPerCm));
        rightLin.setTargetPosition(rightLin.getTargetPosition() - (int) (cm * ticksPerCm));
        leftLin.setPower(100);
        rightLin.setPower(100);
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