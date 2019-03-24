package main.ventanas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import main.componentes.MetodosCartas;
import main.implementacionesDAO.ConectionExist;
import main.implementacionesDAO.ConectionMongoDB;
import main.interfacesDAO.Conexion;
import main.interfacesDAO.IBaraja;
import main.models.Baraja;
import main.models.Carta;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class VentanaPrincipal {

	private JFrame frame;
	private JTextField textFieldBaraja;
	private JList listaIquierda;
	private JList listaDerecha;
	private JButton btnAdd;
	private JButton btnRemove;
	private JButton btnCargarCartas;
	private JButton btnRandomCards;
	private JButton btnGuardar;
	private JButton btnLoadDeck;
	private JLabel valorTotal;
	public static DefaultListModel modelo;
	public static DefaultListModel modeloBaraja;
	public static int sumaValor;
	public boolean load = false;
	public static Baraja barajaCaragada;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 704, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		listaIquierda = new JList();

		listaDerecha = new JList();

		btnAdd = new JButton("->");
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					// carta equivale a esto = selecciona el elemento de la lista de la izquierda
					Carta carta = MetodosCartas.cartasNombre.get(listaIquierda.getSelectedIndex());
					// instanciamos los default list model
					modelo = new DefaultListModel<>();
					modeloBaraja = new DefaultListModel<>();
					// sumamos el valor de cada carta para despues comprobar que ha sobrepasado el
					// limite de 20
					sumaValor = sumaValor + carta.getValue();
					if (sumaValor <= 20) {
						// insertamos el dato del primer array al segundo, el dato del primer array se
						// elimina
						MetodosCartas.cartasEscogidas.add(carta);
						MetodosCartas.cartasNombre.remove(carta);
						// recorremos y printamos de nuevo el primer array al default list model y lo
						// ponemos de nuevo en la lista de la izquierda
						for (Carta cartas : MetodosCartas.cartasNombre) {
							modelo.addElement(cartas.getName() + " Valor: " + cartas.getValue());
						}
						listaIquierda.setModel(modelo);
						// recorremos y printamos de nuevo el segundo array al default list model y lo
						// ponemos de nuevo en la lista de la derecha
						for (Carta cartas : MetodosCartas.cartasEscogidas) {
							modeloBaraja.addElement(cartas.getName() + " Valor: " + cartas.getValue());
						}
						listaDerecha.setModel(modeloBaraja);
						valorTotal.setText(Integer.toString(sumaValor));
					} else {
						JOptionPane.showMessageDialog(null, "Tu baraja tiene un valor superior a 20", "Error",
								JOptionPane.ERROR_MESSAGE);
						sumaValor = sumaValor - carta.getValue();
					}
				} catch (ArrayIndexOutOfBoundsException errorNull) {
					JOptionPane.showMessageDialog(null, "No se puede porque no hay ninguna carta seleccionada", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnRemove = new JButton("<-");
		btnRemove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					// selecciona el elemento de la lista de la izquierda
					Carta carta = MetodosCartas.cartasEscogidas.get(listaDerecha.getSelectedIndex());
					modelo = new DefaultListModel<>();
					modeloBaraja = new DefaultListModel<>();
					sumaValor = sumaValor - carta.getValue();

					MetodosCartas.cartasEscogidas.remove(carta);
					MetodosCartas.cartasNombre.add(carta);

					valorTotal.setText(Integer.toString(sumaValor));
					for (Carta cartas : MetodosCartas.cartasNombre) {
						modelo.addElement(cartas.getName() + " Valor: " + cartas.getValue());
					}

					listaIquierda.setModel(modelo);
					for (Carta cartas : MetodosCartas.cartasEscogidas) {
						modeloBaraja.addElement(cartas.getName() + " Valor: " + cartas.getValue());
					}

					listaDerecha.setModel(modeloBaraja);

				} catch (ArrayIndexOutOfBoundsException errorNull) {
					JOptionPane.showMessageDialog(null, "No se puede porque no hay ninguna carta seleccionada", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnCargarCartas = new JButton("Load Cards");
		btnCargarCartas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ConectionExist ce = new ConectionExist();

				ce.conectionCards();
				listaIquierda.setModel(MetodosCartas.insertarCartasListas(modelo));
			}
		});

		btnRandomCards = new JButton("Random cards");
		btnRandomCards.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				modelo = new DefaultListModel<>();
				modeloBaraja = new DefaultListModel<>();
				modeloBaraja.removeAllElements();
				listaDerecha.setModel(modeloBaraja);
				MetodosCartas.cartasEscogidas.clear();
				sumaValor = 0;
				boolean salir = true;
				int valorMasPequeno = 0;
				for (Carta cartas1 : MetodosCartas.cartasNombre) {
					if (cartas1.getValue() < 20) {
						valorMasPequeno = cartas1.getValue();
					}
				}
				MetodosCartas.insertarCartasListas(modelo);
				int sumaValorBaraja = sumaValor + valorMasPequeno;
				while(sumaValorBaraja <= 20 && salir) {
					Carta carta = MetodosCartas.cartasNombre.get(new Random().nextInt(MetodosCartas.cartasNombre.size()));
					sumaValor = sumaValor + carta.getValue();
					if(sumaValor <= 20) {
						MetodosCartas.cartasEscogidas.add(carta);
						MetodosCartas.cartasNombre.remove(carta);
						for (Carta cartas1 : MetodosCartas.cartasNombre) {
							if (cartas1.getValue() < 20 && valorMasPequeno > cartas1.getValue()) {
								valorMasPequeno = cartas1.getValue();
							}
						}
						sumaValorBaraja = sumaValor + valorMasPequeno;
					} else {
						sumaValor = sumaValor - carta.getValue();
						salir = false;
					}
				}
				valorTotal.setText(Integer.toString(sumaValor));
				System.out.println(sumaValor);
				for (Carta cartas1 : MetodosCartas.cartasNombre) {
					modelo.addElement(cartas1.getName() + " Valor: " + cartas1.getValue());
				}
				listaIquierda.setModel(modelo);
				// recorremos y printamos de nuevo el segundo array al default list model y lo
				// ponemos de nuevo en la lista de la derecha
				for (Carta cartas2 : MetodosCartas.cartasEscogidas) {
				modeloBaraja.addElement(cartas2.getName() + " Valor: " + cartas2.getValue());
				}
				listaDerecha.setModel(modeloBaraja);
			
				
//				boolean a = true;
//				int valorMasPequeno = 0;
//				sumaValor = 0;
//				listaDerecha.setModel(modeloBaraja);
//				
//				MetodosCartas.cartasEscogidas.clear();
//				while (a) {
//					System.out.println("hola");
//
//					for (Carta cartas1 : MetodosCartas.cartasNombre) {
//						if (cartas1.getValue() < 20) {
//							valorMasPequeno = cartas1.getValue();
//						}
//					}
//					// Hace
//					Carta carta = MetodosCartas.cartasNombre.get(new Random().nextInt(MetodosCartas.insertarCartasListas(modelo).size()));
//
//					// instanciamos los default list model
//
//					// sumamos el valor de cada carta para despues comprobar que ha sobrepasado el
//					// limite de 20
//				
//					if ((valorMasPequeno + sumaValor) > 20) {
//						a = false;
//					} else {
//							// insertamos el dato del primer array al segundo, el dato del primer array se
//							// elimina
//						System.out.println(carta.getName());
//						sumaValor = sumaValor + carta.getValue();
//						MetodosCartas.cartasEscogidas.add(carta);
//						MetodosCartas.cartasNombre.remove(carta);
//						System.out.println("despues: "+MetodosCartas.cartasNombre.size());
//						System.out.println("suma"+(valorMasPequeno + sumaValor));
//					}
//				}
//				System.out.println("Valor" + sumaValor);
//
//
//				// recorremos y printamos de nuevo el primer array al default list model y lo
//				// ponemos de nuevo en la lista de la izquierda
//				for (Carta cartas1 : MetodosCartas.cartasNombre) {
//					modelo.addElement(cartas1.getName() + " Valor: " + cartas1.getValue());
//				}
//				listaIquierda.setModel(modelo);
//				// recorremos y printamos de nuevo el segundo array al default list model y lo
//				// ponemos de nuevo en la lista de la derecha
//				for (Carta cartas2 : MetodosCartas.cartasEscogidas) {
//					modeloBaraja.addElement(cartas2.getName() + " Valor: " + cartas2.getValue());
//				}
//				listaDerecha.setModel(modeloBaraja);
			}
		});
		
		
		btnGuardar = new JButton("Save Deck");
		btnGuardar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ConectionMongoDB m = new ConectionMongoDB();

				System.out.println(valorTotal.getText());
				if(load) {
					Baraja b1 = new Baraja(barajaCaragada.getDeckName(), valorTotal.getText(),MetodosCartas.cartasEscogidas);
					m.modificarBarajaCreada(b1);
				}else {
				String Barajanombre= JOptionPane.showInputDialog("Introudce el nombre: ");
				Baraja b1 = new Baraja(Barajanombre, valorTotal.getText(),MetodosCartas.cartasEscogidas);
				m.saveBaraja(b1);
			}
			}

		});

		textFieldBaraja = new JTextField();
		textFieldBaraja.setColumns(10);

		btnLoadDeck = new JButton("Load Deck");
		btnLoadDeck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				load = true;
				modeloBaraja = new DefaultListModel<>();
				ConectionMongoDB m = new ConectionMongoDB();
				Baraja b1;
				b1 = m.getBarajaPorNombre(textFieldBaraja.getText());
				System.out.println(b1.getBaraja().toString());
				System.out.println(b1.getDeckName());
				for (Carta cartas2 : b1.getBaraja()) {
					modeloBaraja.addElement(cartas2.getName() + " Valor: " + cartas2.getValue());
					}
				barajaCaragada = b1;
				System.out.println(b1.getDeckValue());
				valorTotal.setText(b1.getDeckValue());
				MetodosCartas.cartasEscogidas = b1.getBaraja();
				listaDerecha.setModel(modeloBaraja);
			}
		});
		
		valorTotal = new JLabel("0");

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(53)
							.addComponent(listaIquierda, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnAdd)
								.addComponent(btnRemove, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
							.addGap(26)
							.addComponent(listaDerecha, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
							.addGap(40)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(10)
									.addComponent(btnLoadDeck))
								.addComponent(textFieldBaraja, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(223)
							.addComponent(btnRandomCards))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(83)
							.addComponent(btnCargarCartas)
							.addGap(183)
							.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(39, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(390, Short.MAX_VALUE)
					.addComponent(valorTotal, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addGap(255))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(16)
									.addComponent(valorTotal)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(listaIquierda, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE)
										.addComponent(listaDerecha, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(112)
									.addComponent(btnAdd)
									.addGap(46)
									.addComponent(btnRemove)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnCargarCartas)
									.addGap(29)
									.addComponent(btnRandomCards))
								.addComponent(btnGuardar)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(124)
							.addComponent(textFieldBaraja, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnLoadDeck)))
					.addContainerGap(48, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
