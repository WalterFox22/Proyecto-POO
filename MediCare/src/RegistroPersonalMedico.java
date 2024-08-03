import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class RegistroPersonalMedico {
    public JPanel panelRegistroMedico;
    public JLabel tit1;
    public JLabel tit2;
    public JTextField correo;
    public JPasswordField contraseña;
    public JLabel titIngresoCorreo;
    public JLabel titIngresoContra;
    public JButton Ingresar;
    public JButton cancelar;

    public RegistroPersonalMedico() {
        Ingresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Correo = correo.getText();
                String Contra = new String(contraseña.getPassword());

                String connectionString = "mongodb+srv://Walter:Walyfox22@cluster0.p2y1kwu.mongodb.net/POO?retryWrites=true&w=majority";
                MongoClientSettings settings = MongoClientSettings.builder()
                        .applyConnectionString(new ConnectionString(connectionString))
                        .build();

                try (MongoClient mongoClient = MongoClients.create(settings)) {
                    MongoDatabase database = mongoClient.getDatabase("POO");
                    MongoCollection<Document> collection = database.getCollection("usuarios");

                    Document personalMedico = new Document("rol", "Personal médico")
                            .append("correo", Correo)
                            .append("contraseña", Contra);

                    collection.insertOne(personalMedico);
                    JOptionPane.showMessageDialog(null, "Personal médico ingresado correctamente");

                    correo.setText("");
                    contraseña.setText("");

                } catch (Exception ex) {
                    System.err.println("Error del ingreso de Pacientes en la base de datos: " + ex.getMessage());
                    JOptionPane.showMessageDialog(null, "No se a logrado registrar al personal médico");
                }



            }
        });
        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(panelRegistroMedico);
                currentFrame.dispose();

                JFrame frame = new JFrame("MEDICARE");
                JPanel panel = new JPanel();
                panel.setLayout(new FlowLayout());
                panel.add(new JLabel("Bienvenido a Medicare"));
                frame.setContentPane(new RegistroPaciente().panelRegistroPac);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
