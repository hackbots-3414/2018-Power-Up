package org.usfirst.frc.team3414.autonomous;

import org.usfirst.frc.team3414.actuators.ActuatorConfig;

public class AutonSideSwitchAndScale extends AutonBase
{

	protected void left(String gameData) 
	{
		if(gameData == "LRL") //Switch only
		{
		
		ActuatorConfig.getInstance().getDrivetrain().movePid(4);//find actual measurements
		ActuatorConfig.getInstance().getDrivetrain().turnRight(.8, 90);
		ActuatorConfig.getInstance().getDrivetrain().movePid(1);
		//Drop the power cube
		ActuatorConfig.getInstance().getDrivetrain().movePid(-1);
		System.out.println("Position left: Going for the switch");
	}
		else if (gameData == "LLL") //Both Switch and Scale
		{
		ActuatorConfig.getInstance().getDrivetrain().movePid(4);//find actual measurements
		ActuatorConfig.getInstance().getDrivetrain().turnRight(.8, 90);
		ActuatorConfig.getInstance().getDrivetrain().movePid(1);
		//Drop the power cube in switch
		ActuatorConfig.getInstance().getDrivetrain().movePid(-1);
		ActuatorConfig.getInstance().getDrivetrain().turnLeft(.8, 90);
		ActuatorConfig.getInstance().getDrivetrain().movePid(1);
		ActuatorConfig.getInstance().getDrivetrain().turnRight(.8, 90);
		//Pick up power cube from right past switch
		ActuatorConfig.getInstance().getDrivetrain().movePid(-1);
		ActuatorConfig.getInstance().getDrivetrain().turnLeft(.8, 90);
		ActuatorConfig.getInstance().getDrivetrain().movePid(3);
		ActuatorConfig.getInstance().getDrivetrain().turnRight(.8, 90);
		//Raise elevator
		ActuatorConfig.getInstance().getDrivetrain().movePid(1);
		//Drop off power cube in Scale
		ActuatorConfig.getInstance().getDrivetrain().movePid(-1);
		//Lower elevator
		ActuatorConfig.getInstance().getDrivetrain().turnRight(.8, 90);
		System.out.println("Postion left: Going for both the switch and scale (wish me luck ^-^)");
		}
		else if (gameData == "RLR")//Scale only
		{
		ActuatorConfig.getInstance().getDrivetrain().movePid(7);
		ActuatorConfig.getInstance().getDrivetrain().turnRight(.8, 90);
		//Raise elevator
		ActuatorConfig.getInstance().getDrivetrain().movePid(1);
		//Drop off power cube in Scale
		ActuatorConfig.getInstance().getDrivetrain().movePid(-1);
		//Lower elevator
		System.out.println("Postion left: Going for scale");
		}
		else
		{
		ActuatorConfig.getInstance().getDrivetrain().movePid(5);
		ActuatorConfig.getInstance().getDrivetrain().turnRight(.8,90);
		ActuatorConfig.getInstance().getDrivetrain().movePid(3);
		System.out.println("Position left: Parking past switch");
		}
	}

	protected void center(String gameData)
	{
	System.out.println("Nothing here to see here o_o -Hannah");	
	}

	protected void right(String gameData) {
		if(gameData == "RLR") //Switch only
		{
		
		ActuatorConfig.getInstance().getDrivetrain().movePid(4);//find actual measurements
		ActuatorConfig.getInstance().getDrivetrain().turnLeft(.8, 90);
		ActuatorConfig.getInstance().getDrivetrain().movePid(1);
		//Drop the power cube
		ActuatorConfig.getInstance().getDrivetrain().movePid(-1);
		System.out.println("Position right: Going for the switch");
	}
		else if (gameData == "RRR") //Both Switch and Scale
		{
		ActuatorConfig.getInstance().getDrivetrain().movePid(4);//find actual measurements
		ActuatorConfig.getInstance().getDrivetrain().turnLeft(.8, 90);
		ActuatorConfig.getInstance().getDrivetrain().movePid(1);
		//Drop the power cube in switch
		ActuatorConfig.getInstance().getDrivetrain().movePid(-1);
		ActuatorConfig.getInstance().getDrivetrain().turnRight(.8, 90);
		ActuatorConfig.getInstance().getDrivetrain().movePid(1);
		ActuatorConfig.getInstance().getDrivetrain().turnLeft(.8, 90);
		//Pick up power cube from right past switch
		ActuatorConfig.getInstance().getDrivetrain().movePid(-1);
		ActuatorConfig.getInstance().getDrivetrain().turnRight(.8, 90);
		ActuatorConfig.getInstance().getDrivetrain().movePid(3);
		ActuatorConfig.getInstance().getDrivetrain().turnLeft(.8, 90);
		//Raise elevator
		ActuatorConfig.getInstance().getDrivetrain().movePid(1);
		//Drop off power cube in Scale
		ActuatorConfig.getInstance().getDrivetrain().movePid(-1);
		//Lower elevator
		ActuatorConfig.getInstance().getDrivetrain().turnLeft(.8, 90);
		System.out.println("Position right: Going for both the switch and scale (wish me luck ^-^)");
		}
		else if (gameData == "LRL")//Scale only
		{
		ActuatorConfig.getInstance().getDrivetrain().movePid(7);
		ActuatorConfig.getInstance().getDrivetrain().turnLeft(.8, 90);
		//Raise elevator
		ActuatorConfig.getInstance().getDrivetrain().movePid(1);
		//Drop off power cube in Scale
		ActuatorConfig.getInstance().getDrivetrain().movePid(-1);
		//Lower elevator
		System.out.println("Position right: Going for scale");
		}
		else
		{
		ActuatorConfig.getInstance().getDrivetrain().movePid(5);
		ActuatorConfig.getInstance().getDrivetrain().turnLeft(.8,90);
		ActuatorConfig.getInstance().getDrivetrain().movePid(3);
		System.out.println("Position right: Parking past switch");
		}
	}

}


