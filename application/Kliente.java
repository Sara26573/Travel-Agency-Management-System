package application;

public class Kliente {
 private String data;
 private String destinacion;
 private int dite;
 private String emer;
 private String mbiemer;
 private String IdNum;
 private String numerTelefoni;
 private float pagesa;
 private int vende;
 private int id;
 
 
 public Kliente(String data, String destinacion, int dite, String emer, String mbiemer, String idNum,
		String numerTelefoni, float pagesa, int vende, int id) {
	super();
	this.data = data;
	this.destinacion = destinacion;
	this.dite = dite;
	this.emer = emer;
	this.mbiemer = mbiemer;
	IdNum = idNum;
	this.numerTelefoni = numerTelefoni;
	this.pagesa = pagesa;
	this.vende = vende;
	this.id = id;
}

public String getData() {
	return data;
}
public void setData(String data) {
	this.data = data;
}
public String getDestinacion() {
	return destinacion;
}
public void setDestinacion(String destinacion) {
	this.destinacion = destinacion;
}
public int getDite() {
	return dite;
}
public void setDite(int dite) {
	this.dite = dite;
}
public String getEmer() {
	return emer;
}
public void setEmer(String emer) {
	this.emer = emer;
}
public String getMbiemer() {
	return mbiemer;
}
public void setMbiemer(String mbiemer) {
	this.mbiemer = mbiemer;
}
public String getIdNum() {
	return IdNum;
}
public void setIdNum(String idNum) {
	IdNum = idNum;
}
public String getNumerTelefoni() {
	return numerTelefoni;
}
public void setNumerTelefoni(String numerTelefoni) {
	this.numerTelefoni = numerTelefoni;
}
public float getPagesa() {
	return pagesa;
}
public void setPagesa(float pagesa) {
	this.pagesa = pagesa;
}
public int getVende() {
	return vende;
}
public void setVende(int vende) {
	this.vende = vende;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

}
