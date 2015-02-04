package org.usfirst.frc.team1482.robot;

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
	CanTalonSRX MotorBackLeft;
	CanTalonSRX MotorBackRight;
	CanTalonSRX MotorFrontLeft;
	CanTalonSRX MotorFrontRight; 
	Joystick joystick;
	Joystick launchpad;
	RobotDrive Drive;
	Encoder encoder; 
	PIDController DriveControll;
	pneumaticControll gearBoxShifter;
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
		encoder = new Encoder(0, 1);
		Motor1 = new CanTalonSRX(1);
		Motor4 = new CanTalonSRX(4);
		MotorBackLeft = new CanTalonSRX(2);
		MotorBackRight = new CanTalonSRX(3);
		MotorFrontLeft = new CanTalonSRX(5);
		MotorFrontRight = new CanTalonSRX(6);
		gearBoxShifter = new pneumaticControll(0, 1);
		
		
		motors = new pidControl(Motor1, Motor4);
		
		DriveControll = new PIDController(0.001, 0, 0.00015, encoder,motors);
		
		

	}
		int autonomusloopcount;
	public void autonomousInit(){
		autonomusloopcount = 0;
		DriveControll.enable();
		DriveControll.setContinuous();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
	SmartDashboard.putNumber("encoderDistance",encoderDistance(0));	
	int encoderDistance = encoder.get();
	SmartDashboard.putNumber("encoderDistance", encoderDistance);
	DriveControll.setSetpoint(0);
	Timer.delay(2);
	DriveControll.setSetpoint(level1);
	Timer.delay(2);
	DriveControll.setSetpoint(level2);
	Timer.delay(2);
	DriveControll.setSetpoint(level3);
	Timer.delay(2);
	DriveControll.setSetpoint(level4);
	Timer.delay(2);
	DriveControll.setSetpoint(level5);
	Timer.delay(2);
	autonomusloopcount++;
	System.out.println("autonomus has run" + autonomusloopcount + "times");
	
//	if(encoderDistance > 280){
//		Motor1.Set(1);
//		Motor4.Set(1);
//	}else if(encoderDistance > 175){
//		Motor1.Set(.5);
//		Motor4.Set(.5);
//	}else if(encoderDistance > 30){
//		Motor1.Set(.15);
//		Motor4.Set(.15);
//	}else if(encoderDistance > -30){
//		Motor1.Set(0);
//		Motor4.Set(0);
//	}else if(encoderDistance > -175){
//		Motor1.Set(-.2);
//		Motor4.Set(-.2);
//	}else if(encoderDistance <= -175){
//		Motor1.Set(-1);
//		Motor4.Set(-1);
//		
//	}	
	}

	private double encoderDistance(int i) {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * This function is called periodically during operator control
	 */
	Double x_value;
	Double y_value;
	boolean state = false;
	int count = 0;
	int remainder = 0;
	public void teleopPeriodic() {
		count++;
		remainder = count % 6;
		
		int encoderDistance = encoder.get();
		SmartDashboard.putNumber("encoderDistance", encoderDistance);
		y_value = joystick.getRawAxis(0);
		x_value = joystick.getRawAxis(1);
		Motor1.Set(y_value);
		Motor4.Set(y_value);
		
		state = !state;

//		
//		launchpad.setOutput(1, remainder ==0);
//		launchpad.setOutput(2, remainder ==0);
//		launchpad.setOutput(3, remainder ==0);
//		launchpad.setOutput(4, remainder ==0); 
//		launchpad.setOutput(5, remainder ==0);
//		launchpad.setOutput(6, remainder ==0);
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}

}
