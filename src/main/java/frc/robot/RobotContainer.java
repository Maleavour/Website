// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.cscore.CvSink;
import edu.wpi.first.cscore.CvSource;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cscore.MjpegServer;
import edu.wpi.first.cameraserver.CameraServer;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.ConstantSpeedDrive;
import frc.robot.commands.swervedrive.drivebase.AbsoluteDriveAdv;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import java.io.File;
import frc.robot.Robot;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.path.PathPlannerPath;
import com.revrobotics.*;
import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a "declarative" paradigm, very
 * little robot logic should actually be handled in the {@link Robot} periodic methods (other than the scheduler calls).
 * Instead, the structure of the robot (including subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer
{
  
  Robot robot;
  // Initialize your camera
    public void initializeCamera() {
      UsbCamera camera = CameraServer.startAutomaticCapture();
      camera.setResolution(640, 480);
    }
  private final SlewRateLimiter limitY = new SlewRateLimiter(4.0);
    private final SlewRateLimiter limitX = new SlewRateLimiter(4.0);
      private final SlewRateLimiter limitRot = new SlewRateLimiter(6.0);
  
      // Load a Choreo trajectory as a PathPlannerPath
PathPlannerPath DSRTest = PathPlannerPath.fromChoreoTrajectory("DSR");
PathPlannerPath DSFTest = PathPlannerPath.fromChoreoTrajectory("DSF");
PathPlannerPath DSLTest = PathPlannerPath.fromChoreoTrajectory("DSL");
PathPlannerPath Test = PathPlannerPath.fromChoreoTrajectory("Test");
  // The robot's subsystems and commands are defined here...
  private final SwerveSubsystem drivebase;
  public final SendableChooser<Command> AUTO_CHOOSER;
  // Replace with CommandPS4Controller or CommandJoystick if needed
  final CommandXboxController driverXbox = new CommandXboxController(0);


  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer(Robot robot)
  {
    
    this.robot = robot;
    NamedCommands.registerCommand("Shoot", Shoot());
    NamedCommands.registerCommand("Zero Gyro", zeroGyro());
    NamedCommands.registerCommand("Intake", intake());
    NamedCommands.registerCommand("Indexer Up", IndexerUp());
    NamedCommands.registerCommand("Indexer Down", IndexerDown());
    NamedCommands.registerCommand("Shooter Stop", ShooterStop());
    NamedCommands.registerCommand("Intake Stop", intakeStop());
    NamedCommands.registerCommand("Indexer Stop", IndexerStop());
    drivebase = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(),
                                                                         "swerve/neo"));
    AUTO_CHOOSER = AutoBuilder.buildAutoChooser();
    // Configure the trigger bindings
    configureBindings();
   
    SmartDashboard.putData("My Auto's", AUTO_CHOOSER);
    
    

    

    AbsoluteDriveAdv closedAbsoluteDriveAdv = new AbsoluteDriveAdv(drivebase,
                                                                   () -> -MathUtil.applyDeadband(driverXbox.getLeftY(),
                                                                                                OperatorConstants.LEFT_Y_DEADBAND),
                                                                   () -> -MathUtil.applyDeadband(driverXbox.getLeftX(),
                                                                                                OperatorConstants.LEFT_X_DEADBAND),
                                                                   () -> -MathUtil.applyDeadband(driverXbox.getRightX(),
                                                                                                OperatorConstants.RIGHT_X_DEADBAND),
                                                                   driverXbox.getHID()::getYButtonPressed,
                                                                   driverXbox.getHID()::getAButtonPressed,
                                                                   driverXbox.getHID()::getXButtonPressed,
                                                                   driverXbox.getHID()::getBButtonPressed);

    // Applies deadbands and inverts controls because joysticks
    // are back-right positive while robot
    // controls are front-left positive
    // left stick controls translation
    // right stick controls the desired angle NOT angular rotation
    Command driveFieldOrientedDirectAngle = drivebase.driveCommand(
        () -> -MathUtil.applyDeadband(driverXbox.getLeftY(), OperatorConstants.LEFT_Y_DEADBAND),
        () -> -MathUtil.applyDeadband(driverXbox.getLeftX(), OperatorConstants.LEFT_X_DEADBAND),
        () -> -driverXbox.getRightX(),
        () -> -driverXbox.getRightY());

    // Applies deadbands and inverts controls because joysticks
    // are back-right positive while robot
    // controls are front-left positive
    // left stick controls translation
    // right stick controls the angular velocity of the robot
    Command driveFieldOrientedAnglularVelocity = drivebase.driveCommand(
        () -> -MathUtil.applyDeadband(limitY.calculate(driverXbox.getLeftY()), OperatorConstants.LEFT_Y_DEADBAND),
        () -> -MathUtil.applyDeadband(limitX.calculate(driverXbox.getLeftX()), OperatorConstants.LEFT_X_DEADBAND),
        () -> -limitRot.calculate(driverXbox.getRightX()));

    Command driveFieldOrientedDirectAngleSim = drivebase.simDriveCommand(
        () -> -MathUtil.applyDeadband(driverXbox.getLeftY(), OperatorConstants.LEFT_Y_DEADBAND),
        () -> -MathUtil.applyDeadband(driverXbox.getLeftX(), OperatorConstants.LEFT_X_DEADBAND),
        () -> -driverXbox.getRawAxis(2));

    drivebase.setDefaultCommand(
        !RobotBase.isSimulation() ? driveFieldOrientedAnglularVelocity : driveFieldOrientedDirectAngleSim);

    
  }
  
  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary predicate, or via the
   * named factories in {@link edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
   * {@link CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller PS4}
   * controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight joysticks}.
   */
  private void configureBindings()
  {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`


    driverXbox.leftBumper().whileTrue(new ConstantSpeedDrive(drivebase));
    driverXbox.a().onTrue((Commands.runOnce(drivebase::zeroGyro)));
    driverXbox.x().onTrue(Commands.runOnce(drivebase::addFakeVisionReading));
    driverXbox.b().whileTrue(
        Commands.deferredProxy(() -> drivebase.driveToPose(
                                   new Pose2d(new Translation2d(4, 4), Rotation2d.fromDegrees(0)))
                              ));
    // driverXbox.x().whileTrue(Commands.runOnce(drivebase::lock, drivebase).repeatedly());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand()
  {
    // An example command will be run in autonomous
    // Return an instance of PrintAutonomousStatus
    return AUTO_CHOOSER.getSelected();
  }

  
  public Command Shoot() 
  {
    return new InstantCommand(() -> {
      robot.TopShooter.set(Robot.shooter_reverse);
      robot.BottomShooter.set(Robot.shooter_reverse);
    }).andThen(new WaitCommand(0.2));
  }
  
  public Command zeroGyro() 
  {
    return new InstantCommand(() -> {
      Commands.runOnce(drivebase::zeroGyro);
    }).andThen(new WaitCommand(0.2));
  }

   public Command intake() 
  {
    return new InstantCommand(() -> {
      robot.Intake.set(-Robot.intake_power);
    }).andThen(new WaitCommand(0.2));
  }

  public Command IndexerUp() 
  {
    return new InstantCommand(() -> {
      robot.Indexer.set(Robot.Indexer_power);
    }).andThen(new WaitCommand(0.2));
  }
  public Command IndexerDown() 
  {
    return new InstantCommand(() -> {
      robot.Indexer.set(Robot.Indexer_reverse);
    }).andThen(new WaitCommand(0.2));
  }

  public Command IndexerStop() 
  {
    return new InstantCommand(() -> {
      robot.Indexer.set(Robot.generic_stop);
    }).andThen(new WaitCommand(0.2));
  }

  public Command intakeStop() 
  {
    return new InstantCommand(() -> {
      robot.Intake.set(Robot.generic_stop);
    }).andThen(new WaitCommand(0.2));
  }

  public Command ShooterStop() 
  {
    return new InstantCommand(() -> {
      robot.TopShooter.set(Robot.generic_stop);
      robot.BottomShooter.set(Robot.generic_stop);
    }).andThen(new WaitCommand(0.2));
  }

  public void setDriveMode()
  {
    //drivebase.setDefaultCommand();
  }

  public void setMotorBrake(boolean brake)
  {
    drivebase.setMotorBrake(brake);
  }
  
}
