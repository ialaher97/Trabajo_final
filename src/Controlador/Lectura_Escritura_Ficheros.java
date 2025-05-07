package Controlador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import Operaciones.Contacto;

public class Lectura_Escritura_Ficheros {
	Scanner sc = new Scanner(System.in);

	public List<Contacto> contactos() {
		BufferedReader br = null;
		List<Contacto> contactos = new ArrayList<>();

		try {
			br = new BufferedReader(new FileReader("Contactos.txt"));
			String linea = br.readLine();

			if (linea.isEmpty()) {
				System.out.println("La agenda está vacía");
			} else {
				while (linea != null) {
					String[] array = linea.split(" ");
					Contacto c = new Contacto(array[0],array[1],array[2],array[3],Boolean.valueOf(array[4]));
					contactos.add(c);
					linea = br.readLine();

				}

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return contactos;
	}

	public void nuevoContacto(Contacto contacto) {
		
			BufferedWriter bw = null;

			try {
				bw = new BufferedWriter(new FileWriter("Contactos.txt", true));
				bw.write(contacto.getId()+" "+contacto.getNombre()+" "+contacto.getApellido()+" "+contacto.getNum()+" "+contacto.isBloqueado());
				bw.newLine();
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
	
	public void actualizarContactos(List<Contacto> contactos) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter("Contactos.txt"));
			for (int i = 0; i < contactos.size(); i++) {
				bw.write(contactos.get(i).getNombre() + " " + contactos.get(i).getNum());
			}

			System.out.println("Lista actualizada");

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
	}

