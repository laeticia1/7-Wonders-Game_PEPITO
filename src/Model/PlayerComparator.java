package Model;

import java.util.Comparator;

public class PlayerComparator implements Comparator<Player> {

	@Override
	public int compare(Player o1, Player o2) {
int res = Integer.compare(o2.getCreditsECTS()*50+
o2.getMoney()*25 +
o2.getOwnBuildingUTBM().getImprovementLevel()* 25, o1.getCreditsECTS()*50 +
o1.getMoney()*25 +
o1.getOwnBuildingUTBM().getImprovementLevel() * 25);

return res;
}

}
