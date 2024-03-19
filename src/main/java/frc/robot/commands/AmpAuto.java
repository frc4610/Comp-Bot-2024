// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.*;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AmpAuto extends SequentialCommandGroup {

  DriveBase m_DriveBase;
  Claw m_Claw;

  /** Creates a new ScoreAndLeave. */
  public AmpAuto(DriveBase driveBase, Claw claw) {

    this.m_DriveBase = driveBase;
    this.m_Claw = claw;

    addCommands( 
    Commands.runOnce( () -> {m_DriveBase.resetEncoders(); m_DriveBase.resetGyro();}),
    new WaitCommand(2),
    new DriveDistance(24, -0.5, driveBase),
    new WaitCommand(2),
    //Commands.runOnce(() ->{m_DriveBase.arcadeDrive(-0.25, 0.5 );}, m_DriveBase)); //  Havent tested yet, I dont fundemantally understand it
    new TurnToAngle(90, driveBase), //  Turn to angle is turnng to the appropriate angle but then trying to overshoot it and starts spinning in 
    new WaitCommand(2),
    //new DriveDistance(24, -0.5, m_DriveBase));
    //new WaitCommand(2);
    Commands.runOnce(() -> {m_Claw.scoreAmp();}));
    
  }
}
