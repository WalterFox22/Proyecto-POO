import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loginAdmi {
    public JPanel panel11;
    public JLabel titulo;
    public JLabel ingresoID;
    public JTextField cedulaAdmi;
    public JLabel ingresoContra;
    public JPasswordField contraAdmi;
    public JButton ingresar;

    public loginAdmi() {
        ingresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
