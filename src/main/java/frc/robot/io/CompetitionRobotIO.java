package frc.robot.io;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj.Timer;

/**
 * This is the implementation of RobotIO for an FRC competition robot.  In this class, it is perfectly safe to make
 * calls to WPILib and vendor hardware libraries.  This class will only ever be loaded when the code is running on an
 * actual roboRIO.  Since the code in this file cannot be tested without a RIO, there should be little to no "logic"
 * defined in here, instead simply proxying calls to WPILib and vendor libs.
 */
public class CompetitionRobotIO implements RobotIO {
    private static final double REV_TO_RAD = 2 * Math.PI; //Conversion factor from revolutions to radians

    //Variables to store data retrieved by the "update" method
    private double timestamp;
    private double leftEncoderRadians;
    private double rightEncoderRadians;

    //Actual hardware objects should also be initialized here:
    //For this example, we're just using SPARK MAX, but CTRE or simple PWM devices would also be used here.
    private final CANSparkMax leftSpark = new CANSparkMax(0, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax rightSpark = new CANSparkMax(1, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANEncoder leftEncoder = leftSpark.getEncoder(); //getEncoder makes a new encoder object each time it is called, so we make one copy up front to reuse
    private final CANEncoder rightEncoder = rightSpark.getEncoder();

    @Override
    public void update() {
        //Read all data, starting with the timestamp.
        timestamp = Timer.getFPGATimestamp();
        leftEncoderRadians = leftEncoder.getPosition() * REV_TO_RAD;
        rightEncoderRadians = rightEncoder.getPosition() * REV_TO_RAD;
    }

    @Override
    public void setup() {
        //leftSpark.enableVoltageCompensation(12.0); //This would be used if the SPARK did not have a direct "setVoltage" function, i.e. if this were a CTRE motor controller

        //Some example "setup" calls that could be made here:
        leftSpark.setSmartCurrentLimit(20);
        rightSpark.setSmartCurrentLimit(20);
    }

    //Getters for the variables defined at the top; these are required to satisfy the requirements set by our interface.
    //Note that these simply return the values from the variables, and do NOT make any hardware calls.  This is important
    //so that readings are consistent each loop cycle.  All values should be read once at the beginning of the loop (by the "update" method),
    //and then those values read at that time should be used by the code.  This is very important for control because it keeps the measurements
    //synchronized with the timestamp.  It also greatly improves code performance by limiting the number of costly calls into hardware functions.
    @Override
    public double getTimestamp() {
        return timestamp;
    }

    @Override
    public double getLeftEncoderRadians() {
        return leftEncoderRadians;
    }

    @Override
    public double getRightEncoderRadians() {
        return rightEncoderRadians;
    }

    //Setters for our motor controllers.  These can call directly to the hardware functions since they're usually only
    //called by a single subsystem, and only called once per cycle.
    @Override
    public void setLeftOutputVolts(double volts) {
        leftSpark.setVoltage(volts); //The SPARK provides a direct voltage setter.  If this were a CTRE device, we'd need to scale the "volts" value by 12
                                     //and ensure that we have voltage compensation already enabled on the controller.

        //leftSpark.set(volts / 12.0); //It would look something like this if the setter did not exist.
    }

    @Override
    public void setRightOutputVolts(double volts) {
        rightSpark.setVoltage(volts);
    }
}
