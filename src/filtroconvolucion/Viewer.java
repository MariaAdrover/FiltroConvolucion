package filtroconvolucion;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Viewer extends Canvas implements ActionListener {

    BufferedImage img1;
    FilteredImage img2;
    ControlPanel panel;

    public Viewer() {
        this.loadImages();
        this.panel = new ControlPanel(this);
        this.setSize(this.img1.getWidth() * 2 + 60, this.img1.getHeight() + 40);
        this.setBackground(Color.black);
    }
    
    private void aplicarFiltro() {
            this.img2.setKernel(this.panel.getKernelValues());
            this.img2.setDivisor(this.panel.getDivisor());
            this.img2.aplicarFiltro();  
            this.repaint();      
    }

    private void loadImages() {
        try {
            this.img1 = ImageIO.read(new File("4.jpg"));
            this.img2 = new FilteredImage(ImageIO.read(new File("4.jpg")));
        } catch (IOException e) {
            System.out.println("File not found");
        }

    }
    
    private void replaceFilter(int[][] kernel) {
        this.panel.setKernelValues(kernel);
        this.aplicarFiltro();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(img1, 20, 20, null);
        g.drawImage(img2, this.img1.getWidth() + 40, 20, this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.panel.getOk()) {
            aplicarFiltro();
        } else if (ae.getSource() == this.panel.getReset()) {
            this.img2.resetBytesArray(); 
            this.repaint();
        } else if (ae.getSource() == this.panel.getSharpen()) {
            this.replaceFilter(this.panel.getSharpenKernel());
        } else if (ae.getSource() == this.panel.getEmboss()) {
            this.replaceFilter(this.panel.getEmbossKernel());
        } else if (ae.getSource() == this.panel.getEdge()) {
            this.replaceFilter(this.panel.getEdgeKernel());
        } else if (ae.getSource() == this.panel.getEdge2()) {
            this.replaceFilter(this.panel.getEdgeKernel2());
        } else if (ae.getSource() == this.panel.getGradient()) {
            this.replaceFilter(this.panel.getGradientKernel());
        }
    }
}
