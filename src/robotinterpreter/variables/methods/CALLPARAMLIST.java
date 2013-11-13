package robotinterpreter.variables.methods;

import robotinterpreter.Code;
import robotinterpreter.RobotInterpreter;
import robotinterpreter.terminals.Terminals;
import robotinterpreter.variables.CALL;
import robotinterpreter.variables.Variable;

public class CALLPARAMLIST extends Variable
{
	private CALL call;
	private CALLPARAMLIST nextParam;
	private METHOD method;
	private int paramNum;
	
	public CALLPARAMLIST(Code c, String s, METHOD m, int p) 
	{
		method = m;
		paramNum = p;
		lineNum = c.currentLineNum();
		code = c.currentLine();

		String tokens[] = s.split(Terminals.COMMA);
		
		if(tokens[0].trim().length() > 0)
		{
			call = new CALL(c, tokens[0]);
		}
		else RobotInterpreter.halt("CALLPARAMLIST", lineNum, code, "Syntax error in CALLPARAMLIST");

		if(tokens.length > 1)
		{
			nextParam = new CALLPARAMLIST(c, Code.implode(tokens, ",", 1, tokens.length - 1), m, ++p);
		}
	}
	
	public void print() 
	{
		System.out.print(paramNum + " ");
		call.print();
			
		if(nextParam != null)
		{
			System.out.print(", ");
			nextParam.print();
		}
	}

	//1. Validate call
	//2. Ensure that call should exist at all (what if param doesn't exist?)
	//3. Ensure that call is of right type.
	//4. Validate next call
	public void validate() 
	{
		System.out.println("Validating CALLPARAMLIST");

		//1
		call.validate();
		METHODDEFINE methdef = RobotInterpreter.findMethod(method.id());
		
		//As the method should always be validated before the calllist, it should always be valid, but just in case we check.
		if(methdef != null)
		{	
			//2
			DEFPARAMLIST paramdef = methdef.getParam(paramNum);
			if(paramdef == null)
			{
				RobotInterpreter.halt("CALLPARAMLIST", lineNum, code, "Parameter " + paramNum + " does not exist for method " + methdef.id());
			}
			else
			{
				//3
				String callType = call.getType();
				String defType = paramdef.getType();
				if(!callType.equals(defType))
				{
					RobotInterpreter.halt("CALLPARAMLIST", lineNum, code, "Parameter " + paramNum + " is of wrong type. Method " + methdef.id() + " parameter " + paramNum + " requires " + defType + ", but was provided " + callType);
				}
			}
		}
		
		//4
		if(nextParam != null)
		{
			nextParam.validate();
		}
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
}
