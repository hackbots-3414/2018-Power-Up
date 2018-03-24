package org.usfirst.frc.team3414.autonomous;

import org.usfirst.frc.team3414.sensor.SensorConfig;
import org.usfirst.frc.team3414.actuators.ActuatorConfig;

public class AutonCenterSwitch extends AutonBase{
//Set the robot right in front of the right side of the switch
	public AutonCenterSwitch() {
		super();
	}

	protected void left() 
	{
		ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(18, 0.5);
	}

	protected void center() 
	{
		if("LLL".equals(this.gameData) || "LRL".equals(this.gameData)) 
		{
			ActuatorConfig.getInstance().getDrivetrain().lowerAnglerTo(200);
			ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(8, 0.5);
			SensorConfig.getInstance().getTimer().waitTimeInMillis(300);
			ActuatorConfig.getInstance().getDrivetrain().turnLeft(0.3, 90);
			SensorConfig.getInstance().getTimer().waitTimeInMillis(300);
			ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(15, 0.5);
			SensorConfig.getInstance().getTimer().waitTimeInMillis(300);
			ActuatorConfig.getInstance().getDrivetrain().turnRight(0.3, 90);
			SensorConfig.getInstance().getTimer().waitTimeInMillis(300);
			ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(10, 0.5);
			ActuatorConfig.getInstance().getDrivetrain().liftToScale();
			ActuatorConfig.getInstance().getDrivetrain().lowerAnglerScale();
			ActuatorConfig.getInstance().getMotorIntakeOne().setSpeed(0.4);
			ActuatorConfig.getInstance().getMotorIntakeTwo().setSpeed(0.4);						

//			ActuatorConfig.getInstance().getDrivetrain().motionMagic(3);
//			ActuatorConfig.getInstance().getDrivetrain().turnLeft(.45, 90);
//			ActuatorConfig.getInstance().getDrivetrain().motionMagic(2);
//			ActuatorConfig.getInstance().getDrivetrain().turnRight(.45, 90);
//			ActuatorConfig.getInstance().getDrivetrain().motionMagic(1);
//			ActuatorConfig.getInstance().getLift().setSpeed(-.20);
//			SensorConfig.getInstance().getTimer().waitTimeInMillis(1500);
//			ActuatorConfig.getInstance().getLift().setSpeed(0);
//			ActuatorConfig.getInstance().getMotorIntakeOne().setSpeed(.35);
//			ActuatorConfig.getInstance().getMotorIntakeTwo().setSpeed(.35);

			
//			ActuatorConfig.getInstance().getDrivetrain().movePid(2);
//			ActuatorConfig.getInstance().getDrivetrain().turnLeft(0.8, 90);
//			ActuatorConfig.getInstance().getDrivetrain().movePid(2);
//			ActuatorConfig.getInstance().getDrivetrain().turnRight(0.8, 90);
		}
		
		else if("RLR".equals(this.gameData) || "RRR".equals(this.gameData)) 
		{
			ActuatorConfig.getInstance().getDrivetrain().lowerAnglerTo(200);
			ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(16, 90);
			ActuatorConfig.getInstance().getDrivetrain().liftToSwitch();
			ActuatorConfig.getInstance().getDrivetrain().lowerAnglerSwitch();
			ActuatorConfig.getInstance().getMotorIntakeOne().setSpeed(-0.4);
			ActuatorConfig.getInstance().getMotorIntakeTwo().setSpeed(-0.4);
			
//			ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(16, 90);
//			ActuatorConfig.getInstance().getLift().setSpeed(0.3);
//			ActuatorConfig.getInstance().getMotorIntakeAngler().setSpeed(-0.4);
//			SensorConfig.getInstance().getTimer().waitTimeInMillis(1500);
//			ActuatorConfig.getInstance().getLift().setSpeed(0);
//			ActuatorConfig.getInstance().getMotorIntakeAngler().setSpeed(0);
//			ActuatorConfig.getInstance().getMotorIntakeOne().setSpeed(0.5);
//			ActuatorConfig.getInstance().getMotorIntakeTwo().setSpeed(0.5);
//			SensorConfig.getInstance().getTimer().waitTimeInMillis(1500);
//			ActuatorConfig.getInstance().getMotorIntakeOne().setSpeed(0);
//			ActuatorConfig.getInstance().getMotorIntakeTwo().setSpeed(0);
			
			
			
			
			
			
//			ActuatorConfig.getInstance().getDrivetrain().movePid(2);
//			ActuatorConfig.getInstance().getDrivetrain().turnRight(0.8, 90);
//			ActuatorConfig.getInstance().getDrivetrain().movePid(2);
//			ActuatorConfig.getInstance().getDrivetrain().turnLeft(0.8, 90);
		}
		
		else //This does the right auton so be careful to not run into another robot. 
		{ 
			ActuatorConfig.getInstance().getDrivetrain().lowerAnglerTo(200);
			ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(16, 0.5);
			ActuatorConfig.getInstance().getDrivetrain().liftToSwitch();
			ActuatorConfig.getInstance().getDrivetrain().lowerAnglerSwitch();		}
	}

	protected void right() 
	{
		ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(18, 0.5);

	}

}

