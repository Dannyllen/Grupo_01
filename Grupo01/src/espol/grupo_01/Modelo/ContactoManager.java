package espol.grupo_01.Modelo;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ContactoManager {
    
    private static LinkedListDobleCircular<Contacto> contactos = new LinkedListDobleCircular<>();

    static {
        // Cargar contactos desde archivo
         contactos = readListFromFileSerContactos();
        
    }

    public static LinkedListDobleCircular<Contacto> getContactos(){
        return contactos;
    }

    public static void agregarContacto(Contacto contacto) {
        contactos.add(contacto);
    }
    
    public static boolean eliminarContacto(Contacto contacto){
        boolean eliminado = contactos.removeContacto(contacto);
        if(eliminado) return true;
        return false;
    }
    
        //Metodo que guarda la lista de contactos en archivo ser
   public static void saveListToFileSerContactos(LinkedListDobleCircular<Contacto> contactos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("resources/Contactos.ser"))) {
            oos.writeObject(contactos);
        } catch (Exception e) {e.printStackTrace();}
    }
    
    //Metodo que recupera la lista de contactos desde el archivo ser
    public static LinkedListDobleCircular<Contacto> readListFromFileSerContactos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("resources/Contactos.ser"))) {
            contactos = (LinkedListDobleCircular<Contacto>) ois.readObject();
        } catch (IOException e) { e.printStackTrace();}
        catch (ClassNotFoundException e) {e.printStackTrace(); } 
        catch (Exception a) {a.printStackTrace();}
        return contactos;
    }
}