// Imports stuff (again!)

package frc.robot;

import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.path.PathPlannerPath;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.Controls;
import frc.robot.Constants.ControllersConstants;
import frc.robot.commands.LEDColorChangeCommand;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.ShoterSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

// This class is where the bulk of the robot should be declared.  Since Command-based is a
// "declarative" paradigm, very little robot logic should actually be handled in the Robot
// periodic methods (other than the scheduler calls).  Instead, the structure of the robot
// (including subsystems, commands, and button mappings) should be declared here.

public class RobotContainer {

  // Robot's Subsystems
  private final DriveSubsystem m_robotDrive = new DriveSubsystem();
  private final LEDSubsystem m_LEDSubsystem = new LEDSubsystem();
  private final ClimberSubsystem m_ClimberSubsystem = new ClimberSubsystem();
  private final ShoterSubsystem m_ShoterSubsystem = new ShoterSubsystem();

  SendableChooser<Command> m_chooser = new SendableChooser<>();

  // Controllers
  CommandXboxController m_driverController = new CommandXboxController(ControllersConstants.kDriverControllerPort);

  // The container for the robot. Contains subsystems, OI devices, and commands.
  public RobotContainer() {

    // Configure the button bindings
    configureButtonBindings();

    // DEPRECATED: Calibrates Gyro
    m_robotDrive.calibrateGyro();

    // Configure Default Commands
    m_robotDrive.setDefaultCommand(
      
        // The left stick on the controller controls robot translation.
        // Turning is controlled by the X axis of the right stick.
        new RunCommand(

            // Joystick input to tele-op control
            () -> m_robotDrive.drive(
                -MathUtil.applyDeadband(-m_driverController.getLeftY(), ControllersConstants.kDriveDeadband),
                -MathUtil.applyDeadband(-m_driverController.getLeftX(), ControllersConstants.kDriveDeadband),
                -MathUtil.applyDeadband(m_driverController.getRightX(), ControllersConstants.kDriveDeadband),
                true, true), m_robotDrive));

    SmartDashboard.putData("AutoMode", m_chooser);

    // Named Command Configuration
    NamedCommands.registerCommand("Change LED Color", new LEDColorChangeCommand(m_LEDSubsystem));

    // Autos
    m_chooser.addOption("Curvy yay", m_robotDrive.getAuto("Curvy yay"));
    m_chooser.addOption("Move and Spin", m_robotDrive.getAuto("Move and Spin"));
    m_chooser.addOption("xuehuapiaopiaoAuto", m_robotDrive.getAuto("xuehuapiaopiaoAuto"));

    // Load a Choreo trajectory as a PathPlannerPath
    PathPlannerPath Test = PathPlannerPath.fromChoreoTrajectory("Test");

    // Choreo Autos
    m_chooser.addOption("Choreo Test 1", m_robotDrive.getAuto("Test"));

  }

  // Use this method to define your button to command mappings. Buttons can be
  // created by instantiating a edu.wpi.first.wpilibj.GenericHID or one of its
  // subclasses edu.wpi.first.wpilibj.Joystick or XboxController, and then
  // passing it to a JoystickButton.

  private void configureButtonBindings() {
    new JoystickButton(m_driverController.getHID(), ControllersConstants.k_intakeButton)
    .whileTrue(
      new InstantCommand(() -> m_ShoterSubsystem.intake(),m_ShoterSubsystem))
    .whileFalse(
      new InstantCommand(() -> m_ShoterSubsystem.stop(),m_ShoterSubsystem)
    );
    new Trigger(() -> m_driverController.getRawAxis(ControllersConstants.k_ShootRevButton)>0.05)
    .whileTrue(
      new InstantCommand(() -> m_ShoterSubsystem.shooterRev(), m_ShoterSubsystem))
    .whileFalse(
      new InstantCommand(() -> m_ShoterSubsystem.stop(), m_ShoterSubsystem)
    );
    new Trigger(() -> m_driverController.getRawAxis(ControllersConstants.k_ShootButton)>0.05)
    .whileTrue(
      new InstantCommand(() -> m_ShoterSubsystem.shoot(), m_ShoterSubsystem))
    .whileFalse(
      new InstantCommand(() -> m_ShoterSubsystem.stop(), m_ShoterSubsystem)
    );
    new JoystickButton(m_driverController.getHID(), ControllersConstants.k_ampRevButton)
    .whileTrue(
      new InstantCommand(() -> m_ShoterSubsystem.ampRev(),m_ShoterSubsystem))
    .whileFalse(
      new InstantCommand(() -> m_ShoterSubsystem.stop(),m_ShoterSubsystem)
    );
    new JoystickButton(m_driverController.getHID(), ControllersConstants.k_ampButton)
    .whileTrue(
      new InstantCommand(() -> m_ShoterSubsystem.amp(),m_ShoterSubsystem))
    .whileFalse(
      new InstantCommand(() -> m_ShoterSubsystem.stop(),m_ShoterSubsystem)
    );
    //shooter end

    new JoystickButton(m_driverController.getHID(), ControllersConstants.k_climbUpButton)
    .whileTrue(
      new InstantCommand(() -> m_ClimberSubsystem.climbUp(),m_ClimberSubsystem))
    .whileFalse(
      new InstantCommand(() -> m_ClimberSubsystem.stop(),m_ClimberSubsystem)
    );
    new JoystickButton(m_driverController.getHID(), ControllersConstants.k_climbDnButton)
    .whileTrue(
      new InstantCommand(() -> m_ClimberSubsystem.climbdown(),m_ClimberSubsystem))
    .whileFalse(
      new InstantCommand(() -> m_ClimberSubsystem.stop(),m_ClimberSubsystem)
    );
    //climb end

    
  }

  public Command getAutonomousCommand() {
    return m_chooser.getSelected();   
  } 
}