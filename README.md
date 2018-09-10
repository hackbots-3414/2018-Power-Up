# 2018-Power-Up
2018 Power Up FRC robot code. (post-MARC competition)4

Commit Messages: these messages detail the main changes that were made to the existing code pulled from the 2018-Power-Up master branch (last commit April 18, 2018). 
Pretty much all of this was made possible by the groundwork laid by other coders (mainly Kayleigh and Hannah this year) and the help from our mentors. Of course, the first thing you should keep in mind when reading this is that even with these changes and fixes, the code is far from perfect; there are things left to do and lessons that were learned from the competition. 
Read through this list and then the code to get a complete sense of what was changed or added, and what still needs to be done.
 
THINGS LEFT TO DO: 
-Vision: it would make getting multiple cubes onto the switch/scale possible, as well as attaining the far side switch/scale (all in auton)

-Auton capabilities used in AutonGoForward need to be moved to their respective classes

-Add detection/reporting/logging features to identify and detail hardware malfunctions for mechanical to fix (for example, the elevator frame deformed and jammed the lift)

-The robot should be able to move faster in auton when getting to the switch/scale (robot movement should be more efficient:
delays in between each action should be removed and accuracy maintained) 

-The robot would sometimes stay still during auton. More testing needs to be done to find out the cause of this bug and the solution for it.  

List of commits:

TripleMotor.java
Add TripleMotor class

Copied from existing code so that
three-motor chassis may use this project

SpeedPlan.java
Add SpeedPlan class

Created a new class for the purpose of controlling motor speed 

RobotChassis.java
Add enum RobotChassis

Defines a list of robot chassis names

RobotSelection.java
Add RobotSelection class

Selects a robot or chassis from the list defined in RobotChassis.java

PacbotTeleop.java
Add PacbotTeleop class

Added existing teleop control class to avoid changing WrongWayTeleop, which is used in the master branch

NavX.java
Add getTrueYaw function to existing NavX class

getTrueYaw is meant to reduce angle-related programs' complexity. 
Existing getYaw returns values [0, 360]; getTrueYaw will expand that range to all real numbers, making it possible to make multiple successive revolutions without the robot freaking out.
	
Robot.java
Adds on to existing Robot class

Made some changes to the NavX initialization.
PacbotTeleop is now used instead of WrongWayTeleop.
The code now bypasses other actuators when testing in only drive train mode (no lift, intake, etc)

ActuatorConfig.java
Makes configuration flexible for different robots and chassis

Some parameters were added to wheel diameter and robot chassis actuators to accommodate varying robot designs.
When testing, we can now turn off things like the wings, intake, and lift for convienence.

DriveTrain.java 1

The original liftToScale function was used in teleop and auton.
Jon made some adjustments to liftToScaleTeleop, now using liftToScaleAutonPhillip, which was named by him.

DriveTrain.java 2
Adds speed control for auton lift

SpeedPlan is now implemented in liftToScaleAutonPhillip.

DriveTrain.java 3
Adds speed control declarations

SpeedPlan is now applied to going straight forward/backward, turning, and liftToScale.

DriveTrain.java 4
Adds turnRadius (turnLeftRadius and turnRightRadius)

Added to replace turnLeft and turnRight to simplify loop logic and make it possible to move forward while turning either right or left by making the rotation radius > 0.
The robot can now rotate more than one full circle since these functions use getTrueYaw.

DriveTrain.java 5
Modifies moveGyro, goForwardGyro, and goBackwardsGyro

Corrected the moving forward (isReversed == false) angle correction algorithm. 
Angle is now a function parameter; it's useful for when the robot approaches a target position with a slight angle difference.

DriveTrain.java 6 
Some notes and small changes

Distance parameter for goForward and goBackward changed data type from int to double.
In addition, some notes were added for future reference. 

IDriveTrain.java
Adds declaration of turnRightRadius

Adds declaration of turnRightRadius to interface (IDriveTrain).

AutonGoForward.java
Now utilizes turnRadius and tweaks the code to have center switch and side near switches auton

The existing code was fit--using additions like turnRadius and the modified moveGyro--to be able to make a more precise auton (center switch and side near switches only). 
Eventually, switch handling actions will need to be moved to the appropriate auton classes.

Other auton classes
Adjusts parameters of goForwardGyro/goBackwardsGyro to now have angle and makes AutonSideSwitchAndScalePriority work

goForwardGyro/goBackwardsGyro now use argument angle to potentially correct previous steps' accumulated angle error. 
Turning angles and distance are adjusted to fit new movement functions.
AutonUtility + AutonSideSwitchAndScalePriority were updated before the MARC competition playoffs since our alliance members' robots were not prepared to take the scale in auton. 

ActuatorConfig.java 2 and DriveTrain.java 7
Replaces tripleMotor with doubleMotor

Instances of tripleMotor are replaced with doubleMotor.
tripleMotor-related functions need to be tested and accordingly fixed when a triple-motor chassis is available.

 

