package frc.robot.io;

/**
 * A second implementation of RobotIO, this time for a simulator.
 * Since the simulation API is not yet finished, I have left these methods as no-op.  However, it should be clear
 * to see how we can implement the same interface methods differently here to support multiple platforms our code can run on.
 */
public class SimulatedRobotIO implements RobotIO {
    @Override
    public void update() {
        //simulation sensor values would be read here
    }

    @Override
    public void setup() {
        //this probably wouldn't be used for simulation, but since we're using an interface, it's perfectly acceptable
        //to leave out an implementation of a method that isn't needed for a given platform.
    }

    @Override
    public double getTimestamp() {
        return 0; //simulated timestamp would be returned here
    }

    @Override
    public double getLeftEncoderRadians() {
        return 0;
    }

    @Override
    public double getRightEncoderRadians() {
        return 0;
    }

    @Override
    public void setLeftOutputVolts(double volts) {
        //we would make a call to the simulator's motor controller API for these methods
    }

    @Override
    public void setRightOutputVolts(double volts) {

    }
}
