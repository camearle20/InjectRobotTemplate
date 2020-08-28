/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.google.inject.Guice;
import com.google.inject.Injector;
import edu.wpi.first.wpilibj.RobotBase;
import frc.robot.modules.CompetitionRobotModule;

/**
 * Do NOT add any static variables to this class, or any initialization at all. Unless you know what
 * you are doing, do not modify this file except to change the parameter class to the startRobot
 * call.
 */
public final class Main {
  private Main() {
  }

  /**
   * Main initialization function. Do not perform any initialization here.
   *
   * <p>If you change your main robot class, change the parameter type.
   */
  public static void main(String... args) {
    //We make a slight modification to the Main class: we add the "injector".
    //The injector is an object provided by Guice which is responsible for managing instances of various shared objects
    //in our code.  We use it to create the instance of "Robot", and by doing this, we give any code called by Robot,
    //which in an FRC program is all of the code, access to injection.

    //Note that we have also put the CompetitionRobotModule here.  This particular Main class will only be called on a real robot,
    //so it is safe to directly define that here.  The simulator will actually provide its own main class that properly selects
    //the simulation module for you so that you don't need to worry about it.
    Injector injector = Guice.createInjector(new CompetitionRobotModule());

    //We use the injector to create the instance of Robot, thus giving Robot and anything it references access to the @Inject annotation.
    RobotBase.startRobot(() -> injector.getInstance(Robot.class));

    //This main class may look slightly more complex, but it can be cut and paste into any robot project using injection,
    //with the only change being substituting "new CompetitionRobotModule()" for whatever your module is called.  No further
    //modification is required to make this work.
  }
}
