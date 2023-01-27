package Model;
import java.util.ArrayList;

public class Category {

	private String typeCategory;

private String nameCategoryEnum;

private Gain gainValidationCategory;

private ArrayList<UV> coutCategory = null;

public Category(String name, String type, Gain gainValidationCategory, ArrayList<UV> cout) {
this.nameCategoryEnum = name;
this. typeCategory = type;
this. gainValidationCategory = gainValidationCategory;
this.coutCategory = new ArrayList<UV>(cout);

}

public Category(Category c) {
typeCategory = c.typeCategory;
nameCategoryEnum = c.nameCategoryEnum;
gainValidationCategory = c.gainValidationCategory;
coutCategory = new ArrayList<UV>();
for(UV uv : c.coutCategory) {
coutCategory.add(new UV(uv));
}
}
public ArrayList<UV> getCoutCategory() {
return coutCategory;
}

public Gain getGainValidationCategory() {
return gainValidationCategory;
}

public void setGainValidationCategory(Gain gainValidationCategory) {
this. gainValidationCategory = gainValidationCategory;
}


public void setCoutCategory(ArrayList<UV> coutCategory) {
this.coutCategory = coutCategory;
}

public void setTypeCategory(String typeCategory) {
this. typeCategory = typeCategory;
}

public String getEnumCategoryName() {
return nameCategoryEnum;
}

public void setNameCategoryEnum(String nameCategoryEnum) {
this.nameCategoryEnum = nameCategoryEnum;
}
@Override
public String toString() {
String cout = " Cost: " + coutCategory.toString() + ")";
if(coutCategory.size() == 0) {
cout = "Free! )";
}
return nameCategoryEnum + " " + typeCategory + " (Gain validation: " + gainValidationCategory.toString() + " | " + cout;
}

public boolean equals(Category category) {
if(this != null) {
if (this == category) {
return true;
}
else if(category.nameCategoryEnum.equals(nameCategoryEnum)&& category.typeCategory.equals(typeCategory)&& category.gainValidationCategory.equals(gainValidationCategory) && category.coutCategory.equals(coutCategory) ){
return true;
}
}
return false;
  
}
}