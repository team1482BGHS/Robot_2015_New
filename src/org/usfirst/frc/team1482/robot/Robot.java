package org.usfirst.frc.team1482.robot;

import org.usfirst.frc.team1482.GearShifter;
import org.usfirst.frc.team1482.pidControl;
import org.usfirst.frc.team1482.pneumaticControll;

import edu.wpi.first.wpilibj.hal.CanTalonSRX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

public class Robot extends IterativeRobot {
	CanTalonSRX Motor1;
	CanTalonSRX Motor4;
	org.usfirst.frc.team1482.newTalonTest MotorBackLeft;
	org.usfirst.frc.team1482.newTalonTest MotorBackRight;
	org.usfirst.frc.team1482.newTalonTest MotorFrontLeft;
	org.usfirst.frc.team1482.newTalonTest MotorFrontRight;
	Joystick joystick;
	Joystick launchpad;
	RobotDrive Drive;
	Encoder encoderleft;
	Encoder encoderright;
	Encoder encoderlifter;
	PIDController DriveControll;
	GearShifter gearBoxShifter;
	pidControl motors;
	double level1 = 1000;
	double level2 = 2000;
	double level3 = 3000;
	double level4 = 4000;
	double level5 = 5000;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {

		joystick = new Joystick(1);
		launchpad = new Joystick(2);
		encoderleft = new Encoder(0, 1);
		encoderright = new Encoder(2, 3);
		encoderlifter = new Encoder(4, 5);
		Motor1 = new CanTalonSRX(5);
		Motor4 = new CanTalonSRX(6);
		MotorBackLeft = new org.usfirst.frc.team1482.newTalonTest(2);
		MotorBackRight = new org.usfirst.frc.team1482.newTalonTest(1);
		MotorFrontLeft = new org.usfirst.frc.team1482.newTalonTest(3);
		MotorFrontRight = new org.usfirst.frc.team1482.newTalonTest(4);
		gearBoxShifter = new GearShifter(0, 1);
		Drive = new RobotDrive(MotorFrontLeft, MotorBackLeft, MotorFrontRight,
				MotorBackRight);

		motors = new pidControl(Motor1, Motor4);

		DriveControll = new PIDController(0.001, 0, 0.00015, encoderlifter,
				motors);
		encoderleft.setDistancePerPulse(0.11);
		encoderright.setDistancePerPulse(0.11);
		

	}
	boolean finish1 = false;
	boolean finish2 = false;
	// boolean for the autonomous code
	
	int autonomusloopcount;

	public void autonomousInit() {
		autonomusloopcount = 0;
		DriveControll.enable();
		DriveControll.setContinuous();
		finish1 = false;
		finish2 = false;
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		double distance = (encoderleft.getDistance() + encoderright
				.getDistance()) /2;
		int encoderCounts = encoderleft.get();
		SmartDashboard.putNumber("encoderCounts", encoderCounts);
		SmartDashboard.putNumber("Movement", distance);
		SmartDashboard.putNumber("Auto Loop Count", autonomusloopcount);
		
//		DriveControll.setSetpoint(0);
//		Timer.delay(2);
//		DriveControll.setSetpoint(level1);
//		Timer.delay(2);
//		DriveControll.setSetpoint(level2);
//		Timer.delay(2);
//		DriveControll.setSetpoint(level3);
//		Timer.delay(2);
//		DriveControll.setSetpoint(level4);
//		Timer.delay(2);
//		DriveControll.setSetpoint(level5);
//		Timer.delay(2);
		autonomusloopcount++;
		System.out.println("autonomus has run" + autonomusloopcount + "times");
			

		if ((distance > -12) && autonomusloopcount < 1000000 && !finish1) {
			Drive.arcadeDrive(-.5, 0);
			
		} else if(distance <= -12 && !finish1) {
			finish1 = true;
		
		} else if(distance < 12 && autonomusloopcount < 3000000 && !finish2) {
			Drive.arcadeDrive(.5, 0);
			
		} else if(distance >= 12 && !finish2) {	
			finish2 = true;
			
		} else if(distance >= 12) {
			Drive.arcadeDrive(0, 0);
			System.out.println("Ryan is awesome.");
			
		}

		// if(encoderDistance > 280){
		// Motor1.Set(1);
		// Motor4.Set(1);
		// }else if(encoderDistance > 175){
		// Motor1.Set(.5);
		// Motor4.Set(.5);
		// }else if(encoderDistance > 30){
		// Motor1.Set(.15);
		// Motor4.Set(.15);
		// }else if(encoderDistance > -30){
		// Motor1.Set(0);
		// Motor4.Set(0);
		// }else if(encoderDistance > -175){
		// Motor1.Set(-.2);
		// Motor4.Set(-.2);
		// }else if(encoderDistance <= -175){
		// Motor1.Set(-1);
		// Motor4.Set(-1);
		//
		// }
	}

	public void teleopInit() {
		gearBoxShifter.setPiston(true);
	}

	/**
	 * This function is called periodically during operator control
	 *
	 */
	Double x_value;
	Double y_value;
	Double leftspeed;
	Double rightspeed;
	boolean state = false;
	int count = 0;
	int remainder = 0;

	public void teleopPeriodic() {
		leftspeed = -encoderleft.getRate();
		rightspeed = encoderright.getRate();
		if (leftspeed + rightspeed > 3000 && gearBoxShifter.getGear() == 1) {
			gearBoxShifter.setPiston(false);

		}
		if (leftspeed + rightspeed < 2000 && gearBoxShifter.getGear() == 2) {
			gearBoxShifter.setPiston(true);

		}
		SmartDashboard.putNumber("Left encoder", leftspeed);
		SmartDashboard.putNumber("Right encoder", rightspeed);

		// count++;
		// remainder = count % 6;

		// int encoderDistance = encoder.get();
		// SmartDashboard.putNumber("encoderDistance", encoderDistance);
		y_value = joystick.getRawAxis(0);
		x_value = joystick.getRawAxis(1);
		// Motor1.Set(y_value);
		// Motor4.Set(y_value);
		Drive.arcadeDrive(x_value, y_value);
		// state = !state;

		//
		// launchpad.setOutput(1, remainder ==0);
		// launchpad.setOutput(2, remainder ==0);
		// launchpad.setOutput(3, remainder ==0);
		// launchpad.setOutput(4, remainder ==0);
		// launchpad.setOutput(5, remainder ==0);
		// launchpad.setOutput(6, remainder ==0);
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}

}
