package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="test", group="Autonomous")
public class test extends LinearOpMode{

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

    //Constants
    double timePerCm = 1000;
    double timePerDegree = 1000;
    double ticksPerCm = 1;

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

        //Tell user that initialization is complete
        telemetry.addData("Status", "Initialized");

        waitForStart();

        extendLinearSlide(1);
        extendLinearSlide(-1);
        //runForward(10);
        //runBackward(10);
        //rotateRight(180);
        //rotateLeft(180);


        /*double lo = 0.0, hi = 10, mid;
        while (hi - lo > 0.0000001) {
            mid = (lo + hi) / 2;
            ticksPerCm = mid;
            run(10);
            boolean ishi = false;
            while (!gamepad1.a || !gamepad1.b) {
                if (gamepad1.a) {
                    ishi = true;
                    break;
                } else if (gamepad1.b) {
                    ishi = false;
                    break;
                }
            }
            if (ishi) hi = mid;
            else lo = mid;
        }

        lo = 0.0;
        hi = 10;
        while (hi - lo > 0.0000001) {
            mid = (lo + hi) / 2;
            ticksPerDegree = mid;
            rotate(90);
            boolean ishi = false;
            while (!gamepad1.a || !gamepad1.b) {
                if (gamepad1.a) {
                    ishi = true;
                    break;
                } else if (gamepad1.b) {
                    ishi = false;
                    break;
                }
            }
            if (ishi) hi = mid;
            else lo = mid;
        }*/
    }

    /**
     * Rotates right by a number of degrees, negative degrees will rotate left
     * @param degrees the number of degrees to rotate right
     */
    public void rotateRight(double degrees) {
        leftBack.setPower(100);
        leftFront.setPower(100);
        rightBack.setPower(-100);
        rightFront.setPower(-100);
        try {
            Thread.sleep((int) (timePerDegree * degrees));
        } catch (Exception e) { }
        setPowerAllMotors(0);
    }

    public void rotateLeft(double degrees) {
        leftBack.setPower(-100);
        leftFront.setPower(-100);
        rightBack.setPower(100);
        rightFront.setPower(100);
        try {
            Thread.sleep((int) (timePerDegree * degrees));
        } catch (Exception e) { }
        setPowerAllMotors(0);
    }

    /**
     * Go forward a number of centimeters
     * @param cm the distance to travel
     */
    public void runForward(double cm) {
        setPowerAllMotors(100);
        try {
            Thread.sleep((int) (timePerCm * cm));
        } catch (Exception e) { }
        setPowerAllMotors(0);
    }

    public void runBackward(double cm) {
        setPowerAllMotors(-100);
        try {
            Thread.sleep((int) (timePerCm * cm));
        } catch (Exception e) { }
        setPowerAllMotors(0);
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

    /**
     * Extends the linear slide a certain distance
     * @param cm the distance to extend
     */
    public void extendLinearSlide(double cm) {
        leftLin.setTargetPosition(leftLin.getTargetPosition() + (int) (cm * ticksPerCm));
        //rightLin.setTargetPosition(rightLin.getTargetPosition() - (int) (cm * ticksPerCm));
        leftLin.setPower(100);
        //rightLin.setPower(100);
    }

}