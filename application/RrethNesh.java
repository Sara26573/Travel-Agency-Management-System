package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.stage.Stage;

public class RrethNesh {
	@FXML
    private TextField Destinacion;

    @FXML
    private RadioButton FrekuentimJo;

    @FXML
    private RadioButton FrekuentimPo;

    @FXML
    private TextArea Komente;

    @FXML
    private TextField QytetEuropian;

    @FXML
    private TextField QytetShqiptar;

    @FXML
    private RadioButton SherbimMjaftueshem;

    @FXML
    private RadioButton SherbimPak;

    @FXML
    private RadioButton SherbimShume;
    
    @FXML
    private RadioButton aspak;
    
    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label label3;
    
    @FXML
    private Label label4;

    @FXML
    private Label label5;

    
    
    public void Regjistro() {
    	
    	SQLConnect connectioncon = new SQLConnect();
        Connection connect = connectioncon.getConnection();
        
        
        try {
			PreparedStatement deklarate = connect.prepareStatement("insert into form(QytetEuropian,QytetShqiptar,Frekuentim,Destinacioni,SherbimGuide,Komente) values(?,?,?,?,?,?)");
			
	        deklarate.setString(1, QytetEuropian.getText());
	        if(QytetEuropian.getText().equals("")) {
	        	label1.setText("Duhet te plotesoni kete hapesire.");
	        }
	        deklarate.setString(2, QytetShqiptar.getText());
	        if(QytetShqiptar.getText().equals("")) {
	        	label2.setText("Duhet te plotesoni kete hapesire.");
	        }
	        if(FrekuentimJo.isSelected()) {
	        deklarate.setString(3,"Jo");
	        }
	        else if(FrekuentimPo.isSelected()) {
	        deklarate.setString(3, "Po");
	        }
	        else {
	           label3.setText("Duhet te selektoni nje opsion.");
	        }
	        
	        if(FrekuentimPo.isSelected()) {
		        deklarate.setString(4, Destinacion.getText());
		        if(Destinacion.getText().equals("")) {
		        	label4.setText("Duhet te plotesoni kete hapesire.");
		        	return;
		        }
		        
		            if(SherbimPak.isSelected()){
			        deklarate.setString(5, "Pak");
			        }
			        else if(aspak.isSelected()) {
			        deklarate.setString(5, "Aspak");
			        }
			        else if(SherbimShume.isSelected()) {
				        deklarate.setString(5, "Shume");
				    }
			        else if(SherbimMjaftueshem.isSelected()) {
				        deklarate.setString(5, "Mjaftueshem");
				     }
			        else {
			        	deklarate.setString(5, " ");
			        	label5.setText("Duhet te plotesoni kete hapesire.");
			        	return;
			        }
		    }
 	        else if(FrekuentimJo.isSelected()){
 	        	deklarate.setString(4, "");
 	        	deklarate.setString(5, " ");
 	        }
	        
	        deklarate.setString(6, Komente.getText());
	        if(Komente.getText().equals("")) {
	        	
	        }
	      
	        deklarate.execute();
	        Alert alert=new Alert(Alert.AlertType.INFORMATION);
	   		alert.setTitle("Forma u regjistrua");
	   		alert.setHeaderText("Forma u plotesua me sukses!Faleminderit per bashkepunimin!");
	 	    alert.showAndWait();
	 	    pastro();
	 	    
	        }
			
		 catch (SQLException e) {

			e.printStackTrace();
		}
       
        }
    public void pastro() {
    	Destinacion.setText("");
    	Komente.setText("");
    	QytetEuropian.setText("");
    	QytetShqiptar.setText("");
    	FrekuentimJo.setSelected(false);
    	FrekuentimPo.setSelected(false);
    	SherbimPak.setSelected(false);
    	aspak.setSelected(false);
    	SherbimShume.setSelected(false);
    	SherbimMjaftueshem.setSelected(false);
    	label1.setText("");
    	label2.setText("");
    	label3.setText("");
    	label4.setText("");
    	label5.setText("");
    	
    	
    }
    Scene scene;
	Parent root;
	Stage stage;

    
   public void SignOut(ActionEvent event) throws IOException {
    NdrroFaqe(event,"LogIn.fxml");
	   
   }
   public void PaketaSell(ActionEvent event) throws IOException{
	NdrroFaqe(event,"Paketa.fxml");
   }
   public void ShtoPaketa1(ActionEvent event) throws IOException{
		NdrroFaqe(event,"ShtoPaketa.fxml");
	   }
   public void ShtoKliente1(ActionEvent event) throws IOException{
		NdrroFaqe(event,"ShtoKliente.fxml");
	   }
   public void FaqeKryesore(ActionEvent event) throws IOException{
		NdrroFaqe(event,"MainPage.fxml");
   }
   public void RrethNesh1(ActionEvent event) throws IOException{
		NdrroFaqe(event,"RrethNesh.fxml");
   }
   
   public void NdrroFaqe(ActionEvent event,String a) throws IOException {
	   root=FXMLLoader.load(getClass().getResource(a));
 	   stage=(Stage)((Node)event.getSource()).getScene().getWindow();
       scene=new Scene(root);
       stage.setScene(scene);
       stage.show();
   }
}
