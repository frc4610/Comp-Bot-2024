// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.controls.DifferentialFollower;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.Pigeon2;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;

public class DriveBase extends SubsystemBase {

  /* Device Declaration */

  // Declare Controller
  CommandXboxController m_driverControl = new CommandXboxController(Constants.OperatorConstants.kDriverControllerPort);

  // Declare Motors
  TalonFX rightFrontMotor = new TalonFX(Constants.DeviceIds.kFrontRightId);
  TalonFX rightBackMotor = new TalonFX(Constants.DeviceIds.kBackRightId);
  TalonFX leftFrontMotor = new TalonFX(Constants.DeviceIds.kFrontLeftId);
  TalonFX leftBackMotor = new TalonFX(Constants.DeviceIds.kBackLeftId);

  // Delcare Gyro
  Pigeon2 m_gyro = new Pigeon2(Constants.DeviceIds.kGyroId);

  // Declare Encoders
  Encoder m_rightEncoder = new Encoder(0,1, true);
  Encoder m_lefEncoder = new Encoder(2, 3, true);

  // Declare Differential Drive
  DifferentialDrive m_drive;

  // Declare Shuffleboard Encoder Tab
  private final ShuffleboardTab m_SensorTab;

  /** Creates a new DriveBase. */
  public DriveBase() {
    
    /* Motor Configuration */

    // Motor Safety
    rightFrontMotor.setSafetyEnabled(false);
    rightBackMotor.setSafetyEnabled(false);
    leftFrontMotor.setSafetyEnabled(false);
    leftBackMotor.setSafetyEnabled(false);

    // Motor Control
    rightFrontMotor.setInverted(false);
    leftFrontMotor.setInverted(true);

    // Encoder Config
    m_rightEncoder.setDistancePerPulse(Constants.DriveBaseConstants.kLinearDistanceConversionFactor/2048);
    m_lefEncoder.setDistancePerPulse(Constants.DriveBaseConstants.kLinearDistanceConversionFactor/2048);

    rightBackMotor.setControl(new Follower(Constants.DeviceIds.kFrontRightId, false));
    leftBackMotor.setControl(new Follower(Constants.DeviceIds.kFrontLeftId, false));

    m_drive = new DifferentialDrive(leftFrontMotor, rightFrontMotor);

    /*  Shuffleboard Tab Setup */

    //  Makes sensor tab
    m_SensorTab = Shuffleboard.getTab("Sensors");

    //  Shuffleboard Right Side Encoders
    m_SensorTab.addDouble("Right Encoder Distance", () -> m_rightEncoder.getDistance());
    m_SensorTab.addDouble("Right Encoder Rate", () -> m_rightEncoder.getRate());

    //  Shuffleboard Left Side Encoders
    m_SensorTab.addDouble("Left Encoder Distance", () -> m_lefEncoder.getDistance());
    m_SensorTab.addDouble("Left Encoder Rate", () -> m_lefEncoder.getRate());
    
    // Shuffleboard Gyro Output
    m_SensorTab.addDouble("Gyro Direction", ()-> m_gyro.getAngle());
  }

  public void curvatureDrive(){
    m_drive.curvatureDrive(
      MathUtil.applyDeadband(m_driverControl.getLeftY(), 0.05),
      MathUtil.applyDeadband(m_driverControl.getRightX(), 0.05), 
      m_driverControl.getHID().getRightBumper());

    // m_drive.curvatureDrive(m_driverControl.getLeftY()*-1, m_driverControl.getRightX(), m_driverControl.getHID().getRightBumper()); Old Working Code
  }

  public void arcadeDrive(double speed, double rotation){
    m_drive.arcadeDrive(speed, rotation);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    curvatureDrive();
  }

  public void resetEncoders(){
    m_rightEncoder.reset();
    m_lefEncoder.reset();
  }

  public double getAverageEncoderDistance() {
    return (m_lefEncoder.getDistance() + m_rightEncoder.getDistance()) / 2.0;
  }

  public void resetGyro() {
    m_gyro.reset();
  }

  public double getHeading() {
    return m_gyro.getAngle();
  }

  public Command exampleCommand(){
    
    return this.runOnce(() -> { /* Command Logic */});
  }
}
