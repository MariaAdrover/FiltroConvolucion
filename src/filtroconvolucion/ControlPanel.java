package filtroconvolucion;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ControlPanel extends JFrame {

    JTextField value1;
    JTextField value2;
    JTextField value3;
    JTextField value4;
    JTextField value5;
    JTextField value6;
    JTextField value7;
    JTextField value8;
    JTextField value9;
    JTextField divisor;
    JButton ok;
    JButton reset;
    JMenu menu;
    JMenuBar menuBar;
    JMenuItem sharpen;
    JMenuItem emboss;
    JMenuItem edge;
    JMenuItem edge2;
    JMenuItem gradient;
    
    int[][] sharpenKernel;    
    int[][] embossKernel;   
    int[][] edgeKernel; 
    int[][] edgeKernel2;
    int[][] gradientKernel;

    Viewer viewer;

    public ControlPanel(Viewer viewer) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.viewer = viewer;
        this.setPredeterminateKernels();
        Container container = this.getContentPane();
        this.setContent(container);

        this.pack();
        this.setLocation(10, 50);
        this.setVisible(true);
    }
    
    public void setKernelValues(int[][] k) {
        this.value1.setText(String.valueOf(k[0][0]));
        this.value2.setText(String.valueOf(k[0][1]));
        this.value3.setText(String.valueOf(k[0][2]));
        this.value4.setText(String.valueOf(k[1][0]));
        this.value5.setText(String.valueOf(k[1][1]));
        this.value6.setText(String.valueOf(k[1][2]));
        this.value7.setText(String.valueOf(k[2][0]));
        this.value8.setText(String.valueOf(k[2][1]));
        this.value9.setText(String.valueOf(k[2][2]));
        
        this.divisor.setText("1");
    }

    public int[][] getKernelValues() {
        int[][] kernel = new int[3][3];
        kernel[0][0] = Integer.parseInt(this.value1.getText());
        kernel[0][1] = Integer.parseInt(this.value2.getText());
        kernel[0][2] = Integer.parseInt(this.value3.getText());
        kernel[1][0] = Integer.parseInt(this.value4.getText());
        kernel[1][1] = Integer.parseInt(this.value5.getText());
        kernel[1][2] = Integer.parseInt(this.value6.getText());
        kernel[2][0] = Integer.parseInt(this.value7.getText());
        kernel[2][1] = Integer.parseInt(this.value8.getText());
        kernel[2][2] = Integer.parseInt(this.value9.getText());

        return kernel;
    }

    public int getDivisor() {

        return Integer.parseInt(this.divisor.getText());
    }

    private void setContent(Container container) {
        JPanel panel = new JPanel();
        GridBagLayout grid = new GridBagLayout();
        panel.setLayout(grid);
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = 1;
        constraints.weightx = 1;
        constraints.weighty = 1;

        Font font = new Font("Courier New", Font.BOLD, 25);
        
        this.value1 = new JTextField("-1");
        this.value1.setFont(font);
        this.value2 = new JTextField("-2");
        this.value2.setFont(font);
        this.value3 = new JTextField("-1");
        this.value3.setFont(font);
        panel.add(value1, constraints);
        panel.add(value2, constraints);
        panel.add(value3, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.value4 = new JTextField("0");
        this.value4.setFont(font);
        this.value5 = new JTextField("0");
        this.value5.setFont(font);
        this.value6 = new JTextField("0");
        this.value6.setFont(font);
        panel.add(value4, constraints);
        constraints.gridx = 1;
        panel.add(value5, constraints);
        constraints.gridx = 2;
        panel.add(value6, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        this.value7 = new JTextField("1");
        this.value7.setFont(font);
        this.value8 = new JTextField("2");
        this.value8.setFont(font);
        this.value9 = new JTextField("1");
        this.value9.setFont(font);
        panel.add(value7, constraints);
        constraints.gridx = 1;
        panel.add(value8, constraints);
        constraints.gridx = 2;
        panel.add(value9, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;

        JLabel label = new JLabel("DIVISOR: ");
        label.setFont(new Font("Courier New", Font.BOLD, 15));
        panel.add(label, constraints);

        constraints.gridx = 2;
        constraints.gridy = 3;
        constraints.gridwidth = 1;

        this.divisor = new JTextField("1");
        divisor.setFont(font);
        panel.add(divisor, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 3;

        this.ok = new JButton("Aplicar filtro");
        ok.setFont(new Font("Courier New", Font.BOLD, 20));
        this.ok.addActionListener(this.viewer);
        panel.add(ok, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;

        this.reset = new JButton("Img Original");
        reset.setFont(new Font("Courier New", Font.BOLD, 20));
        this.reset.addActionListener(this.viewer);
        panel.add(reset, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        panel.add(menuBar, constraints);

        menu = new JMenu("Choose filter -->");
        menu.setFont(new Font("Courier New", Font.BOLD, 20));
        menuBar.add(menu);

        sharpen = new JMenuItem("sharpen");
        sharpen.setFont(new Font("Courier New", Font.BOLD, 20));
        sharpen.addActionListener(viewer);
        menu.add(sharpen);

        emboss = new JMenuItem("emboss");
        emboss.setFont(new Font("Courier New", Font.BOLD, 20));
        emboss.addActionListener(viewer);
        menu.add(emboss);

        edge = new JMenuItem("edge");
        edge.setFont(new Font("Courier New", Font.BOLD, 20));
        edge.addActionListener(viewer);
        menu.add(edge);

        edge2 = new JMenuItem("edge2");
        edge2.setFont(new Font("Courier New", Font.BOLD, 20));
        edge2.addActionListener(viewer);
        menu.add(edge2);

        gradient = new JMenuItem("gradient");
        gradient.setFont(new Font("Courier New", Font.BOLD, 20));
        gradient.addActionListener(viewer);
        menu.add(gradient);

        container.add(panel);
    }
    
    private void setPredeterminateKernels() {
        int[][] s = {{0,-1,0}, {-1,5,-1},{0,-1,0}};
        this.sharpenKernel = s;
        
        int[][] e = {{-2,-1,0}, {-1,1,1},{0,1,2}};
        this.embossKernel = e; 
        
        int[][] ed = {{0,-1,0}, {-1,4,-1},{0,-1,0}};
        this.edgeKernel = ed; 
        
        int[][] ed2 = {{-1,-1,-1}, {-1,8,-1},{-1,-1,-1}};
        this.edgeKernel2 = ed2; 
        
        int[][] g = {{-1,-1,-1}, {0,0,0},{1,1,1}};
        this.gradientKernel = g;    
    }

    public JButton getOk() {
        return ok;
    }

    public JButton getReset() {
        return reset;
    }

    public JMenuItem getSharpen() {
        return sharpen;
    }

    public JMenuItem getEmboss() {
        return emboss;
    }

    public JMenuItem getEdge() {
        return edge;
    }

    public JMenuItem getEdge2() {
        return edge2;
    }

    public int[][] getSharpenKernel() {
        return sharpenKernel;
    }

    public int[][] getEmbossKernel() {
        return embossKernel;
    }

    public int[][] getEdgeKernel() {
        return edgeKernel;
    }

    public int[][] getEdgeKernel2() {
        return edgeKernel2;
    }

    public JMenuItem getGradient() {
        return gradient;
    }

    public int[][] getGradientKernel() {
        return gradientKernel;
    }
}
