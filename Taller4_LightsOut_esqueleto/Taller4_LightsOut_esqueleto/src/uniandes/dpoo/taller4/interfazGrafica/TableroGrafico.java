package uniandes.dpoo.taller4.interfazGrafica;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.*;

import uniandes.dpoo.taller4.modelo.Tablero;
import uniandes.dpoo.taller4.modelo.Top10;

public class TableroGrafico extends JPanel {
	
  // Tamaño de la ventana
  private final int windowSize = 500;
  
  private boolean[][] matrizTablero;
  private boolean[][] matrizTableroOriginal;
  private Top10 top10 = new Top10();
  
  public int puntaje;

  // Imagen simétrica
  private BufferedImage symmetricalImage = new BufferedImage(windowSize, windowSize, BufferedImage.TYPE_INT_ARGB);

private int jugadas;

  public TableroGrafico(int tamanoTablero, int dificultad) {
	  
	// Tamaño de la imagen
	  final int imageSize = 450/tamanoTablero;

	  // Margen de la imagen
	  final int imageMargin = 50/tamanoTablero;
	  
	// Número de filas y columnas
	  final int rows = windowSize / (imageSize + imageMargin);
	  final int cols = windowSize / (imageSize + imageMargin);
	  
	// Matriz para almacenar las imágenes
	  BufferedImage[][] images = new BufferedImage[rows][cols];
	  
	// entidad de la clase tablero para desarrollar el juego
	  Tablero tablero = new Tablero(tamanoTablero);
	  
	// Obtiene el contexto gráfico de la imagen simétrica
    final Graphics2D g2d = symmetricalImage.createGraphics();

    // Dibuja la matriz simétrica
    tablero.desordenar(dificultad);
    matrizTablero= tablero.darTablero();
    matrizTableroOriginal = matrizTablero;
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        // Calcula la posición de la imagen con margen
        int x = col * (imageSize + imageMargin) + imageMargin;
        int y = row * (imageSize + imageMargin) + imageMargin;

        // Crea una nueva imagen con el tamaño actual
        BufferedImage image = new BufferedImage(imageSize, imageSize, BufferedImage.TYPE_INT_ARGB);

        // Dibuja algo en la imagen (por ejemplo, un rectángulo relleno de color)
        Graphics2D g2dImage = image.createGraphics();
        if (matrizTablero[row][col] == true){
        	g2dImage.setColor(Color.YELLOW);
        }
        else {
        	g2dImage.setColor(Color.GRAY);
        }
        g2dImage.fillRect(0, 0, imageSize, imageSize);
        g2dImage.dispose();

        // Guarda la imagen en la matriz
        images[row][col] = image;

        // Copia la imagen en la posición actual
        g2d.drawImage(image, x, y, null);
      }
    }

    // Agrega un MouseListener al panel
    addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        // Obtiene la posición del clic del mouse
        int mouseX = e.getX();
        int mouseY = e.getY();

        // Calcula la fila y la columna en la que se hizo clic
        int row = mouseY / (imageSize + imageMargin);
        int col = mouseX / (imageSize + imageMargin);

        // Obtiene la imagen que se encuentra en la fila y la columna
        BufferedImage clickedImage = images[row][col];

        // Realiza alguna acción con la imagen (por ejemplo, cambia su color)
        
        tablero.jugar(row, col);
        matrizTablero = tablero.darTablero();
        for (int row_ = 0; row_ < rows; row_++) {
            for (int col_ = 0; col_ < cols; col_++) {
              // Calcula la posición de la imagen con margen
              int x = col_ * (imageSize + imageMargin) + imageMargin;
              int y = row_ * (imageSize + imageMargin) + imageMargin;

              // Crea una nueva imagen con el tamaño actual
              BufferedImage image = new BufferedImage(imageSize, imageSize, BufferedImage.TYPE_INT_ARGB);

              // Dibuja algo en la imagen (por ejemplo, un rectángulo relleno de color)
              Graphics2D g2dImage = image.createGraphics();
              if (matrizTablero[row_][col_] == true){
              	g2dImage.setColor(Color.YELLOW);
              }
              else {
              	g2dImage.setColor(Color.GRAY);
              }
              g2dImage.fillRect(0, 0, imageSize, imageSize);
              g2dImage.dispose();
              
           // Guarda la imagen en la matriz
              images[row_][col_] = image;

              // Copia la imagen en la posición actual
              g2d.drawImage(image, x, y, null);
            }
            
            
          }
        
     // Vuelve a dibujar la imagen simétrica en el panel
        repaint();
        jugadas = tablero.darJugadas(); 
        
        //identifica si el juego fue completado y ejecuta una accion
        if (tablero.tableroIluminado()){
        	System.out.println("has ganado");
        	puntaje = tablero.calcularPuntaje();
        	JOptionPane.showMessageDialog(null, "Has ganado.    putaje: " + puntaje , "Título del mensaje", JOptionPane.INFORMATION_MESSAGE);
        	
        }
        
      }
    });
    }

  public int darJugadas() {
	  return jugadas;
  }
  
  public void paintComponent(Graphics g) {
  super.paintComponent(g);
  g.drawImage(symmetricalImage, 0, 0, null);
  File archivoTop10 = new File("./data/top10.csv");
  }

}


 /*
  public static void main(String[] args) {
 
  JFrame frame = new JFrame("Symmetrical Image Matrix");
  //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.setContentPane(new TableroGrafico(5, 5));
  frame.pack();
  frame.setVisible(true);
  frame.setSize(530, 550);
  }
  }
  
  */