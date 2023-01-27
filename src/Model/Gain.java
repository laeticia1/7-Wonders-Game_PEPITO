package Model;
import java.util.ArrayList;

public class Gain {

	private int gainCreditsECTS = 0;

private ArrayList<UV> gainUVs = null;


public Gain(int numberCredits ) {
this.gainCreditsECTS = numberCredits;

}
public Gain( ArrayList<UV> listUVs) {
if(listUVs != null) {
this.gainUVs = new ArrayList<UV>(listUVs);
}
}

public int getGainCreditsECTS() {
return gainCreditsECTS;
}

public ArrayList<UV> getGainUVs() {
return gainUVs;
}

public void addGainUV(UV uv){
gainUVs.add(uv);
}

public void setGainCreditsECTS(int gainCreditsECTS) {
this. gainCreditsECTS = gainCreditsECTS;
}
@Override
public String toString() {
if(gainUVs == null) {
return gainCreditsECTS + "ECTS credits";
}
return gainUVs.toString();
}
}