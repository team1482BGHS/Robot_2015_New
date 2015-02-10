package org.usfirst.frc.team1482;

public class GearShifter extends pneumaticControll {
	public GearShifter(int extendChannel, int retractChannel){
		super(extendChannel, retractChannel);
	}
	int Gear = 0;
	public int getGear () {
		return Gear; 
	}
	public void setPiston(boolean state) {
		Gear = state ? 1 : 2;
		super.setPiston(state);
		
	}

}
