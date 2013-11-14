package robotinterpreter.variables.wait;

import robotinterpreter.Code;
import robotinterpreter.RobotInterpreter;
import robotinterpreter.variables.BODY;
import robotinterpreter.variables.Variable;

public class WAITFOR extends Variable
{
	private int iterations;
	private BODY codeBody;
	
	public WAITFOR(BODY b, Code c)
	{
		body = b;
		code = c.currentLine();
		lineNum = c.currentLineNum();
		
		String[] tokens = Code.tokenize(code);
		
		if(tokens[1].matches("-?[0-9]+"))
		{
			iterations = Integer.parseInt(tokens[1]);
		}
		else RobotInterpreter.halt("WAITFOR", lineNum, code, "WAITFOR integer is missing or of invalid format.");
		
		c.nextLine();
		codeBody = new BODY(body, c);
	}
	
	public void print() 
	{
		RobotInterpreter.writeln("waitfor " + iterations + " seconds");
		codeBody.print();
	}

	//Ensure iterations is not negative (this may be taken care of via parsing)
	//Validate body
	public void validate() 
	{
		RobotInterpreter.writeln("Validating WAITFOR");

		if(iterations < -1)
		{
			RobotInterpreter.halt("LOOPFOR", lineNum, code, "LOOPFOR iterations integer cannot be less than -1");
		}
		codeBody.validate();		
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
}