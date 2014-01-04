package robotinterpreter;

import java.util.ArrayList;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import robotinterpreter.terminals.Terminals;

public class RobotInterpreter
{
	
	public static ArrayList<RobotListener> robotlisteners = new ArrayList<RobotListener>();
	private Interpreter r;
	
	public RobotInterpreter() 
	{ 
		Terminals.init();
		robotlisteners.clear();
	}

	public void load(String c)
	{
		r = new Interpreter(c);
	}

	public void execute()
    {
    	if(r != null && r.isReady())
    	{
    		r.execute();
    	}
    }

    public void stop()
    {
    	if(r != null)
    	{
    		Interpreter.halt();
    	}    
    }
	
	public static ArrayList<RobotListener> getRobotListeners()
	{
		return robotlisteners;
	}
	
	public void addRobotListener(RobotListener toAdd)
	{
		robotlisteners.add(toAdd);
	}
	
	public static void main(String args[])
	{
		UIManager.put("swing.boldMetal", Boolean.FALSE);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				components.FileChooserDemo.createAndShowGUI();
			}
		});   
	}

	public boolean isReady() 
	{
		if(r != null)
		{
			return r.isReady();
		}
		else return false;
	}
}
