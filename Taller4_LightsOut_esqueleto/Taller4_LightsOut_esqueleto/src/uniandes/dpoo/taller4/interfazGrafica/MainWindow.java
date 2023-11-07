package uniandes.dpoo.taller4.interfazGrafica;

import java.awt.EventQueue;
import javax.swing.Timer;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import uniandes.dpoo.taller4.modelo.RegistroTop10;
import uniandes.dpoo.taller4.modelo.Top10;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.RootPaneContainer;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Collection;
import java.util.PriorityQueue;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow extends JFrame {

	private JPanel mainPane;
	private int dificultad = 5;
	public String textoDificultad;
	private int tamano = 5;
	private Top10 top10;
	private Collection<RegistroTop10> registrosTop10;
	private String nombreJugador = "AAA";
	private Timer timer;
	
	private TableroGrafico tablero;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private String textoRegistro;
	private String numJugadas;
	private static final int CENTER = SwingConstants.CENTER;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 734, 618);
		mainPane = new JPanel();
		mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(mainPane);
		mainPane.setLayout(null);
		tablero = new TableroGrafico(tamano, dificultad);
		tablero.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				numJugadas = Integer.toString(tablero.darJugadas());
				System.out.println(numJugadas);
				
				
			}
		});
		JPanel panel = tablero;
		tablero.setBackground(new Color(0, 0, 0));
		panel.setBounds(10, 40, 505, 503);
		mainPane.add(panel);
		
		JButton btnNUEVO = new JButton("NUEVO");
		btnNUEVO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPane.remove(panel);
				mainPane.remove(tablero);
				System.out.println(Integer.toString(dificultad));
				JPanel tablero = new TableroGrafico(tamano,dificultad);
				JPanel panel = tablero;
				tablero.setBackground(new Color(0, 0, 0));
				panel.setBounds(10, 40, 505, 503);
				mainPane.add(panel);
				panel.revalidate();
				tablero.revalidate();
				panel.repaint();
				tablero.repaint();
				
				
			}
		});
		btnNUEVO.setForeground(Color.WHITE);
		btnNUEVO.setBackground(new Color(41, 125, 218));
		btnNUEVO.setBounds(519, 138, 191, 46);
		mainPane.add(btnNUEVO);
		
		JButton btnREINICIAR = new JButton("REINICIAR");
		btnREINICIAR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPane.remove(panel);
				mainPane.remove(tablero);
				System.out.println(Integer.toString(dificultad));
				//JPanel tablero = ;
				JPanel panel = tablero;
				tablero.setBackground(new Color(0, 0, 0));
				panel.setBounds(10, 40, 505, 503);
				mainPane.add(panel);
				panel.revalidate();
				tablero.revalidate();
				panel.repaint();
				tablero.repaint();
			}
		});
		btnREINICIAR.setForeground(Color.WHITE);
		btnREINICIAR.setBackground(new Color(41, 125, 218));
		btnREINICIAR.setBounds(519, 206, 191, 46);
		mainPane.add(btnREINICIAR);
		
		// Crear un JPanel con BoxLayout vertical para contener los JLabel
				JPanel panelTOP10 = new JPanel();
				panelTOP10.setLayout(new BoxLayout(panelTOP10, BoxLayout.Y_AXIS));
				top10 = new Top10();
				File archivoTop10 = new File("./data/top10.csv");
				top10.cargarRecords(archivoTop10);
				registrosTop10 = top10.darRegistros();

				// Agregar los 11 JLabel al panel
				int i = 0;
				int i_= 1;
					if (i == 0) {
						JLabel label_ = new JLabel("TOP 10");
						label_.setHorizontalAlignment(CENTER);
					    label_.setBackground(new Color(41, 125, 218));
					    label_.setFont(new Font("Arial", Font.PLAIN, 25));
					    panelTOP10.add(label_);
					    i++;
					}
						for (RegistroTop10 registro : registrosTop10) {
						    textoRegistro = registro.toString();
						    JLabel label_ = new JLabel((i_) + ". " + textoRegistro);
						    label_.setHorizontalAlignment(CENTER);
						    label_.setBackground(Color.RED);
						    label_.setFont(new Font("Arial", Font.PLAIN, 20));
						    panelTOP10.add(label_);
						    i_++;
						  
					}		
		
		JButton btnTOP10 = new JButton("TOP-10");
		btnTOP10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, panelTOP10, "top 10", JOptionPane.PLAIN_MESSAGE);
			}
		});
		btnTOP10.setForeground(Color.WHITE);
		btnTOP10.setBackground(new Color(41, 125, 218));
		btnTOP10.setBounds(519, 275, 191, 46);
		mainPane.add(btnTOP10);
		
		JButton btnCAMBIARJUGADOR = new JButton("CAMBIAR JUGADOR");
		btnCAMBIARJUGADOR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelCambiarJugador panelCambiarJugador = new PanelCambiarJugador();
		        int opcion = JOptionPane.showOptionDialog(
		                null,
		                panelCambiarJugador,
		                "Cambiar Jugador",
		                JOptionPane.DEFAULT_OPTION,
		                JOptionPane.PLAIN_MESSAGE,
		                null,
		                null,
		                null
		        );
		        if (opcion == JOptionPane.OK_OPTION) {
		            nombreJugador = panelCambiarJugador.getValor();
		            // hacer algo con el valor ingresado
		        }
			}
		});
		btnCAMBIARJUGADOR.setForeground(Color.WHITE);
		btnCAMBIARJUGADOR.setBackground(new Color(41, 125, 218));
		btnCAMBIARJUGADOR.setBounds(519, 348, 191, 46);
		mainPane.add(btnCAMBIARJUGADOR);
		
		
		JPanel panelConfiguracion = new JPanel();
		panelConfiguracion.setBackground(new Color(41, 125, 218));
		panelConfiguracion.setBounds(0, 0, 710, 41);
		mainPane.add(panelConfiguracion);
		panelConfiguracion.setLayout(null);
		
		JLabel lblTamano = new JLabel("Tama\u00F1o:");
		lblTamano.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTamano.setForeground(new Color(255, 255, 255));
		lblTamano.setHorizontalAlignment(SwingConstants.CENTER);
		lblTamano.setBounds(21, 10, 121, 21);
		panelConfiguracion.add(lblTamano);
		
		JComboBox comboBoxTamano = new JComboBox();
		comboBoxTamano.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textoTamano = (String) comboBoxTamano.getSelectedItem();
				if (textoTamano == "5x5"){
					tamano = 5 ;}
				else if (textoTamano == "7x7") {
					tamano = 7 ;}
				else if (textoTamano == "15x15") {
					tamano = 15;}
				
				
			}
		});
		comboBoxTamano.setModel(new DefaultComboBoxModel(new String[] {"5x5", "7x7", "15x15"}));
		comboBoxTamano.setBounds(127, 10, 121, 21);
		panelConfiguracion.add(comboBoxTamano);
		
		JLabel lblDificultad = new JLabel("Dificultad:");
		lblDificultad.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDificultad.setForeground(new Color(255, 255, 255));
		lblDificultad.setBounds(292, 10, 121, 21);
		panelConfiguracion.add(lblDificultad);
		
		JRadioButton rdbtnFacil = new JRadioButton("f\u00E1cil");
		rdbtnFacil.setSelected(true);
		rdbtnFacil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dificultad = tamano; 
			}
		});
		buttonGroup.add(rdbtnFacil);
		rdbtnFacil.setBackground(new Color(41, 125, 218));
		rdbtnFacil.setForeground(new Color(255, 255, 255));
		rdbtnFacil.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnFacil.setBounds(397, 12, 73, 21);
		panelConfiguracion.add(rdbtnFacil);
		
		JRadioButton rdbtnMedio = new JRadioButton("medio");
		rdbtnMedio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dificultad = tamano *2;
			}
		});
		rdbtnMedio.setSelected(true);
		buttonGroup.add(rdbtnMedio);
		rdbtnMedio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnMedio.setForeground(new Color(255, 255, 255));
		rdbtnMedio.setBackground(new Color(41, 125, 218));
		rdbtnMedio.setBounds(472, 10, 73, 25);
		panelConfiguracion.add(rdbtnMedio);
		
		JRadioButton rdbtnDificil = new JRadioButton("dif\u00EDcil");
		rdbtnDificil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dificultad = tamano * 3;
			}
		});
		buttonGroup.add(rdbtnDificil);
		rdbtnDificil.setForeground(new Color(255, 255, 255));
		rdbtnDificil.setBackground(new Color(41, 125, 218));
		rdbtnDificil.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnDificil.setBounds(567, 12, 112, 21);
		panelConfiguracion.add(rdbtnDificil);
		
		JPanel panelInfo = new JPanel();
		panelInfo.setBounds(10, 553, 700, 23);
		mainPane.add(panelInfo);
		panelInfo.setLayout(null);
		
		JLabel lblJugadas = new JLabel("Jugadas:");
		lblJugadas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblJugadas.setBounds(10, 0, 104, 23);
		panelInfo.add(lblJugadas);
		
		JLabel lblJugador = new JLabel("Jugador:");
		lblJugador.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblJugador.setBounds(301, 1, 131, 20);
		panelInfo.add(lblJugador);
		
		numJugadas = Integer.toString(tablero.darJugadas());
		JLabel lblNumJugadas = new JLabel(numJugadas);
		lblNumJugadas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNumJugadas.setBounds(80, 0, 104, 20);
		panelInfo.add(lblNumJugadas);
		Timer timer = new Timer(200, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                lblNumJugadas.setText(numJugadas);
            }
        });
        timer.start();

		
		JLabel lblNombreJugador = new JLabel(nombreJugador);
		lblNombreJugador.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNombreJugador.setBounds(375, 0, 125, 20);
		panelInfo.add(lblNombreJugador);
		Timer timer_ = new Timer(1000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                lblNombreJugador.setText(nombreJugador);
            }
        });
        timer_.start();
		
		
		
	}
}
