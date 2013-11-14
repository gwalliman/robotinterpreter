package robotinterpreter.variables.loop;

import robotinterpreter.Code;
import robotinterpreter.RobotInterpreter;
import robotinterpreter.terminals.Terminals;
import robotinterpreter.variables.BODY;
import robotinterpreter.variables.Variable;
import robotinterpreter.variables.conditional.CONDITIONLIST;

public class LOOPUNTIL extends Variable
{
	private CONDITIONLIST cl;
	private BODY codeBody;
	
	public LOOPUNTIL(BODY b, Code c)
	{
		body = b;
		code = c.currentLine();
		lineNum = c.currentLineNum();
		
		String[] tokens = Code.tokenize(code);
		
		if(tokens[1] != Terminals.OPENPAREN)
		{
			RobotInterpreter.halt("LOOPUNTIL", lineNum, code, "LOOPUNTIL must open with (");
		}
		
		if(tokens[tokens.length - 1] != Terminals.CLOSEPAREN)
		{
			RobotInterpreter.halt("LOOPUNTIL", lineNum, code, "LOOPUNTIL must close with )");
		}
		
		if(tokens.length > 3)
		{
			cl = new CONDITIONLIST(body, c, code.substring(11, code.length() - 1));
		}
		else RobotInterpreter.halt("LOOPUNTIL", lineNum, code, "LOOPUNTIL must contain a condition list!");

		c.nextLine();
		codeBody = new BODY(body, c);
	}
	
	public void print() 
	{
		RobotInterpreter.write("loopuntil (");
		cl.print();
		RobotInterpreter.writeln(")");
		codeBody.print();
	}

	//Validate condition list
	//Validate body
	public void validate() 
	{
		RobotInterpreter.writeln("Validating LOOPUNTIL");
		cl.validate();
		codeBody.validate();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
}