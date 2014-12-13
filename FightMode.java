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
		if(this.turnCounter == 1){
		 	if(this.moveMade == false && ke == "1"){
				return new FightMode(this.shadowPic, protag pic, new Character(player.hP - (player.hP / 10), player.sP),
									 this.ally, this.player.bash(this.shadow), this.turnCounter, true);
			}
			else if(this.move == false && ke == "2"){
				return new FightMode(this.shadowPic protag pic, new Character(this.player.hP, this.player.sP - 3), this.ally, this.player.agi(this.shadow), this.turnCounter, true);
			}
			else if(this.move == false && ke == "3"){
				return new FightMode(this.shadowPic protag poc, this.player.dia(), this.ally, this.shadow, this.turnCounter, true);
			}
			else if(this.move == false && ke == "4"){
				return new FightMode(this.shadowPic, protag pic, this.player, this.player.allyHeal(this.ally, 1), this.shadow, this.turnCounter, true);
			}
			else if(this.move == false && ke == "5"){
				return new FightMode(this.shadowPic, protag pic, this.player, this.player.allyHeal(this.ally, 2), this.shadow, this.turnCounter, true);
			}
			else if(this.moveMade == false && ke == "i"){
				return new ItemMode(this.player.itembag, this.player.hP, this.player.sP, this.ally.allyOneHP, this.ally,allyTwoHP, this);
			}
			else if(this.moveMade == true && ke == "enter"){
				return new FightMode(this.shadowPic, eliz pic, this.player, this.ally, this.shadow, 2, false);
			}
			else{
				return this;
			}
		}
		else if(this.turnCounter == 2){
			if(this.moveMade == false && ke == "enter"){
				int elizMove = rn.nextInt(2) + 1;
				if(elizMove == 1){
				return new FightMode(this.shadowPic, this.actionPic, this.player, this.ally,
								 new ShadowFight(this.shadow.hP - 50), this.turnCounter, true);
				}
				else{
					int elizHeal = rn.nextInt(3) + 1;
					if(elizHeal = 3){ 
					return new FightMode(this.shadowPic, this.actionPic, this.ally.playerHeal(this.player), this.ally, this.shadow, this.turnCounter, true);
					}
					else{
						return new FightMode(this.shadowPic, this.actionPic, this.player, this.ally.allyHeal(elizHeal), this.turnCounter, true);
					}
				}
			}
			else if(this.moveMade == true && ke == "enter"){
				return new FightMode(this.shadowPic, koropic, this.player, this.ally, this.shadow, 3, false);
			}
			else{
				return this;
			}
		}
		else if(this.turnCounter == 3){
			if(this.moveMade == false && ke == "enter"{
				int koroMove = rn.nextInt(2) + 1;
				if(koroMove == 1){
					return new FightMode(this.shadowPic, this.actionPic, this.player, this.ally,
										 this.allyAttack(this.shadow), this.turnCounter, true);
				}
				else{
					int koroHeal = rn.nextInt(3) + 1;
					if(koroHeal = 3){ 
						return new FightMode(this.shadowPic, this.actionPic, this.ally.playerHeal(this.player), this.ally, this.shadow, this.turnCounter, true);
					}
					else{
						return new FightMode(this.shadowPic, this.actionPic, this.player, this.ally.allyHeal(koroHeal), this.turnCounter, true);
					}
				}
			}
			else if(this.moveMade == true && ke == "enter"){
				return new FightMode(this.shadowPic, this.actionPic, this.player, this.ally, this.shadow, 4, false);
			}
			else{
				return this;
			}
		}
		else if(this.turnCounter == 4){
			if(this.moveMade == false && ke == "enter"){
				int shadowAttack = rn.nextInt(3) + 1;
				if(shadowAttack == 3){
					return new FightMode(this.shadowPic, this.actionPic, this.player.shadowAttack(), this.ally, this.shadow, 4, true);
				}
				else{
					return new FightMode(this.shadowPic, this.actionPic, this.player, this.ally.shadowAttack(shadowAttack), this.shadow, 4, true);
				}
			}
			else if(this.moveMade == true && ke == "enter"){
				return new FightMode(this.shadowPic, this.actionPic, this.player, this.ally, this.shadow, 1, false);
			}
			else{
				return this;
			}
		}
		else{
			return this;
		}
	}
	public World onTick(){
		if(this.shadow.hP =< 0){
			return new WalkMode();
		}
		else{
			return this;
		}
	}

	public WorldEnd worldEnds(){
		if(this.player.hP =< 0){
			return new WorldEnd(true, new OverlayImages(this.makeImage()),
				new OverlayImages(this.elizImage,
				new TextImage(new Posn((worldWidth / 2), (worldLength / 2)),
					("Oh dear, I guess we lose..."), Color.black)));
		}
	}






}