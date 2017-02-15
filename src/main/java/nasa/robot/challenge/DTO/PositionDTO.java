package nasa.robot.challenge.DTO;

import java.io.Serializable;

public class PositionDTO implements Serializable{
	public PositionDTO(int x, int y, String orientation) {
		this.x = x;
		this.y = y;
		this.orientation = orientation;
	}
	public int x,y;
	public String orientation;
	
	public boolean equals(Object obj){
		if (!(obj instanceof PositionDTO))
			return false;
		
		PositionDTO obj2 = (PositionDTO) obj;
		
		return this.x == obj2.x && this.y == obj2.y && this.orientation.equals(obj2.orientation);
	}
}
