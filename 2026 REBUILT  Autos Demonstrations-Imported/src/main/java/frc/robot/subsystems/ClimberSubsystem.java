package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimberConstants;

public class ClimberSubsystem extends SubsystemBase{
    private Talon m_climb = new Talon(ClimberConstants.k_climberPort);

    public void climbUp(){
        m_climb.set(ClimberConstants.k_climberSpeed);
    }
    public void climbdown(){
        m_climb.set(-ClimberConstants.k_climberPort);
    }
    public String stop(){
        String message = new String("easter omlet");
        m_climb.set(0);
        return (message);
    }
}
