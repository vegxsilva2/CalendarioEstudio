package main;

import java.util.Objects;

public class Asignatura implements Comparable<Asignatura> {

	private String nombreAsignatura;
	Grado grado;
	private int curso;
	
	public Asignatura(Grado grado, String nombreAsignatura, int curso) {
		super();
		this.grado = grado;
		this.nombreAsignatura = nombreAsignatura;
		this.curso = curso;
	}

	public Grado getGrado() {
		return grado;
	}

	public void setGrado(Grado grado) {
		this.grado = grado;
	}

	public String getNombreAsignatura() {
		return nombreAsignatura;
	}

	public void setNombreAsignatura(String nombreAsignatura) {
		this.nombreAsignatura = nombreAsignatura;
	}

	public int getCurso() {
		return curso;
	}

	public void setCurso(int curso) {
		this.curso = curso;
	}
	
	 @Override
	    public boolean equals(Object obj) {
	        if (this == obj) { // Comprueba si es la misma instancia
	            return true;
	        }
	        if (obj == null || getClass() != obj.getClass()) { // Comprueba si el objeto es null o no es una instancia de Asignatura
	            return false;
	        }
	        Asignatura otraAsignatura = (Asignatura) obj; // Convierte el objeto a Asignatura
	        // Compara todos los campos y devuelve true si son iguales, false en caso contrario
	        return curso == otraAsignatura.curso &&
	               Objects.equals(grado, otraAsignatura.grado) &&
	               Objects.equals(nombreAsignatura, otraAsignatura.nombreAsignatura);
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(grado, nombreAsignatura, curso);
	    }
	    
	    @Override
	    public int compareTo(Asignatura otraAsignatura) {
	        // Una asignatura es mayor que otra si es de un curso superior
	        return Integer.compare(curso, otraAsignatura.curso);
	    }
}
