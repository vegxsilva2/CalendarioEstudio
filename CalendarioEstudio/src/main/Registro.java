package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registro {

	Set<Usuario> usuarios = new TreeSet<>();
	
	
	/*
	 * Hashea el nombre y la contrase�a que recibe por par�metro
	 * Busca en la estructura si est� registrado el nombre, y si lo est�, si la contrase�a coincide
	 * En caso de que lo est� devuelve true
	 */
	public void inicioSesion(String nombre, String contrase�a) {
		boolean res = false;
		
		hash(nombre,contrase�a);
		
		//Comprobamos si el inicio de sesi�n se realiza con �xito
        res = buscarUsuario(nombre,contrase�a);
		
		
		if(res) System.out.println("Inicio de sesi�n realizado con �xito.");
		else System.out.println("El nombre de usuario o contrase�a no son correctos");
	}
	
	/*
	 *Crea un usuario preguntando todos los par�metros para ello.
	 *A�ade el usuario a usuarios 
	 */
	public void registro(Scanner leerComando) {
		
		Usuario user = new Usuario("",null,"","");
		
		
		//Guardo en variables el nombre y la contrase�a porque necesito hashearlos antes de guardarlos
		System.out.println("Introduzca su nombre:\n");
		String n = leerComando.next();
		
		System.out.println("Introduzca su contrase�a:\n");
		String c = leerComando.next();
		
		//hasheo para no almacenar datos sensibles en claro
		hash(c,n);
		user.setNombre(n);
		user.setContrase�a(c);
		
		System.out.println(user.getNombre() + " " + user.getContrase�a());
		
		/*
		 * Debo controlar que se introduzca al menos un formato de correo
		 */
		System.out.println("Introduzca su correo electr�nico:\n");
		String correo = leerComando.next();
		while(!esCorreo(correo)) {
			System.out.println("Introduzca su correo electr�nico:\n");
			correo = leerComando.next();
		}
		user.setCorreoE(correo);
		
		System.out.println("�Cu�l de los siguientes es su grado? ");
		//Mostrar listado de grados para que el usuario escoja el suyo
		
		
		
		//A�adimos al usuario
		usuarios.add(user);
		
		escribirFich(user.toString(), "Usuarios.txt");
	}
	
	private void hash(String nombre, String contrase�a) {
		
		try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            
            //Creamos el hash del nombre y lo pasamos a hex
            byte[] hashNombre = digest.digest(nombre.getBytes());
            nombre = byteToHex(hashNombre);
            
            //Creamos el hash de la contrase�a y lo pasamos a hex
            byte[] hashContra = digest.digest(contrase�a.getBytes());
            contrase�a = byteToHex(hashContra);
            
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
	}
	
	private String byteToHex(byte[] bArray) {
		
		StringBuilder hexString = new StringBuilder();
        for (byte b : bArray) {
            // M�scara para convertir el byte sin signo a un valor hexadecimal
            String hex = Integer.toHexString(0xff & b);
            // A�adir un cero adelante si el valor hexadecimal es menor que 16
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
	}
	
	private boolean buscarUsuario(String hashNombre, String hashContrase�a) {
		
		boolean res = false;
		
			Iterator<Usuario> it = usuarios.iterator();
			
			while(it.hasNext()) {
				Usuario user = it.next();
				if(user.getNombre().equals(hashNombre)) {
					if(user.getContrase�a().equals(hashContrase�a)) {
						res = true;
						break;
					}
				}
			}
			
		
		
		return res;
	}
	
	/*
	 * Devuelve true en caso de que la cadena recibida sea una direcci�n de correo
	 */
	private boolean esCorreo(String cadena) {
		boolean res = false;
		// Expresi�n regular para validar direcciones de correo electr�nico
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        // Compilar la expresi�n regular
        Pattern pattern = Pattern.compile(regex);

        // Crear un objeto Matcher
        Matcher matcher = pattern.matcher(cadena);

        // Verificar si la cadena introducida coincide con la expresi�n regular
        if(matcher.matches()) {
           res = true;
        }
        return res;
    }

	
	
	public void escribirFich(String cadena, String nomFich) {
		
		//String ruta = "C:\\Users\\User\\eclipse-workspace\\CalendarioEstudio\\";
		
		
		try(FileWriter fw = new FileWriter(nomFich, true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			    {
			        out.println(cadena);
			} catch (IOException e) {
			    //exception handling left as an exercise for the reader
				e.printStackTrace();
			}
	}
	
	public void leerFich(String nomFich) throws IOException {
		try (FileReader fr = new FileReader(nomFich)) {
	         BufferedReader br = new BufferedReader(fr);
	         // Lectura del fichero
	         String linea;
	         while((linea=br.readLine())!=null)
	            System.out.println(linea);
	      }
	      catch(Exception e){
	         e.printStackTrace();
	      }
		
	}
	
}
