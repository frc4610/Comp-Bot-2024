// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {

    public static final int kDriverControllerPort = 0;
    public static final int kOperatorControllerPort = 1;
  }

  public final class DeviceIds {

    /* Drive Base Subsystem Device Ids */

    // Drive Motor Ids
    public static final int kFrontRightId = 10;
    public static final int kBackRightId = 11;
    public static final int kFrontLeftId = 12;
    public static final int kBackLeftId = 13;

    // Gyro
    public static final int kGyroId = 14;

    /* Shooter Subsystem Device Ids */
    
    // Shooter Motor Ids
    public static final int kTopMotorId = 20;
    public static final int kBottomMotorId = 21;

    /* Claw Subsystem Device Ids */

    // Claw Motor Ids
    public static final int kClawMotorId = 30;

    /* Climber Subsystem Device Ids */

    // Climber Motor Ids
    public static final int kClimbMotorId = 40;

    public static final int kLedPort = 0;
  }

  public final class DriveBaseConstants {

    /* Speed Control (Must be negative) */
    public static final double kSpeedMultiplier = -0.5;

    // Wheel circumference in inches
    public static final double kLinearDistanceConversionFactor = 6*Math.PI;

    /* Turn PID Constants */
    public static final double kTurnP = 0.05;
    public static final double kTurnI = 0.05;
    public static final double kTurnD = 0.05;

    /* Gyro Turn Command Constants */
    public static final double kTurnToleranceDeg = 5;
    public static final double kTurnRateToleranceDegPerS = 5; // degrees per second
  }
  public final class ShooterConstants {
    /*  Shooter Constants */

    //  Edit based on robot funtionality or driveteam preference
    //  Edit at Mentors Discretion
    
    // Stop Motors 
    public static final int kStopMotors = 0;

   //  Set Shoot Constant
    public static final double kShootSpeaker = -0.75;

    //  Set intake Constant
    public static final double kShooterIntake = 0.3;
  }
  
  public final class ClimbConstants {
    /*  Climber Constants */
    //  Edit based on climb mechanisms functionality

    //  Set Climbing Upwards Constant
    public static final double kClimbUpSpeed = 0.25;

    //  Set Climbing Down Constant
    public static final double kClimbDownSpeed = -0.25;

    public static final int kStopClimb = 0;

  }

  public final class ClawConstants{

    /*Claw motor speeds*/

    // Claw motor forward speed
    public static final double kClawIntakeSpeed = 0.75;

    // Claw motor backward speed
    public static final double kClawOuttakeSpeed = -0.75;

    // Claw idle speed 
    public static final double kClawIdleSpeed = -0.25;
  }
}
