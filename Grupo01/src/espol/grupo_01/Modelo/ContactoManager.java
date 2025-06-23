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

    public static LinkedListDobleCircular<Contacto> getContactos() {
        return contactos;
    }

    public static void agregarContacto(Contacto contacto) {
        contactos.add(contacto);
    }

    public static boolean eliminarContacto(Contacto contacto) {
        boolean eliminado = contactos.removeContacto(contacto);
        if (eliminado) {
            return true;
        }
        return false;
    }

    //Metodo que guarda la lista de contactos en archivo ser
    public static void saveListToFileSerContactos(LinkedListDobleCircular<Contacto> contactos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("resources/Contactos.ser"))) {
            oos.writeObject(contactos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Metodo que recupera la lista de contactos desde el archivo ser
    public static LinkedListDobleCircular<Contacto> readListFromFileSerContactos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("resources/Contactos.ser"))) {
            contactos = (LinkedListDobleCircular<Contacto>) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception a) {
            a.printStackTrace();
        }
        return contactos;
    }

    public static void ordenarPorNombre() {
        int n = contactos.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                Contacto c1 = contactos.get(j);
                Contacto c2 = contactos.get(j + 1);
                String clave1 =  c1.getNombre().toLowerCase();
                String clave2 =  c2.getNombre().toLowerCase();
                if (clave1.compareTo(clave2) > 0) {
                    contactos.swap(j, j + 1);
                }
            }
        }
    }

    public static void ordenarPorCantidadDeFotos() {
        int n = contactos.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                Contacto c1 = contactos.get(j);
                Contacto c2 = contactos.get(j + 1);
                int atributos1 = c1.getFotos().size();
                int atributos2 = c2.getFotos().size();
                if (atributos1 > atributos2) {
                    contactos.swap(j, j + 1);
                }
            }
        }
    }

    public static void ordenarPorTipoDeContacto() {
        int n = contactos.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                Contacto c1 = contactos.get(j);
                Contacto c2 = contactos.get(j + 1);
                String tipo1 = (c1 instanceof ContactoPersona) ? "A" : "B";
                String tipo2 = (c2 instanceof ContactoPersona) ? "A" : "B";
                if (tipo1.compareTo(tipo2) > 0) {
                    contactos.swap(j, j + 1);
                }
            }
        }
    }

    public static LinkedListDobleCircular<Contacto> filtrarContactos(
            String textoNombreApellido,
            String tipoPersona,
            String pais
    ) {
        LinkedListDobleCircular<Contacto> filtrados = new LinkedListDobleCircular<>();

        for (int i = 0; i < contactos.size(); i++) {
            Contacto c = contactos.get(i);

            // Filtrar por nombre
            boolean cumpleNombre = true;
            if (textoNombreApellido != null && !textoNombreApellido.isEmpty()) {
                String completo = c.getNombre().toLowerCase();
                cumpleNombre = completo.contains(textoNombreApellido.toLowerCase());
            }

            // Filtrar por tipo de persona
            boolean cumpleTipo = true;
            if (tipoPersona != null && !tipoPersona.isEmpty()) {
                if (tipoPersona.equalsIgnoreCase("persona")) {
                    cumpleTipo = c instanceof ContactoPersona;
                } else if (tipoPersona.equalsIgnoreCase("empresa")) {
                    cumpleTipo = c instanceof ContactoEmpresa;
                }
            }

            // Filtrar por país
            boolean cumplePais = true;
            if (pais != null && !pais.isEmpty()) {
                String paisContacto = c.getPais();
                cumplePais = paisContacto != null && paisContacto.toLowerCase().contains(pais.toLowerCase());
            }

            if (cumpleNombre && cumpleTipo && cumplePais) {
                filtrados.add(c);
            }
        }

        return filtrados;
    }
}