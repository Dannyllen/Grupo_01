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

    public static void ordenarPorApellidoYNombre() {
        int n = contactos.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                Contacto c1 = contactos.get(j);
                Contacto c2 = contactos.get(j + 1);
                String clave1 = c1.getApellido().toLowerCase() + c1.getNombre().toLowerCase();
                String clave2 = c2.getApellido().toLowerCase() + c2.getNombre().toLowerCase();
                if (clave1.compareTo(clave2) > 0) {
                    contactos.swap(j, j + 1);
                }
            }
        }
    }

    public static void ordenarPorCantidadAtributos() {
        int n = contactos.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                Contacto c1 = contactos.get(j);
                Contacto c2 = contactos.get(j + 1);
                int atributos1 = contarAtributos(c1);
                int atributos2 = contarAtributos(c2);
                if (atributos1 > atributos2) {
                    contactos.swap(j, j + 1);
                }
            }
        }
    }

    private static int contarAtributos(Contacto c) {
        int total = 0;
        if (c.getNombre() != null && !c.getNombre().isEmpty()) {
            total++;
        }
        if (c.getApellido() != null && !c.getApellido().isEmpty()) {
            total++;
        }
        if (c.getDirecciones() != null && !c.getDirecciones().isEmpty()) {
            total++;
        }
        if (c.getEmails() != null && !c.getEmails().isEmpty()) {
            total++;
        }
        if (c.getNumTelefonicos() != null && !c.getNumTelefonicos().isEmpty()) {
            total++;
        }
        if (c.getIdentRedesSociales() != null && !c.getIdentRedesSociales().isEmpty()) {
            total++;
        }

        return total;
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
            String tipoRedSocial
    ) {
        LinkedListDobleCircular<Contacto> filtrados = new LinkedListDobleCircular<>();

        for (int i = 0; i < contactos.size(); i++) {
            Contacto c = contactos.get(i);

            // Filtrar nombre y apellido
            boolean cumpleNombreApellido = true;
            if (textoNombreApellido != null && !textoNombreApellido.isEmpty()) {
                String completo = (c.getNombre() + " " + c.getApellido()).toLowerCase();
                cumpleNombreApellido = completo.contains(textoNombreApellido.toLowerCase());
            }

            // Filtrar tipo persona
            boolean cumpleTipo = true;
            if (tipoPersona != null) {
                if (tipoPersona.equalsIgnoreCase("persona")) {
                    cumpleTipo = c instanceof ContactoPersona;
                } else if (tipoPersona.equalsIgnoreCase("empresa")) {
                    cumpleTipo = c instanceof ContactoEmpresa;
                } else {
                    cumpleTipo = true;
                }
            }

            // Filtrar red social
            boolean cumpleRedSocial = true;
            if (tipoRedSocial != null && !tipoRedSocial.isEmpty()) {
                LinkedListSimpleCircular<Dato> redes = c.getIdentRedesSociales();
                if (redes != null && redes.size() > 0) {
                    cumpleRedSocial = false;
                    for (int j = 0; j < redes.size(); j++) {
                        Dato datoRed = redes.get(j);
                        String redStr = datoRed.getValor(); // cambia getValor() según tu clase Dato
                        if (redStr != null && redStr.toLowerCase().contains(tipoRedSocial.toLowerCase())) {
                            cumpleRedSocial = true;
                            break;
                        }
                    }
                } else {
                    cumpleRedSocial = false;
                }
            }

            // Si cumple todos los filtros
            if (cumpleNombreApellido && cumpleTipo && cumpleRedSocial) {
                filtrados.add(c);
            }
        }

        return filtrados;
    }





}