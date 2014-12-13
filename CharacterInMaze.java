package Game2;
import javalib.funworld.*;
import javalib.worldimages.*;
import javalib.worldcanvas.*;
import javalib.colors.*;
import java.io.*;
import java.util.*;
import java.awt.Color;
import java.awt.geom.*;
import java.util.*;
import java.util.Random;
import java.lang.Object.*;

class CharacterInMaze{
	public Posn pinhole;
	public int width;
	public int length;
	public Picture pic;
	public Posn direction;
	public List itembag;

	public CharacterInMaze(Posn pinhole, int width, int length, Picture pic, Posn direction, ArrayList itembag){
		this.pinhole = pinhole;
		this.width = width;
		this.length = length;
		this.pic = pic;
		this.direction = direction;
		this.itembag = itembag;
	}


	public WorldImage Image(){
		return new RectangleImage(this.pinhole, this.width, this.length, this.pic, this.direction);
	}

	public CharacterInMaze moveChar(String ke){
	 	if (ke.equals("right") && (this.pinhole.x + (this.width / 2) <= 500)){
	 		return new CharacterInMaze(new Posn(this.pinhole.x + 5, this.pinhole.y),
	 			this.width, this.length, this.pic, new Posn(1, 0));
	 	}
	 	else if (ke.equals("left") && (this.pinhole.x - (this.width / 2) >= 0)){
	 		return new CharacterInMaze(new Posn(this.pinhole.x - 5, this.pinhole.y),
	 			this.width, this.length, this.pic, new Posn(-1, 0));
	 	}
	 	else if (ke.equals("up") && (this.pinhole.y - (this.length / 2) >= 0)){
	 		return new CharacterInMaze(new Posn(this.pinhole.x, this.pinhole.y - 5),
	 			this.width, this.length, this.pic, new Posn(0, -1));
	 	}
	 	else if (ke.equals("down") && (this.pinhole.y + this.length / 2) <= 500){
	 		return new CharacterInMaze(new Posn(this.pinhole.x, this.pinhole.y + 5),
	 			this.width, this.length, this.pic, new Posn(0, 1));
	 	}
	 	else {
	 		return this;
	 	}
	}

	public CharacterInMaze charImageChange(){
		if(this.direction.x == -1){
			return new CharacterInMaze(this.pinhole, this.width, this.length, this.pic, this.direction);
		}
		else if(this.direction.x == 1){
			return new CharacterInMaze(this.pinhole, this.width, this.length, this.pic, this.direction);
		}
		else if(this.direction.y == -1){
			return new CharacterInMaze(this.pinhole, this.width, this.length, this.pic, this.direction);
		}
		else if(this.direction.y == 1){
			return new CharacterInMaze(this.pinhole, this.width, this.length, this.pic, this.direction);
		}
		else if(this.direction.x == 0 && this.direction.x == 0){
			return new CharacterInMaze(this.pinhole, this.width, this.length, this.pic, this.direction);
		}
		else{
			return this;
		}
	}

	public CharacterInMaze charPlacer(int x, int y) {
		return new CharacterInMaze(new Posn(x, y), this.width, this.length, this.pic, new Posn(0, 0), this.item);
	}
}