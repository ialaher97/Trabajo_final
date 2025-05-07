package Operaciones;

public class Contacto {
	private String id;
	private String nombre;
	private String apellido;
	private String num;
	boolean bloqueado = false;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public boolean isBloqueado() {
		return bloqueado;
	}
	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}
	public Contacto(String id, String nombre, String apellido, String num, boolean bloqueado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.num = num;
		this.bloqueado = bloqueado;
	}
	public Contacto() {
		super();
	}
	@Override
	public String toString() {
		return "Contacto [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", num=" + num + ", bloqueado="
				+ bloqueado + "]";
	}
	
	
}

