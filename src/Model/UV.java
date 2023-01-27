package Model;
public class UV {

	private String UVName;
private int age;


public UV(String UVName){
this.UVName=UVName;
this. age = -1;
}

public UV(String UVName, int UVAge){
this.UVName=UVName;
this. age = UVAge;
}

public UV(UV uv) {
UVName = uv.UVName;
age = uv.age;
}
public int getAge() {
return age;
}
public String getUVName() {
return UVName;
}



public void setUVName(String UVName) {
this. UVName = UVName;
}

public boolean equals(UV uv) {
if(uv.toString().equals(this.toString())) {
return true;
}
return false;
}
@Override
public String toString() {
return UVName;
}


}