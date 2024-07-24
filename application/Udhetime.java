package application;

public class Udhetime {
 private int id;
 private String data;
 private String mberritja;
 private String nisja;
 private String pershkrim;
 private int vende;
 private String destinacioni;
 private int dite;
 private int cmimi;
public Udhetime(int id,String data, String mberritja, String nisja, String pershkrim, int vende, String destinacioni, int dite,
		int cmimi) {
	super();
	this.id=id;
	this.data = data;
	this.mberritja = mberritja;
	this.nisja = nisja;
	this.pershkrim = pershkrim;
	this.vende = vende;
	this.destinacioni = destinacioni;
	this.dite = dite;
	this.cmimi = cmimi;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getData() {
	return data;
}
public void setData(String data) {
	this.data = data;
}
public String getMberritja() {
	return mberritja;
}
public void setMberritja(String mberritja) {
	this.mberritja = mberritja;
}
public String getNisja() {
	return nisja;
}
public void setNisja(String nisja) {
	this.nisja = nisja;
}
public String getPershkrim() {
	return pershkrim;
}
public void setPershkrim(String pershkrim) {
	this.pershkrim = pershkrim;
}
public int getVende() {
	return vende;
}
public void setVende(int vende) {
	this.vende = vende;
}
public String getDestinacioni() {
	return destinacioni;
}
public void setDestinacioni(String destinacioni) {
	this.destinacioni = destinacioni;
}
public int getDite() {
	return dite;
}
public void setDite(int dite) {
	this.dite = dite;
}
public int getCmimi() {
	return cmimi;
}
public void setCmimi(int cmimi) {
	this.cmimi = cmimi;
}
 
}
