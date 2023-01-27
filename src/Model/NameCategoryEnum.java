package Model;

import java.util.Random;

public enum NameCategoryEnum {

	EC("EC"),

CG("CG"),

OM("OM"),

TM("TM"),

CS("CS"),

ST("ST");

private final String catName;
private NameCategoryEnum(String n) {
catName = n;
}
@Override
public String toString() {
return this. catName;
}
public static NameCategoryEnum getRandomNameCat() {
Random random = new Random();
return values()[random.nextInt(values().length)];
}
}