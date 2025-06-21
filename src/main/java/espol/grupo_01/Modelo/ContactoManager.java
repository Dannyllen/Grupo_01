package espol.grupo_01.Modelo;

public class ContactoManager {
    private static LinkedListDobleCircular<Contacto> contactos = new LinkedListDobleCircular<>();

    /*static {
        // Cargar contactos desde archivo
        LinkedListDobleCircular<Contacto> cargados = Contacto.readListFromFileSerContactos();
        contactos = cargados;
    }*/

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
}