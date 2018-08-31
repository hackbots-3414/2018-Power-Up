package org.usfirst.frc.team3414.actuators;

import java.awt.Robot;
import java.util.concurrent.TimeUnit;

import org.usfirst.frc.team3414.actuators.ActuatorConfig;
import org.usfirst.frc.team3414.actuators.DoubleMotor;
//import org.usfirst.frc.team3414.actuators.TripleMotor;
import org.usfirst.frc.team3414.actuators.SpeedPlan;
import org.usfirst.frc.team3414.autonomous.AutonStatus;
import org.usfirst.frc.team3414.sensor.HBJoystick;
import org.usfirst.frc.team3414.sensor.NavX;
import org.usfirst.frc.team3414.sensor.SensorConfig;
import org.usfirst.frc.team3414.util.Status;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import org.usfirst.frc.team3414.robot.RobotStatus;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drivetrain implements IDriveTrain {
	
	private DoubleMotor rightMotor;
	private DoubleMotor leftMotor;
	
	

//???  3 motors, or triple motors	
//	private TripleMotor rightMotor;
//	private TripleMotor leftMotor;
	
	double currentHeading;
	
	private HBJoystick rightJoystick;
	private HBJoystick leftJoystick;
	
	private double startYaw;
	private double endYaw;
	
	private boolean isSwitched = false;
	
	public boolean turnRadiusCancel = false;
	
	public SpeedPlan turnRadiusSpeedPlan = new SpeedPlan();
	public SpeedPlan moveStraightSpeedPlan = new SpeedPlan();
	public SpeedPlan liftSpeedPlan = new SpeedPlan();

	public Drivetrain(DoubleMotor rightMotor, DoubleMotor leftMotor)
	{
		this.rightMotor = rightMotor;
		this.leftMotor = leftMotor;
	}

//  3 motors, or triple motors	
//	public Drivetrain(TripleMotor rightMotor, TripleMotor leftMotor)
//	{
//		this.rightMotor = rightMotor;
//		this.leftMotor = leftMotor;
//	}

	public void setSpeed(double speed)
	{
		rightMotor.setSpeed(speed);
		leftMotor.setSpeed(speed);
	}

	public void setSpeed(double leftSpeed, double rightSpeed)
	{
		leftMotor.setSpeed(leftSpeed);
		rightMotor.setSpeed(rightSpeed);
	}

	public void stop()
	{
		leftMotor.stop();
		rightMotor.stop();
	}
	
	public void setInitialServoPosition()
	{
		//The wings were removed from Alpha
		if(ActuatorConfig.getInstance().getWingDisabled())
			return;

		ActuatorConfig.getInstance().getServoWingOne().disengage();
		ActuatorConfig.getInstance().getServoWingTwo().setAngle(130);
	}

	public DoubleMotor getRightMotor()
	{
		return rightMotor;
	}

	public DoubleMotor getLeftMotor()
	{
		return leftMotor;
	}

/*	
//  3 motors, or triple motors	
	public TripleMotor getRightMotor()
	{
		return rightMotor;
	}
	
	public TripleMotor getLeftMotor()
	{
		return leftMotor;
	}
*/
	public void liftToScale()
	{
		int encoderPos = ActuatorConfig.getInstance().getLiftTalonTwo().getSensorCollection().getQuadraturePosition();
		while (encoderPos < 27000 && RobotStatus.isAuto())
		{
			ActuatorConfig.getInstance().getLift().setSpeed(-.70);//40
			encoderPos = ActuatorConfig.getInstance().getLiftTalonTwo().getSensorCollection().getQuadraturePosition();
			SmartDashboard.putNumber("Elevator Encoder", encoderPos);
		}
//		ActuatorConfig.getInstance().getLiftTalonTwo().set(ControlMode.MotionMagic, 27000);
		ActuatorConfig.getInstance().getLift().setSpeed(0);
	}
	public void liftToScaleAutonPhillip()
	{
		int encoderPos = ActuatorConfig.getInstance().getLiftTalonTwo().getSensorCollection().getQuadraturePosition();
		double encoderStart = encoderPos;
		double distance = 18500-encoderStart;
		double currentSpeed = 0.5;
		double currentPosition = 0;
		while (encoderPos < 18500 /*&& RobotStatus.isAuto()*/)
		{
			currentSpeed = liftSpeedPlan.getSpeed(distance, 0.7, encoderPos - encoderStart);
			//ActuatorConfig.getInstance().getLift().setSpeed(-.70);//40
			ActuatorConfig.getInstance().getLift().setSpeed(-.70);//40

			
			encoderPos = ActuatorConfig.getInstance().getLiftTalonTwo().getSensorCollection().getQuadraturePosition();
			SmartDashboard.putNumber("Elevator Encoder", encoderPos);
		}
//		ActuatorConfig.getInstance().getLiftTalonTwo().set(ControlMode.MotionMagic, 27000);
	}
	
	public void liftToSwitch()
	{
		int encoderPos = ActuatorConfig.getInstance().getLiftTalonTwo().getSensorCollection().getQuadraturePosition();
		while (encoderPos < 9000 && RobotStatus.isAuto())
		{
			ActuatorConfig.getInstance().getLift().setSpeed(-.70);
			encoderPos = ActuatorConfig.getInstance().getLiftTalonTwo().getSensorCollection().getQuadraturePosition();
			SmartDashboard.putNumber("Elevator Encoder", encoderPos);
		}
		ActuatorConfig.getInstance().getLift().setSpeed(0);
	}
	
	public void liftTo(double distance)
	{
		int encoderPos = ActuatorConfig.getInstance().getLiftTalonTwo().getSensorCollection().getQuadraturePosition();
		while (encoderPos < distance)
		{
			ActuatorConfig.getInstance().getLift().setSpeed(-.70);//40, 55
			encoderPos = ActuatorConfig.getInstance().getLiftTalonTwo().getSensorCollection().getQuadraturePosition();
			SmartDashboard.putNumber("Elevator Encoder", encoderPos);
		}
		ActuatorConfig.getInstance().getLift().setSpeed(0);
	}
	
	public void lowerLiftTo(double distance)
	{
		int encoderPos = ActuatorConfig.getInstance().getLiftTalonTwo().getSensorCollection().getQuadraturePosition();
		while (encoderPos> distance)
		{
			ActuatorConfig.getInstance().getLift().setSpeed(.50);//40, 55
			encoderPos = ActuatorConfig.getInstance().getLiftTalonTwo().getSensorCollection().getQuadraturePosition();
			SmartDashboard.putNumber("Elevator Encoder", encoderPos);
		}
		ActuatorConfig.getInstance().getLift().setSpeed(0);
	}
	
	public void liftToScaleTeleop()
	{			ActuatorConfig.getInstance().getLift().setSpeed(-.70);//4
			SensorConfig.getInstance().getTimer().waitTimeInMillis(1000);
			ActuatorConfig.getInstance().getLift().setSpeed(0);
	}
		/*
	}
		int encoderPos = ActuatorConfig.getInstance().getLiftTalonTwo().getSensorCollection().getQuadraturePosition();
		while (encoderPos < 27000 && RobotStatus.isTeleop())
		{
			ActuatorConfig.getInstance().getLift().setSpeed(-.70);//40
			encoderPos = ActuatorConfig.getInstance().getLiftTalonTwo().getSensorCollection().getQuadraturePosition();
			SmartDashboard.putNumber("Elevator Encoder", encoderPos);
		}
//		ActuatorConfig.getInstance().getLiftTalonTwo().set(ControlMode.MotionMagic, 27000);
		ActuatorConfig.getInstance().getLift().setSpeed(0);
	}*/
	
	public void liftToSwitchTeleop()
	{
		int encoderPos = ActuatorConfig.getInstance().getLiftTalonTwo().getSensorCollection().getQuadraturePosition();
		while (encoderPos < 10000 && RobotStatus.isTeleop())
		{
			ActuatorConfig.getInstance().getLift().setSpeed(-.60);
			encoderPos = ActuatorConfig.getInstance().getLiftTalonTwo().getSensorCollection().getQuadraturePosition();
			SmartDashboard.putNumber("Elevator Encoder", encoderPos);
		}
		ActuatorConfig.getInstance().getLift().setSpeed(0);
	}
	
	public void lowerAnglerSwitch()
	{
		int encoderPos = ActuatorConfig.getInstance().talonIntakeAngler().getSensorCollection().getQuadraturePosition();
		while (encoderPos > -1000 && RobotStatus.isAuto())
		{
			ActuatorConfig.getInstance().getMotorIntakeAngler().setSpeed(-.40);
			encoderPos = ActuatorConfig.getInstance().talonIntakeAngler().getSensorCollection().getQuadraturePosition();
			SmartDashboard.putNumber("Angler Encoder", encoderPos);
		}
		ActuatorConfig.getInstance().getMotorIntakeAngler().setSpeed(0);
	}
	
	public void lowerAnglerScale()
	{
		int encoderPos = ActuatorConfig.getInstance().talonIntakeAngler().getSensorCollection().getQuadraturePosition();
		while (encoderPos > -500 && RobotStatus.isAuto())
		{
			ActuatorConfig.getInstance().getMotorIntakeAngler().setSpeed(-.40);
			encoderPos = ActuatorConfig.getInstance().talonIntakeAngler().getSensorCollection().getQuadraturePosition();
			//System.out.println(encoderPos);
		}
		ActuatorConfig.getInstance().getMotorIntakeAngler().setSpeed(0);
	}
	
	public void lowerAnglerTo(double distance)
	{
		int encoderPos = ActuatorConfig.getInstance().talonIntakeAngler().getSensorCollection().getQuadraturePosition();
		System.out.println("encoderPos: " + encoderPos + "  distance: " + distance);
		while (encoderPos > -distance)
		{
			ActuatorConfig.getInstance().getMotorIntakeAngler().setSpeed(-.40);
			encoderPos = ActuatorConfig.getInstance().talonIntakeAngler().getSensorCollection().getQuadraturePosition();
			SmartDashboard.putNumber("Angler Encoder", encoderPos);
		}
		ActuatorConfig.getInstance().getMotorIntakeAngler().setSpeed(0);
	}
	
	public void raiseAnglerTo(double distance)
	{
		int encoderPos = ActuatorConfig.getInstance().talonIntakeAngler().getSensorCollection().getQuadraturePosition();
		int startPos = encoderPos;
		//while (encoderPos < -distance)
		while (encoderPos < startPos-distance)
		{
			ActuatorConfig.getInstance().getMotorIntakeAngler().setSpeed(.40);
			encoderPos = ActuatorConfig.getInstance().talonIntakeAngler().getSensorCollection().getQuadraturePosition();
			SmartDashboard.putNumber("Angler Encoder", encoderPos);
		}
		ActuatorConfig.getInstance().getMotorIntakeAngler().setSpeed(0);
	}

	public void turnRightRadius(double speed, double angle, double radius, double width)
	{
		turnRadius( speed,  angle,  radius,  width);
	}
	
	public void turnLeftRadius(double speed, double angle, double radius, double width)
	{
		turnRadius( speed,  -angle,  radius,  width);
	}
	
	public void turnRadius(double speed, double angle, double radius, double width)
	{
		rightJoystick = new HBJoystick(0);
		leftJoystick = new HBJoystick(1);

		isSwitched = false;
		
		NavX navX = SensorConfig.getInstance().getNavX();
		navX.resetLastRawYaw();
		double InnerSpeed = speed*((radius-width/2)/(radius+width/2));
		double currentYaw = navX.getTrueYaw();
		double endAngle = currentYaw + angle;
		System.out.println("+------------------------------+");
		System.out.println("RightRadius: speed =" + speed + "; angle=" + angle);
		System.out.println("Start Angle: " + currentYaw);
		System.out.println("End Angle: " + endAngle);
		System.out.println("InnerSpeed: " + InnerSpeed);
		if(angle > 0) // up right
			ActuatorConfig.getInstance().getDrivetrain().setSpeed(speed, InnerSpeed);
		else // up left
			ActuatorConfig.getInstance().getDrivetrain().setSpeed(-InnerSpeed, -speed);
		
		// down right
		//  ActuatorConfig.getInstance().getDrivetrain().setSpeed(InnerSpeed, speed);
		// down left
		//	ActuatorConfig.getInstance().getDrivetrain().setSpeed(-speed, -InnerSpeed);

		//???		ActuatorConfig.getInstance().getDrivetrain().setSpeed(speed/2, -speed/2);

double lastYaw = currentYaw;
double startYaw = currentYaw;
double currentSpeed = 0;
double turnedAngle = 0;
			if(Math.abs(angle) <= 1)
			{
				System.out.println("Hard to rotate such a small angle: " + angle);
				return;
			}
			while ((angle > 0 && currentYaw < endAngle)|| 
			(angle < 0 && currentYaw > endAngle))
			{
				currentYaw = navX.getTrueYaw();
				turnedAngle = Math.abs(currentYaw - startYaw);
				currentSpeed = turnRadiusSpeedPlan.getSpeed(Math.abs(angle), speed, turnedAngle);
				InnerSpeed = currentSpeed*((radius-width/2)/(radius+width/2));
				if(angle > 0)
					ActuatorConfig.getInstance().getDrivetrain().setSpeed(currentSpeed, InnerSpeed);
				else 
					ActuatorConfig.getInstance().getDrivetrain().setSpeed(-currentSpeed, -InnerSpeed);

				//if (Math.abs(currentYaw - lastYaw) > 10) //NavX has a bug: Yaw values suddenly change signs
				//	currentYaw = -currentYaw;
				//if(lastYaw < currentYaw - 0.5 || lastYaw > currentYaw + 0.5)
				//	   System.out.println("current Angle: " + currentYaw);
				if (RobotStatus.isAuto() && (AutonStatus.getInstance().getStatus() == Status.CANCELED))
				{
					break;
				}
				if (RobotStatus.isTeleop() && turnRadiusCancel)
				{
					break;
				}
				lastYaw = currentYaw;

			}
//		}
		System.out.println("After turning: " + currentYaw);
		ActuatorConfig.getInstance().getDrivetrain().stop();
		currentYaw = navX.getTrueYaw();
		System.out.println("After stopped angle: " + currentYaw);
		System.out.println("--------------------");

	}

	public void turnRight(double speed, double angle)
	{
		rightJoystick = new HBJoystick(0);
		leftJoystick = new HBJoystick(1);

		isSwitched = false;

		NavX navX = SensorConfig.getInstance().getNavX();

		float currentYaw = navX.getYaw();
		float endAngle = currentYaw + (float) angle;
	//	System.out.println("Start Angle: " + currentYaw);
	//	System.out.println("End Angle: " + endAngle);
		ActuatorConfig.getInstance().getDrivetrain().setSpeed(speed, -speed);


		if (endAngle > 360)
		{
			endAngle = endAngle - 360;
			System.out.println("Adjusted End Angle: " + endAngle);
			// Turn right until passing 0
			while ((currentYaw + angle) > 360)
			{
				currentYaw = navX.getYaw();
				if (RobotStatus.isTeleop() && (AutonStatus.getInstance().getStatus() == Status.CANCELED))
				{
					break;
				}
			}
			// Continue turning right until target is reached
			while (currentYaw < endAngle)
			{
				currentYaw = navX.getYaw();
				if (RobotStatus.isTeleop() && (AutonStatus.getInstance().getStatus() == Status.CANCELED))
				{
					break;
				}
			}
		} else
		{
			while (currentYaw < endAngle)
			{
				currentYaw = navX.getYaw();
				if (RobotStatus.isTeleop() && (AutonStatus.getInstance().getStatus() == Status.CANCELED))
				{
					break;
				}
			}
		}
		ActuatorConfig.getInstance().getDrivetrain().stop();
	}

	public void turnLeft(double speed, double angle)
	{
		rightJoystick = new HBJoystick(0);
		leftJoystick = new HBJoystick(1);

		isSwitched = false;

		NavX navX = SensorConfig.getInstance().getNavX();

		float currentYaw = navX.getYaw();
		float endAngle = currentYaw - (float) angle;
		System.out.println("Start Angle: " + currentYaw);
		System.out.println("End Angle: " + endAngle);
		ActuatorConfig.getInstance().getDrivetrain().setSpeed(-speed, speed);

		
		if (endAngle < 0)
		{
			endAngle = 360 + endAngle;
			System.out.println("Adjusted End Angle: " + endAngle);
			// Loop until we pass 0
			while ((currentYaw + angle) <= 360)
			{
				currentYaw = navX.getYaw();
				//System.out.println("Current Angle: " + currentYaw);
				if (RobotStatus.isTeleop() && (AutonStatus.getInstance().getStatus() == Status.CANCELED))
				{
					break;
				}
				try
				{
					TimeUnit.MILLISECONDS.sleep(10);
				}
				catch(Exception e)
				{
					
				}
				//SensorConfig.getInstance().getTimer().waitTimeInMillis(10);
			}
			// Now loop until we reach the target angle
			while (currentYaw > endAngle)
			{
				currentYaw = navX.getYaw();
				//System.out.println("Current Angle: " + currentYaw);
				if (RobotStatus.isTeleop() && (AutonStatus.getInstance().getStatus() == Status.CANCELED))
				{
					break;
				}
				try
				{
					TimeUnit.MILLISECONDS.sleep(10);
				}
				catch(Exception e)
				{
					
				}
				//SensorConfig.getInstance().getTimer().waitTimeInMillis(10);
			}
		} else
		{
			while (currentYaw > endAngle)
			{
				currentYaw = navX.getYaw();
				if (RobotStatus.isTeleop() && (AutonStatus.getInstance().getStatus() == Status.CANCELED))
				{
					break;
				}
				try
				{
					TimeUnit.MILLISECONDS.sleep(10);
				}
				catch(Exception e)
				{
					
				}
				//SensorConfig.getInstance().getTimer().waitTimeInMillis(10);
			}
		}
		ActuatorConfig.getInstance().getDrivetrain().stop();
	}


	private void move(double distance, double startSpeed, boolean isReversed)
	{
		if (RobotStatus.isTeleop() && (AutonStatus.getInstance().getStatus() == Status.CANCELED))
		{
			return;
		}

		double rightEncoderValue = Math
				.abs(ActuatorConfig.getInstance().getRightEncoder().getSensorCollection().getQuadraturePosition()
						* (0.000122));
		double leftEncoderValue = Math
				.abs(ActuatorConfig.getInstance().getLeftEncoder().getSensorCollection().getQuadraturePosition()
						* (-0.000122));

		double speed = 0;

		while (true)
		{
			rightEncoderValue = Math
					.abs(ActuatorConfig.getInstance().getRightEncoder().getSensorCollection().getQuadraturePosition()
							* (0.000122));
			leftEncoderValue = Math
					.abs(ActuatorConfig.getInstance().getLeftEncoder().getSensorCollection().getQuadraturePosition()
							* (-0.000122));

			// System.out.println("Forwarding");

			if (RobotStatus.isTeleop() && (AutonStatus.getInstance().getStatus() == Status.CANCELED))
			{
				break;
			}

			currentHeading = SensorConfig.getInstance().getNavX().getRawYaw();

			if (isReversed)
			{
				speed = Math.log(((leftEncoderValue + rightEncoderValue) / 2) + (distance));
			} else
			{
				speed = Math.log(-((leftEncoderValue + rightEncoderValue) / 2) + (distance));// distance
																								// +
																								// 1
			}

			if (speed < 0.10)
			{
				ActuatorConfig.getInstance().getDrivetrain().stop();
				System.err.println("Stopping Drivetrain");
				break;
			} else if (speed > 0.25)
			{
				speed = 0.25;
			}

			if (isReversed)
			{
				speed = speed * -1;
			}

			startYaw = SensorConfig.getInstance().getNavX().getRawYaw();
			double leftCorrect = 0;
			double rightCorrect = 0;

			if (endYaw > (startYaw))
			{

				// drivetrain.setSpeed((leftJoystick.getYAxis() / 2),
				// (rightJoystick.getYAxis() / 2) + 0.2);//Add Gyro
				System.out.println("Veering Right Telop");
				rightCorrect = 0.2;
			} else if (endYaw < (startYaw))
			{
				// drivetrain.setSpeed((leftJoystick.getYAxis() / 2) + 0.2,
				// (rightJoystick.getYAxis() / 2));//Add Gyro
				System.out.println("Veering Left Telop");
				leftCorrect = 0.2;
			} else
			{
				leftCorrect = 0;
				rightCorrect = 0;
			}

			// drivetrain.setSpeed((leftJoystick.getYAxis() / 2) + leftCorrect,
			// (rightJoystick.getYAxis() / 2) + rightCorrect);//Add Gyro
			ActuatorConfig.getInstance().getDrivetrain().setSpeed(speed + leftCorrect, speed + rightCorrect);
			endYaw = SensorConfig.getInstance().getNavX().getRawYaw();
		}
	}

	/**
	 * Go forward number of rotations (1 Rot ~ 4pi ~ 1ft)
	 * 
	 * @param distance
	 */
	public void goForward(double distance, double speed)
	{
		move(distance, speed, false);
	}

	public void goBackward(int distance, double speed)
	{
		move(distance, speed, true);
	}

	// public void moveGyro(double distance, double speed, boolean isReversed)
	// {
	// double startYaw = SensorConfig.getInstance().getNavX().getRawYaw();
	// double leftCorrect = 0;
	// double rightCorrect = 0;
	//
	// double deviationDistance =0 ;
	// double theta = 0;
	//
	// boolean isDone = false;
	//
	// while(RobotStatus.isAuto())
	// {
	//
	// theta = endYaw - startYaw;
	//
	// deviationDistance = Math.sin(Math.abs(theta));
	//
	// if(endYaw > (startYaw + .5))
	// {
	// System.out.println("Veering Right Auton");
	// leftCorrect =+ 0.09; //.1
	// rightCorrect =-0.05;
	// }
	// else if (endYaw < (startYaw - .5))
	// {
	// System.out.println("Veering Left Auton");
	// rightCorrect =+ 0.05; //.1
	// leftCorrect =- 0.09;
	// }
	// else
	// {
	// leftCorrect = 0;
	// rightCorrect = 0;
	// }
	//
	// setSpeed(speed + leftCorrect, speed + rightCorrect);
	// endYaw = SensorConfig.getInstance().getNavX().getRawYaw();
	//
	// SmartDashboard.putNumber("Statrt Yaw Tle", startYaw);
	// SmartDashboard.putNumber("End Yaw Tle", endYaw);
	// SmartDashboard.putNumber("Left Speed",
	// ActuatorConfig.getInstance().getDrivetrain().getLeftMotor().getSpeed());
	// SmartDashboard.putNumber("Right Speed",
	// ActuatorConfig.getInstance().getDrivetrain().getRightMotor().getSpeed());
	//
	// SmartDashboard.putNumber("Deviation Distance", deviationDistance);
	// SmartDashboard.putNumber("Theta", theta);
	// }
	// }

	public void moveGyro(double distance, double angle, double speed,  boolean isReversed)
	{
		NavX navx = SensorConfig.getInstance().getNavX();

		boolean isRightComplete = false;
		boolean isLeftComplete = false;
		double distanceRight;
		double distanceLeft;
		double rightEncoderValue = ActuatorConfig.getInstance().getRightEncoder().getSensorCollection()
				.getQuadraturePosition() / (2048.0);// 8192 is the ppr of the
													// encoder x4
		double leftEncoderValue = ActuatorConfig.getInstance().getLeftEncoder().getSensorCollection()
				.getQuadraturePosition() / (-2048.0);
		double rightEncoderStartValue = rightEncoderValue;
		double leftEncoderStartValue = leftEncoderValue;
		
//		double deaccIdealDistance
//		double 
		
		System.out.println("Left Encoder Start Value: " + leftEncoderValue);
		System.out.println("Right Encoder Start Value: " + rightEncoderValue);

		if (isReversed)
		{
			speed = speed * -1;
			distanceRight = rightEncoderValue - distance;
			distanceLeft = leftEncoderValue - distance;
		} else
		{
			distanceRight = rightEncoderValue + distance;
			distanceLeft = leftEncoderValue + distance;
		}

		double currentYaw;
		double startYaw = navx.getRawYaw();
		double targetYaw = startYaw + angle;
		 SmartDashboard.putNumber("Start Yaw: ", startYaw);
		ActuatorConfig.getInstance().getDrivetrain().setSpeed(speed);
		System.out.println("Start Yaw: " + startYaw);
		System.out.println("Target Yaw: " + targetYaw);
		// SmartDashboard.putNumber("Right Distance To ", distanceRight);
		// SmartDashboard.putNumber("Left Distance To ", distanceLeft);
		// SmartDashboard.putNumber("Start Left Enoder Value ",
		// leftEncoderValue);
		// SmartDashboard.putNumber("Start Right Encoder Value",
		// rightEncoderValue);

		//??? following condition let left and right stop to move at same time,
		//??? any of them complete, then the other one also stops moving.
		//??? we need both completes, i.e., in front of switch, let robot touch switch by whole front frame.
		double movedDistance = 0;
		double currentSpeed = 0;
		//while ((!isRightComplete || !isLeftComplete) && RobotStatus.isAuto() && RobotStatus.isRunning())
		while (!isRightComplete && !isLeftComplete && RobotStatus.isAuto() && RobotStatus.isRunning())
		{

			rightEncoderValue = ActuatorConfig.getInstance().getRightEncoder().getSensorCollection()
					.getQuadraturePosition() / (2048.0);
			leftEncoderValue = ActuatorConfig.getInstance().getLeftEncoder().getSensorCollection()
					.getQuadraturePosition() / (-2048.0);

//			SmartDashboard.putNumber("Left Encoder Value ", leftEncoderValue);
//			SmartDashboard.putNumber("Right Encoder Value", rightEncoderValue);

			movedDistance = Math.abs(rightEncoderValue - rightEncoderStartValue);
			currentSpeed = turnRadiusSpeedPlan.getSpeed(Math.abs(distance), speed, movedDistance);
			if (isReversed)
			{
				currentSpeed = currentSpeed * -1;
			}
			// This Kill Switch will only work once Teleop begins and Joysticks
			// start working
			// This is a safety in case loop cannot complete for some reason
			// while running Auton.
			// To test: Run Auton and hit disable before lop completes. Then
			// start teleop and press kill switch buttons
			/*
			 * if(rightJoystick.getRawButton(1) || leftJoystick.getRawButton(1))
			 * { System.out.println("Kill Switch"); break; }
			 */

			if (isReversed)
			{
				
				if (rightEncoderValue <= distanceRight)
				{
					isRightComplete = true;
					// ActuatorConfig.getInstance().getDrivetrain().getLeftMotor().stop();
					
				}
				if (leftEncoderValue <= distanceLeft)
				{
					isLeftComplete = true;
					// ActuatorConfig.getInstance().getDrivetrain().getLeftMotor().stop();
				}
				currentYaw = navx.getRawYaw();
//				SmartDashboard.putNumber("Current Yaw ", currentYaw);
				if (currentYaw > (targetYaw + 1))
				{
					// Veering left, so slow down right
					// System.out.println("Veering left");
//					ActuatorConfig.getInstance().getDrivetrain().setSpeed(speed, (speed+ .15));
					//ActuatorConfig.getInstance().getDrivetrain().setSpeed(speed, (speed + Math.abs(currentYaw - targetYaw)/30));
					//ActuatorConfig.getInstance().getDrivetrain().setSpeed(currentSpeed, (currentSpeed + Math.abs(currentYaw - targetYaw)/30));
					ActuatorConfig.getInstance().getDrivetrain().setSpeed(currentSpeed, (currentSpeed + Math.abs(currentSpeed)/10));
				}
			 //???else if (currentYaw < (startYaw + 0.5))
				else if (currentYaw < (targetYaw - 1))
				{
					// Veering right, so slow down left
					// System.out.println("Veering right");
//					ActuatorConfig.getInstance().getDrivetrain().setSpeed((speed+ .15), speed);
					//ActuatorConfig.getInstance().getDrivetrain().setSpeed((currentSpeed + Math.abs(currentYaw - targetYaw)/30), currentSpeed);
					ActuatorConfig.getInstance().getDrivetrain().setSpeed((currentSpeed + Math.abs(currentSpeed)/10), currentSpeed);
				}
				else 
					ActuatorConfig.getInstance().getDrivetrain().setSpeed(currentSpeed);
			} else
			{
				if (rightEncoderValue >= distanceRight)
				{
					isRightComplete = true;
					// ActuatorConfig.getInstance().getDrivetrain().getLeftMotor().stop();
					//System.out.println("Left Finished First");
				}
				if (leftEncoderValue >= distanceLeft)
				{
					isLeftComplete = true;
					
				}
				currentYaw = navx.getRawYaw();
//				SmartDashboard.putNumber("Current Yaw ", currentYaw);
				if (currentYaw > (targetYaw + 1))
				{
					// Veering left, so slow down right
					// System.out.println("Veering left");

//					ActuatorConfig.getInstance().getDrivetrain().setSpeed(speed, (speed + .12));// 0.05,
																								// +.16
					//ActuatorConfig.getInstance().getDrivetrain().setSpeed(currentSpeed, (currentSpeed + Math.abs(currentYaw - targetYaw)/30));
					ActuatorConfig.getInstance().getDrivetrain().setSpeed(currentSpeed, (currentSpeed + Math.abs(currentSpeed)/10));

				//???} else if (currentYaw < (startYaw + 1))
			    } else if (currentYaw < (targetYaw - 1))
				{
					// Veering right, so slow down left
					// System.out.println("Veering right");

//					ActuatorConfig.getInstance().getDrivetrain().setSpeed((speed + .12), speed);// 0.05,
																								// +.16
					//ActuatorConfig.getInstance().getDrivetrain().setSpeed((currentSpeed + Math.abs(currentYaw - targetYaw)/30), currentSpeed);
					ActuatorConfig.getInstance().getDrivetrain().setSpeed((currentSpeed + Math.abs(currentSpeed)/10), currentSpeed);
				}
			    else
			    	ActuatorConfig.getInstance().getDrivetrain().setSpeed(currentSpeed, currentSpeed);
			}
		}
		rightEncoderValue = ActuatorConfig.getInstance().getRightEncoder().getSensorCollection()
				.getQuadraturePosition() / (2048.0);
		leftEncoderValue = ActuatorConfig.getInstance().getLeftEncoder().getSensorCollection()
				.getQuadraturePosition() / (-2048.0);
		currentYaw = navx.getRawYaw();
		
		System.out.println("End Yaw: " + currentYaw);
		System.out.println("Left Encoder End Value: " + leftEncoderValue);
		System.out.println("Right Encoder End Value: " + rightEncoderValue);
		
		ActuatorConfig.getInstance().getDrivetrain().stop();
		
		rightEncoderValue = ActuatorConfig.getInstance().getRightEncoder().getSensorCollection()
				.getQuadraturePosition() / (2048.0);
		leftEncoderValue = ActuatorConfig.getInstance().getLeftEncoder().getSensorCollection()
				.getQuadraturePosition() / (-2048.0);
		
	
		System.out.println("Left Encoder Moved Value: " + (leftEncoderValue - leftEncoderStartValue));
		System.out.println("Right Encoder Moved Value: " + (rightEncoderValue- rightEncoderStartValue));
		SensorConfig.getInstance().getTimer().waitTimeInMillis(200); //???
	}

	public void movePid(double distance, double speed)
	{

		// double RtargetVelocity_UnitsPer100ms = -0.35 * 8192 * 500.0 / 600;
		// double LtargetVelocity_UnitsPer100ms = -0.35 * 8192 * 500.0 / 600;

		double finalDistance = ActuatorConfig.getInstance().getLeftTalonFront().getSelectedSensorPosition(0) + distance;
		//

		SmartDashboard.putNumber("Final distance", finalDistance);

		while (ActuatorConfig.getInstance().getLeftTalonFront().getSelectedSensorPosition(0) < finalDistance)
		{
			SmartDashboard.putNumber("Enc distance",
					ActuatorConfig.getInstance().getLeftTalonFront().getSelectedSensorPosition(0));
			// System.out.println(_Ltalon.getSelectedSensorPosition(0));
			/* Speed mode */
			/*
			 * 4096 Units/Rev * 500 RPM / 600 100ms/min in either direction:
			 * velocity setpoint is in units/100ms
			 */
			/* 1500 RPM in either direction */
			//
			// ActuatorConfig.getInstance().getRightTalonOne().set(ControlMode.Velocity,
			// RtargetVelocity_UnitsPer100ms);
			// ActuatorConfig.getInstance().getLeftTalonOne().set(ControlMode.Velocity,
			// LtargetVelocity_UnitsPer100ms);

			ActuatorConfig.getInstance().getRightTalonFront().set(ControlMode.PercentOutput, -speed);
			ActuatorConfig.getInstance().getLeftTalonFront().set(ControlMode.PercentOutput, speed);

			// ActuatorConfig.getInstance().getRightTalonOne().getSelectedSensorVelocity(ActuatorConfig.kPIDLoopIdx);
			// ActuatorConfig.getInstance().getLeftTalonOne().getSelectedSensorVelocity(ActuatorConfig.kPIDLoopIdx);
		}

		ActuatorConfig.getInstance().getRightTalonFront().set(ControlMode.PercentOutput, 0);
		ActuatorConfig.getInstance().getLeftTalonFront().set(ControlMode.PercentOutput, 0);

	}
	
	public void goForwardGyro(double distance, double angle, double speed )
	{
		System.out.println("Forward: d=" + distance + "; angle=" + angle + "speed=" + speed);
		moveGyro(distance, angle, speed, false);
		System.out.println("--------------------");
	
	}
	
	public void goBackwardsGyro(double distance, double angle, double speed)
	{ 
		System.out.println("BackwardsGyro: d=" + distance + "; angle=" + angle + "speed=" + speed);
		moveGyro(distance, angle, speed, true);
		System.out.println("--------------------");
	}

	public void motionMagic(double distance)
	{
		ActuatorConfig.getInstance().getLeftTalonFront().setSelectedSensorPosition(0, 0, 10);
		ActuatorConfig.getInstance().getRightTalonFront().setSelectedSensorPosition(0, 0, 10);
		double targetPos = distance * 4096 * 4;// 4096 * 10.00
		ActuatorConfig.getInstance().getLeftTalonFront().set(ControlMode.MotionMagic, targetPos);
		ActuatorConfig.getInstance().getRightTalonFront().set(ControlMode.MotionMagic, -targetPos);

	}
}
