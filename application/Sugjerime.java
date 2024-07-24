package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Sugjerime {
	@FXML
    private TextField Destinacion;

    @FXML
    private TableColumn<Reviews, String> Destinacioni;

    @FXML
    private TableColumn<Reviews, String> Europian;

    @FXML
    private TableColumn<Reviews, String> Komente;

    @FXML
    private TextField QytetEuropian;

    @FXML
    private TextField QytetShqiptar;

    @FXML
    private TableColumn<Reviews, String> SherbimGuide;

    @FXML
    private TableColumn<Reviews, String> Shqiptar;

    @FXML
    private TableView<Reviews> TableReview;

    @FXML
    private TextField Udhetim;

    @FXML
    private TableColumn<Reviews, String> UdhetimMeAgjensine;

    @FXML
    private TextArea komente;

    @FXML
    private TextField sherbimGuide;
    @FXML
    private Label idLabel;
    @FXML
    private TextField textfieldFilter;
    
    ObservableList<Reviews> SugjerimeList =FXCollections.observableArrayList();
    
    public void initialize() throws SQLException {
        
    	Destinacioni.setCellValueFactory(new PropertyValueFactory<Reviews, String>("destinacioni"));
    	Europian.setCellValueFactory(new PropertyValueFactory<Reviews, String>("europian"));
    	Shqiptar.setCellValueFactory(new PropertyValueFactory<Reviews, String>("shqiptar"));
    	UdhetimMeAgjensine.setCellValueFactory(new PropertyValueFactory<Reviews, String>("udhetimMeAgjensine"));
    	Shqiptar.setCellValueFactory(new PropertyValueFactory<Reviews, String>("shqiptar"));
    	SherbimGuide.setCellValueFactory(new PropertyValueFactory<Reviews, String>("sherbim"));
    	Komente.setCellValueFactory(new PropertyValueFactory<Reviews, String>("koment"));
    	
    	 textfieldFilter.textProperty().addListener((observable, oldValue, newValue) -> {
	  	        filterData(newValue);
	  	    });
    	
    	popullo();
    	
    	
    }
    
    public void popullo() throws SQLException {
    	SQLConnect connectioncon = new SQLConnect();
        Connection connect = connectioncon.getConnection();
        ObservableList<Reviews> SugjerimeList =FXCollections.observableArrayList();
        
        try {
        	Statement statement=connect.createStatement();
	        ResultSet data1=statement.executeQuery("select * from form");
	        while(data1.next()) {
	        	Reviews review=new Reviews(data1.getString("Destinacioni"),data1.getString("QytetShqiptar"),data1.getString("QytetEuropian"),data1.getString("Komente"),data1.getString("Frekuentim"),data1.getString("SherbimGuide"),data1.getInt("idform"));
	        	SugjerimeList.add(review);
	        	
	        
	        }
	        TableReview.setItems(SugjerimeList);
        }
        catch(SQLException ex) {
        	ex.printStackTrace();
        }
  
    }
    
    public void perditeso(MouseEvent ngjarje) {
        Reviews rev = TableReview.getSelectionModel().getSelectedItem();
        Destinacion.setText(rev.getDestinacioni());
        QytetEuropian.setText(rev.getEuropian());
    	QytetShqiptar.setText(rev.getShqiptar());
    	Udhetim.setText(rev.getUdhetimMeAgjensine());
    	sherbimGuide.setText(rev.getSherbim());
    	komente.setText(rev.getKoment());
    	idLabel.setText(String.valueOf(rev.getId()));
    }
    public void Clear() {
        
        Destinacion.setText("");
        QytetEuropian.setText("");
    	QytetShqiptar.setText("");
    	Udhetim.setText("");
    	sherbimGuide.setText("");
    	komente.setText("");
    	idLabel.setText("");
    }
    
    
    public void fshi(ActionEvent ngjarje) throws SQLException {
        
    	SQLConnect connectioncon = new SQLConnect();
        Connection connect = connectioncon.getConnection();
        PreparedStatement st = connect.prepareStatement("delete from form where idform  = ?"); 	
        st.setString(1, idLabel.getText());
        st.execute();
        popullo();
        Reviews removeRresht = TableReview.getSelectionModel().getSelectedItem();
        SugjerimeList.remove(removeRresht);
    }
    public void filterData(String filterText) {
    	SQLConnect connectioncon = new SQLConnect();
        Connection connect = connectioncon.getConnection();
        ObservableList<Reviews> SugjerimeList =FXCollections.observableArrayList();
        
        try {
        	Statement statement=connect.createStatement();
	        ResultSet data1=statement.executeQuery("select * from form");
	        while(data1.next()) {
	        	Reviews review=new Reviews(data1.getString("Destinacioni"),data1.getString("QytetShqiptar"),data1.getString("QytetEuropian"),data1.getString("Komente"),data1.getString("Frekuentim"),data1.getString("SherbimGuide"),data1.getInt("idform"));
	        	SugjerimeList.add(review);
	        	
	        
	        }
	        TableReview.setItems(SugjerimeList);
        }
        catch(Exception e) {}
        
        FilteredList<Reviews> filteredList = new FilteredList<>(SugjerimeList, sugjerime -> true);

       
        filteredList.setPredicate(sugjerime -> {
            if (filterText == null || filterText.isEmpty()) {
                return true;
            }
            
            return sugjerime.getDestinacioni().toLowerCase().contains(filterText.toLowerCase())
                    || sugjerime.getEuropian().contains(filterText.toLowerCase())
                    || sugjerime.getKoment().toLowerCase().contains(filterText.toLowerCase())
                    || sugjerime.getShqiptar().toLowerCase().contains(filterText.toLowerCase())
                    || sugjerime.getUdhetimMeAgjensine().toLowerCase().contains(filterText.toLowerCase())
                    || sugjerime.getSherbim().toLowerCase().contains(filterText.toLowerCase());
                    
        });

       
        TableReview.setItems(filteredList);
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
