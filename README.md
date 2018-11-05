# 2018-Power-Up
2018 Power Up FRC robot code. (post-MARC competition)

## Commit Messages:

These messages detail the main changes that were made to the existing code pulled from the 2018-Power-Up master branch (last commit April 18, 2018). 
Pretty much all of this was made possible by the groundwork laid by other coders (mainly Kayleigh and Hannah this year) and the help from our mentors. Of course, the first thing you should keep in mind when reading this is that even with these changes and fixes, the code is far from perfect; there are things left to do and lessons that were learned from the competition. 
Read through this list and then the code to get a complete sense of what was changed or added, and what still needs to be done.
 
## THINGS LEFT TO DO: 

-Vision: it would make getting multiple cubes onto the switch/scale possible, as well as attaining the far side switch/scale (all in auton)

-DONE: Auton capabilities used in AutonGoForward need to be moved to their respective classes

-Add detection/reporting/logging features to identify and detail hardware malfunctions for mechanical to fix (for example, the elevator frame deformed and jammed the lift)

-The robot should be able to move faster in auton when getting to the switch/scale (robot movement should be more efficient:
delays in between each action should be removed and accuracy maintained) 

-The robot would sometimes stay still during auton. More testing needs to be done to find out the cause of this bug and the solution for it.  

## List of commits:

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

09/23/2018 Post-MARC event-----------------------------------------------------------------------

Robot.java 2 + a few auton class names
Adds (Replaces old classes) AutonCrossAutoLine, AutonSideSwitchNear,  (New) AutonSideSwitchNearOrFar, and AutonScaleNearOrFar

AutonUtility.java 2
Adds a couple of functions to AutonUtility

AutonCrossAutoLine
Implements AutonCrossAutoLine

If at left or right, robot moves forward to cross line. If in center, Robot uses turnRadius to cross auton line.

AutonScaleNearOrFar
Implements AutonScaleNearOrFar

This class focuses on delivering the cube to the scale, no matter the distance.

AutonBase.java 
Adds caption

For GUI and error logging purposes.

AutonCenterSwitch.java 
Updates AutonCenterSwitch

Updates the old function with to be in line with the post-MARC updates

Other auton classes
Implements some new ones and updates the rest

09/30/18 Pre-Girls Comp --------------------------------------
Drivetrain.java 7
Fixed moveGyro

This update changes the speed plan used in moveGyro so the robot won't stay still unintentionally during auton.

MoveCorrection.java 
Adds MoveCorrection class to actuators

Tracks instances where the robot moved/rotated more of less than the target value. It then makes up for the difference by appropriately adjusting the next target value. 
 
 Drivetrain.java 8
 Applies MoveCorrection
 
 moveGyroCorrection (new) is based on moveGyro. turnRadiusCorrection is also a new function, this time based on turnRadius. 
 
 AutonUtility.java 3
 Tests MoveCorrection
 
 Tests the autonCenterSwitchDelivery function, while removing the delay between steps and applying MoveCorrection between each step.
 
 10/01/18
 
 AutonCenterSwitch.java and AutonScaleNearOrFar.java
 Adds AutonUtility.autonStartActions() routine to both autons listed
 
 Noticed that these classes were missing the auton start routine in certain places, and now it's added to them.

10/11/18

AutonUtility.java 
Fixes bugs

Fixes some bugs with auton movement. After testing on a real chassis, some functions were corrected. 

AutonSwitchandScalePriority.java
Fixes bugs

Also fixes some bugs with auton movement. After testing on a real chassis, some functions were corrected.

ActuatorConfig.java 3
Alters SpeedPlan parameters

Some speed parameters were changed so that the test chassis wouldn't get stuck in the carpet during testing.

10/13/18
SensorConfig.java and NavXThread.java
Adds NavXThread class

The NavXThread class collects NavX readings in a separate thread.

Drivetrain.java 9, and TurnDirection.java
Defines turning in multiple directions

Adds FORWARD_LEFT, FORWARD_RIGHT, BACKWARD_LEFT, and BACKWARD_RIGHT to turn along an arc in specified directions.

10/14/18
ActuatorConfig.java 4 and Drivetrain.java 10
Adds robot size values and tracks turnRadius distance traveled on an arc, respectively

 11/05/18
 Auton and Teleop adjustments
 
 Testing some new auton/teleop algorithms on the robot
 The NavXThread class was disabled, and its functions were merged with the existing NavX class.


