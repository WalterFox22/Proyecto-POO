import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class login {
    public JPanel panel1;
    public JLabel titulo;
    public JLabel tituloSeleccion;
    public JComboBox roles;
    private JButton aceptar;


    public login() {
        aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Objects.equals(roles.getSelectedItem(), "Administrador")) {
                    JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(panel1);
                    currentFrame.dispose();
                    JFrame frame = new JFrame("Mi aplicación");
                    frame.setContentPane(new loginAdmi().panel11);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(2600, 200);
                    frame.pack();
                    frame.setVisible(true);
                }
                if (roles.getSelectedItem().equals("Personal médico")) {

                }
            }
        });
    }
}
