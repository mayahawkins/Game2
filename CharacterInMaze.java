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
	public int height;
	public WorldImage pic;
	public Posn direction;
	public FiniteBags itembag;
	public String protagFileName = "MaleProtag";

	public CharacterInMaze(Posn pinhole, WorldImage pic, Posn direction, FiniteBags itembag){
		this.pinhole = pinhole;
		this.pic = new FromFileImage(pinhole, protagFileName);
		this.width = pic.getWidth();
		this.height = pic.getHeight();
		this.direction = direction;
		this.itembag = itembag;	
	}

	public WorldImage characterInMazeImage(){
		return pic.getMovedTo(pinhole);
	}

	public CharacterInMaze moveChar(String ke){
	 	if (ke.equals("right") && (this.pinhole.x + (this.width / 2) <= 500)){
	 		return new CharacterInMaze(new Posn(this.pinhole.x + 5, this.pinhole.y),
	 			 new Posn(1, 0), new FromFileImage(new Posn(this.pinhole.x + 5, this.pinhole.y));
	 	}
	 	else if (ke.equals("left") && (this.pinhole.x - (this.width / 2) >= 0)){
	 		return new CharacterInMaze(new Posn(this.pinhole.x - 5, this.pinhole.y),
	 			this.pic, new Posn(-1, 0) this.itembag);
	 	}
	 	else if (ke.equals("up") && (this.pinhole.y - (this.length / 2) >= 0)){
	 		return new CharacterInMaze(new Posn(this.pinhole.x, this.pinhole.y - 5),
	 			this.pic, new Posn(0, -1) this.itembag);
	 	}
	 	else if (ke.equals("down") && (this.pinhole.y + this.length / 2) <= 500){
	 		return new CharacterInMaze(new Posn(this.pinhole.x, this.pinhole.y + 5),
	 			this.width, this.length, this.pic, new Posn(0, 1) this.itembag);
	 	}
	 	else {
	 		return this;
	 	}
	}

//	public CharacterInMaze charImageChange(){
//		if(this.direction.x == -1){
//			return new CharacterInMaze(this.pinhole, this.width, this.length, this.pic, this.direction);
//		}
//		else if(this.direction.x == 1){
//			return new CharacterInMaze(this.pinhole, this.width, this.length, this.pic, this.direction);
//		}
//		else if(this.direction.y == -1){
//			return new CharacterInMaze(this.pinhole, this.width, this.length, this.pic, this.direction);
//		}
//		else if(this.direction.y == 1){
///			return new CharacterInMaze(this.pinhole, this.width, this.length, this.pic, this.direction);
//		}
//		else if(this.direction.x == 0 && this.direction.x == 0){
//			return new CharacterInMaze(this.pinhole, this.width, this.length, this.pic, this.direction);
//		}
//		else{
//			return this;
//		}
//	}

	public CharacterInMaze charPlacer(int x, int y) {
		return new CharacterInMaze(new Posn(x, y), this.width, this.length, this.pic, new Posn(0, 0), this.item);
	}
}