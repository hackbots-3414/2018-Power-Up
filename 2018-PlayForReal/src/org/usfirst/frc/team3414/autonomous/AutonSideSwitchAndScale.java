package org.usfirst.frc.team3414.autonomous;

import org.usfirst.frc.team3414.actuators.ActuatorConfig;

public class AutonSideSwitchAndScale extends AutonBase
{

	public AutonSideSwitchAndScale() {
		super();
	}

	protected void left() 
	{
		if("LRL".equals(this.gameData)) //Switch only
		{
		
//			ActuatorConfig.getInstance().getDrivetrain().movePid(4);//find actual measurements
//			ActuatorConfig.getInstance().getDrivetrain().turnRight(.8, 90);
//			ActuatorConfig.getInstance().getDrivetrain().movePid(1);
//			//Drop the power cube
//			ActuatorConfig.getInstance().getDrivetrain().movePid(-1);
			System.out.println("Position left: Going for the switch");
	}
		else if ("LLL".equals(this.gameData)) //Both Switch and Scale
		{
//			ActuatorConfig.getInstance().getDrivetrain().movePid(4);//find actual measurements
//			ActuatorConfig.getInstance().getDrivetrain().turnRight(.8, 90);
//			ActuatorConfig.getInstance().getDrivetrain().movePid(1);
//			//Drop the power cube in switch
//			ActuatorConfig.getInstance().getDrivetrain().movePid(-1);
//			ActuatorConfig.getInstance().getDrivetrain().turnLeft(.8, 90);
//			ActuatorConfig.getInstance().getDrivetrain().movePid(1);
//			ActuatorConfig.getInstance().getDrivetrain().turnRight(.8, 90);
//			//Pick up power cube from right past switch
//			ActuatorConfig.getInstance().getDrivetrain().movePid(-1);
//			ActuatorConfig.getInstance().getDrivetrain().turnLeft(.8, 90);
//			ActuatorConfig.getInstance().getDrivetrain().movePid(3);
//			ActuatorConfig.getInstance().getDrivetrain().turnRight(.8, 90);
//			//Raise elevator
//			ActuatorConfig.getInstance().getDrivetrain().movePid(1);
//			//Drop off power cube in Scale
//			ActuatorConfig.getInstance().getDrivetrain().movePid(-1);
//			//Lower elevator
//			ActuatorConfig.getInstance().getDrivetrain().turnRight(.8, 90);
			System.out.println("Postion left: Going for both the switch and scale (wish me luck ^-^)");
		}
		else if ("RLR".equals(this.gameData))//Scale only
		{
//			ActuatorConfig.getInstance().getDrivetrain().movePid(7);
//			ActuatorConfig.getInstance().getDrivetrain().turnRight(.8, 90);
//			//Raise elevator
//			ActuatorConfig.getInstance().getDrivetrain().movePid(1);
//			//Drop off power cube in Scale
//			ActuatorConfig.getInstance().getDrivetrain().movePid(-1);
//			//Lower elevator
//			System.out.println("Postion left: Going for scale");
		}
		else if ("RRR".equals(this.gameData))
		{
//			ActuatorConfig.getInstance().getDrivetrain().movePid(5);
//			ActuatorConfig.getInstance().getDrivetrain().turnRight(.8,90);
//			ActuatorConfig.getInstance().getDrivetrain().movePid(3);
//			System.out.println("Position left: Parking past switch");
		}
		else
		{
//			ActuatorConfig.getInstance().getDrivetrain().movePid(4);	
			System.out.println("Hannah, ya done messed up, fix your code.");
		}
	}

	protected void center() //Perhaps make a failsafe here if this becomes a problem.
	{
	System.out.println("Nothing here to see here o_o -Hannah");	
	}

	protected void right() {
		if("RLR".equals(this.gameData)) //Switch only
		{
		
//		ActuatorConfig.getInstance().getDrivetrain().movePid(4);//find actual measurements
//		ActuatorConfig.getInstance().getDrivetrain().turnLeft(.8, 90);
//		ActuatorConfig.getInstance().getDrivetrain().movePid(1);
//		//Drop the power cube
//		ActuatorConfig.getInstance().getDrivetrain().movePid(-1);
		System.out.println("Position right: Going for the switch");
	}
		else if ("RRR".equals(this.gameData)) //Both Switch and Scale
		{
//			ActuatorConfig.getInstance().getDrivetrain().movePid(4);//find actual measurements
//			ActuatorConfig.getInstance().getDrivetrain().turnLeft(.8, 90);
//			ActuatorConfig.getInstance().getDrivetrain().movePid(1);
//			//Drop the power cube in switch
//			ActuatorConfig.getInstance().getDrivetrain().movePid(-1);
//			ActuatorConfig.getInstance().getDrivetrain().turnRight(.8, 90);
//			ActuatorConfig.getInstance().getDrivetrain().movePid(1);
//			ActuatorConfig.getInstance().getDrivetrain().turnLeft(.8, 90);
//			//Pick up power cube from right past switch
//			ActuatorConfig.getInstance().getDrivetrain().movePid(-1);
//			ActuatorConfig.getInstance().getDrivetrain().turnRight(.8, 90);
//			ActuatorConfig.getInstance().getDrivetrain().movePid(3);
//			ActuatorConfig.getInstance().getDrivetrain().turnLeft(.8, 90);
//			//Raise elevator
//			ActuatorConfig.getInstance().getDrivetrain().movePid(1);
//			//Drop off power cube in Scale
//			ActuatorConfig.getInstance().getDrivetrain().movePid(-1);
//			//Lower elevator
//			ActuatorConfig.getInstance().getDrivetrain().turnLeft(.8, 90);
//			System.out.println("Position right: Going for both the switch and scale (wish me luck ^-^)");
		}
		else if ("LRL".equals(this.gameData))//Scale only
		{
//			ActuatorConfig.getInstance().getDrivetrain().movePid(7);
//			ActuatorConfig.getInstance().getDrivetrain().turnLeft(.8, 90);
//			//Raise elevator
//			ActuatorConfig.getInstance().getDrivetrain().movePid(1);
//			//Drop off power cube in Scale
//			ActuatorConfig.getInstance().getDrivetrain().movePid(-1);
//			//Lower elevator
//			System.out.println("Position right: Going for scale");
		}
		else if ("LLL".equals(this.gameData))
		{
//			ActuatorConfig.getInstance().getDrivetrain().movePid(5);
//			ActuatorConfig.getInstance().getDrivetrain().turnLeft(.8,90);
//			ActuatorConfig.getInstance().getDrivetrain().movePid(3);
			System.out.println("Position right: Parking past switch");
		}
		else
		{
//			ActuatorConfig.getInstance().getDrivetrain().movePid(4);	
			System.out.println("Hannah, ya done messed up, fix your code.");
		}
		
	}

}


