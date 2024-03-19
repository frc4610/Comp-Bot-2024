// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;
import frc.robot.Constants.OperatorConstants;

public class Shooter extends SubsystemBase {
  /*  Device declaration  */ 

  //  Declare Operator Controller
  CommandXboxController m_operatorController = new CommandXboxController(OperatorConstants.kOperatorControllerPort);

  //  Declare Motors
  TalonFX topMotor = new TalonFX(Constants.DeviceIds.kTopMotorId);
  TalonFX bottomMotor = new TalonFX(Constants.DeviceIds.kBottomMotorId);

  /*  Creates a new Shooter */
  public Shooter() {

    /*  Motor Configuration */

    // Motor Safety
    topMotor.setSafetyEnabled(false);
    bottomMotor.setSafetyEnabled(false);

    /*  Motor control */
    //  Set motors Inverted
    topMotor.setInverted(true);
    bottomMotor.setInverted(true);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
   public void scoreSpeaker(){
    //  Set motors to the speed constant for speaker
    topMotor.set(Constants.ShooterConstants.kShootSpeaker);
    Timer.delay(1.0);
    bottomMotor.set(Constants.ShooterConstants.kShootSpeaker);
   }
   public void stopShooter(){
    //  Set motors to zero through constants
    topMotor.set(Constants.ShooterConstants.kStopMotors);
    bottomMotor.set(Constants.ShooterConstants.kStopMotors);
   }
    public void intakeNote(){
    //  Set motors to the intake speed from constants
    topMotor.set(Constants.ShooterConstants.kShooterIntake);
    bottomMotor.set(Constants.ShooterConstants.kShooterIntake);
    }
}
