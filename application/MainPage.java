package application;

import java.io.IOException;

import javafx.event.ActionEvent;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

public class MainPage {
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
   public void RrethNesh(ActionEvent event) throws IOException{
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
