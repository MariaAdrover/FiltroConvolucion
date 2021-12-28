package filtroconvolucion;

import java.awt.Container;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class FiltroConvolucion extends JFrame{
    Viewer viewer;
    
    public FiltroConvolucion() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.viewer = new Viewer();
        Container c = this.getContentPane();
        c.add(viewer);
        this.pack();
        this.setLocation(235, 50);        
    }

    public static void main(String[] args) {
        FiltroConvolucion frame = new FiltroConvolucion();
        frame.setVisible(true);
    }    
}
