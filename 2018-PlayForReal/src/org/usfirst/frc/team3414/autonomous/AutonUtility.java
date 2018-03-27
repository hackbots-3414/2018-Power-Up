package org.usfirst.frc.team3414.autonomous;

import org.usfirst.frc.team3414.actuators.ActuatorConfig;
import org.usfirst.frc.team3414.actuators.Drivetrain;
import org.usfirst.frc.team3414.sensor.SensorConfig;

public class AutonUtility 
{
	
	public static void deliverSideSwitch(boolean turnRight)
	{
		ActuatorConfig.getInstance().getDrivetrain().setInitialServoPosition();
		ActuatorConfig.getInstance().getDrivetrain().lowerAnglerTo(10);
		ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(24, .35);//18, .85
		SensorConfig.getInstance().getTimer().waitTimeInMillis(500);
		if(turnRight == true) {
			ActuatorConfig.getInstance().getDrivetrain().turnRight(.38, 75);
		}
		else 
		{
			ActuatorConfig.getInstance().getDrivetrain().turnLeft(.50, 45);
		}
		
		SensorConfig.getInstance().getTimer().waitTimeInMillis(1000);
		ActuatorConfig.getInstance().getDrivetrain().liftToSwitch();
		ActuatorConfig.getInstance().getDrivetrain().lowerAnglerSwitch();
		ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(3, .35);//18, .85
		//SensorConfig.getInstance().getTimer().waitTimeInMillis(1500);
		SensorConfig.getInstance().getTimer().waitTimeInMillis(500);//2500 for scale
		ActuatorConfig.getInstance().getMotorIntakeOne().setSpeed(.40);
		ActuatorConfig.getInstance().getMotorIntakeTwo().setSpeed(.40);
	}
	
	public static void deliverSideScale(boolean turnRight)
	{
		ActuatorConfig.getInstance().getDrivetrain().setInitialServoPosition();
		//ActuatorConfig.getInstance().getDrivetrain().lowerAnglerTo(10);
		ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(48, .45);//53
		SensorConfig.getInstance().getTimer().waitTimeInMillis(500);
		if(turnRight == true)
		{
			ActuatorConfig.getInstance().getDrivetrain().turnRight(.35, 88);
		}
		else 
		{
			ActuatorConfig.getInstance().getDrivetrain().turnLeft(.55, 27);
		}
		SensorConfig.getInstance().getTimer().waitTimeInMillis(50);
		ActuatorConfig.getInstance().getDrivetrain().liftToScale();
		SensorConfig.getInstance().getTimer().waitTimeInMillis(50);
		ActuatorConfig.getInstance().getDrivetrain().lowerAnglerScale();
		SensorConfig.getInstance().getTimer().waitTimeInMillis(200);
		ActuatorConfig.getInstance().getMotorIntakeOne().setSpeed(.40);
		ActuatorConfig.getInstance().getMotorIntakeTwo().setSpeed(.40);
	}

}
