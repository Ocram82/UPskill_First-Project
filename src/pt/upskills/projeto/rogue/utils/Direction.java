package pt.upskills.projeto.rogue.utils;

/**
 * @author POO2016
 * 
 * Cardinal directions
 *
 */
public enum Direction {
	LEFT, UP, RIGHT, DOWN, NW, NE, SW,SE;

	public Vector2D asVector() {
		//movimento defenido para cada direccao
		if(this==Direction.UP){
			return new Vector2D(0,-1);
		}
		if(this==Direction.DOWN){
			return new Vector2D(0, 1);
		}
		if(this==Direction.LEFT){
			return new Vector2D(-1,0);
		}
		if(this==Direction.RIGHT){
			return new Vector2D(1,0);
		}
		if(this==Direction.NW){
			return new Vector2D(-1,-1);
		}
		if(this==Direction.NE){
			return new Vector2D(1,-1);
		}
		if(this==Direction.SW){
			return new Vector2D(-1,1);
		}
		if(this==Direction.SE) {
			return new Vector2D(1,1);
		}
		return new Vector2D(0, 0);

	}
}




