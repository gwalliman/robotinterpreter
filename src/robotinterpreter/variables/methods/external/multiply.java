package robotinterpreter.variables.methods.external;

public class multiply extends ExtMethod 
{
	public multiply()
	{
		id = "multiply";
		type = "int";
		paramTypes = new String[2];
		paramTypes[0] = "int";
		paramTypes[1] = "int";
	}
	
	public Object execute(Object[] args) 
	{
		return ((int)args[0]) * ((int)args[1]);
	}
}
