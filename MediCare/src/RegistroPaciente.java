import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistroPaciente {
    public JPanel panelRegistroPac;
    public JLabel titRegistro;
    public JButton IngresoMayorEdad;
    public JButton IngresoMenorEdad;
    public JButton volver;
    private JLabel titulo;
    private JLabel titPersonalMedico;
    private JButton titRegiMedico;

    public RegistroPaciente() {
        IngresoMayorEdad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(panelRegistroPac);
                currentFrame.dispose();

                JFrame frame = new JFrame("MEDICARE");
                JPanel panel = new JPanel();
                panel.setLayout(new FlowLayout());
                panel.add(new JLabel("Bienvenido a Medicare"));
                frame.setContentPane(new HistorialAdulto().panelHitorialAdulto);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
        IngresoMenorEdad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(panelRegistroPac);
                currentFrame.dispose();

                JFrame frame = new JFrame("MEDICARE");
                JPanel panel = new JPanel();
                panel.setLayout(new FlowLayout());
                panel.add(new JLabel("Bienvenido a Medicare"));
                frame.setContentPane(new HistorialMenor().panelHisMenor);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
        volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(panelRegistroPac);
                currentFrame.dispose();

                JFrame frame = new JFrame("MEDICARE");
                JPanel panel = new JPanel();
                panel.setLayout(new FlowLayout());
                panel.add(new JLabel("Bienvenido a Medicare"));
                frame.setContentPane(new login().panel1);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
