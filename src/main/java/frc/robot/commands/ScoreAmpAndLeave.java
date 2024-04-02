// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Claw;

public class ScoreAmpAndLeave extends SequentialCommandGroup {
  /** Creates a new ScoreAmpAndLeave. */
  
  DriveBase m_DriveBase;
  Claw m_Claw;

  public ScoreAmpAndLeave(DriveBase driveBase, Claw claw) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.m_DriveBase = driveBase;
    this.m_Claw = claw;

    addCommands(
    Commands.runOnce( () -> {m_DriveBase.resetEncoders(); m_DriveBase.resetGyro();}),
    new DriveDistance(32, 0.5, driveBase),
    new WaitCommand(2),
    new TurnToAngle(-90, driveBase),
    new WaitCommand(2),
    new DriveDistance(20, 0.5, driveBase),
    new WaitCommand(2),
    Commands.runOnce( () -> {m_Claw.scoreAmp();}, m_Claw),
    new WaitCommand(2),
    new DriveDistance(20, -0.5, driveBase),
    new WaitCommand(2),
    new TurnToAngle(90, driveBase));
  }
}
