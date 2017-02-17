package nasa.robot.challenge.DTO;

import java.io.Serializable;

/**
 * This class is a Data Transaction Object to represent a Position which is a representation of a response when the response
 * is made successfully. 
 * @author Walter A. Alves
 *
 */
public class PositionDTO implements Serializable{
	/**
	 * Basic constructor representing a response. 
	 * @param x Robot`s position X.
	 * @param y Robot`s position Y.
	 * @param orientation Robot`s direction(N[orth], S[outh], E[ast], W[est] 
	 */
	public PositionDTO(int x, int y, String orientation) {
		this.x = x;
		this.y = y;
		this.orientation = orientation;
	}
	public int x,y;
	public String orientation;
	
	/**
	 * Equals method used in the Test to verify if the service is processing correctly.
	 */
	public boolean equals(Object obj){
		if (!(obj instanceof PositionDTO))
			return false;
		
		PositionDTO obj2 = (PositionDTO) obj;
		
		return this.x == obj2.x && this.y == obj2.y && this.orientation.equals(obj2.orientation);
	}
}
