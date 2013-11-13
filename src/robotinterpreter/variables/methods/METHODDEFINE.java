package robotinterpreter.variables.methods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import robotinterpreter.Code;
import robotinterpreter.RobotInterpreter;
import robotinterpreter.terminals.Terminals;
import robotinterpreter.variables.BODY;
import robotinterpreter.variables.ID;
import robotinterpreter.variables.Variable;
import robotinterpreter.variables.methods.external.ExtMethod;

public class METHODDEFINE extends Variable {
	
	private String type;
	private String methodType;
	private String id;
	private BODY body;
	private DEFPARAMLIST params;
	
	//Define INTERNAL method
	public METHODDEFINE(Code c)
	{
		methodType = "internal";
		lineNum = c.currentLineNum();
		code = c.currentLine();
		
		String[] tokens = Code.tokenize(c.currentLine());
		
		type = tokens[1];
		if(!Terminals.dataTypes.contains(type)) RobotInterpreter.halt("METHODDEFINE", lineNum, code, "Invalid METHODDEFINE data type. Must be int, string, or bool");
			
		id = ID.validate(tokens[2], c);
		
		if(tokens[3] != Terminals.OPENPAREN)
		{
			RobotInterpreter.halt("METHODDEFINE", lineNum, code, "ID must be followed by (");
		}
		
		if(tokens[tokens.length - 1] != Terminals.CLOSEPAREN)
		{
			RobotInterpreter.halt("METHODDEFINE", lineNum, code, "METHODDEFINE header must end with )");
		}
		
		if(tokens[4] != Terminals.CLOSEPAREN)
		{
			params = new DEFPARAMLIST(c, Code.implode(tokens, " ", 4, tokens.length - 2), 0);
		}
		
		c.nextLine();
		body = new BODY(c);
		
		RobotInterpreter.methodTable.add(this);
	}
	
	//Define EXTERNAL method
	public METHODDEFINE(String s)
	{
		lineNum = -1;
		code = "Externally defined";
		methodType = "external";
		id = s;
		body = null;
		
		for(Object ext : RobotInterpreter.extMethodTable)
		{
			if(((ExtMethod)ext).id() == s)
			{
				type = ((ExtMethod)ext).type();
				ArrayList<String> pt = new ArrayList<String>(Arrays.asList(((ExtMethod)ext).paramTypes()));
				params = new DEFPARAMLIST(pt, 0);
			}
		}
		
	}
	
	public String id()
	{
		return id;
	}
	
	public String type()
	{
		return type;
	}
	
	public DEFPARAMLIST getParam(int paramNum) 
	{
		DEFPARAMLIST param = params;
		while(param.getNum() != paramNum && param != null)
		{
			param = param.nextParam();
		}
		
		return param;
	}
	
	public void print() 
	{
		System.out.print("methoddefine " + type + " " + id + "(");
		if(params != null)
			params.print();
		System.out.println(")");
		body.print();
	}

	//Ensure that method doesn't exist twice
	//Validate body
	//Validate params
	public void validate() 
	{
		System.out.println("Validating METHODDEFINE");

		if(Collections.frequency(RobotInterpreter.methodTable, RobotInterpreter.findMethod(id)) > 1)
		{
			RobotInterpreter.halt("METHODDEFINE", lineNum, code, "Method " + id + " cannot be defined more than once!");
		}		
		if(params != null)
		{
			params.validate();
		}
		body.validate();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
}
