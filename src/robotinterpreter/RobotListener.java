package robotinterpreter;

public interface RobotListener 
{
	public void print(String s);
	public void println(String s);
	public void error(String var, String e);
	
	public void driveForward();
	public void driveBackwards();
	public void turnLeft();
	public void turnRight();
	public void stop();
	public int getSonarData(int num);
	public int getBearing();
	public void driveDistance(int dist);
	public void turnAngle(int angle);
	public void turnToBearing(int bearing);
	
}