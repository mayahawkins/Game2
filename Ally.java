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

public class Ally{
	private int allyOneHP;
	private int allyTwoHP;

	public Ally(int allyOneHP, int allyTwoHP){
		this.allyOneHP = allyOneHP;
		this.allyTwoHP = allyTwoHP;
	}


	public Character playerHeal(player){
		return new Character(this.hP + 50, this.sP);
	}

	public Ally allyHeal(int x){
		if(x == 1){
		return new Ally(allyOneHP + 50, allyTwoHP, allyOneSP, allyTwoSP);
		}
		else{
			return new Ally(allyOneHP, allyTwoHP + 50, allyOneSP, allyTwoSP);
		}
	}


}