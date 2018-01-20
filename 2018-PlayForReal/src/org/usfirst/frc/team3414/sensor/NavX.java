package org.usfirst.frc.team3414.sensor;

import com.kauailabs.navx.frc.AHRS;

public class NavX {
	private AHRS ahrs;
	
	private final static double GRAVITY = 9.80;
	
	private boolean isEnabled = true;
	
	private double pastYaw = 0;

	//private Thread newThread = new Thread(this);
	
	public NavX(AHRS ahrs)
	{
		this.ahrs = ahrs;
		
		//newThread.start();
		
	}
		
	public double getX()
	{
		return ahrs.getWorldLinearAccelX();
	}

	public double getY()
	{
		return ahrs.getWorldLinearAccelY();
	}

	public double getZ()
	{
		return ahrs.getWorldLinearAccelZ() + GRAVITY;
	}

	public void resetDisplacment()
	{
		ahrs.resetDisplacement();
	}
	
	public float getYaw()
	{
		pastYaw = ahrs.getYaw();
		
		if(pastYaw < 0)
		{			
			return (float)ahrs.getYaw() + 360;
		}
		else if(pastYaw > 0)
		{
			return (float)ahrs.getYaw();
		}
		else
		{
			return 0;
		}
	}
	
	public double getPitch()
	{
		return ahrs.getPitch();
	}
	
	public double getRoll()
	{
		return ahrs.getRoll();
	}
	
	public double getRawYaw()
	{
		return ahrs.getYaw();
	}
	
	public double getRate()
	{
		return ahrs.getRate();
	}

	public void enable()
	{
		isEnabled = true;
	}

	public void disable()
	{
		isEnabled = false;
	}
	
	public boolean isEnabled() 
	{
		return isEnabled;
	}
	
	public void reset()
	{
		ahrs.reset();
	}
	
	public double getVelocityX()
	{
		return ahrs.getVelocityX();
	}
	
	public double getVelocityY()
	{
		return ahrs.getVelocityY();
	}
	
	public double getVelocityZ()
	{
		return ahrs.getVelocityZ();
	}

	/*public void run() 
	{
		double lastYaw = 0.0;
		double lastPitch = 0.0;
		double lastRoll = 0.0;
		
		while(RobotStatus.isRunning())
		{
			double currentYaw = Math.round(getRawYaw());
			double currentPitch = Math.round(getPitch());
			double currentRoll = Math.round(getRoll());
			
			
			/*if((lastYaw != currentYaw) || (lastPitch != currentPitch) || (lastRoll != currentRoll))
			{
				notifyObservers();
			}*/
			
			/*if((lastYaw == currentYaw) || ((currentYaw + 1) <= lastYaw) || (lastYaw >= (currentYaw - 1)))
			{
				System.out.println("Yaw Changed....");
				System.out.println("Last: " + lastYaw + " Current: " + currentYaw ) ;
			}*/
			
			/*if(lastYaw != currentYaw)
			{
				//System.out.println("Yaw Changed....");
			}
			
			if(lastPitch != currentPitch)
			{
				//System.out.println("Pitch Changed....");
			}
			
			///System.out.println("Yaw: " + getYaw());
			
			if(lastRoll != currentRoll)
			{
				//System.out.println("Roll Changed....");
			}

			
			lastYaw = currentYaw;
			lastPitch = currentPitch;
			lastRoll = currentRoll;
			
		}
	}*/
}
