package main;

import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {

		Registro registro = new Registro();
		Calendar calendarioI = Calendar.getInstance();
		Calendar calendarioF = null;
		int anio = -1;
		int mes = -1;
		int dia = -1;
		int horaI=-1;
		int minI=-1;
		int segI=-1;
		int horaF=-1;
		int minF=-1;
		int segF=-1;
		int valoracion = -1;
		String duracion = "";
		String asignatura = "";
		Usuario user = null;
		
		Sesion sesion = new Sesion(user, calendarioI, calendarioF, anio, mes, dia, horaI, minI, segI, horaF, segF, minF,duracion, valoracion, asignatura);
		
		Scanner leerComando = new Scanner(System.in);
		String comando = "";
		String comando2 = "";
		
		System.out.println("Hola buenas tardes que tal");
		//Comprobamos si va a ser un inicio de sesion o un registro
		System.out.println("�Tiene una cuenta?:[Y/N]");
		comando = leerComando.next();
		while(!comando.equals("Y") && !comando.equals("N")) {
			System.out.println("�Tiene una cuenta?:[Y/N]");
			comando = leerComando.next();
		}
		
		if(comando.equals("Y")) {
			registro.inicioSesion(leerComando);
		}else {
			registro.registro(leerComando);
			
		}
		
		//Inicia la sesi�n de estudio, "ponemos el cron�metro"
		sesion.inicio(calendarioI, leerComando);
		//Finalizamos la sesi�n, "paramos el cron�metro"
		sesion.fin(calendarioF,leerComando);
		//Consultamos al usuario sobre el rendimiento de la sesi�n
		sesion.puntuacion(valoracion,leerComando);
		
		
		
		
		String tostring = sesion.toString();
		
		System.out.println(tostring);
		
		//Escribo en fichero y muestro
		sesion.escribirFich(tostring, "sesiones.txt");
		sesion.leerFich("sesiones.txt");

	}

}