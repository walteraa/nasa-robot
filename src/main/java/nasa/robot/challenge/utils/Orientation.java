package nasa.robot.challenge.utils;
/**
 * This ENUM represents the Cardinal Orientation
 * @author Walter A. Alves
 *
 */
public enum Orientation {
	N(0){
		@Override
		public String toString(){
			return "N";
		}
	},E(1){
		@Override
		public String toString(){
			return "E";
		}
	}, S(2){
		@Override
		public String toString(){
			return "S";
		}
	},
	W(3){
		@Override
		public String toString(){
			return "W";
		}
	};
	private int value;
	Orientation(int value){
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}
	
}
