

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


class Shadow{
	public Posn pinhole;
	public int width;
	public int length;
	public FromFileImage pic;
	public Posn direction;
	public int walkCounter;
	
	public Shadow(Posn pinhole, int width, int length, Picture pic, Posn direction, int walkCounter){
		this.pinhole = pinhole;
		this.length = length;
		this.lidth = width;
		this.pic = pic;
		this.direction = direction;
		this.walkCounter = walkCounter;	
	}

	public WorldImage ShadowImage(){
		return new RectangleImage(this.pinhole, this.width, 
								  this.length, this.pic, this.direction, this.walkCounter);
	}

	public boolean fightHuh(CharacterInMaze character){
		return (character.pinhole.x <= (500 - this.sTPinhole.x + (this.sTWidth / 2)))
			&& (character.pinhole.x >= (this.sTPinhole.x - (this.sTWidth / 2)))
			&& (character.pinhole.y <= (500 - this.sTPinhole.y + (this.sTLength / 2)))
			&& (character.pinhole.y >= (this.sTPinhole.y - (this.sTLength / 2)));
	}
//	public Shadow shadowWalking(){
//
//	}



	public CharacterInMaze shadowImageChange(){
		if(this.direction.x == -1){
			return new Shadow(this.pinhole, this.width, this.length, this.pic, this.direction, this.walkCounter);
		}
		else if(this.direction.x == 1){
			return new Shadow(this.pinhole, this.width, this.length, this.pic, this.direction, this.walkCounter);
		}
		else if(this.direction.y == -1){
			return new Shadow(this.pinhole, this.width, this.length, this.pic, this.direction, this.walkCounter);
		}
		else if(this.direction.y == 1){
			return new Shadow(this.pinhole, this.width, this.length, this.pic, this.direction, this.walkCounter);
		}
		else if(this.direction.x == 0 && this.direction.x == 0){
			return new Shadow(this.pinhole, this.width, this.length, this.pic, this.direction, this.walkCounter);
		}
		else{
			return this;
		}
	}
	public Shadow shadowplacer(int w, int x, int y, int z){
		return new Shadow(new Posn(w, x), this.width, this.length, this.pic, new Posn(0, 0), this.walkCounter);
	}

}