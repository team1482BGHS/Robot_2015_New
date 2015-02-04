package org.usfirst.frc.team1482;

import edu.wpi.first.wpilibj.Solenoid;

public class pneumaticControll {
	Solenoid extend;
	Solenoid retract;
	
	public pneumaticControll(int extendChannel, int retractChannel) {
		this.extend = new Solenoid(extendChannel);
		this.retract = new Solenoid(retractChannel);
		
		
		// TODO Auto-generated constructor stub
	}
	public void setPiston(boolean state) {
		extend.set(state);
		retract.set(!state);
	}
	

}