package Model;
import java.util.ArrayList;
import java.util.Random;

public class MainCards {
	
	ArrayList<Category> listCards;
ArrayList<Category> listCardsUsed;

public MainCards(String ageCurrent) {
listCards = new ArrayList<Category>();
listCardsUsed = new ArrayList<Category>();

for(int i = 0; i < 7; i++) {
setUpRandomCards(ageCurrent);
}
}

public MainCards(MainCards m) {
listCards = new ArrayList<Category>();
for(Category c: m.listCards) {
listCards.add(new Category(c));
}
listCardsUsed = new ArrayList<Category>();
for(Category c: m.listCardsUsed) {
listCardsUsed.add(new Category(c));
}
}

public void setUpRandomCards(String currentAge) {
Category randomCategory =randomMap(currentAge);

for (int j=0; j<listCardsUsed.size();j++){
while(randomCategory.equals(listCardsUsed.get(j))){
randomCategory =randomMap(currentAge);

}
}
listCardsUsed.add(randomCategory);
listCards.add(randomCategory);
}
public Category randomMap(String ageCurrent) {
Random rand = new Random();
String typeName = "";
int nbCreditGain =randInt(1,3);
int nbGainUV =randInt(1,4);
int nbCoutUV =randInt(0,3);
if(nbCoutUV == 2) {
nbCoutUV = 1;
}else if(nbCoutUV == 3) {
nbCoutUV = 2;
}

int randomChoiceGain =randInt(0,1);
ArrayList<UV> gainUVs = new ArrayList<UV>();
ArrayList<UV> coutUVs = new ArrayList<UV>();
ArrayList<String> UVNameList = new ArrayList<String>();

UVNameList.add("LO");
UVNameList.add("GE");
UVNameList.add("LP");
UVNameList.add("AG");
UVNameList.add("MI");
UVNameList.add("MT");

String CatName = new String(NameCategoryEnum.getRandomNameCat().toString());

int randomAgeMap = randInt(0,2);

int ageGainCout = Integer.parseInt(ageCurrent);

if(randomAgeMap == 2) {
if(ageGainCout == 2) {
ageGainCout = 1;
}
else if(ageGainCout == 3) {
ageGainCout = 2;
}
}
		
		for (int i=0;i<nbGainUV;i++){
gainUVs.add(new UV(UVNameList.get(rand.nextInt(UVNameList.size()))+ageCurrent+randInt(1,2)));
}
for (int i=0;i<nbCoutUV;i++){
coutUVs.add(new UV(UVNameList.get(rand.nextInt(UVNameList.size()))+(ageGainCout+"")+randInt(1,2)));
}
        
if (CatName.equals("TM")) {
typeName = "Technician and Manager";
}
else if(CatName.equals("EC")){
typeName = "Expressive Communication";
}
else if(CatName.equals("CS")){
typeName = "Scientific Culture";
}
else if(CatName.equals("CG")){
typeName = "General Culture";
}
else if(CatName.equals("OM")){
typeName = "Organize and Manage";
}
else if(CatName.equals("ST")){
typeName = "Internships and Projects";
}

Gain gainUV = null;
if (randomChoiceGain == 0){
gainUV = new Gain(nbCreditGain);
} else{
gainUV = new Gain(gainUVs);
}

return new Category(CatName,typeName,gainUV,coutUVs);
}

public static int randInt(int min, int max) {
Random rand = new Random();
int randomNum = rand.nextInt((max - min) + 1) + min;

    return randomNum;
}

public ArrayList<Category> getListCards() {
return listCards;
}

public void setListCards(ArrayList<Category> listCards) {
this.listCards = listCards;
}



}