package Model;

public class Player {

	private int money = 3;

private int creditsECTS = 0;

private String playerName;

private MainCards mainMaps;

private BuildingUTBM ownsBuildingUTBM;

public Player(String playerName) {
this. playerName = playerName;
this. mainMaps = new MainCards("1");
this.ownsBuildingUTBM = new BuildingUTBM();
}

public void newHandCards(String age) {
this. mainMaps = new MainCards(age + "");
}
public void discardMap(Category map) {
mainMaps.getListCards().remove(map);
money += 3;
}

public void chooseMap(Category map) {
Gain gain = map.getGainValidationCategory();
if(gain.getGainUVs() == null) {
creditsECTS += gain.getGainCreditsECTS();
} else {
for(UV uv: gain.getGainUVs()) {
ownsBuildingUTBM.addUV(uv);
}
}
mainMaps.getListCards().remove(map);
}

public boolean buyUvNeighbor(Player player, UV uv) {
if(money >= 3) {
ownsBuildingUTBM.getResources().add(uv);
player.money += 3;
money -= 3;
return true;
}
return false;
}

public boolean validateCategory(Category category) {
if(category.getCoutCategory().size() == 0) {
mainMaps.getListCards().remove(category);
return true;
}
boolean verif = false;
for(UV cost: category.getCoutCategory()) {
verif = false;
for(UV resource: ownsBuildingUTBM.getResources()) {
if(resource.equals(cost)) {
verif = true;
}
}
if(!verif) {
return verif;
}
}
mainMaps.getListCards().remove(category);
		Gain gainValide = category.getGainValidationCategory();
if(gainValide.getGainUVs() == null) {
creditsECTS += gainValide.getGainCreditsECTS();
} else {
ownsBuildingUTBM.getResources().addAll(gainValide.getGainUVs());
}

return verif;

}

public boolean improveBuilding(Category map) {
Gain res = ownsBuildingUTBM.improveBuilding();

if(res != null) {
mainMaps.getListCards().remove(map);
if(res.getGainUVs() != null) {
ownsBuildingUTBM.getResources().addAll(res.getGainUVs());
} else {
creditsECTS += res.getGainCreditsECTS();
}
return true;
}
return false;

}

public int calculateTotalCredit() {
return creditsECTS;
}

public BuildingUTBM getOwnBuildingUTBM() {
return ownsBuildingUTBM;
}

public void setOwnBuildingUTBM(BuildingUTBM ownsBuildingUTBM) {
this.ownsBuildingUTBM = ownsBuildingUTBM;
}

public int getMoney() {
return money;
}

public void setMoney(int money) {
this.money = money;
}

public int getCreditsECTS() {
return creditsECTS;
}

public void setCreditsECTS(int creditsECTS) {
this. creditsECTS = creditsECTS;
}

public String getPlayerName() {
return playerName;
}

public void setPlayerName(String playerName) {
this. playerName = playerName;
}

public MainCards getMainCards() {
return mainMaps;
}

public void setMainCards(MainCards mainCards) {
this.mainMaps = mainCards;
}
@Override
public String toString() {
return playerName;
}
}