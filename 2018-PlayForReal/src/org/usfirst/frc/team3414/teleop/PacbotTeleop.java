package org.usfirst.frc.team3414.teleop;

import org.usfirst.frc.team3414.sensor.IGamepad;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Encoder;

import org.usfirst.frc.team3414.autonomous.AutonStatus;
import org.usfirst.frc.team3414.sensor.ClockTimer;
import org.usfirst.frc.team3414.util.Status;

import com.ctre.phoenix.motorcontrol.ControlMode;

import org.usfirst.frc.team3414.sensor.SensorConfig;
import org.usfirst.frc.team3414.actuators.ActuatorConfig;
import org.usfirst.frc.team3414.actuators.Drivetrain;
import org.usfirst.frc.team3414.sensor.Gamepad;
import org.usfirst.frc.team3414.sensor.IGamepad;
import org.usfirst.frc.team3414.sensor.HBJoystick;


public class PacbotTeleop implements ITeleop{
	
	
	private HBJoystick rightJoystick;
	private HBJoystick leftJoystick;
	
	private IGamepad gamepad;
	
	private Thread driveThread;
	
	private boolean isRunning;
	
	private Drivetrain drivetrain = ActuatorConfig.getInstance().getDrivetrain();
	
	private double startYaw;
	private double endYaw;
	
	private PowerDistributionPanel pdb;
	
	public void init() {
		
		rightJoystick = new HBJoystick(0);
		leftJoystick = new HBJoystick(1);
		
		AutonStatus.getInstance().setStatus(Status.CANCELED);
		System.out.println("Killing Auton! -----------------------------------------------------");
		System.out.println("Interrupting Timer: " + ClockTimer.getInstance().interrupt());
		isRunning = false;

		gamepad = new Gamepad(2);

		driveThread = new Thread(new DriveThread());
		
		driveThread.start();
		
		isRunning = true;
	}
	
	public void stop() 
	{
		if (isRunning)
		{
			isRunning = false;
		}
	}
	
	public class DriveThread implements Runnable {

		public void run()
		{
			while(isRunning) {
				
			SmartDashboard.putNumber("Left Encoder - Teleop", ActuatorConfig.getInstance().getLeftEncoder().getSensorCollection().getQuadraturePosition() * (-0.000122));//
			SmartDashboard.putNumber("Right Encoder - Teleop", ActuatorConfig.getInstance().getRightEncoder().getSensorCollection().getQuadraturePosition() * (0.000122));
//			System.out.println("Left Encoder Value" + ActuatorConfig.getInstance().getLeftEncoder().getSensorCollection().getQuadraturePosition());
//			System.out.println("Right Encoder Value"  + ActuatorConfig.getInstance().getRightEncoder().getSensorCollection().getQuadraturePosition());
			if(leftJoystick.getRawButton(1)) 
			{
				double rightYJoystick = rightJoystick.getY();
				double leftYJoystick = leftJoystick.getY();
				double RtargetVelocity_UnitsPer100ms = rightYJoystick * 4096 * 500.0 / 600;
				double LtargetVelocity_UnitsPer100ms = rightYJoystick * 4096 * 500.0 / 600;
				
				ActuatorConfig.getInstance().getRightTalonOne().set(ControlMode.Velocity, RtargetVelocity_UnitsPer100ms);
				ActuatorConfig.getInstance().getLeftTalonOne().set(ControlMode.Velocity, LtargetVelocity_UnitsPer100ms);
				ActuatorConfig.getInstance().getRightTalonOne().getSelectedSensorVelocity(ActuatorConfig.kPIDLoopIdx);
				ActuatorConfig.getInstance().getLeftTalonOne().getSelectedSensorVelocity(ActuatorConfig.kPIDLoopIdx);
				
			}
			
			else if (leftJoystick.getY() > 0.15 || rightJoystick.getY() > 0.15 || leftJoystick.getY() < -0.20 || rightJoystick.getY() < -0.1)
			{
				startYaw = SensorConfig.getInstance().getNavX().getRawYaw();
				double leftCorrect = 0;
				double rightCorrect = 0;
				
				if (endYaw > (startYaw)) 
				{
				
					//drivetrain.setSpeed((leftJoystick.getYAxis() / 2), (rightJoystick.getYAxis() / 2) + 0.2);//Add Gyro 
					System.out.println("Veering Right Telop");
					rightCorrect = 0.2;
				}
				else if (endYaw < (startYaw)) 
				{	
					//drivetrain.setSpeed((leftJoystick.getYAxis() / 2) + 0.2, (rightJoystick.getYAxis() / 2));//Add Gyro 
					System.out.println("Veering Left Telop");
					leftCorrect = 0.2;
				}
				else
				{
					leftCorrect = 0;
					rightCorrect = 0;
				}
				
			//	drivetrain.setSpeed((leftJoystick.getYAxis() / 2) + leftCorrect, (rightJoystick.getYAxis() / 2) + rightCorrect);//Add Gyro 
				drivetrain.setSpeed((leftJoystick.getYAxis()) + leftCorrect, (rightJoystick.getYAxis()) + rightCorrect);
				endYaw = SensorConfig.getInstance().getNavX().getRawYaw();
				
				SmartDashboard.putNumber("Statrt Yaw Tle", startYaw);
				SmartDashboard.putNumber("End Yaw Tle", endYaw);
			}
			else if((leftJoystick.getY() > 0.15 && rightJoystick.getY() < -0.15) || (leftJoystick.getY() < -0.20 && rightJoystick.getY() > 0.1))
			{
				if(rightJoystick.isReversed() && leftJoystick.isReversed())
				{
					drivetrain.setSpeed((leftJoystick.getYAxis() / 2), (rightJoystick.getYAxis() / 2));
					//drivetrain.setSpeed((leftJoystick.getYAxis()), (rightJoystick.getYAxis()));
				}
				
				else
				{
					drivetrain.setSpeed((leftJoystick.getYAxis() / 2), (rightJoystick.getYAxis() / 2));
					//drivetrain.setSpeed((leftJoystick.getYAxis()), (rightJoystick.getYAxis()));
				}
			}
			
			else
			{
				drivetrain.setSpeed(0);
			}
			
			try 
			{
				Thread.sleep(1);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			
//			if (gamepad.getButtonState(6) && 
//			(ActuatorConfig.getInstance().getLiftLimitSwitch().getSensorCollection().isRevLimitSwitchClosed()
//			 || ActuatorConfig.getInstance().getLiftLimitSwitch().getSensorCollection().getQuadraturePosition() < 5000)) 
//			{
//				ActuatorConfig.getInstance().getLift().setSpeed(1);
//			}
//			else if(gamepad.getButtonState(5)  && 
//			(ActuatorConfig.getInstance().getLiftLimitSwitch().getSensorCollection().isFwdLimitSwitchClosed()
//					 || ActuatorConfig.getInstance().getLiftLimitSwitch().getSensorCollection().getQuadraturePosition() > 0)) 
//			{
//				ActuatorConfig.getInstance().getLift().setSpeed(-1);
//			}
//			else 
//			{
//				ActuatorConfig.getInstance().getLift().setSpeed(0);
//			}
		}
	}
}
	
	
}
