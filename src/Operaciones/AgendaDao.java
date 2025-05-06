package Operaciones;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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
	
			

	public void eliminarContacto(String nombre, String numero) {
		List<Contacto> lista = lef.contactos();
		if (lista.isEmpty()) {
			System.out.println("No hay ningún contacto en la agenda");
		} else {

			Iterator it = lista.iterator();
			boolean encontrado = false;

			while (it.hasNext() && !encontrado) {
				Contacto c = (Contacto) it.next();

				if (c.getNombre().equalsIgnoreCase("nombre"));
				encontrado = true;
				lista.remove(c);
				System.out.println("Contacto elimado");

			}
			if (!encontrado) {
				System.out.println("El contacto no se encuentra en la lista");
			}

			BufferedWriter bw = null;
			try {
				bw = new BufferedWriter(new FileWriter("Contactos.txt", true));
				for (int i = 0; i < lista.size(); i++) {
					bw.write(lista.get(i).getNombre() + " " + lista.get(i).getNum());
				}

				System.out.println("Contacto añadido");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (bw != null) {
					try {
						bw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}
		
		Connection con = con();
		Statement sentencia = null;
		String sql;
		
		
		try {
			sql ="INSERT INTO CONTACTOS (nombre, numero) values"
					+ "("+nombre+","+numero+");";
			sentencia.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void buscarContacto() {
		List<Contacto> lista = lef.contactos();
		System.out.println("Dime le nombre del contacto que deseas buscar");
		
		Iterator it = lista.iterator();
		boolean encontrado = false;

		while (it.hasNext() && !encontrado) {
			Contacto c = (Contacto) it.next();

			if (c.getNombre().equalsIgnoreCase("nombre"));
			encontrado = true;
			System.out.println(c);
		}
		if (!encontrado) {
			System.out.println("El contacto no se encuentra en la lista");
		}
	}

}
