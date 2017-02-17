package nasa.robot.challenge.exceptions;

/**
 * This Exception represents when some invalid command is identified.
 * @author Walter A. Alves
 *
 */
public class InvalidCommandException extends Exception{
	public InvalidCommandException(String msg) {
		super(msg);
	}
}
