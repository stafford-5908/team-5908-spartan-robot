// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;

import java.util.ResourceBundle.Control;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * This is a demo program showing the use of the DifferentialDrive class. Runs the motors with
 * arcade steering.
 */
// Defining Variables
public class Robot extends TimedRobot {
  // Drive Motors Restablish/Recheck the motors later
  private final WPI_TalonSRX rightLeadMotor = new WPI_TalonSRX(1);
  private final WPI_TalonSRX leftLeadMotor = new WPI_TalonSRX(4);
  private final WPI_TalonSRX leftBackMotor = new WPI_TalonSRX(3);
  private final WPI_TalonSRX rightBackMotor = new WPI_TalonSRX(2);
  // Intake/Belt System
  private final WPI_TalonSRX intakeMotor = new WPI_TalonSRX(9);
  private final WPI_TalonSRX beltMotor = new WPI_TalonSRX(13);
  private final WPI_TalonSRX climberMotor = new WPI_TalonSRX(14);
  private final WPI_TalonSRX shooterMotor = new WPI_TalonSRX(15);
  // Controller
  private final DifferentialDrive m_robotDrive = new DifferentialDrive(leftLeadMotor, rightLeadMotor);
  private final XboxController player1 = new XboxController(0);
  private final XboxController player2 = new XboxController(1);
  private final Timer robotTimer = new Timer();

    // player1 is driver, player2 is shooter

// when the robot first starts
  @Override
  public void robotInit() {
    leftBackMotor.follow(leftLeadMotor);
    rightBackMotor.follow(rightLeadMotor);
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
   // m_rightMotor.setInverted(true);
  }

  // when autonomous starts
  @Override
  public void autonomousInit() {
    robotTimer.reset();
    robotTimer.start();
  }

// when autonomous is running
  @Override
  public void autonomousPeriodic() {

  }
// when tele op is running
  @Override
  public void teleopPeriodic() {
    m_robotDrive.arcadeDrive(player1.getRightX(), player1.getLeftY());
    climberMotor.set(ControlMode.PercentOutput, player2.getLeftY());

    if (player1.getRightTriggerAxis() >= 0.6) {
      intakeMotor.set(ControlMode.PercentOutput,1.0);
    }
    else {
      intakeMotor.set(ControlMode.PercentOutput,0.0);
    }

    if (player1.getLeftTriggerAxis() >= 0.6) {
      intakeMotor.set(ControlMode.PercentOutput,1.0);
    }
    else {
      intakeMotor.set(ControlMode.PercentOutput,0.0);
    }

    if (player2.getAButton()==true) {
      beltMotor.set(ControlMode.PercentOutput,-1.0);
    }
    else {
      beltMotor.set(ControlMode.PercentOutput,0.0);
    }

    if (player2.getBButton()==true) {
      shooterMotor.set(ControlMode.PercentOutput,1.0);
    }
    else {
      shooterMotor.set(ControlMode.PercentOutput,0.0);
    }

    if (player2.getPOV() == 0) {
      beltMotor.set(ControlMode.PercentOutput,0.5);
      shooterMotor.set(ControlMode.PercentOutput,0.5);
    }
    else {
      shooterMotor.set(ControlMode.PercentOutput,0.0);
      beltMotor.set(ControlMode.PercentOutput,0.0);
    }


    }
    // Drive with arcade drive.
    // That means that the Y axis drives forward
    // and backward, and the X turns left and right.
  
  }
}
