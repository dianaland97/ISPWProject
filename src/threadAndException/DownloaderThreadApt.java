package threadAndException;
import java.util.Vector;
import entity.Apartment;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DownloaderThreadApt implements Runnable{

	private String nick;
	private TableView table;
	private Vector<Apartment> apt;

	public DownloaderThreadApt (Vector<Apartment> apt, String nickname, TableView table) {
		this.nick = nickname;
		this.table = table;
		this.apt = apt;
	}
	public void run() {
	
		Vector<String> part =  new Vector<String>();
		String [] partStrings = null;
		
		for (int i=0; i<apt.size(); i++)
   	 	{		
   	 	if (apt.get(i).getState().equals("Delete")){
   	 		 continue; 
   	 		 }
   	 	else {
   	 		
   	 		part.add(Integer.toString(apt.get(i).getApartmentId()));
   	 		part.add(apt.get(i).getState());
   	 		part.add(apt.get(i).getAddress());
   	 		part.add(apt.get(i).getCitys());
   	 		
   	 		}
   	 	}
		
		for (int i = 0; i< part.size(); i++) {
			partStrings = part.get(i).split(",");
		}
		
		Apartment a = new Apartment(part.get(0),part.get(1),part.get(2),part.get(3));
		table.getItems().add(a);
	}
}
