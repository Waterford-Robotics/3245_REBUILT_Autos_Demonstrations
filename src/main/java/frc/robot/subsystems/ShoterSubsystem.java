package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class ShoterSubsystem extends SubsystemBase{
    private Talon m_shotL =new Talon(ShooterConstants.k_shootLPort);
    private Talon m_shotR =new Talon(ShooterConstants.k_shootRPort);
    private Talon m_shotK =new Talon(ShooterConstants.k_shootKPort);
    //jerry wants a line between here
    public void shooterRev(){
        m_shotL.set(ShooterConstants.k_shootRevSpeed);
        m_shotR.set(ShooterConstants.k_shootRevSpeed);
    }
    public void shoot(){
        m_shotL.set(ShooterConstants.k_shootRevSpeed);
        m_shotR.set(ShooterConstants.k_shootRevSpeed);
        m_shotK.set(ShooterConstants.k_shootRevSpeed);
    }
    public void intake(){
        m_shotL.set(ShooterConstants.k_intakeRevSpeed);
        m_shotR.set(ShooterConstants.k_intakeRevSpeed);
        m_shotK.set(ShooterConstants.k_intakeRevSpeed);
    }
    public void ampRev(){
        m_shotL.set(ShooterConstants.k_ampTopSpeed);
        m_shotR.set(ShooterConstants.k_ampBotSpeed);
    }
    public void amp(){
        m_shotL.set(ShooterConstants.k_ampTopSpeed);
        m_shotR.set(ShooterConstants.k_ampBotSpeed);
        m_shotK.set(ShooterConstants.k_shootRevSpeed);
    }
    public void stop(){
        m_shotL.set(0);
        m_shotR.set(0);
        m_shotK.set(0);
    }
}
