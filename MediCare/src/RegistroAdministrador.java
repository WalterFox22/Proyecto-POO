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

public class RegistroAdministrador {
    public JPanel panelRegistroAdmi;
    public JLabel tit;
    public JLabel tit2;
    public JTextField cedula;
    private JLabel imagen1;
    private JLabel imagen2;
    public JLabel titCedula;
    public JTextField contraseña;
    public JTextField nombres;
    public JButton confirmar;
    public JButton Cancelar;
    public JLabel titContra;
    public JLabel titnombres;

    public RegistroAdministrador() {
        confirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Cedula = cedula.getText();
                String Contraseña = contraseña.getText();
                String Nombres = nombres.getText();

                String connectionString = "mongodb+srv://Walter:Walyfox22@cluster0.p2y1kwu.mongodb.net/POO?retryWrites=true&w=majority";
                MongoClientSettings settings = MongoClientSettings.builder()
                        .applyConnectionString(new ConnectionString(connectionString))
                        .build();

                try (MongoClient mongoClient = MongoClients.create(settings)) {
                    MongoDatabase database = mongoClient.getDatabase("POO");
                    MongoCollection<Document> collection = database.getCollection("usuarios");

                    Document personalMedico = new Document("rol", "Administrador")
                            .append("cedula", Cedula)
                            .append("contraseña", Contraseña)
                            .append("nombres", Nombres);

                    collection.insertOne(personalMedico);
                    JOptionPane.showMessageDialog(null, "Personal Administrativo ingresado correctamente");

                    cedula.setText("");
                    contraseña.setText("");
                    nombres.setText("");

                } catch (Exception ex) {
                    System.err.println("Error del ingreso de Pacientes en la base de datos: " + ex.getMessage());
                    JOptionPane.showMessageDialog(null, "No se a logrado registrar al personal Administrador");
                }

            }
        });
        Cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(panelRegistroAdmi);
                currentFrame.dispose();

                JFrame frame = new JFrame("MEDICARE");
                RegistroPaciente registroPaciente = new RegistroPaciente();
                JScrollPane scrollPane = new JScrollPane(registroPaciente.panelRegistroPac);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

                frame.setContentPane(scrollPane);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800, 600);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
