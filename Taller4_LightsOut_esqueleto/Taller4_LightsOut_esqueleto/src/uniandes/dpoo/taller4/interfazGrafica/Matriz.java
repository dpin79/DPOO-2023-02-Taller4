package InterfazGrafica;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class Matriz extends JPanel {
	
  // entidad de la clase tablero para desarrollar el juego
  private Tablero tablero = new Tablero();
	
  // Matriz que contiene el tablero de juego
  private boolean[][] = tablero.darTablero()
	
  // Tamaño de la ventana
  private final int windowSize = 500;

  // Tamaño de la imagen
  private final int imageSize = 80;

  // Margen de la imagen
  private final int imageMargin = 5;

  // Número de filas y columnas
  private final int rows = windowSize / (imageSize + imageMargin);
  private final int cols = windowSize / (imageSize + imageMargin);

  // Matriz para almacenar las imágenes
  private BufferedImage[][] images = new BufferedImage[rows][cols];

  // Imagen simétrica
  private BufferedImage symmetricalImage = new BufferedImage(windowSize, windowSize, BufferedImage.TYPE_INT_ARGB);

  public Matriz() {
    // Obtiene el contexto gráfico de la imagen simétrica
    final Graphics2D g2d = symmetricalImage.createGraphics();

    // Dibuja la matriz simétrica
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        // Calcula la posición de la imagen con margen
        int x = col * (imageSize + imageMargin) + imageMargin;
        int y = row * (imageSize + imageMargin) + imageMargin;

        // Crea una nueva imagen con el tamaño actual
        BufferedImage image = new BufferedImage(imageSize, imageSize, BufferedImage.TYPE_INT_ARGB);

        // Dibuja algo en la imagen (por ejemplo, un rectángulo relleno de color)
        Graphics2D g2dImage = image.createGraphics();
        g2dImage.setColor(Color.RED);
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
        Graphics2D g2dImage = clickedImage.createGraphics();
        g2dImage.setColor(Color.BLUE);
        g2dImage.fillRect(0, 0, imageSize, imageSize);
        g2dImage.dispose();

        // Vuelve a dibujar la imagen actualizada en la posición actual
        int x = col * (imageSize + imageMargin) + imageMargin;
        int y = row * (imageSize + imageMargin) + imageMargin;
        g2d.drawImage(clickedImage, x, y, null);

        // Vuelve a dibujar la imagen simétrica en el panel
        repaint();
      }
    });
    
  }

  public void paintComponent(Graphics g) {
  super.paintComponent(g);
  g.drawImage(symmetricalImage, 0, 0, null);
  }

  public static void main(String[] args) {
 
  JFrame frame = new JFrame("Symmetrical Image Matrix");
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.setContentPane(new Matriz());
  frame.pack();
  frame.setVisible(true);
  }
  }