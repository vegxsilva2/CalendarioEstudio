package main;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class Grado {
	
	private String nombre;
	private List<Asignatura> asignaturas = new ArrayList<>();
	
	public Grado(String nombre, List<Asignatura> asignaturas) {
		this.nombre = nombre;
		this.asignaturas = asignaturas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Asignatura> getAsignaturas() {
		return asignaturas;
	}

	public void setAsignaturas(List<Asignatura> asignaturas) {
		this.asignaturas = asignaturas;
	}
	
	@Override
    public boolean equals(Object obj) {
	
		if (this == obj) { // Comprueba si es la misma instancia
			return true;
		}
		
		if (obj == null || getClass() != obj.getClass()) { // Comprueba si el objeto es null o no es una instancia de Asignatura
			return false;
		}
	    
		Grado otroGrado = (Grado) obj; // Convierte el objeto a Asignatura
		
		// Compara todos los campos y devuelve true si son iguales, false en caso contrario
		return this.nombre == otroGrado.nombre;
	}

	 @Override
	 public int hashCode() {
		 return Objects.hash(nombre);
	 }
	
}
