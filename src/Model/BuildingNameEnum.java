package Model;

import java.util.Random;

public enum BuildingNameEnum {

	A("A"),
B("B"),
C("C"),
D("D"),
E("E"),
F("F"),
G("G");

private final String batName;

private BuildingNameEnum(String n) {
    batName = n;
}
@Override
public String toString() {
return this. batName;
}
public static BuildingNameEnum getRandomBatName() {
          Random random = new Random();
          BuildingNameEnum res = values()[random.nextInt(values().length)];
          return res;
    }
}