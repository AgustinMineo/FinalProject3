import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Transfers.Transferencia;
import UI.BienvenidaUI;
import Usuarios.Usuario;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class Main{
	public static HashMap<String,Usuario> map=new HashMap<String,Usuario>();
	public static List<Transferencia> lista=new ArrayList<>();

	public static void main(String[] args) {

		
		map=cargaHashUsuarios();
		
		//lista=cargaListaTransferencias();
		
		//Muestro usuarios precargados
		for (Map.Entry<String, Usuario> set :map.entrySet()) {
			System.out.println(set.getKey() + " = " + set.getValue());
		}
		BienvenidaUI nuevaInterfaz=new BienvenidaUI(map,lista);
		nuevaInterfaz.setVisible(true);
	}
	public static HashMap<String,Usuario> cargaHashUsuarios(){
		try {
			//JsonElement fileElement = JsonParser.parseReader(new FileReader("C:\\Users\\Agustin\\Desktop\\Cambios\\TP FINAL\\listaUsuarios.json"));
			JsonElement fileElement = JsonParser.parseReader(new FileReader("C:\\Users\\lcoluccio\\Desktop\\GIT\\FinalProject3\\listaUsuarios.json"));
            Gson gson = new Gson();
            Type empMapType = new TypeToken< HashMap <String, Usuario>>() {}.getType();
            HashMap<String,Usuario> map= gson.fromJson(fileElement,empMapType);
             return map;
	      } catch (IOException i) {
	         i.printStackTrace();
	         return null;
	      } catch (JsonSyntaxException c) {
	         c.printStackTrace();
			return null;
	      }
	}
	public static List<Transferencia> cargaListaTransferencias(){
		try {
	         //FileInputStream fileIn = new FileInputStream("C:\\Users\\Agustin\\Desktop\\TP FINAL LAST\\TP FINAL\\listaTransferencias.ser");
			 FileInputStream fileIn = new FileInputStream("C:\\Users\\lcoluccio\\Desktop\\GIT\\FinalProject3\\listaTransferencias.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         lista = (List<Transferencia>) in.readObject();
	         in.close();
	         fileIn.close();
	         return lista;
	      } catch (IOException i) {
	    	  i.printStackTrace();
	         return null;
	      } catch (ClassNotFoundException c) {
	         c.printStackTrace();
	         return null;
	      }
	}

}