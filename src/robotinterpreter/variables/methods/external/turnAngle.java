package robotinterpreter.variables.methods.external;

import robotinterpreter.Interpreter;
import robotinterpreter.RobotListener;
import robotinterpreter.terminals.Terminals;

public class turnAngle extends ExtMethod 
{
	private Interpreter interpreter;
	
	public turnAngle(Interpreter in)
	{
		interpreter = in;
		id = "turnAngle";
		type = Terminals.VOID;
		paramTypes = new String[1];
		paramTypes[0] = Terminals.INT;
	}
	
	public Object execute(Object[] args) 
	{
		for(RobotListener l : interpreter.getRobotInterpreter().getRobotListeners())
		{
			l.turnAngle((Integer) args[0]);
		}
		return null;
	}
}