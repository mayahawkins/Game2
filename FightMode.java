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


class FightMode extends World{
	
	public Picture shadowPic;
	public Picture actionPic;
	public Character player;
	public Ally ally;
	public ShadowFight shadow;
	public int turnCounter;
	public boolean moveMade;


	public FightMode(Picture shadowPic, Picture actionPic, Character player, Ally ally,
					 ShadowFight shadow, int turnCounter, boolean moveMade){
		this.shadowPic = shadowPic;
		this.actionPic = actionPic;
		this.player = player;
		this.ally = ally;
		this.shadow = shadow;
		this.turnCounter = turnCounter;
		this.moveMade = moveMade;
	}
	Random rn = new Random();
	public World onKeyEvent(String ke){
		if(this.turnCounter == 1 && this.moveMade == false && ke == "1"){
			return new FightMode(this.shadowPic, protag pic, new Character(player.hP - (player.hP / 10), player.sP),
								 this.ally, this.bash(this.shadow), this.turnCounter, true);
		}
		else if(this.turnCounter == 1 && this.moveMade == true && ke == "enter"){
			return new FightMode(this.shadowPic, eliz pic, this.player, this.ally, this.shadow, 2, false);
		}
		else if(this.turnCounter == 2 && this.moveMade == false && ke == "enter"){
			int elizMove = rn.nextInt(2) + 1;
			if(elizMove == 1){
			return new FightMode(this.shadowPic, this.actionPic, this.player, this.ally,
								 this.allyAttack(this.shadow), this.turnCounter, true);
			}
			else{
				int elizHeal = rn.nextInt(3) + 1;
				if(elizHeal = 3){ 
				return new FightMode(this.shadowPic, this.actionPic, this.player.playerHeal(), this.ally, this.shadow, this.turnCounter, true);
				}
				else{
					return new FightMode(this.shadowPic, this.actionPic, this.player, this.ally.allyHeal(elizHeal), this.turnCounter, true);
				}
			}
		}
		else if(this.turnCounter == 2 && this.moveMade == true && ke == "enter"){
			return new FightMode(this.shadowPic, koropic, this.player, this.ally, this.shadow, 3, false);
		}
		else if(this.turnCounter == 3 && this.moveMade == false && ke == "enter"{
						int koroMove = rn.nextInt(2) + 1;
			if(koroMove == 1){
			return new FightMode(this.shadowPic, this.actionPic, this.player, this.ally,
								 this.allyAttack(this.shadow), this.turnCounter, true);
			}
			else{
				int koroHeal = rn.nextInt(3) + 1;
				if(koroHeal = 3){ 
				return new FightMode(this.shadowPic, this.actionPic, this.player.playerHeal(), this.ally, this.shadow, this.turnCounter, true);
				}
				else{
					return new FightMode(this.shadowPic, this.actionPic, this.player, this.ally.allyHeal(koroHeal), this.turnCounter, true);
				}
			}
		}

		else if(this.turnCounter == 3 && this.moveMade == true && ke == "enter"){
			return new FightMode(this.shadowPic, this.actionPic, this.player, this.ally, this.shadow, 4, false);
		}

		else if(this.turnCounter == 3 && this.moveMade == true && ke == "enter")
	}
	






}