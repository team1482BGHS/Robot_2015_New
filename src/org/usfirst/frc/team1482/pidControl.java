package org.usfirst.frc.team1482;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.hal.CanTalonSRX;
public class pidControl implements PIDOutput{
	CanTalonSRX motor1;
	CanTalonSRX motor2;
	
	public pidControl(CanTalonSRX motor1, CanTalonSRX motor2){
		this.motor1 = motor1;
		this.motor2 = motor2;
	}
	
	@Override
	public void pidWrite(double output) {
		motor1.Set(-output);
		motor2.Set(-output);
		// TODO Auto-generated method stub
		System.out.println("Settings speed to" + output);
		
		
		
	}

}
