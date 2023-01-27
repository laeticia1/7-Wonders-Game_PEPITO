package Model;
import java.util.ArrayList;
import java.util.Random;
public class BuildingUTBM {

	private int improvementlevel = 0;
private static final int MaxImprovementLevel = 2;
private BuildingNameEnum buildingNameEnum;
private UV BaseUV;
private ArrayList<Gain> listGainPossibleBuilding;
private ArrayList<UV> UVList;
public static ArrayList<BuildingNameEnum> BuildingAlreadyUse = new ArrayList<BuildingNameEnum>();

public BuildingUTBM() {
UVList = new ArrayList<>();
listGainPossibleBuilding = new ArrayList<Gain>();
this.buildingNameEnum = randomBuildingName();
        randomGains();
    }

public void setUvDeBase(UV uv) {
BaseUV = uv;
UVList.add(getUvDeBase());
}
public BuildingNameEnum randomBuildingName() {
BuildingNameEnum randomBuilding = BuildingNameEnum.getRandomBatName();
while(BuildingAlreadyUse.contains(randomBuilding)) {
randomBuilding = BuildingNameEnum.getRandomBatName();
}
BuildingAlreadyUse.add(randomBuilding);

return randomBuilding;
}

public void randomGains() {
Random rand = new Random();

int nbCreditGain;
int nbGainUV;
int randomChoiceGain;
int randomAge;

ArrayList<String> UVNameList = new ArrayList<String>();
UVNameList.add("LO");
UVNameList.add("GE");
UVNameList.add("LP");
UVNameList.add("AG");
UVNameList.add("MI");
UVNameList.add("MT");

for(int i = 0; i < 3; i++) {
nbCreditGain = randInt(1,3);
nbGainUV =randInt(1,3);
randomChoiceGain =randInt(0,1);
randomAge = randInt(1,3);

Gain gainUV = null;
if (randomChoiceGain == 0){
gainUV = new Gain(nbCreditGain);
} else{
ArrayList<UV> gainUVs = new ArrayList<UV>();
for (int y=0;y<nbGainUV;y++){
gainUVs.add(new UV(UVNameList.get(rand.nextInt(UVNameList.size()))+randomAge+randInt(1,2)));
}

gainUV = new Gain(gainUVs);
}

this.listGainPossibleBuilding.add(gainUV);
}

setUvDeBase(new UV(UVNameList.get(rand.nextInt(UVNameList.size()))+1+randInt(1,2)));


}
public static int randInt(int min, int max) {


Random rand = new Random();


int randomNum = rand.nextInt((max - min) + 1) + min;

return randomNum;
}
    

public void addUV(UV uv){
UVList.add(uv);
    }

	public Gain improveBuilding() {
        if(getImprovementLevel() < 0 || getImprovementLevel() > getImprovementLevel()){
            System.out.println("Impossible to improve the building");
            return null;
        }
        Gain res = listGainPossibleBuilding.get(getImprovementLevel());
       
        setImprovementLevel(getImprovementLevel()+1);
        
return res;
}

public UV getUvDeBase() {
return BaseUV;
}

public Gain getBuildingImprovementGain() {
return listGainPossibleBuilding.get(getImprovementLevel());
}

public ArrayList<UV> getResources(){
return this. UVList;
}

public static int getMaxImprovementLevel() {
return MaxImprovementLevel;
}

public int getImprovementLevel() {
return improvementlevel;
}

public void setImprovementLevel(int improvementLevel) {
this.improvementlevel = improvementlevel;
}

public BuildingNameEnum getBuildingNameEnum() {
return buildingNameEnum;
}

}