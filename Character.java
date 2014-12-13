

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

interface Character{
	public int hP;
	public int sP;




	public Character dia(){
		return new Character(this.hP + 50, this.sP - 30, this.personaMoves);
	}

	public Ally healAlly(Ally ally, int x){
		if(x == 1){
		return new Ally(allyOneHP + 50, allyTwoHP, allyOneSP, allyTwoSP);
		}
		else{
			return new Ally(allyOneHP, allyTwoHP + 50, allyOneSP, allyTwoSP);
		}
	}

Random rn = new Random();


	public ShadowFight bash(ShadowFight shadow){
		int attackLvl = rn.nextInt(20) + 1;	
		if(attackLvl >= 2){
			int lowRange = rn.nextInt(10) + 21;
			return new ShadowFight(shadow.hP - lowRange); 
		}
		else if(attackLvl >= 19){
			int regRange = rn.nextInt(20) + 31;
			return new ShadowFight(shadow.hP - medRange);
		}
		else{
			int critRange = rn.nextInt(20) + 41;
			return new ShadowFight(shadow.hP - critRange);
		}
	}


	public ShadowFight agi(ShadowFight shadow){
		int attackLvl = rn.nextInt(10) + 1;	
		if(attackLvl >= 2){
			int lowRange = rn.nextInt(10) + 15;
			return new ShadowFight(shadow.hP - lowRange); 
		}
		else if(attackLvl >= 8){
			int regRange = rn.nextInt(10) + 20;
			return new ShadowFight(shadow.hP - medRange);
		}
		else{
			int critRange = rn.nextInt(10) + 30;
			return new ShadowFight(shadow.hP - critRange);
		}
	}	
}