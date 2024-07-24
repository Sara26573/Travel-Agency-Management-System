package application;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ShtoPaketa {

    @FXML
    private TableColumn<Udhetime, String> Data;

    @FXML
    private TableColumn<Udhetime, String> Mberritja;

    @FXML
    private TableColumn<Udhetime, String> Nisja;

    @FXML
    private TableColumn<Udhetime, String> Pershkrim;

    @FXML
    private TableView<Udhetime> TeDhenaVende;

    @FXML
    private TableColumn<Udhetime, Integer> Vende;

    @FXML
    private TableColumn<Udhetime, Integer> cmimi;

    @FXML
    private TableColumn<Udhetime, String> destinacioni;

    @FXML
    private TableColumn<Udhetime, Integer> diteUdhetimi;

    @FXML
    private Label idlabel;
   
    @FXML
    private TextField NisjaTextFeild;

    @FXML
    private ComboBox<Integer> NumerVendeshCombo;

    @FXML
    private TextField MberritjatextFeild;
    
    @FXML
    private TextField CmimiTextFeild;
    
    @FXML
    private DatePicker Date;

    @FXML
    private TextField DestinacioniTextField;

    @FXML
    private ComboBox<Integer> DiteUdhetimiCombo;
    
    @FXML
    private ImageView imageview2;
     
    @FXML
    private Label LabelDiteudhetimi;

    @FXML
    private Label LabelNrVende;
    
    @FXML
    private Label dataLabel1;
    
    @FXML
    private TextArea TextArea1;
    
    @FXML
    private TextField textfieldFilter;
    
    
    FileInputStream inputStream;
    File selectedFile;
    
    ObservableList<Integer> vendelist=FXCollections.observableArrayList();
    ObservableList<Integer> diteUdhetimilist=FXCollections.observableArrayList();
    
    
    public void populloComboBox() {
    	for(int i=1;i<100;i++) {
    		vendelist.add(i);
    	}
    	for(int i=1;i<14;i++) {
    		diteUdhetimilist.add(i);
    	}
    }
    
    public void initialize() throws SQLException {
        
    	Data.setCellValueFactory(new PropertyValueFactory<Udhetime, String>("data"));
    	Mberritja.setCellValueFactory(new PropertyValueFactory<Udhetime, String>("mberritja"));
    	Nisja.setCellValueFactory(new PropertyValueFactory<Udhetime, String>("nisja"));
    	Vende.setCellValueFactory(new PropertyValueFactory<Udhetime, Integer>("vende"));
    	Pershkrim.setCellValueFactory(new PropertyValueFactory<Udhetime, String>("pershkrim"));
    	destinacioni.setCellValueFactory(new PropertyValueFactory<Udhetime, String>("destinacioni"));
    	diteUdhetimi.setCellValueFactory(new PropertyValueFactory<Udhetime, Integer>("dite"));
    	cmimi.setCellValueFactory(new PropertyValueFactory<Udhetime, Integer>("cmimi"));
    	populloComboBox();
    	NumerVendeshCombo.setItems(vendelist);
    	DiteUdhetimiCombo.setItems(diteUdhetimilist);
    	
   	 textfieldFilter.textProperty().addListener((observable, oldValue, newValue) -> {
	        filterData(newValue);
	    });

    	
    	popullo();
    	
    	
    	
    }

	  
    
    
    public void popullo() throws SQLException {
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
    
    public void shto(ActionEvent ngjarje) throws SQLException {
    	try {
    	Integer cmimi1=Integer.parseInt(CmimiTextFeild.getText());
    	
    	
    	try {
			inputStream=new FileInputStream(selectedFile);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
    	try {
    	SQLConnect connectioncon = new SQLConnect();
        Connection connect = connectioncon.getConnection();
        
        PreparedStatement st = connect.prepareStatement("insert into paketa (Destinacioni,Vende,Data, Dite,Nisja,Mberritja,Pershkrimi,imazh,cmimi) values (?, ?, ?, ? ,? ,? ,?,?,?)");
        st.setString(1, DestinacioniTextField.getText());
        st.setInt(2, NumerVendeshCombo.getSelectionModel().getSelectedItem());
        st.setString(3, Date.getValue().toString());
        st.setInt(4, DiteUdhetimiCombo.getSelectionModel().getSelectedItem());
        st.setString(5, NisjaTextFeild.getText());
        st.setString(6, MberritjatextFeild.getText());
        st.setString(7,TextArea1.getText());
        st.setBinaryStream(8, (InputStream)inputStream);
        st.setInt(9, cmimi1);
        st.execute();
        }
    	catch(SQLException ex) {
    		ex.printStackTrace();
    	}
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
        PreparedStatement st = connect.prepareStatement("delete from paketa where idpaketa  = ?"); 	
        st.setString(1, idlabel.getText());
        st.execute();
        popullo();
    }    
    public void modifiko(ActionEvent ngjarje) throws SQLException {
    	 Integer cmimi1 = Integer.parseInt(CmimiTextFeild.getText());
    	    
    	    if (selectedFile != null) {
    	        try {
    	            inputStream = new FileInputStream(selectedFile);
    	        } catch (FileNotFoundException e) {
    	            e.printStackTrace();
    	        }
    	    } else {
    	        inputStream = null;
    	    }
    	    
    	    SQLConnect connectioncon = new SQLConnect();
    	    Connection connect = connectioncon.getConnection();
    	    
    	    try {
    	        if (inputStream == null) {    	            
    	        	PreparedStatement st = connect.prepareStatement("UPDATE paketa SET Destinacioni = ?, Vende = ?, Data = ?, Dite = ?, Nisja = ?, Mberritja = ?, Pershkrimi = ?, cmimi = ? WHERE idpaketa = ?");
    	            st.setString(1, DestinacioniTextField.getText());
    	            st.setInt(2, NumerVendeshCombo.getSelectionModel().getSelectedItem());
    	            st.setString(3, Date.getValue().toString());
    	            st.setInt(4, DiteUdhetimiCombo.getSelectionModel().getSelectedItem());
    	            st.setString(5, NisjaTextFeild.getText());
    	            st.setString(6, MberritjatextFeild.getText());
    	            st.setString(7, TextArea1.getText());
    	            st.setInt(8, cmimi1);
    	            st.setString(9, idlabel.getText());
    	            
    	            st.execute();
    	        } else {
    	            PreparedStatement st = connect.prepareStatement("UPDATE paketa SET Destinacioni = ?, Vende = ?, Data = ?, Dite = ?, Nisja = ?, Mberritja = ?, Pershkrimi = ?, imazh = ?, cmimi = ? WHERE idpaketa = ?");
    	            st.setString(1, DestinacioniTextField.getText());
    	            st.setInt(2, NumerVendeshCombo.getSelectionModel().getSelectedItem());
    	            st.setString(3, Date.getValue().toString());
    	            st.setInt(4, DiteUdhetimiCombo.getSelectionModel().getSelectedItem());
    	            st.setString(5, NisjaTextFeild.getText());
    	            st.setString(6, MberritjatextFeild.getText());
    	            st.setString(7, TextArea1.getText());
    	            st.setBinaryStream(8, (InputStream) inputStream);
    	            st.setInt(9, cmimi1);
    	            st.setString(10, idlabel.getText());
    	            
    	            st.execute();
    	        }
    	    } finally {
    	        if (inputStream != null) {
    	            try {
    	                inputStream.close(); //mbyll input Stream
    	            } catch (IOException e) {
    	                e.printStackTrace();
    	            }
    	        }
    	    }
    	    
    	    popullo();
    }  
    
    public void perditeso(MouseEvent ngjarje) {
    	SQLConnect connectioncon = new SQLConnect();
        Connection connect = connectioncon.getConnection();

    	Udhetime u = TeDhenaVende.getSelectionModel().getSelectedItem();
    	
    	NumerVendeshCombo.getSelectionModel().select(u.getVende()-1);
    	DiteUdhetimiCombo.getSelectionModel().select(u.getDite()-1);
    	
    	CmimiTextFeild.setText(Integer.toString(u.getCmimi()));
  
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	LocalDate date = LocalDate.parse(u.getData(), formatter);
    	Date.setValue(date);
       

  
     	DestinacioniTextField.setText(u.getDestinacioni());
     	NisjaTextFeild.setText(u.getNisja());
     	MberritjatextFeild.setText(u.getMberritja());
     
     	TextArea1.setText(u.getPershkrim());
     	TextArea1.setWrapText(true);
     	idlabel.setText(String.valueOf(u.getId()));
        try {
	     Statement statement=connect.createStatement();
		 ResultSet data1=statement.executeQuery("select imazh from paketa where idpaketa="+idlabel.getText());
		    while(data1.next()) {
		        Blob blob = data1.getBlob("imazh");
			    InputStream inputStream = blob.getBinaryStream();
			    Image image = new Image(inputStream);
			    imageview2.setImage(image);
		        }
	      }
	    catch(SQLException ex) {
	        	ex.printStackTrace();
	      }
    }     
    
    public void updateImage() {
        try {
            FileChooser fileChooser = new FileChooser();
            selectedFile = fileChooser.showOpenDialog(null);//hap nje dritare te pavarur
            fileChooser.setTitle("Zgjedh imazh");
            inputStream = new FileInputStream(selectedFile);
            Image newImage = new Image(inputStream);
            imageview2.setImage(newImage);
          
        } catch (Exception e) {
            e.printStackTrace();
        }
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
       public void ShtoPaketa1(ActionEvent event) throws IOException{
    		NdrroFaqe(event,"ShtoPaketa.fxml");
    	   }
       public void ShtoKliente(ActionEvent event) throws IOException{
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
