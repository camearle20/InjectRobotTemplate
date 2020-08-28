/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.google.inject.Inject;
import frc.robot.Robot;
import frc.robot.io.RobotIO;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class ExampleCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ExampleSubsystem m_subsystem;

  //private RobotIO io = Robot.getRobotIO();
  @Inject private RobotIO io;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ExampleCommand(ExampleSubsystem subsystem) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //I've just added a simple example here, where we will drive at half power until the target is reached:
    m_subsystem.tankDrive(6.0, 6.0); //6 volts ~= .5 percent out

    //Note that I used the "tankDrive" method I implemented in the subsystem class.  It also is acceptable to access the RobotIO
    //directly from a command.  Whether or not you choose to do this entirely depends on the subsystem being controlled.
    //For example, for an intake, it would probably be redundant to have a method defined in the subsystem that sets the voltage of the wheels,
    //as that method would simply be calling another method in RobotIO to do it.  Instead of implementing that redundant method, you can just
    //use RobotIO directly from the command.  However, more complex methods (like tankDrive) make sense to be implemented in the Subsystem level.

    //Thus, this would also be acceptable in this case:
    //io.setLeftOutputVolts(6.0);
    //io.setRightOutputVolts(6.0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.tankDrive(0.0, 0.0); //Used to stop the drivetrain.
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_subsystem.getLeftPositionInches() >= 6.0; //We'll stop driving when the left reaches 6 inches.
  }
}
