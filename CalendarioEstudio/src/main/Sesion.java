package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Scanner;

public class Sesion implements Comparable<Sesion> {

	Usuario user;
	Calendar calendarioI = null;
	Calendar calendarioF = null;
	int anio, mes, dia, horaI, minI, segI, horaF, minF, segF, valoracion;
	String duracion = "";
	String asignatura;
	
	public Sesion(Usuario user, Calendar calendarioI, Calendar calendarioF, int anio, int mes, int dia, int horaI, int minI, int segI, int horaF, int minF, int segF, String duracion, int valoracion, String asignatura) {
		this.user = user;
		this.calendarioI = calendarioI;
		this.calendarioF = calendarioF;
		this.anio = anio;
		this.mes = mes;
		this.dia = dia;
		this.horaI = horaI;
		this.minI = minI;
		this.segI = segI;
		this.horaF = horaF;
		this.minF = minF;
		this.segF = segF;
		this.duracion = duracion;
		this.asignatura = asignatura;
	}

	public Usuario getUser() {
		return this.user;
	}
	
	public void setUser(Usuario u) {
		this.user = u;
	}
	
	public Calendar getCalendario() {
		return calendarioI;
	}

	public void setCalendario(Calendar calendario) {
		this.calendarioI = calendario;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getHoraI() {
		return horaI;
	}

	public void setHoraI(int horaI) {
		this.horaI = horaI;
	}

	public int getMinI() {
		return minI;
	}

	public void setMinI(int minI) {
		this.minI = minI;
	}

	public int getSegI() {
		return segI;
	}

	public void setSegI(int segI) {
		this.segI = segI;
	}

	public int getHoraF() {
		return horaF;
	}

	public void setHoraF(int horaF) {
		this.horaF = horaF;
	}

	public int getMinF() {
		return minF;
	}

	public void setMinF(int minF) {
		this.minF = minF;
	}

	public int getSegF() {
		return segF;
	}

	public void setSegF(int segF) {
		this.segF = segF;
	}
	
	public int getValoracion() {
		return this.valoracion;
	}
	
	public void setValoracion(int v) {
		this.valoracion = v;
	}
	
	public String getAsignatura() {
		return this.asignatura;
	}
	
	public void setAsignatura(String a) {
		this.asignatura = a;
	}
	
	public String duracion(int horaI, int minI, int segI, int horaF, int minF, int segF) {
		String res = "";
		int h,m,s;
		h = (horaF-horaI)%60;
		m = (minF-minI)%60;
		s = (segF-segI)%60;
		
		res = h + ":" + m + ":" + s;
		
		return res;
	}
	
	public void inicio(Calendar calendarioI, Scanner leerComando) {
		
		//Pedimos por linea de comandos que se introduzca el comando inicio y lo leemos
		System.out.println("Introduzca el comando 'inicio' cuando comience su sesión:");
		String comando = leerComando.next();
		
		
		while(!comando.equals("inicio")) {
			System.out.println("Introduzca el comando 'inicio' cuando comience su sesión:");
			comando = leerComando.next();
		}
		
		//iniciamos la sesion
		calendarioI = Calendar.getInstance();
		setAnio(calendarioI.get(Calendar.YEAR));
		setMes(calendarioI.get(Calendar.MONTH)+1);
		setDia(calendarioI.get(Calendar.DAY_OF_MONTH));
		setHoraI(calendarioI.get(Calendar.HOUR_OF_DAY));
		setMinI(calendarioI.get(Calendar.MINUTE));
		setSegI(calendarioI.get(Calendar.SECOND));
		
		//Se informa de que la sesión ha comenzado
		System.out.println("Su sesión ha empezado a las " + getHoraI() + ":" + getMinI() + ":" + getSegI());
	}
	
	public void fin(Calendar calendarioF, Scanner leerComando) {
		//Pedimos por linea de comandos que se introduzca el comando fin y lo leemos
		System.out.println("Escriba el comando 'fin' cuando su sesión haya finalizado:");
		String comando = leerComando.next();
		
		while(!comando.equals("fin")) {
			System.out.println("Escriba el comando 'fin' cuando su sesión haya finalizado");
			comando = leerComando.next();
		}
		
		//finalizamos la sesion
		calendarioF = Calendar.getInstance();
		setHoraF(calendarioF.get(Calendar.HOUR_OF_DAY));
		setMinF(calendarioF.get(Calendar.MINUTE));
		setSegF(calendarioF.get(Calendar.SECOND));
		
	}

	public void puntuacion(int valo, Scanner leerComando) {
		
		System.out.println("¿Cómo valoraría del 1 al 5 esta sesión?: ");
		valo = leerComando.nextInt();
		while(valo<1 || valo>5) {
			System.out.println("Introduzca un número del 1 al 5 (ambos inclusive):");
			valo = leerComando.nextInt();
		}
		setValoracion(valo);
		
	}
	
	
	@Override
	public String toString() {
		String res = "";
		
		res = "Sesión de estudio día " + this.dia + "-" + this.mes + "-" + this.anio + ":\n" + 
				"Inicio: " + this.horaI + ":" + this.minI + ":" + this.segI + "\n" + 
				"Final: " + this.horaF + ":" + this.minF + ":" + this.segF + "\n" +
				"Duración de la sesión: " + duracion(this.horaI,this.minI,this.segI,this.horaF,this.minF,this.segF) + "\n" +
				"Valoración del usuario: " + this.valoracion +
				"\n-------------------------------";
		
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

	@Override
	public int compareTo(Sesion o) {
		// Primero, convierte las duraciones de String a la representación numérica adecuada
        String[] duracionSplit = this.duracion.split(":");
        int duracionThis = Integer.parseInt(duracionSplit[0]) * 3600 +
                           Integer.parseInt(duracionSplit[1]) * 60 +
                           Integer.parseInt(duracionSplit[2]);
        
        duracionSplit = o.duracion.split(":");
        int duracionOtra = Integer.parseInt(duracionSplit[0]) * 3600 +
                           Integer.parseInt(duracionSplit[1]) * 60 +
                           Integer.parseInt(duracionSplit[2]);

        // Ahora, compara las duraciones y devuelve el resultado
        return Integer.compare(duracionThis, duracionOtra);
	}
	
	
	
	

}
