package nasa.robot.challenge.service;

import java.awt.Point;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import nasa.robot.challenge.exceptions.BadMovimentRequestException;
import nasa.robot.challenge.exceptions.EmptyMovimentRequestException;
import nasa.robot.challenge.exceptions.InvalidCommandException;
import nasa.robot.challenge.utils.Orientation;

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

	public String move(String input)
			throws EmptyMovimentRequestException, InvalidCommandException, BadMovimentRequestException {
		if (input.trim().isEmpty())
			throw new EmptyMovimentRequestException("Empty Input");

		maskIndex = Orientation.N.getValue();
		position = new Point(0, 0);
		for (char mov : input.toCharArray()) {
			processMov(mov);
		}
		String payload = "(" + (int) position.getX() + "," + (int) position.getY() + ","
				+ Orientation.values()[maskIndex].toString() + ")";
		return payload;
	}

	private void processMov(char m) throws InvalidCommandException, BadMovimentRequestException {
		String mov = Character.toString(m).toLowerCase();
		if (M.equals(mov) || L.equals(mov) || R.equals(mov)) {
			if (M.equals(mov)) {
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

				if (maskIndex == Orientation.N.getValue()) {
					
					maskIndex = Orientation.W.getValue();
				} else {
					maskIndex--;
				}
			} else {

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
