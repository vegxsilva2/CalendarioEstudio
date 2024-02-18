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

public class Registro {

	Set<Usuario> usuarios = new TreeSet<>();
	
	
	/*
	 * Hashea el nombre y la contraseña que recibe por parámetro
	 * Busca en la estructura si está registrado el nombre, y si lo está, si la contraseña coincide
	 * En caso de que lo esté devuelve true
	 */
	public void inicioSesion(String nombre, String contraseña) {
		boolean res = false;
		
		hash(nombre,contraseña);
		
		//Comprobamos si el inicio de sesión se realiza con éxito
        res = buscarUsuario(nombre,contraseña);
		
		
		if(res) System.out.println("Inicio de sesión realizado con éxito.");
		else System.out.println("El nombre de usuario o contraseña no son correctos");
	}
	
	/*
	 *Crea un usuario preguntando todos los parámetros para ello.
	 *Añade el usuario a usuarios 
	 */
	public void registro(Scanner leerComando) {
		
		Usuario user = new Usuario("",null,"","");
		
		
		//Guardo en variables el nombre y la contraseña porque necesito hashearlos antes de guardarlos
		System.out.println("Introduzca su nombre:\n");
		String n = leerComando.next();
		
		System.out.println("Introduzca su contraseña:\n");
		String c = leerComando.next();
		
		//hasheo para no almacenar datos sensibles en claro
		hash(c,n);
		user.setNombre(n);
		user.setContraseña(c);
		
		System.out.println(user.getNombre() + " " + user.getContraseña());
		
		/*
		 * Debo controlar que se introduzca al menos un formato de correo
		 */
		System.out.println("Introduzca su correo electrónico:\n");
		user.setCorreoE(leerComando.next());
		
		System.out.println("¿Cuál de los siguientes es su grado? ");
		//Mostrar listado de grados para que el usuario escoja el suyo
		
		
		
		//Añadimos al usuario
		usuarios.add(user);
		
		escribirFich(user.toString(), "Usuarios.txt");
	}
	
	private void hash(String nombre, String contraseña) {
		
		try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            
            //Creamos el hash del nombre y lo pasamos a hex
            byte[] hashNombre = digest.digest(nombre.getBytes());
            nombre = byteToHex(hashNombre);
            
            //Creamos el hash de la contraseña y lo pasamos a hex
            byte[] hashContra = digest.digest(contraseña.getBytes());
            contraseña = byteToHex(hashContra);
            
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
	}
	
	private String byteToHex(byte[] bArray) {
		
		StringBuilder hexString = new StringBuilder();
        for (byte b : bArray) {
            // Máscara para convertir el byte sin signo a un valor hexadecimal
            String hex = Integer.toHexString(0xff & b);
            // Añadir un cero adelante si el valor hexadecimal es menor que 16
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
	}
	
	private boolean buscarUsuario(String hashNombre, String hashContraseña) {
		
		boolean res = false;
		
			Iterator<Usuario> it = usuarios.iterator();
			
			while(it.hasNext()) {
				Usuario user = it.next();
				if(user.getNombre().equals(hashNombre)) {
					if(user.getContraseña().equals(hashContraseña)) {
						res = true;
						break;
					}
				}
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
