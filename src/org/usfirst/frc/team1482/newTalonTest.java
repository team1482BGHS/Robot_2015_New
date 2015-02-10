package org.usfirst.frc.team1482;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.hal.CanTalonSRX;

public class newTalonTest extends CanTalonSRX implements SpeedController {

	public newTalonTest(int port) {
		super(port);
	}
	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub

	}

	@Override
	public double get() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void set(double speed, byte syncGroup) {
		// TODO Auto-generated method stub
		this.Set(speed);

	}

	@Override
	public void set(double speed) {
		this.Set(speed);

	}

	@Override
	public void disable() {
		// TODO Auto-generated method stub

	}

}
