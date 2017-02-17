package nasa.robot.challenge.services;

import java.awt.Point;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import nasa.robot.challenge.DTO.PositionDTO;
import nasa.robot.challenge.exceptions.BadMovimentRequestException;
import nasa.robot.challenge.exceptions.EmptyMovimentRequestException;
import nasa.robot.challenge.exceptions.InvalidCommandException;
import nasa.robot.challenge.utils.Orientation;
/**
 * This is a class which represents a service to simulate movement of a robot in Mars by NASA.
 * @author Walter A. Alves
 *
 */
@Service
public class RobotService {

	private Point[] movimentMasks = new Point[4];
	private int maskIndex;
	Point position = new Point(0, 0);
	private final String M = "m", L = "l", R = "r";

	@PostConstruct
	public void init() {
		movimentMasks[Orientation.N.getValue()] = new Point(0, 1);
		movimentMasks[Orientation.E.getValue()] = new Point(1, 0);
		movimentMasks[Orientation.S.getValue()] = new Point(0, -1);
		movimentMasks[Orientation.W.getValue()] = new Point(-1, 0);
	}

	/**
	 * This method receives all input and process each atomic movement individually. 
	 * @param input Command with all movements to be processed. 
	 * @return Return a DTO object which represents the NASA command.
	 * @throws EmptyMovimentRequestException Raise this Exception when at least one movement is not a valid character.
	 * @throws InvalidCommandException Raise this Exception when 
	 * @throws BadMovimentRequestException
	 */
	public PositionDTO move(String input)
			throws EmptyMovimentRequestException, InvalidCommandException, BadMovimentRequestException {
		if (input.trim().isEmpty())
			throw new EmptyMovimentRequestException("Empty Input");

		maskIndex = Orientation.N.getValue();
		position = new Point(0, 0);
		for (char mov : input.toCharArray()) {
			processMov(mov);
		}
		
		PositionDTO payload = new PositionDTO((int) position.getX(), (int) position.getY(), Orientation.values()[maskIndex].toString());
		
		return payload;
	}

	/**
	 * This method process each atomic movement for a movement input. 
	 * @param m An atomic movement.
	 * @throws InvalidCommandException Raise this Exception when the movement is not a valid character.
	 * @throws BadMovimentRequestException Raise this Exception when the Robot move out of limits.
	 */
	private void processMov(char m) throws InvalidCommandException, BadMovimentRequestException {
		String mov = Character.toString(m).toLowerCase();
		if (M.equals(mov) || L.equals(mov) || R.equals(mov)) {
			if (M.equals(mov)) {
				//Verify if this is a valid movement, if yes make the movement, else raise an Exception
				int auxX = (int) (position.getX() + movimentMasks[maskIndex].getX());
				int auxY = (int) (position.getY() + movimentMasks[maskIndex].getY());
				if ( 0 <= auxX && auxX <= 4
					&& 0 <= auxY && auxY <= 4) {
					position.x = (int) (position.getX() + movimentMasks[maskIndex].getX());
					position.y = (int) (position.getY() + movimentMasks[maskIndex].getY());
				} else {
					
					throw new BadMovimentRequestException("Out of range");
				}
			} else if (L.equals(mov)) {
				//Move to left
				if (maskIndex == Orientation.N.getValue()) {
					
					maskIndex = Orientation.W.getValue();
				} else {
					maskIndex--;
				}
			} else {
				//Move to right
				if (maskIndex == Orientation.W.getValue()) {
					maskIndex = Orientation.N.getValue();
				} else {
					maskIndex++;
				}
			}

		} else {
			throw new InvalidCommandException("This input is not permited");
		}

	}

}
