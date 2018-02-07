package org.usfirst.frc.team3414.autonomous;

import org.usfirst.frc.team3414.actuators.ActuatorConfig;

public class AutonCenterSwitch extends AutonBase{

	public AutonCenterSwitch(String gameData) {
		super(gameData);
	}

	protected void left() 
	{
		
	}

	protected void center() 
	{
		if("LLL".equals(gameData) || "LRL".equals(gameData)) {
			ActuatorConfig.getInstance().getDrivetrain().movePid(2);
			ActuatorConfig.getInstance().getDrivetrain().turnLeft(0.8, 90);
			ActuatorConfig.getInstance().getDrivetrain().movePid(2);
		}
		
		else {
			
		}
	}

	protected void right() 
	{
		
	}

}
