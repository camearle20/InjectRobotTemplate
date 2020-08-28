package frc.robot.io;

import javax.inject.Singleton;

/**
 * This interface defines the input data and output operations that can be done by every type of system this code can run on.
 * This includes competition robots, practice robots (if they differ), simulation, and testing environments.
 *
 * The purpose of this interface is to define a common "contract" of functionality that all platforms must support.
 * One or more classes will implement this interface, defining functionality for the various functions here.
 *
 * There are many ways to create such an interface, this is just a layout that makes the most sense to me:
 */
@Singleton //This annotation is important.  It tells Guice that we only ever want one instance of any subclass of RobotIO.
           //If we don't have this annotation here, when we go to inject instances of RobotIO, it will create new instances.
           //Putting the annotation here makes sure that Guice reuses the one instance we have.
public interface RobotIO {
    /**
     * This function is called every loop cycle, and is responsible for reading data from all attached robot sensors,
     * as well as reading the timestamp.  By doing this once each cycle in a single place (as opposed to spread around
     * different subsystems) we ensure that all sensor data is recorded at a given timestamp, and that this data is accessible and
     * consistent throughout the loop cycle.  This becomes extremely important for more advanced control techniques, and generally
     * makes code much easier to implement and debug.
     *
     * A sample set of operations for this function would be:
     * * Read timestamp: getFPGATimestamp()
     * * Read sensors attached to the RIO
     * * Read sensors attached to external CAN devices (if these values are needed by the code)
     * * Read limelight/vision sensor
     *
     * By doing all these at once, all our data has a common timebase and can be used by one or more consuming subsystems,
     * allowing us to fuse and reuse sensor data during a loop cycle.
     */
    void update();

    /**
     * This function serves a similar purpose to the setup function on an Arduino.  It performs the initial hardware configuration
     * for each device requiring it.  This should be called from "robotInit".  Instead of having a single setup method,
     * it is also possible to have several "configuration" type methods for setting different settings during runtime,
     * such as adjusting PID gains on the fly, etc.  Which option used purely depends on the requirements for a given robot design.
     *
     * We'll use a single method here as an example to configure our robot for voltage compensation on the motor controllers.
     */
    void setup();

    //Our sample robot will have two sensors: a left and right drive encoder.
    //Notice how radians are used here; the idea for this interface is not to implement any subsystem logic, but instead
    //to simply provide a means of reading from and writing to sensors and actuators on the robot.  Thus, even though
    //we may wish to use a unit such as "inches" for a drivetrain encoder application, that conversion is the job of the
    //DriveSubsystem class.

    //In general, here is a good set of units to use for these types of abstractions:
    //Inputs: Radians, seconds, rad/s (for velocity sensors), etc. (no unit conversions except for getting out of "native" units for specific hardware)
    //Outputs: volts (voltage compensation should probably be enabled for all motor controllers, since voltage is a much more meaningful unit than percent output)
    //We use units like radians instead of ticks since not all environments have the concept of a "tick".  Simulation measures in radians, testing will be done in radians,
    //and radians are easier to work with than obscure, sensor defined units.

    /**
     * Getter for the timestamp read by the "update" method.
     * @return The current robot timestamp, in seconds
     */
    double getTimestamp();

    //More getters:
    double getLeftEncoderRadians();
    double getRightEncoderRadians();


    //Some setters for hardware:

    /**
     * Sets the voltage applied to the left motor.
     * @param volts The voltage to apply
     */
    void setLeftOutputVolts(double volts);
    void setRightOutputVolts(double volts);

    //This would also be a good place to put Limelight, gyro (IMU), etc functions (both getters and setters as needed)
}
