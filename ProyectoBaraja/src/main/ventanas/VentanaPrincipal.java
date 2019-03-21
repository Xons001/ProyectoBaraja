package main.ventanas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;
import javax.swing.LayoutStyle.ComponentPlacement;

import main.componentes.MetedosCartas;
import main.implementacionesDAO.ConectionExist;

import javax.swing.JButton;
import javax.swing.JTextField;

public class VentanaPrincipal {

	private JFrame frame;
	private JTextField textField;

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
		
		JList list = new JList();
		
		JList list_1 = new JList();
		
		JButton button = new JButton("->");
		
		JButton button_1 = new JButton("<-");
		
		JButton btnCargarCartas = new JButton("Load Cards");
		btnCargarCartas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ConectionExist ce = new ConectionExist();
				MetedosCartas mc = new MetedosCartas();
				ce.conectionCards();
				list.setModel(mc.insertarCartasListas());
			}
		});
		
		JButton btnRandomCards = new JButton("Random cards");
		
		JButton btnGuardar = new JButton("Save Deck");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btnLoadDeck = new JButton("Load Deck");
		btnLoadDeck.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		
		
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(53)
							.addComponent(list, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(button)
								.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
							.addGap(26)
							.addComponent(list_1, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
							.addGap(40)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(10)
									.addComponent(btnLoadDeck))
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(223)
							.addComponent(btnRandomCards))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(83)
							.addComponent(btnCargarCartas)
							.addGap(183)
							.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(39, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(41)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(list, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE)
										.addComponent(list_1, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(112)
									.addComponent(button)
									.addGap(46)
									.addComponent(button_1)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnCargarCartas)
									.addGap(29)
									.addComponent(btnRandomCards))
								.addComponent(btnGuardar)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(124)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnLoadDeck)))
					.addContainerGap(49, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
