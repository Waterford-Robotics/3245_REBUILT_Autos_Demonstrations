package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.AutoTimeConstants;
import frc.robot.subsystems.ShoterSubsystem;

public class ShootRevCommandShort extends Command{
    ShoterSubsystem m_ShoterSubsystem;
    Timer timer = new Timer();
    
    public ShootRevCommandShort (ShoterSubsystem ShoterSubsystem){
        m_ShoterSubsystem = ShoterSubsystem;
        addRequirements(ShoterSubsystem);
    }
    
    @Override public void initialize(){
        timer.start();
        timer.reset();
    }

    @Override public void execute(){
        if(timer.get()<AutoTimeConstants.shootRevAutoTimeShort){
            m_ShoterSubsystem.shooterRev();
        }
        if(timer.get()>AutoTimeConstants.shootRevAutoTimeShort && timer.get()<AutoTimeConstants.shootAutoTimeShort){
            m_ShoterSubsystem.shooterRev();
            m_ShoterSubsystem.shoot();
        }
        if(timer.get()>AutoTimeConstants.shootAutoTimeShort){
            m_ShoterSubsystem.stop();
        }
    }

    @Override public void end (boolean interrupted){

    }

    @Override public boolean isFinished(){
        return timer.get()>AutoTimeConstants.shootAutoTimeShort;
    }
}
