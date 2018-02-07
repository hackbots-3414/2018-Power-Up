package org.usfirst.frc.team3414.autonomous;

import org.usfirst.frc.team3414.actuators.ActuatorConfig;


public class AutonSideSwitch extends AutonBase{


	protected void left(String gameData) {
		if(gameData == "LRL" || gameData == "LLL" ){
		
			ActuatorConfig.getInstance().getDrivetrain().movePid(4);//find actual measurements
			ActuatorConfig.getInstance().getDrivetrain().turnRight(.8, 90);
			ActuatorConfig.getInstance().getDrivetrain().movePid(1);
			//Drop the power cube
			ActuatorConfig.getInstance().getDrivetrain().movePid(-1);
			System.out.println("Position Left: Left switch");
		}
		else {
			ActuatorConfig.getInstance().getDrivetrain().movePid(5);
			ActuatorConfig.getInstance().getDrivetrain().turnRight(.8,90);
			ActuatorConfig.getInstance().getDrivetrain().movePid(3);
			System.out.println("Position Left: Park past switch");
			}
	}

	protected void center(String gameData) {
		if (gameData == "LRL")
			{
			System.out.println("Nothing here to see here o_o -Hannah");			
			}
	}

	protected void right(String gameData) {
		if(gameData == "RLR" || gameData == "RRR" ){
		
		ActuatorConfig.getInstance().getDrivetrain().movePid(4);//find actual measurements
		ActuatorConfig.getInstance().getDrivetrain().turnLeft(.8, 90);
		ActuatorConfig.getInstance().getDrivetrain().movePid(1);
		//Drop the power cube
		ActuatorConfig.getInstance().getDrivetrain().movePid(-1);
		System.out.println("Position Right: Right switch");
	}
	else {
		ActuatorConfig.getInstance().getDrivetrain().movePid(5);
		ActuatorConfig.getInstance().getDrivetrain().turnLeft(.8,90);
		ActuatorConfig.getInstance().getDrivetrain().movePid(3);
		System.out.println("Position Right: Park past switch");	
		}
	}

}
