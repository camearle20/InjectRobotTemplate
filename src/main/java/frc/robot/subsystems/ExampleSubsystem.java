/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.google.inject.Inject;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.io.RobotIO;

public class ExampleSubsystem extends SubsystemBase {
  //private RobotIO io = Robot.getRobotIO(); //Obtain an instance of RobotIO
  @Inject private RobotIO io; //This is the new way to obtain the instance of RobotIO

  private static final double WHEEL_RADIUS = 2.0;  //4 inch diameter wheel as an example

  /**
   * Creates a new ExampleSubsystem.
   */
  public ExampleSubsystem() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    //An example of how we could use RobotIO here:
    System.out.println(io.getTimestamp());

    //Odometry calculations would also be a good thing to put here, as this method is guaranteed to be called
    //directly after sensor readings have been taken, and that keeps odometry data contained in the subsystem class.

    //The subsystem can also house any PID controllers, etc. that run constantly (i.e. those that don't need to be isolated into commands)
    //It is important however that no direct hardware calls be made here.  Some WPILib utility classes can be used (such as PIDController and many others)
    //but hardware calls (reading from sensors, writing to motor controllers, etc.) should NOT be here.  Those functions should only be in the RobotIO implementations.
    //This ensures all platform specific hardware calls are properly isolated from the rest of the code, and makes it painless to simulate without them.
  }

  //Here, I provide some getters more specific to the context of this subsystem.  These use the RobotIO instance to get
  //the information they need to perform the final calculation to get position in inches.  While this is a simple example,
  //the Subsystem class would also be the right place to put more complex computations, such as getting the average distance from
  //both wheels, performing Odometry, etc.  The RobotIO does not replace the subsystem at all, but rather moves the last layer (hardware calls)
  //into an area that can be abstracted.
  public double getLeftPositionInches() {
    return io.getLeftEncoderRadians() * WHEEL_RADIUS; //s = r * theta
  }

  public double getRightPositionInches() {
    return io.getRightEncoderRadians() * WHEEL_RADIUS;
  }

  //A simple tank drive method.  WPILib provides things that do this for you, which could easily be used.
  //I just put my own here to keep the example short:
  public void tankDrive(double leftVolts, double rightVolts) {
    io.setLeftOutputVolts(leftVolts);
    io.setRightOutputVolts(rightVolts);
  }
}
