package main;

import java.util.Objects;

public class Usuario {

	private String nombre, correoE, contrase�a;
	private Grado grado;
	
	public Usuario(String nombre, Grado grado, String correoE, String contrase�a) {
		this.nombre = nombre;
		this.grado = grado;
		this.correoE = correoE;
		this.contrase�a = contrase�a;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Grado getGrado() {
		return grado;
	}

	public void setGrado(Grado grado) {
		this.grado = grado;
	}

	public String getCorreoE() {
		return correoE;
	}

	public void setCorreoE(String correoE) {
		this.correoE = correoE;
	}

	public String getContrase�a() {
		return contrase�a;
	}

	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}

	
	@Override
    public boolean equals(Object obj) {
	
		if (this == obj) { // Comprueba si es la misma instancia
			return true;
		}
		
		if (obj == null || getClass() != obj.getClass()) { // Comprueba si el objeto es null o no es una instancia de Asignatura
			return false;
		}
	    
		Usuario otroUsuario = (Usuario) obj; // Convierte el objeto a Asignatura
		
		// Compara todos los campos y devuelve true si son iguales, false en caso contrario
		return this.nombre == otroUsuario.nombre && this.correoE == otroUsuario.correoE;
	}

	 @Override
	 public int hashCode() {
		 return Objects.hash(nombre, correoE);
	 }	
	
	
}
