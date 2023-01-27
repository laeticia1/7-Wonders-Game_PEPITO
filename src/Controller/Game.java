/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.ArrayList;
import java.util.Collections;

import Model.Category;
import Model.Player;
import Model.PlayerComparator;
import Model.MainCards;
import Model.UV;
import View.View;

public class Game {

	private int numberPlayers;
private int currentturn;
private int CurrentAge;
private static final int ageMax = 3;
private static final int lapMax = 6;

private boolean exchangeClockwise = true;

private int indexCurrentPlayer;
private Player CurrentPlayer;
private ArrayList<Player> playerList;
private static View view = null;
private static Game instanceGame = null;

Game() {
this.currentturn = 1;
this.CurrentAge = 1;
this.playerList = new ArrayList<Player>();
this.indexCurrentPlayer = 0;
}


public static void setUpSingleton(View v) {
view = v;
}
public static Game getGameInstance(){
if(instanceGame == null) {
instanceGame = new Game();
}
return instanceGame;
}



public void nextage() {
if(CurrentAge < ageMax) {
CurrentAge++;
view.displayNewAge();
for(Player player: playerList) {
player.newHandCards((CurrentAge+1)+"");
}
currentturn = 1;

indexCurrentPlayer = 0;
CurrentPlayer = playerList.get(indexCurrentPlayer);

if(exchangeClockwise) {
exchangeClockwise = false;
} else {
exchangeClockwise = true;
}
} else {
endGame();
}
}

public void turnNext() {
if(currentturn < ageMax) {
currentturn++;
view.displayNewTower();
indexCurrentPlayer = 0;
CurrentPlayer = playerList.get(indexCurrentPlayer);
MainCards mainTemp;
ArrayList<MainCards> listHands = new ArrayList<MainCards>();
if(exchangeClockwise) {
mainTemp = playerList.get(playerList.size()-1).getMainCards();
for(int i = 0; i < playerList.size() - 1; i++) {
listHands.add(new MainCards(playerList.get(i).getMainCards()));
}
for(int i = 1; i < playerList.size(); i++) {
playerList.get(i).setMainCards(listHands.get(i-1));
}
CurrentPlayer.setMainCards(new MainCards(mainTemp));
} else {
mainTemp = CurrentPlayer.getMainCards();
for(int i = 1; i < playerList.size(); i++) {
listHands.add(new MainCards(playerList.get(i).getMainCards()));
}

for(int i = 0; i < playerList.size() - 1; i++) {
playerList.get(i).setMainCards(playerList.get(i+1).getMainCards());
}
playerList.get(playerList.size()-1).setMainCards(new MainCards(mainTemp));

}

		} else {
nextage();
}
}

public void endGame() {
view.endPart();
}

public void passNextPlayer() {
if(indexCurrentPlayer < playerList.size() - 1) {
indexCurrentPlayer++;
CurrentPlayer = playerList.get(indexCurrentPlayer);
}else{
turnNext();
}

}
public ArrayList<String> getRanking(){
ArrayList<Player> players = new ArrayList<Player>(playerList);
Collections.sort(players, new PlayerComparator());

ArrayList<String> res = new ArrayList<String>();
for(int i = 0; i < players.size(); i++) {
res.add((i+1) + "- " + players.get(i).getPlayerName() + " (ECTS: " + players.get(i).getCreditsECTS() + " | Money: " +
players.get(i).getMoney()+ " | Building level: " + players.get(i).getOwnBuildingUTBM().getImprovementLevel()+ ")");

}

return res;
}



public void discardMap(Category map) {
CurrentPlayer.discardMap(map);
}

public void improveBuilding(Category map) {
CurrentPlayer.improveBuilding(map);
}

public void validateCategory(Category map) {

CurrentPlayer.validateCategory(map);

}
public boolean buyResourcesNeighbors(Player player, UV uv) {
return CurrentPlayer.buyUvNeighbor(player, uv);
}
public ArrayList<UV> getPlayerResources(Player player){
ArrayList<UV> resourceList = new ArrayList<UV>(player.getOwnBuildingUTBM().getResources());

return resourceList;
}
public ArrayList<Player> getNeighborList(){
ArrayList<Player> res = new ArrayList<Player>(playerList);
res.remove(CurrentPlayer);

return res;
}
public void addPlayer(String playerName) {
Player newPlayer = new Player(playerName);

if(playerAlreadyExists(playerName)) {
throw new IllegalArgumentException("The player already exists!");
}
this.playerList.add(newPlayer);

}



public boolean playerAlreadyExists(String playerName) {
for(Player j: this.playerList) {
if(j.getPlayerName().equals(playerName)) {
return true;
}
}
return false;
}
public int getAgeMax() {
return ageMax;
}

public int getLapMax() {
return lapMax;
}

public int getNumberPlayers() {
return numberPlayers;
}

public void setNumberPlayers(int numberPlayers) {
this.numberPlayers = numberPlayers;
}

public int getCurrentTour() {
return currentturn;
}

public void setCurrentTurn(int currentTurn) {
this.currentturn = currentturn;
}
	public int getCurrentAge() {
return CurrentAge;
}

public void setCurrentAge(int CurrentAge) {
this. CurrentAge = CurrentAge;
}

public boolean isClockwiseExchange() {
return exchangeClockwise;
}

public Player getCurrentPlayer() {
return CurrentPlayer;
}

public void setCurrentPlayer(Player currentplayer) {
this.CurrentPlayer = currentplayer;
}

public ArrayList<Player> getListPlayers() {
return playerList;
}
}