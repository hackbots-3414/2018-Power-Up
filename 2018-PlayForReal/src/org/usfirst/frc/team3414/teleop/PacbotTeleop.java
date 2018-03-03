package org.usfirst.frc.team3414.teleop;

import org.usfirst.frc.team3414.actuators.ActuatorConfig;
import org.usfirst.frc.team3414.actuators.Drivetrain;
import org.usfirst.frc.team3414.autonomous.AutonStatus;
import org.usfirst.frc.team3414.sensor.ClockTimer;
import org.usfirst.frc.team3414.sensor.Gamepad;
import org.usfirst.frc.team3414.sensor.HBJoystick;
import org.usfirst.frc.team3414.sensor.IGamepad;
import org.usfirst.frc.team3414.sensor.SensorConfig;
import org.usfirst.frc.team3414.util.Status;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	
	public class DriveThread implements Runnable 
	{
		public void run()
		{
			while(isRunning)
			{
				drivetrain.setSpeed(-leftJoystick.getY(), leftJoystick.getY());
//			
				
				SmartDashboard.putNumber("Left Encoder - Teleop", ActuatorConfig.getInstance().getLeftEncoder().getSensorCollection().getQuadraturePosition()) ;//* (-0.000122));//
//			SmartDashboard.putNumber("Right Encoder - Teleop", ActuatorConfig.getInstance().getRightEncoder().getSensorCollection().getQuadraturePosition());// * (0.000122));
////			System.out.println("Left Encoder Value" + ActuatorConfig.getInstance().getLeftEncoder().getSensorCollection().getQuadraturePosition());
////			System.out.println("Right Encoder Value"  + ActuatorConfig.getInstance().getRightEncoder().getSensorCollection().getQuadraturePosition());
//
////			if(leftJoystick.getRawButton(1)) 
////			{
////				double rightYJoystick = rightJoystick.getY();
////				double leftYJoystick = leftJoystick.getY();
////				double RtargetVelocity_UnitsPer100ms = rightYJoystick * 4096 * 500.0 / 600;
////				double LtargetVelocity_UnitsPer100ms = rightYJoystick * 4096 * 500.0 / 600;
////				
////				ActuatorConfig.getInstance().getRightTalonOne().set(ControlMode.Velocity, RtargetVelocity_UnitsPer100ms);
////				ActuatorConfig.getInstance().getLeftTalonOne().set(ControlMode.Velocity, LtargetVelocity_UnitsPer100ms);
////				ActuatorConfig.getInstance().getRightTalonOne().getSelectedSensorVelocity(ActuatorConfig.kPIDLoopIdx);
////				ActuatorConfig.getInstance().getLeftTalonOne().getSelectedSensorVelocity(ActuatorConfig.kPIDLoopIdx);
////				
////			}
////			else 
//			if (leftJoystick.getY() > 0.15 || rightJoystick.getY() > 0.15 || leftJoystick.getY() < -0.20 || rightJoystick.getY() < -0.1)
//			{
//				startYaw = SensorConfig.getInstance().getNavX().getRawYaw();
//				double leftCorrect = 0;
//				double rightCorrect = 0;
//				
//				if (endYaw > (startYaw)) 
//				{
//					System.out.println("Veering Right Telop");
//					rightCorrect = 0.2;
//				}
//				else if (endYaw < (startYaw)) 
//				{	
//					System.out.println("Veering Left Telop");
//					leftCorrect = 0.2;
//				}
//				else
//				{
//					leftCorrect = 0;
//					rightCorrect = 0;
//				}
//				drivetrain.setSpeed((leftJoystick.getYAxis()) + leftCorrect, (-rightJoystick.getYAxis()) + rightCorrect);
//				endYaw = SensorConfig.getInstance().getNavX().getRawYaw();
//				
//				SmartDashboard.putNumber("Statrt Yaw Tle", startYaw);
//				SmartDashboard.putNumber("End Yaw Tle", endYaw);
//			}
//			else if((leftJoystick.getY() > 0.15 && rightJoystick.getY() < -0.15) || (leftJoystick.getY() < -0.20 && rightJoystick.getY() > 0.1))
//			{
//				if(rightJoystick.isReversed() && leftJoystick.isReversed())
//				{
//					drivetrain.setSpeed((leftJoystick.getYAxis() / 2), (-rightJoystick.getYAxis() / 2));
//				}
//				
//				else
//				{
//					drivetrain.setSpeed((leftJoystick.getYAxis() / 2), (-rightJoystick.getYAxis() / 2));
//				}
//			}
//			
//			else
//			{
//				drivetrain.setSpeed(0);
//			}
//			
//			try 
//			{
//				Thread.sleep(1);
//			} 
//			catch (InterruptedException e) 
//			{
//				e.printStackTrace();
//			}
//			
			
			if (gamepad.getButtonState(1)) 
			{
				ActuatorConfig.getInstance().motorIntakeAngler().setSpeed(.55);
				System.out.println("Angler: " + ActuatorConfig.getInstance().talonIntakeAngler().getSensorCollection().getPulseWidthPosition());

			}
			
			else if (gamepad.getButtonState(2)) 
			{
				ActuatorConfig.getInstance().motorIntakeAngler().setSpeed(-.55);
				System.out.println("Angler: " + ActuatorConfig.getInstance().talonIntakeAngler().getSensorCollection().getPulseWidthPosition());
			}
			
			else
			{
				ActuatorConfig.getInstance().motorIntakeAngler().setSpeed(0);
			}
			
			if (gamepad.getButtonState(3)) 
			{
				//out
				ActuatorConfig.getInstance().motorIntakeOne().setSpeed(0.50);
				ActuatorConfig.getInstance().motorIntakeTwo().setSpeed(0.50);
			}
			
			else if (gamepad.getButtonState(4)) 
			{
				//in
				ActuatorConfig.getInstance().motorIntakeOne().setSpeed(-.90);
				ActuatorConfig.getInstance().motorIntakeTwo().setSpeed(-.225);
			}
			
			else
			{
				ActuatorConfig.getInstance().motorIntakeOne().setSpeed(0);
				ActuatorConfig.getInstance().motorIntakeTwo().setSpeed(0);
			}
			
			if(gamepad.getButtonState(5) && 
//					(ActuatorConfig.getInstance().getLiftLimitSwitch().getSensorCollection().getQuadraturePosition() < 21470))
//					&& 
			!(ActuatorConfig.getInstance().getLiftLimitSwitch().getSensorCollection().isFwdLimitSwitchClosed()))
//					 || 
//top
			{
				ActuatorConfig.getInstance().getLift().setSpeed(-.30);
				System.out.println("Lift: "+ ActuatorConfig.getInstance().getLiftLimitSwitch().getSensorCollection().getPulseWidthPosition());
			}
			
			else if (gamepad.getButtonState(7) && 
//					(ActuatorConfig.getInstance().getLiftLimitSwitch().getSensorCollection().getQuadraturePosition() > -10061)) 
			!(ActuatorConfig.getInstance().getLiftLimitSwitch().getSensorCollection().isRevLimitSwitchClosed()))
//bottom

			{
				ActuatorConfig.getInstance().getLift().setSpeed(.30);
				System.out.println("Lift: "+ ActuatorConfig.getInstance().getLiftLimitSwitch().getSensorCollection().getPulseWidthPosition());
			}
		
			else 
			{
				ActuatorConfig.getInstance().getLift().setSpeed(0);
			}
			
			if (gamepad.getButtonState(10) && gamepad.getButtonState(9))
			{
				ActuatorConfig.getInstance().doubleMotorWings().setSpeed(-.80);
			}
			
			else if (gamepad.getButtonState(10))
			{
				ActuatorConfig.getInstance().doubleMotorWings().setSpeed(.80);
			}
			
			else
			{
				ActuatorConfig.getInstance().doubleMotorWings().setSpeed(0);
			}
				
				
			if (gamepad.getButtonState(9)) 
			{
//				ActuatorConfig.getInstance().servoWingOne().set(.5);
//				ActuatorConfig.getInstance().servoWingOne().set(.5);
//				ActuatorConfig.getInstance().servoWingOne().setAngle(90);
//				ActuatorConfig.getInstance().servoWingTwo().setAngle(90);
				ActuatorConfig.getInstance().servoWingOne().engage();
				ActuatorConfig.getInstance().servoWingTwo().engage();
//				System.out.println(ActuatorConfig.getInstance().servoWingTwo().get());
			}
			else
			{
//				ActuatorConfig.getInstance().servoWingOne().set(0);
//				ActuatorConfig.getInstance().servoWingOne().set(0);
//				ActuatorConfig.getInstance().servoWingOne().setAngle(0);
//				ActuatorConfig.getInstance().servoWingTwo().setAngle(0);
				ActuatorConfig.getInstance().servoWingOne().disengage();
				ActuatorConfig.getInstance().servoWingTwo().disengage();
			}
			
			
			}
		}
	}	
}
