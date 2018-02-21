package org.usfirst.frc.team3414.autonomous;

import org.usfirst.frc.team3414.actuators.ActuatorConfig;

public class AutonCenterSwitch extends AutonBase{

	public AutonCenterSwitch() {
		super();
	}

	protected void left() 
	{
		
	}

	protected void center() 
	{
		if("LLL".equals(this.gameData) || "LRL".equals(this.gameData)) 
		{
//			ActuatorConfig.getInstance().getDrivetrain().movePid(2);
//			ActuatorConfig.getInstance().getDrivetrain().turnLeft(0.8, 90);
//			ActuatorConfig.getInstance().getDrivetrain().movePid(2);
//			ActuatorConfig.getInstance().getDrivetrain().turnRight(0.8, 90);
		}
		
		else if("RLR".equals(this.gameData) || "RRR".equals(this.gameData)) 
		{
//			ActuatorConfig.getInstance().getDrivetrain().movePid(2);
//			ActuatorConfig.getInstance().getDrivetrain().turnRight(0.8, 90);
//			ActuatorConfig.getInstance().getDrivetrain().movePid(2);
//			ActuatorConfig.getInstance().getDrivetrain().turnLeft(0.8, 90);
		}
		
		else //This does the right auton so be careful to not run into another robot. 
		{ 
//			ActuatorConfig.getInstance().getDrivetrain().movePid(2);
//			ActuatorConfig.getInstance().getDrivetrain().turnRight(0.8, 90);
//			ActuatorConfig.getInstance().getDrivetrain().movePid(2);
//			ActuatorConfig.getInstance().getDrivetrain().turnLeft(0.8, 90);
		}
	}

	protected void right() 
	{
		
	}

}

