package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ShtoKliente {
	  @FXML
	    private TableColumn<Kliente, String> DataKolone;

	    @FXML
	    private DatePicker Date;

	    @FXML
	    private TableColumn<Kliente, String> DestinacionKolone;

	    @FXML
	    private TextField Destinacioni;

	    @FXML
	    private TextField Dite;

	    @FXML
	    private TableColumn<Kliente, Integer> DiteKolone;

	    @FXML
	    private TextField Emer;

	    @FXML
	    private TableColumn<Kliente, String> EmerKoloone;

	    @FXML
	    private TextField IdNumber;

	    @FXML
	    private TableColumn<Kliente, String> IdNumberrKolone;

	    @FXML
	    private TextField Mbiemer;

	    @FXML
	    private TableColumn<Kliente, String> Mbiemerkolone;

	    @FXML
	    private TableColumn<Kliente, String> NrTelefoniKolone;

	    @FXML
	    private TextField NumerTelefoni;

	    @FXML
	    private TextField Pagesa;

	    @FXML
	    private TableColumn<Kliente, Float> PageseKolone;

	    @FXML
	    private TextField Vende;

	    @FXML
	    private TableColumn<Kliente, Integer> VendeKolone;

	    @FXML
	    private Label idLabel;

	    @FXML
	    private TableView<Kliente> table;
	    
	    @FXML
	    private TextField textfieldFilter;
	   

	    
	    
	    
	    public void initialize() throws SQLException {
	        
	    	DataKolone.setCellValueFactory(new PropertyValueFactory<Kliente, String>("data"));
	    	DestinacionKolone.setCellValueFactory(new PropertyValueFactory<Kliente, String>("destinacion"));
	    	DiteKolone.setCellValueFactory(new PropertyValueFactory<Kliente, Integer>("dite"));
	    	EmerKoloone.setCellValueFactory(new PropertyValueFactory<Kliente, String>("emer"));
	    	IdNumberrKolone.setCellValueFactory(new PropertyValueFactory<Kliente, String>("IdNum"));
	    	Mbiemerkolone.setCellValueFactory(new PropertyValueFactory<Kliente, String>("mbiemer"));
	    	NrTelefoniKolone.setCellValueFactory(new PropertyValueFactory<Kliente, String>("numerTelefoni"));
	    	PageseKolone.setCellValueFactory(new PropertyValueFactory<Kliente, Float>("pagesa"));
	    	VendeKolone.setCellValueFactory(new PropertyValueFactory<Kliente, Integer>("vende"));
	    	
	    	
	    	popullo();
	    	
	    	 textfieldFilter.textProperty().addListener((observable, oldValue, newValue) -> {
	    	        filterData(newValue);
	    	    });
	    	
	    	
	    }
	    
	    public void popullo() throws SQLException {
	    	SQLConnect connectioncon = new SQLConnect();
	        Connection connect = connectioncon.getConnection();
	        
	        ObservableList<Kliente> Klientelist=FXCollections.observableArrayList();
	        
	        try {
	        	Statement statement=connect.createStatement();
		        ResultSet data1=statement.executeQuery("select * from kliente");
		        while(data1.next()) {
		        	Kliente kliente=new Kliente(data1.getString("Data"),data1.getString("Destinacioni"),data1.getInt("Dite"),data1.getString("Emer"),data1.getString("Mbiemer"),data1.getString("IdNumber"),data1.getString("NumerTelefoni"),data1.getFloat("PagesaEuro"),data1.getInt("NumerVendesh"),data1.getInt("idKliente"));
		        	Klientelist.add(kliente);
		        	
		        
		        }
		        table.setItems(Klientelist);
	        }
	        catch(SQLException ex) {
	        	ex.printStackTrace();
	        }
	  
	    }
	    
	    public void shto(ActionEvent ngjarje) throws SQLException {
	    
	    	SQLConnect connectioncon = new SQLConnect();
	        Connection connect = connectioncon.getConnection();
	        
	        Integer Vende1=Integer.parseInt(Vende.getText());
	        Float Pagesa1=Float.parseFloat(Pagesa.getText());
	        Integer Dite1=Integer.parseInt(Dite.getText());
	        
	        try {
	        
	        PreparedStatement st = connect.prepareStatement("insert into kliente (Emer,Mbiemer,NumerTelefoni,IdNumber,Destinacioni,Data,NumerVendesh,PagesaEuro,Dite) values (?, ?, ?, ? ,? ,? ,?,?,?)");
	        st.setString(1, Emer.getText());
	        st.setString(2, Mbiemer.getText());
	        st.setString(3, NumerTelefoni.getText());
	        st.setString(4, IdNumber.getText());
	        st.setString(5, Destinacioni.getText());
	        st.setString(6, Date.getValue().toString());
	        st.setInt(7,Vende1);
	        st.setFloat(8, Pagesa1);
	        st.setInt(9, Dite1);
	        st.execute();
	          Alert alert=new Alert(Alert.AlertType.INFORMATION);
 		      alert.setTitle("Shtuat nje klient!");
 		      alert.setHeaderText("Klienti u shtua me sukses!");
              alert.setContentText("");
	          alert.showAndWait();
	    	}
	        catch(SQLException e) {
	        	e.printStackTrace();
	        }
	    	catch(Exception e){
	            Alert alert=new Alert(Alert.AlertType.INFORMATION);
	   		      alert.setTitle("Sigurohuni te shtoni te gjithe elementet");
	   		      alert.setHeaderText("Mungon njera nga te dhenat e kerkuara !");
	              alert.setContentText("");
	 	          alert.showAndWait();
	    	}
	       
	        popullo();
	      
	        
	    }
	    
	    
	    public void fshi(ActionEvent ngjarje) throws SQLException {
	        
	    	SQLConnect connectioncon = new SQLConnect();
	        Connection connect = connectioncon.getConnection();
	        PreparedStatement st = connect.prepareStatement("delete from kliente where idKliente  = ?"); 	
	        st.setString(1, idLabel.getText());
	        st.execute();
	        popullo();
	    }
	    
        public void modifiko(ActionEvent ngjarje) throws SQLException {
	    	
	    	SQLConnect connectioncon = new SQLConnect();
	        Connection connect = connectioncon.getConnection();
	        
	        Integer Vende1=Integer.parseInt(Vende.getText());
	        Float Pagesa1=Float.parseFloat(Pagesa.getText());
	        Integer Dite1=Integer.parseInt(Dite.getText());
	        
	    	PreparedStatement st = connect.prepareStatement("update kliente set Emer=?,Mbiemer=?,NumerTelefoni=?,IdNumber=?,Destinacioni=?,Data=?,NumerVendesh=?,PagesaEuro=?,Dite=? where idKliente = ?");
	    	st.setString(1, Emer.getText());
		    st.setString(2, Mbiemer.getText());
		    st.setString(3, NumerTelefoni.getText());
		    st.setString(4, IdNumber.getText());
		    st.setString(5, Destinacioni.getText());
		    st.setString(6, Date.getValue().toString());
		    st.setInt(7,Vende1);
		    st.setFloat(8, Pagesa1);
		    st.setInt(9, Dite1);
	    	st.setString(10, idLabel.getText());
	    	st.execute();
	    	popullo();
	    }  
        
        public void perditeso(MouseEvent ngjarje) {
	        Kliente kl = table.getSelectionModel().getSelectedItem();
	        Emer.setText(kl.getEmer());
	    	Mbiemer.setText(kl.getMbiemer());
	    	NumerTelefoni.setText(kl.getNumerTelefoni());
	    	IdNumber.setText(kl.getIdNum());
	    	Destinacioni.setText(kl.getDestinacion());
	    	Vende.setText(String.valueOf(kl.getVende()));
	    	Pagesa.setText(String.valueOf(kl.getPagesa()));
	    	Dite.setText(String.valueOf(kl.getDite()));
	    	idLabel.setText(String.valueOf(kl.getId()));
	    	
	     	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    	LocalDate date = LocalDate.parse(kl.getData(), formatter);
	    	Date.setValue(date);
	    } 
        
        public void filterData(String filterText) {
        	SQLConnect connectioncon = new SQLConnect();
	        Connection connect = connectioncon.getConnection();
	        
	        ObservableList<Kliente>list2=FXCollections.observableArrayList();
	        
	        try {
	        	Statement statement=connect.createStatement();
		        ResultSet data1=statement.executeQuery("select * from kliente");
		        while(data1.next()) {
		        	Kliente kliente=new Kliente(data1.getString("Data"),data1.getString("Destinacioni"),data1.getInt("Dite"),data1.getString("Emer"),data1.getString("Mbiemer"),data1.getString("IdNumber"),data1.getString("NumerTelefoni"),data1.getFloat("PagesaEuro"),data1.getInt("NumerVendesh"),data1.getInt("idKliente"));
		        	list2.add(kliente);
		        	
		        
		        }
		        table.setItems(list2);
	        }
	        catch(SQLException ex) {
	        	ex.printStackTrace();
	        }

        	            
	        FilteredList<Kliente> filteredList = new FilteredList<>(list2, kliente -> true);

            filteredList.setPredicate(kliente -> {
                if (filterText == null || filterText.isEmpty()) {
                    return true; 
                }
                
                return kliente.getEmer().toLowerCase().contains(filterText.toLowerCase())
                        || kliente.getMbiemer().toLowerCase().contains(filterText.toLowerCase())
                        || kliente.getDestinacion().toLowerCase().contains(filterText.toLowerCase())
                        || String.valueOf(kliente.getDite()).toLowerCase().contains(filterText.toLowerCase())
                        || kliente.getIdNum().toLowerCase().contains(filterText.toLowerCase())
                        || kliente.getNumerTelefoni().toLowerCase().contains(filterText.toLowerCase())
                        || String.valueOf(kliente.getPagesa()).toLowerCase().contains(filterText.toLowerCase())
                        || String.valueOf(kliente.getVende()).toLowerCase().contains(filterText.toLowerCase())
                        || kliente.getData().toLowerCase().contains(filterText.toLowerCase());
            });

           
            table.setItems(filteredList);
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
       public void Sugjerimi1(ActionEvent event) throws IOException{
      		NdrroFaqe(event,"Sugjerime.fxml");
         }
       
       public void NdrroFaqe(ActionEvent event,String a) throws IOException {
    	   root=FXMLLoader.load(getClass().getResource(a));
     	   stage=(Stage)((Node)event.getSource()).getScene().getWindow();
           scene=new Scene(root);
           stage.setScene(scene);
           stage.show();
       }
        
	    
	    
}
