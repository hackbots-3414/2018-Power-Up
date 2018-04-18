package org.usfirst.frc.team3414.autonomous;

import org.usfirst.frc.team3414.actuators.ActuatorConfig;
import org.usfirst.frc.team3414.actuators.Drivetrain;
import org.usfirst.frc.team3414.sensor.SensorConfig;

public class AutonUtility 
{
	
	public static void deliverSideSwitch(boolean turnRight)
	{
		ActuatorConfig.getInstance().getDrivetrain().setInitialServoPosition();
		ActuatorConfig.getInstance().getDrivetrain().lowerAnglerTo(30);
		ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(23, .35);//24, .85
		SensorConfig.getInstance().getTimer().waitTimeInMillis(500);
		if(turnRight == true) {
			ActuatorConfig.getInstance().getDrivetrain().turnRight(.45, 43);//.38, 75
		}
		else 
		{
			ActuatorConfig.getInstance().getDrivetrain().turnLeft(.50, 45);
		}
		
		SensorConfig.getInstance().getTimer().waitTimeInMillis(500);
		ActuatorConfig.getInstance().getDrivetrain().liftToSwitch();
		ActuatorConfig.getInstance().getDrivetrain().lowerAnglerSwitch();
		ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(2.5, .35);//3, .85
		//SensorConfig.getInstance().getTimer().waitTimeInMillis(1500);
		SensorConfig.getInstance().getTimer().waitTimeInMillis(500);//2500 for scale
		ActuatorConfig.getInstance().getMotorIntakeOne().setSpeed(.40);
		ActuatorConfig.getInstance().getMotorIntakeTwo().setSpeed(.40);
		SensorConfig.getInstance().getTimer().waitTimeInMillis(1000);
		ActuatorConfig.getInstance().getMotorIntakeOne().setSpeed(0);
		ActuatorConfig.getInstance().getMotorIntakeTwo().setSpeed(0);
		ActuatorConfig.getInstance().getDrivetrain().goBackwardsGyro(2, .45);
		ActuatorConfig.getInstance().getDrivetrain().lowerLiftTo(200);
	}
	
	public static void deliverSideScale(boolean turnRight)
	{
		ActuatorConfig.getInstance().getDrivetrain().setInitialServoPosition();
		ActuatorConfig.getInstance().getDrivetrain().lowerAnglerTo(60);
		ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(43, .45);//45, 53
		SensorConfig.getInstance().getTimer().waitTimeInMillis(500);
		if(turnRight == true)
		{
			ActuatorConfig.getInstance().getDrivetrain().turnRight(.43, 38);//.35, 88
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
		SensorConfig.getInstance().getTimer().waitTimeInMillis(1000);
		ActuatorConfig.getInstance().getMotorIntakeOne().setSpeed(0);
		ActuatorConfig.getInstance().getMotorIntakeTwo().setSpeed(0);
		ActuatorConfig.getInstance().getDrivetrain().lowerLiftTo(100);
	}

}
