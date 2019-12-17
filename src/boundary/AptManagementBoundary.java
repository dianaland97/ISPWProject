package boundary;

import java.awt.Event;
import java.io.IOException;

import com.sun.media.jfxmediaimpl.platform.Platform;

import bean.AptManagementBean;
import control.AptManagementControl;
import entity.Apartment;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import threadAndException.EditTextException;

public class AptManagementBoundary {

    private AptManagementBean aptManagementBean;
    private AptManagementControl aptManagementControl;

    @FXML
    private TextArea list;

    @FXML
    private TextField textField;

    @FXML
    private Label controlField = new Label();

    @FXML
    private Button ok;

    @FXML
    private Button home;

    @FXML
    private Label information;

    @FXML
    private Button inserisciApt;

    @FXML
    private Button getioneApt;

    @FXML
    private TableView table;
    @FXML
    TableColumn id;
    @FXML
    TableColumn state;
    @FXML
    TableColumn address;
    @FXML
    TableColumn city;

    @FXML
    private ImageView image;


    public void initiData (String nick){

        information.setText("Benvenuto/a "+ nick);
        aptManagementBean = new AptManagementBean(nick);
         }


    //inizializzatore pagina gestioneApt lanciata da indexPam
    public void getList(String nick) {

    	image.setImage(new Image(getClass().getResource("/Logo.png").toString(), true));
        aptManagementBean = new AptManagementBean(nick);
        aptManagementControl = AptManagementControl.getInstance();

        boolean checkapt = aptManagementControl.checkIdAndStateApartment(aptManagementBean.getNick());

        if (checkapt == false) {

            this.controlField.setText("Nessun appartamento registrato");
        }

        else {

       	 	id.setCellValueFactory(new PropertyValueFactory<>("id"));
            state.setCellValueFactory(new PropertyValueFactory<>("state"));
            address.setCellValueFactory(new PropertyValueFactory<>("address"));
            city.setCellValueFactory(new PropertyValueFactory<>("citys"));
            
            aptManagementControl.printInformationAptOnTable(aptManagementBean.getNick(), table);
        }

/*
	     	for (int i=0;i<app.size();i++)
	    	 {
	    	 	if (stato.get(i).equals("Deleted")){
	    	 		 continue;

	    	 	 }
	    	 	else {
	    	 		String[] parts = app.get(i).split(",");
	    	 		Apartment a=new Apartment(parts[0],parts[1],parts[2],parts[3],parts[4]);
	    	 		table.getItems().add(a);

	    	 	}
	    	 		}

        }
        */

        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            	if (!newValue.matches("\\d{0,7}(\\d{0,4})?")) {
            		textField.setText(oldValue);
                }
            }
        });

    }


    @FXML
    void goToTheNextPage(ActionEvent event) throws IOException {
        
        
        try {
            if (!this.textField.getText().isEmpty()) {
                int setId= Integer.parseInt(this.textField.getText());
                aptManagementBean.setId(setId);
                boolean validateIndice = aptManagementControl.checkId(aptManagementBean.getId(), aptManagementBean.getNick());
                
                try {
                    if (validateIndice==true) {

                        aptManagementControl.goToTheNextPage((Stage)((Node)event.getSource()).getScene().getWindow(), aptManagementBean.getNick());
                    }
                    else {
                        throw new EditTextException();
                    }}catch(EditTextException exc) {
                    this.controlField.setText(exc.wrongID());}
            }else {
                throw new EditTextException();
            }}catch(EditTextException exc) {
            this.controlField.setText(exc.emptyEditText());}

         
    }


    @FXML
    void returnHome(ActionEvent event) throws IOException {
    	aptManagementControl.returnHomeIndexPam((Stage)((Node)event.getSource()).getScene().getWindow(), aptManagementBean.getNick());
    }

    public void GestioneApt (ActionEvent event) throws IOException
    {
    	aptManagementControl = AptManagementControl.getInstance();
    	aptManagementControl.setControlManageApt((Stage) ((Node)event.getSource()).getScene().getWindow(), aptManagementBean.getNick());
    }

    public void InserisciApt(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("insertApt.fxml"));
        Parent IndexPamParent = loader.load();

        Scene tableViewScene = new Scene(IndexPamParent);

        //access the controller and call a method
        //InserisciAptBoundary controller = loader.getController();
        //controller.init(this.getNickname());

        //This line gets the Stage information
        Stage window = (Stage) this.inserisciApt.getScene().getWindow();
        window.setScene(tableViewScene);
        window.setResizable(false);
        window.show();
    }
    
    public void initData (String nick){
    	image.setImage(new Image(getClass().getResource("/Logo.png").toString(), true));
    }
    
    
    @FXML
    void eliminaAppartamento(ActionEvent event) {

    }

    @FXML
    void indietro(ActionEvent event) throws IOException {
    	
    	aptManagementControl.indietro((Stage) ((Node)event.getSource()).getScene().getWindow(), aptManagementBean.getNick());

    }

    @FXML
    void modifcaServizi(ActionEvent event) {

    }

    @FXML
    void modificaAppartamento(ActionEvent event) {

    }
}