package application;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PaketatNeGjendje {
	  @FXML
	    private TableColumn<Udhetime, String> Data;

	    @FXML
	    private TableColumn<Udhetime, String> Mberritja;

	    @FXML
	    private TableColumn<Udhetime, String> Nisja;

	    @FXML
	    private ComboBox<Integer> NumerVendesh;

	    @FXML
	    private TableColumn<Udhetime, String> Pershkrim;

	    @FXML
	    private TableView<Udhetime> TeDhenaVende;

	    @FXML
	    private TableColumn<Udhetime, Integer> Vende;

	    @FXML
	    private TableColumn<Udhetime, String> destinacioni;

	    @FXML
	    private TableColumn<Udhetime, Integer> diteUdhetimi;

	    @FXML
	    private TableColumn<Udhetime, Integer> cmimi;
	    
	    @FXML
	    private TextField aIdTextfield;

	    @FXML
	    private TextField aNumerTelefonitextfield;

	    @FXML
	    private TextField aemerTextField;

	    @FXML
	    private TextField ambiemberTextfield;


	    @FXML
	    private ImageView imageview;
	    @FXML
	    private ScrollPane ScrollPane1;
	    @FXML
	    private Label data;

	    @FXML
	    private Label destinacion;
	    
	    @FXML
	    private Label mberritje;

	    @FXML
	    private Label nisje;

	    @FXML
	    private Label udhetimidite;
	    @FXML
	    private Label cmimi1;
	    @FXML
	    private Label LabelNrVende;

	    @FXML
	    private Button rezervoButton;
	    

	    @FXML
	    private Label mesazhPerrimbsuhje;
	    @FXML
	    private TableColumn<Udhetime, Integer> id;
	    
	    @FXML
	    private Label Vende1;

	    @FXML
	    private Label ID;
	    
	    @FXML
	    private TextField textfieldFilter;


	    
	    ObservableList <Integer> vendeNeGjendje=FXCollections.observableArrayList();
	    
	    
	  
	    public void initialize() {
	    	popullo();
	    
	    	Data.setCellValueFactory(new PropertyValueFactory<Udhetime, String>("data"));
	    	Mberritja.setCellValueFactory(new PropertyValueFactory<Udhetime, String>("mberritja"));
	    	Nisja.setCellValueFactory(new PropertyValueFactory<Udhetime, String>("nisja"));
	    	Vende.setCellValueFactory(new PropertyValueFactory<Udhetime, Integer>("vende"));
	    	Pershkrim.setCellValueFactory(new PropertyValueFactory<Udhetime, String>("pershkrim"));
	    	destinacioni.setCellValueFactory(new PropertyValueFactory<Udhetime, String>("destinacioni"));
	    	diteUdhetimi.setCellValueFactory(new PropertyValueFactory<Udhetime, Integer>("dite"));
	    	cmimi.setCellValueFactory(new PropertyValueFactory<Udhetime, Integer>("cmimi"));
	    	
	    	 textfieldFilter.textProperty().addListener((observable, oldValue, newValue) -> {
	    	        filterData(newValue);
	    	    });

	    	
	     	
	    	NumerVendesh.setItems(vendeNeGjendje);
	    	
	    	
	    }
	    public void popullo() {

	    	SQLConnect connectioncon = new SQLConnect();
	        Connection connect = connectioncon.getConnection();
	        ObservableList<Udhetime> udhetimelist=FXCollections.observableArrayList();
	        try {
	        	Statement statement=connect.createStatement();
		        ResultSet data1=statement.executeQuery("select * from paketa");
		        while(data1.next()) {
		        	Udhetime udhetime=new Udhetime(data1.getInt("idpaketa"),data1.getString("Data"),data1.getString("Mberritja"),data1.getString("Nisja"),data1.getString("Pershkrimi"),data1.getInt("Vende"),data1.getString("Destinacioni"),data1.getInt("Dite"),data1.getInt("Cmimi"));
		        	udhetimelist.add(udhetime);
		        	
		        	
		        }
		        TeDhenaVende.setItems(udhetimelist);
	        }
	        catch(SQLException ex) {
	        	ex.printStackTrace();
	        }
	    }
	    
	    
	    float totalPrice;
	    int VendeP=0;
	    int identification;
	    public void SetValues()
	    {  
	    	SQLConnect connectioncon = new SQLConnect();
	        Connection connect = connectioncon.getConnection();
	        
	        //marrja e instancave te objektit te selektuar
	        
	     	Udhetime p=TeDhenaVende.getSelectionModel().getSelectedItem();
	     	cmimi1.setText(Integer.toString(p.getCmimi())+".0 €");
	     	data.setText(p.getData());
	     	destinacion.setText(p.getDestinacioni());
	     	nisje.setText(p.getNisja());
	     	mberritje.setText(p.getMberritja());
	     	udhetimidite.setText(Integer.toString(p.getDite()));
	     	
	     	
	    	
            identification=p.getId();
	 
	     	Label label=new Label(p.getPershkrim());
	     	label.setPrefWidth(160);
	        label.setWrapText(true);
            ScrollPane1.setContent(label);
            
            VendeP=p.getVende();
            
            for(int i=1;i<=VendeP;i++) {
            	vendeNeGjendje.add(i);
            }
           
            NumerVendesh.setOnAction(event->{               //llogarit cmimin kur zgjedhet vendi ne combobox
                int SeatNumber=NumerVendesh.getSelectionModel().getSelectedItem();
                totalPrice=SeatNumber*p.getCmimi();
                cmimi1.setText(Float.toString(totalPrice)+" €");
                LabelNrVende.setText(Integer.toString(SeatNumber));
            });
  
            	   
	        
	        try {
	        	Statement statement=connect.createStatement();
		        ResultSet data1=statement.executeQuery("select imazh from paketa where idpaketa="+identification);
		        while(data1.next()) {
		        	    Blob blob = data1.getBlob("imazh");
			            InputStream inputStream = blob.getBinaryStream();
			            Image image = new Image(inputStream);
			            imageview.setImage(image);
		        }
	        }
	        catch(SQLException ex) {
	        	ex.printStackTrace();
	        }
	    }
	    

             public void pastro() {
            	 aemerTextField.setText("");
            	 ambiemberTextfield.setText("");
            	 aNumerTelefonitextfield.setText("");
            	 aIdTextfield.setText("");
	
}

  public void blej() {
    SQLConnect connectioncon = new SQLConnect();
    Connection connect = connectioncon.getConnection();

            	   
    Udhetime selectedPaketa = TeDhenaVende.getSelectionModel().getSelectedItem();
    int selectedPaketaId = selectedPaketa.getId();

            	    
    int selectedSeats = NumerVendesh.getSelectionModel().getSelectedItem();
    
    if(aemerTextField.getText().equals("")||ambiemberTextfield.getText().equals("")||aNumerTelefonitextfield.getText().equals("")||aIdTextfield.getText().equals(""))
    {
    	 Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Error");
         alert.setHeaderText("Keni lene fusha pa plotesuar");
         alert.setContentText("Ju lutem mbushni te gjitha fushat!");
         alert.showAndWait();
         return;
    }
    try {
            	       
      PreparedStatement selectStatement = connect.prepareStatement("SELECT Vende FROM paketa WHERE idpaketa = ?");
      selectStatement.setInt(1, selectedPaketaId);
      ResultSet resultSet = selectStatement.executeQuery();

      if (resultSet.next()) {
         int currentSeats = resultSet.getInt("Vende");

            	      
           if (currentSeats >= selectedSeats) {
            	              
             int remainingSeats = currentSeats - selectedSeats; //vendos numrin e vendeve te mbetura pas blerjes
             PreparedStatement updateSeatsStatement = connect.prepareStatement("UPDATE paketa SET Vende = ? WHERE idpaketa = ?");
             updateSeatsStatement.setInt(1, remainingSeats);
             updateSeatsStatement.setInt(2, selectedPaketaId);
             updateSeatsStatement.execute();
             

            	              
             PreparedStatement reservationStatement = connect.prepareStatement("INSERT INTO kliente (emer, mbiemer, numertelefoni, idnumber, destinacioni, data, numervendesh, PagesaEuro, Dite) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
             reservationStatement.setString(1, aemerTextField.getText());
             reservationStatement.setString(2, ambiemberTextfield.getText());
             reservationStatement.setString(3, aNumerTelefonitextfield.getText());
             reservationStatement.setString(4, aIdTextfield.getText());
             reservationStatement.setString(5, destinacion.getText());
             reservationStatement.setString(6, data.getText());
             reservationStatement.setInt(7, selectedSeats);
             reservationStatement.setFloat(8, totalPrice);
             reservationStatement.setInt(9, selectedPaketa.getDite());
             reservationStatement.execute();
            	                
            	               
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Rezervimi u krye");
             alert.setHeaderText("Faleminderit për rezervimin!");
             alert.setContentText("");
             alert.showAndWait();
            	              
            
            
             } else {
            	               
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Error");
             alert.setHeaderText("Nuk ka mjaftueshëm vende të lira.");
             alert.setContentText("Ju lutem zgjidhni një numër më të vogël të vendeve.");
             alert.showAndWait();
             }}}
                 catch (SQLException ex) {
            	        ex.printStackTrace();
            	    }
            popullo();
     	    }
  
  public void filterData(String filterText) {
      SQLConnect connectioncon = new SQLConnect();
      Connection connect = connectioncon.getConnection();
      ObservableList<Udhetime> liste = FXCollections.observableArrayList();

      try {
          Statement statement = connect.createStatement();
          ResultSet data1 = statement.executeQuery("SELECT * FROM paketa");
          while (data1.next()) {
              Udhetime udhetime = new Udhetime(data1.getInt("idpaketa"), data1.getString("Data"),
                      data1.getString("Mberritja"), data1.getString("Nisja"), data1.getString("Pershkrimi"),
                      data1.getInt("Vende"), data1.getString("Destinacioni"), data1.getInt("Dite"),
                      data1.getInt("Cmimi"));
              liste.add(udhetime);
          }
      } catch (SQLException e) {
          e.printStackTrace();
      }

      FilteredList<Udhetime> filteredList = new FilteredList<>(liste, udhetime -> true);

      filteredList.setPredicate(udhetime -> {
          if (filterText == null || filterText.isEmpty()) {
              return true;
          }

          String filterTextLower = filterText.toLowerCase();
          return udhetime.getDestinacioni().toLowerCase().contains(filterTextLower)
                  || udhetime.getData().toLowerCase().contains(filterTextLower)
                  || udhetime.getMberritja().toLowerCase().contains(filterTextLower)
                  || udhetime.getNisja().toLowerCase().contains(filterTextLower)
                  || udhetime.getPershkrim().toLowerCase().contains(filterTextLower)
                  || String.valueOf(udhetime.getVende()).toLowerCase().contains(filterTextLower)
                  || String.valueOf(udhetime.getDite()).toLowerCase().contains(filterTextLower)
                  || String.valueOf(udhetime.getCmimi()).toLowerCase().contains(filterTextLower);
      });

      TeDhenaVende.setItems(filteredList);
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
 public void ShtoPaketa(ActionEvent event) throws IOException{
		NdrroFaqe(event,"ShtoPaketa.fxml");
	   }
 public void FaqeKryesore(ActionEvent event) throws IOException{
		NdrroFaqe(event,"MainPage.fxml");
 }
 public void ShtoKliente(ActionEvent event) throws IOException{
		NdrroFaqe(event,"ShtoKliente.fxml");
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
           
     
            







	            
	 		
	   
	
	   
	    
 