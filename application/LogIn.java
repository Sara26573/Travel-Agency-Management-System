package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LogIn {
    @FXML
    private PasswordField password;

    @FXML
    private TextField username;
    
    @FXML
    private PasswordField passwordSignUp;

    @FXML
    private TextField usernameSignUP;
	Scene scene;
	Parent root;
	Stage stage;
    
    
    public void LogInFunc(ActionEvent event) throws IOException {
    	boolean found=false;
    	String username1=username.getText();
    	String password1=password.getText();
    	
    	SQLConnect connectioncon = new SQLConnect();
        Connection connect = connectioncon.getConnection();
        try {
			Statement statement=connect.createStatement();
	        ResultSet data=statement.executeQuery("select * from members");
	        
	        while(data.next()) {
	        	if(username1.equals(data.getString("username"))&&password1.equals(data.getString("password"))){
	        		found=true;
	          
	            	  root=FXMLLoader.load(getClass().getResource("MainPage.fxml"));
	            	  stage=(Stage)((Node)event.getSource()).getScene().getWindow();
	                  scene=new Scene(root);
	                  stage.setScene(scene);
	                  stage.show();
	                  break;
	        	}
	        
	        }
	        if(!found) {
	        	Alert alert=new Alert(Alert.AlertType.INFORMATION);
	   		    alert.setTitle("Gabim!");
	   		    alert.setHeaderText("Username ose Password i gabuar.");
	            alert.setContentText("Sigurohuni te keni vendosur te dhenat e sakta");
	 	        alert.showAndWait();
	 	       pastro();
	 	        
	        }
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
        
    	
    }
    
    public void SignUp(ActionEvent event) throws IOException {
    	
    	  root=FXMLLoader.load(getClass().getResource("SignUp.fxml"));
    	  stage=(Stage)((Node)event.getSource()).getScene().getWindow();
          scene=new Scene(root);
          stage.setScene(scene);
          stage.show();
       
    }
    public void HaveAccount(ActionEvent event) throws IOException {
    	
  	  root=FXMLLoader.load(getClass().getResource("LogIn.fxml"));
  	  stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
     
  }
    
    public void SignUp2(ActionEvent event) throws IOException {
  
    	
    	boolean found=false;
    	String username2=usernameSignUP.getText();
    	String password2=passwordSignUp.getText();
    	SQLConnect connectioncon = new SQLConnect();
        Connection connect = connectioncon.getConnection();
    	try {
			PreparedStatement deklarate = connect.prepareStatement("insert into members(username,password) values(?,?)");
			Statement statement=connect.createStatement();
	        ResultSet data=statement.executeQuery("select * from members");
	        
	        while(data.next()) {
	        	
	        	if(username2.equals(data.getString("username"))){
	        		found=true;
	        		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		   		    alert.setTitle("Vendosni nje username tjeter ");
		   		    alert.setHeaderText("Username i perdorur.");
		            alert.setContentText("Ju lutem vendosni nje tjeter");
		 	        alert.showAndWait();
	        		
	            	
	        	}
	
	        }
	        
	       	if(username2.equals("") || password2.equals("")) {
        		found=true;
        		Alert alert=new Alert(Alert.AlertType.INFORMATION);
	   		    alert.setTitle("Fushe Bosh");
	   		    alert.setHeaderText("Keni lene nje fushe bosh!");
	            alert.setContentText("Ju lutem plotesoni te gjitha fushat.");
	 	        alert.showAndWait();
        	}
	        if(!found ) {
	        	deklarate.setString(1, username2);
	            deklarate.setString(2, password2);
	            deklarate.execute();
	        	Alert alert=new Alert(Alert.AlertType.INFORMATION);
	   		    alert.setTitle("Regjistrim");
	   		    alert.setHeaderText("U regjistruat ne aplikacion.");
	            alert.setContentText("Regjistrim i suksesshem!");
	 	        alert.showAndWait();
	 	        pastro();
	 	  
	       
	 	        
	        }
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
    }
    
    
    public void pastro() {
    	password.setText("");
    	username.setText("");
    	passwordSignUp.setText("");
    	usernameSignUP.setText("");
    	
    }
    
    
}
