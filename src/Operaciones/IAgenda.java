package Operaciones;

import java.util.List;

public interface IAgenda {
	public List<Contacto> contactos();
	public void nuevoContacto(Contacto contacto);
	public void actualizarContactos(List<Contacto> contactos);
	public void aniadirContacto(Contacto contacto);
	public void eliminarContacto(String id);
	public void buscarContacto(String id);
	public void bloquearContacto(String id);
	public void desbloquearContacto(String id);
}
