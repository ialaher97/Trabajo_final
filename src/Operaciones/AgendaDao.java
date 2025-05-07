package Operaciones;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Controlador.Lectura_Escritura_Ficheros;

public class AgendaDao {
	Lectura_Escritura_Ficheros lef = new Lectura_Escritura_Ficheros();

	private Connection con() {
		Connection con = null;

		String url = "jdbc:mysql://localhost/Instituto";
		try {
			con = DriverManager.getConnection(url, "root", "Xzdy5eyu_12");
		} catch (SQLException ex) {
			System.out.println("Error al conectar al SGBD.");
		}

		return con;
	}

	public void aniadirContacto(Contacto contacto) {
		List<Contacto> lista = lef.contactos();

		Connection con = con();
		PreparedStatement sentencia;
		String sql;

		if (lista.isEmpty()) {
			sql = "Insert into Contactos (id_Contacto,nombre,apellido,numero,bloqueado) Values" + "(?,?,?,?,?)";
			try {
				sentencia = con.prepareStatement(sql);
				sentencia.setString(1, contacto.getId());
				sentencia.setString(2, contacto.getNombre());
				sentencia.setString(3, contacto.getApellido());
				sentencia.setString(4, contacto.getNum());
				sentencia.setString(5, String.valueOf(contacto.isBloqueado()));
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			lef.nuevoContacto(contacto);
		} else {

			Iterator it = lista.iterator();
			boolean encontrado = false;

			while (it.hasNext() && !encontrado) {
				Contacto c = (Contacto) it.next();

				if (c.getId().equals(contacto.getId())) {
					encontrado = true;

				}
			}
			if (!encontrado) {
				sql = "Insert into Contactos (id_Contacto,nombre,apellido,numero,bloqueado) Values" + "(?,?,?,?,?)";
				try {
					sentencia = con.prepareStatement(sql);
					sentencia.setString(1, contacto.getId());
					sentencia.setString(2, contacto.getNombre());
					sentencia.setString(3, contacto.getApellido());
					sentencia.setString(4, contacto.getNum());
					sentencia.setString(5, String.valueOf(contacto.isBloqueado()));
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				lista.add(contacto);
				lef.actualizarContactos(lista);
			}
		}
	}

	public void eliminarContacto(String id) {
		List<Contacto> lista = lef.contactos();
		if (lista.isEmpty()) {
			System.out.println("No hay ningún contacto en la agenda");
		} else {

			Iterator it = lista.iterator();
			boolean encontrado = false;

			while (it.hasNext() && !encontrado) {
				Contacto c = (Contacto) it.next();

				if (c.getId().equals(id)) {
					encontrado = true;
					lista.remove(c);
					System.out.println("Contacto elimado");

				}
			}
			if (!encontrado) {
				System.out.println("El contacto no se encuentra en la lista");
			}
			lef.actualizarContactos(lista);
		}

		Connection con = con();
		Statement sentencia = null;
		String sql;

		try {
			sql = "DELETE FROM Contactos WHERE id_Contacto ="+id+";";
			sentencia.executeUpdate(sql);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void buscarContacto(String id) {
		List<Contacto> lista = lef.contactos();

		Iterator it = lista.iterator();
		boolean encontrado = false;

		while (it.hasNext() && !encontrado) {
			Contacto c = (Contacto) it.next();

			if (c.getId().equals(id)) {
				;
				encontrado = true;
				System.out.println(c);
				System.out.println("AÑADIR PARA MOSTRAR EL CONTACTO EN EL PROGRAMA");
			}
			if (!encontrado) {
				System.out.println("El contacto no se encuentra en la lista");
			}
		}

	}

	public void bloquearContacto(String id) {
		List<Contacto> lista = lef.contactos();

		Iterator it = lista.iterator();
		boolean encontrado = false;

		while (it.hasNext() && !encontrado) {
			Contacto c = (Contacto) it.next();

			if (c.getId().equals(id)) {
				encontrado = true;
				c.bloqueado = true;

			}

		}
		if(!encontrado) {
			System.out.println("AÑADIR ERROR");
		}
		lef.actualizarContactos(lista);
		
		Connection con = con();
		Statement sentencia = null;
		String sql;
		
	
		
		try {
			sql = "UPDATE Contactos"
					+"SET bloqueado = true"
					+"WHERE id_Contacto ="+id+";";
			sentencia.executeUpdate(sql);
			
			sql = "Insert into Bloqueados (id_Contacto) values"
					+"("+id+");";
			sentencia.executeUpdate(sql);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public void desbloquearContacto(String id) {
		List<Contacto> lista = lef.contactos();

		Iterator it = lista.iterator();
		boolean encontrado = false;

		while (it.hasNext() && !encontrado) {
			Contacto c = (Contacto) it.next();

			if (c.getId().equals(id)) {
				encontrado = true;
				c.bloqueado = true;

			}

		}
		if(!encontrado) {
			System.out.println("AÑADIR ERROR");
		}
		lef.actualizarContactos(lista);
		
		Connection con = con();
		Statement sentencia = null;
		String sql;
		
	
		
		try {
			sql = "UPDATE Contactos"
					+"SET bloqueado = false"
					+"WHERE id_Contacto ="+id+";";
			sentencia.executeUpdate(sql);
			
			sql = "DELETE FROM Bloqueados WHERE id_Contacto ="+id+";";
			sentencia.executeUpdate(sql);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
