import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loginPersonalMedico {
    public JPanel panel12;
    public JLabel tituloPersonal;
    public JLabel ingresoCorreo;
    public JTextField correo;
    public JLabel tituloContraMedico;
    public JPasswordField contraMedico;
    public JButton IngresarMedico;
    public JButton volver;

    public loginPersonalMedico() {
        IngresarMedico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String correo = ingresoCorreo.getText();
                String contra = contraMedico.toString();


            }
        });
    }
}
