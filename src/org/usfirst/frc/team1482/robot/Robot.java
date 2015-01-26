package org.usfirst.frc.team1482.robot;

import org.usfirst.frc.team1482.CanTalonSRX;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team1482.CanTalonSRX;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	CanTalonSRX BackLeftTalon;
	CanTalonSRX BackRightTalon;
	CanTalonSRX FrontLeftTalon;
	CanTalonSRX FrontRightTalon;
	Joystick joystick;
	RobotDrive Drive;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		BackRightTalon = new CanTalonSRX(1);
		BackLeftTalon = new CanTalonSRX(2);
		FrontLeftTalon = new CanTalonSRX(3);
		FrontRightTalon = new CanTalonSRX(4);
		Drive = new RobotDrive(FrontLeftTalon, BackRightTalon, FrontRightTalon,BackRightTalon);
		joystick = new Joystick(1);

	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {

	}

	/**
	 * This function is called periodically during operator control
	 */
	Double x_value;
	Double y_value;
	public void teleopPeriodic() {
		y_value = joystick.getRawAxis(0);
		x_value = joystick.getRawAxis(1);
		
		Drive.arcadeDrive(y_value, x_value);
		
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}

}
