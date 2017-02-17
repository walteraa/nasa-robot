package nasa.robot.challenge.exceptions;

/**
 * Exception to be raised when an empty movement is identified.
 * @author Walter A. Alves
 *
 */
public class EmptyMovimentRequestException extends Exception {
	public EmptyMovimentRequestException(String msg) {
		super(msg);
	}
}
