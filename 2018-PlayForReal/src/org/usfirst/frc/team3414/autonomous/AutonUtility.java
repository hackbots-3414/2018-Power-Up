package org.usfirst.frc.team3414.autonomous;

import org.usfirst.frc.team3414.actuators.ActuatorConfig;
import org.usfirst.frc.team3414.actuators.Drivetrain;
import org.usfirst.frc.team3414.sensor.SensorConfig;

public class AutonUtility 
{ 
	
	public static void deliverSideSwitch(boolean turnRight)
	{
		if(turnRight == false) {
		SensorConfig.getInstance().getTimer().waitTimeInMillis(500);
			
			if(!ActuatorConfig.getInstance().getOnlyDriveTrain())
		    {
				ActuatorConfig.getInstance().getDrivetrain().setInitialServoPosition();
				ActuatorConfig.getInstance().getDrivetrain().lowerAnglerTo(35);
		    }
			ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(24, 0.0, 0.35); //??0.4
			SensorConfig.getInstance().getTimer().waitTimeInMillis(500);

//			if ("RLR".equals(this.gameData)|| "RRR".equals(this.gameData))//Runs right auton for right switch 
			
				ActuatorConfig.getInstance().getDrivetrain().turnLeftRadius(0.2, 60, 0, 24 );
				SensorConfig.getInstance().getTimer().waitTimeInMillis(500);
			
				if(!ActuatorConfig.getInstance().getOnlyDriveTrain())
				{
					ActuatorConfig.getInstance().getDrivetrain().liftToSwitch();
				}
				ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(3, 0.0, 0.30);//17, 9.748
				if(!ActuatorConfig.getInstance().getOnlyDriveTrain())
				{
					ActuatorConfig.getInstance().getMotorIntakeOne().setSpeed(.40);
					ActuatorConfig.getInstance().getMotorIntakeTwo().setSpeed(.40);
					SensorConfig.getInstance().getTimer().waitTimeInMillis(500);
					ActuatorConfig.getInstance().getMotorIntakeOne().setSpeed(0);
					ActuatorConfig.getInstance().getMotorIntakeTwo().setSpeed(0);
					ActuatorConfig.getInstance().getDrivetrain().goBackwardsGyro(2, 0.0, .40);
					ActuatorConfig.getInstance().getDrivetrain().lowerLiftTo(200);
				}
		}
		else{
//			SensorConfig.getInstance().getTimer().waitTimeInMillis(1000);
		if(!ActuatorConfig.getInstance().getOnlyDriveTrain())
        {
			ActuatorConfig.getInstance().getDrivetrain().setInitialServoPosition();
			ActuatorConfig.getInstance().getDrivetrain().lowerAnglerTo(35);
        }
		
		ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(25, 0.0, 0.35); //??0.4
		SensorConfig.getInstance().getTimer().waitTimeInMillis(500);

//		if (("LRL".equals(this.gameData))|| ("LLL".equals(this.gameData)))//Runs left auton for left switch

			ActuatorConfig.getInstance().getDrivetrain().turnRightRadius(0.2, 60, 0, 24 );
			SensorConfig.getInstance().getTimer().waitTimeInMillis(500);
			if(!ActuatorConfig.getInstance().getOnlyDriveTrain())
			{
				ActuatorConfig.getInstance().getDrivetrain().liftToSwitch();
			}
			ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(3, 0.0, 0.27);//17, 9.748
			if(!ActuatorConfig.getInstance().getOnlyDriveTrain())
			{
				ActuatorConfig.getInstance().getMotorIntakeOne().setSpeed(.40);
				ActuatorConfig.getInstance().getMotorIntakeTwo().setSpeed(.40);
				SensorConfig.getInstance().getTimer().waitTimeInMillis(500);
				ActuatorConfig.getInstance().getMotorIntakeOne().setSpeed(0);
				ActuatorConfig.getInstance().getMotorIntakeTwo().setSpeed(0);
				ActuatorConfig.getInstance().getDrivetrain().goBackwardsGyro(2, 0.0, .40);
				ActuatorConfig.getInstance().getDrivetrain().lowerLiftTo(200);
			}
		}
			
			/*
		ActuatorConfig.getInstance().getDrivetrain().setInitialServoPosition();
		ActuatorConfig.getInstance().getDrivetrain().lowerAnglerTo(30);
		ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(21, 0.0, .35);//22, .85
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
		ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(2.5, 0.0, .35);//3, .85
		//SensorConfig.getInstance().getTimer().waitTimeInMillis(1500);
		SensorConfig.getInstance().getTimer().waitTimeInMillis(500);//2500 for scale
		ActuatorConfig.getInstance().getMotorIntakeOne().setSpeed(.35);//40
		ActuatorConfig.getInstance().getMotorIntakeTwo().setSpeed(.35);//40
		SensorConfig.getInstance().getTimer().waitTimeInMillis(1000);
		ActuatorConfig.getInstance().getMotorIntakeOne().setSpeed(0);
		ActuatorConfig.getInstance().getMotorIntakeTwo().setSpeed(0);
		ActuatorConfig.getInstance().getDrivetrain().goBackwardsGyro(2, 0.0, .45);
		ActuatorConfig.getInstance().getDrivetrain().lowerLiftTo(200); */
	}
	
	public static void deliverSideScale(boolean turnRight)
	{
		ActuatorConfig.getInstance().getDrivetrain().setInitialServoPosition();
		ActuatorConfig.getInstance().getDrivetrain().lowerAnglerTo(60);
		ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(43,.0,.45);//45, 53
		SensorConfig.getInstance().getTimer().waitTimeInMillis(500);
		if(turnRight == true)
		{
			ActuatorConfig.getInstance().getDrivetrain().turnRightRadius(0.25, 30, 0.0, 24);//.35, 88
		}
		else 
		{
			ActuatorConfig.getInstance().getDrivetrain().turnLeftRadius(0.25, 30, 0.0, 24);
		}
		SensorConfig.getInstance().getTimer().waitTimeInMillis(50);
		ActuatorConfig.getInstance().getDrivetrain().liftToScaleAutonPhillip();
		SensorConfig.getInstance().getTimer().waitTimeInMillis(50);
		ActuatorConfig.getInstance().getDrivetrain().lowerAnglerScale();
		SensorConfig.getInstance().getTimer().waitTimeInMillis(200);
		ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(1, 0.0, 0.4);
		ActuatorConfig.getInstance().getMotorIntakeOne().setSpeed(.40);
		ActuatorConfig.getInstance().getMotorIntakeTwo().setSpeed(.40);
		SensorConfig.getInstance().getTimer().waitTimeInMillis(500);
		ActuatorConfig.getInstance().getMotorIntakeOne().setSpeed(0);
		ActuatorConfig.getInstance().getMotorIntakeTwo().setSpeed(0);
		ActuatorConfig.getInstance().getDrivetrain().lowerLiftTo(100);
		ActuatorConfig.getInstance().getDrivetrain().goBackwardsGyro(1, 0.0, 0.5);
	}

}
