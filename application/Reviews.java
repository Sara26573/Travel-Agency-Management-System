package application;

public class Reviews {
private String destinacioni;
private String shqiptar;
private String europian;
private String koment;
private String udhetimMeAgjensine;
private String sherbim;
private int id;
public Reviews(String destinacioni, String shqiptar, String europian, String koment, String udhetimMeAgjensine,
		String sherbim, int id) {
	super();
	this.destinacioni = destinacioni;
	this.shqiptar = shqiptar;
	this.europian = europian;
	this.koment = koment;
	this.udhetimMeAgjensine = udhetimMeAgjensine;
	this.sherbim = sherbim;
	this.id = id;
}
public String getDestinacioni() {
	return destinacioni;
}
public void setDestinacioni(String destinacioni) {
	this.destinacioni = destinacioni;
}
public String getShqiptar() {
	return shqiptar;
}
public void setShqiptar(String shqiptar) {
	this.shqiptar = shqiptar;
}
public String getEuropian() {
	return europian;
}
public void setEuropian(String europian) {
	this.europian = europian;
}
public String getKoment() {
	return koment;
}
public void setKoment(String koment) {
	this.koment = koment;
}
public String getUdhetimMeAgjensine() {
	return udhetimMeAgjensine;
}
public void setUdhetimMeAgjensine(String udhetimMeAgjensine) {
	this.udhetimMeAgjensine = udhetimMeAgjensine;
}
public String getSherbim() {
	return sherbim;
}
public void setSherbim(String sherbim) {
	this.sherbim = sherbim;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

}
