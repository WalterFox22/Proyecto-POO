import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

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

}
