package threadAndException;
import java.util.Vector;
import entity.Apartment;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DownloaderThreadApt implements Runnable{

	private String nick;
	private TableView<?> table;
	private Vector<Apartment> apt;

	public DownloaderThreadApt (Vector<Apartment> apt, String nickname, TableView<Apartment> table) {
		this.nick = nickname;
		this.table = table;
		this.apt = apt;
	}
	public void run() {
	
		
		for (int i=0; i<apt.size(); i++)
   	 	{		
   	 	if (apt.get(i).getState().equals("Delete")){
   	 		 continue; 
   	 		 }
   	 	else {
   	 		Vector<String> part =  new Vector<String>();
   	 		part.add(apt.get(i).getAddress());
   	 		part.add(Integer.toString(apt.get(i).getApartmentId()));
   	 		part.add(apt.get(i).getState());
   	 		part.add(apt.get(i).getCitys());
   	 		String [] partStrings = part.get(i).split(",");
   	 		Apartment apartment = new Apartment(partStrings[0],partStrings[1],partStrings[2],partStrings[3]);
   	 		table.getItems().add(apartment);
   	 		}
   	 	}
	}
}
