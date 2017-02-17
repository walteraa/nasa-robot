package nasa.robot.challenge.exceptions;

/**
 * Exception to be raised when a movement out of range is identified.
 * @author Walter A. Alves
 *
 */
public class BadMovimentRequestException extends Exception {
	public BadMovimentRequestException(String msg) {
		super(msg);
	}
	
}
