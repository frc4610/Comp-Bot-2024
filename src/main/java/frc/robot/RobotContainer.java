// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.RobotModeTriggers;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.AmpAuto;
import frc.robot.commands.DriveDistance;
import frc.robot.commands.ScoreSpeakerAndLeave;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Shooter;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  /* ---Subsystems--- */
  private final DriveBase m_driveBase = new DriveBase();
  private final Shooter m_Shooter = new Shooter();
  private final Climber m_Climber = new Climber();
  private final Claw m_claw = new Claw();

  private final Command m_ScoreSpeakerAndLeaveCommand = new ScoreSpeakerAndLeave(m_driveBase, m_Shooter);
  private final Command m_DriveDistanceCommand = new DriveDistance(36, 0.25, m_driveBase);
  private final Command m_AmpAutoCommand = new AmpAuto(m_driveBase, m_claw);

  /* ---Controllers--- */

  // Driver Controller
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  // Operator Controller
  private final CommandXboxController m_operatorController = 
      new CommandXboxController(OperatorConstants.kOperatorControllerPort);

      private SendableChooser<String> AutoSelector;
      public ShuffleboardTab Autos;

      public static final String DriveDistance = "Go Backwards";
      public static final String ScoreSpeakerAndLeave = "Score Speaker and Leave";
      public static final String Score = "Score Speaker";
      public static final String AmpAuto = "Drive and Score Amp";

      private String Selected;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    // Configure the trigger bindings
  configureBindings();

  AutoSelector = new SendableChooser<String>();
  Autos = Shuffleboard.getTab("Auto ");


  AutoSelector.addOption("Score Speaker and Leave", ScoreSpeakerAndLeave);
  AutoSelector.addOption("Go Backwards", DriveDistance);
  AutoSelector.addOption("Score Speaker", Score);
  AutoSelector.addOption("Drive and Score Amp", AmpAuto);

  Autos.add("Auto Selector", AutoSelector).withPosition(2, 0).withSize(6, 4);
 }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {

      /* ---Shooter Bindings--- */

      // Trigger scoreSpeaker method when right bumper is pressed, resets to stopShooter when released
      m_operatorController.rightBumper().whileTrue(Commands.startEnd(
        () -> {m_Shooter.scoreSpeaker();},
        () -> {m_Shooter.stopShooter();}, 
        m_Shooter));
    // Trigger intakeNote method when left bumper is pressed, resets to stopShooter when released
      m_operatorController.leftBumper().whileTrue(Commands.startEnd(
        () -> {m_Shooter.intakeNote();},
        () -> {m_Shooter.stopShooter();},
        m_Shooter ));

      /*  ---Climber Bindings--- */

      // Trigger raiseclimber method when 'A' is pressed, resets to stopClimber when released
      m_operatorController.a().whileTrue(Commands.startEnd(
        () -> {m_Climber.raiseClimber();},
        () -> {m_Climber.stopClimb();}, 
        m_Climber));
      // Trigger lowerClimber method when 'Y' is pressed, resets to stopClimber when released
      m_operatorController.y().whileTrue(Commands.startEnd(
        () -> {m_Climber.lowerClimber();},
        () -> {m_Climber.stopClimb();}, 
        m_Climber));

      /* ---Claw Bindings--- */

      // Trigger scoreAmp command 'B' is pressed, reset to idleClaw when released
     m_operatorController.b().whileTrue(Commands.startEnd(
      () -> {m_claw.scoreAmp();},
      () -> {m_claw.idleClaw();}, 
      m_claw));
      // Trigger intakeClaw command 'X' is pressed, reset to idleClaw when released
     m_operatorController.x().whileTrue(Commands.startEnd(
      () -> {m_claw.intakeClaw();},
      () -> {m_claw.idleClaw();}, 
      m_claw));
      
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {

      
if(AutoSelector.getSelected() == DriveDistance){

  return m_DriveDistanceCommand;
}

else if(AutoSelector.getSelected() == ScoreSpeakerAndLeave){

  return m_ScoreSpeakerAndLeaveCommand;
} 
else if(AutoSelector.getSelected() == AmpAuto){

  return m_AmpAutoCommand;
} 

else {
  
  return Commands.runOnce(() -> m_Shooter.scoreSpeaker(), m_Shooter);
}
}
}
