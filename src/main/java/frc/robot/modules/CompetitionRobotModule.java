package frc.robot.modules;

import com.google.inject.AbstractModule;
import frc.robot.io.CompetitionRobotIO;
import frc.robot.io.RobotIO;

/**
 * This is the last part of dependency injection: the module.
 * The module defines which implementations of our interfaces (in this case, RobotIO, but we could have more) are actually loaded
 * at runtime.  We can define multiple modules for multiple different platforms, so we'd have one for comp bot, one for practice (possibly),
 * and one for simulation and testing.  The module is simple, it just "binds" a specific implementation (i.e. CompetitionRobotIO) to an interface (RobotIO).
 */
public class CompetitionRobotModule extends AbstractModule {
    protected void configure() {
        bind(RobotIO.class).to(CompetitionRobotIO.class); //This says to use CompetitionRobotIO whenever an instance of RobotIO is requested.
    }
}
