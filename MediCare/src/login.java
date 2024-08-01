import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class login {
    public JPanel panel1;
    public JLabel titulo;
    public JLabel tituloSeleccion;
    public JComboBox roles;
    public JButton aceptar;
    public JButton titCerrar;


    public login() {
        aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Objects.equals(roles.getSelectedItem(), "Administrador")) {
                    JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(panel1);
                    currentFrame.dispose();
                    JFrame frame = new JFrame("MEDICARE");
                    JPanel panel = new JPanel();
                    panel.setLayout(new FlowLayout());
                    panel.add(new JLabel("Bienvenido a Medicare"));
                    frame.setContentPane(new loginAdmi().panel11);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                }
                if (roles.getSelectedItem().equals("Personal médico")) {
                    JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(panel1);
                    currentFrame.dispose();

                    JFrame frame = new JFrame("MEDICARE");
                    JPanel panel = new JPanel();
                    panel.setLayout(new FlowLayout());
                    panel.add(new JLabel("Bienvenido a Medicare"));
                    frame.setContentPane(new loginPersonalMedico().panel12);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                }
            }
        });
        titCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(panel1);
                currentFrame.dispose();
            }
        });
    }
}
