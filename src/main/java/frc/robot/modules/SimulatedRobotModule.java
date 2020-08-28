package frc.robot.modules;

import com.google.inject.AbstractModule;
import frc.robot.io.RobotIO;
import frc.robot.io.SimulatedRobotIO;

/**
 * This is the exact same as CompetitionRobotModule, except this time we bind "SimulatedRobotIO" to RobotIO.  This module
 * would be used whenever we are simulating.  Because of this, CompetitionRobotIO will never even be loaded, and thus
 * no hardware calls that can only run on the RIO would be made.
 */
public class SimulatedRobotModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(RobotIO.class).to(SimulatedRobotIO.class);
    }
}
