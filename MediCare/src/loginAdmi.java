import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class loginAdmi {
    public JPanel panel11;
    public JLabel titulo;
    public JLabel ingresoID;
    public JTextField cedulaAdmi;
    public JLabel ingresoContra;
    public JPasswordField contraAdmi;
    public JButton ingresar;
    private JButton volver;

    public loginAdmi() {
        ingresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ex) {
                String cedula = cedulaAdmi.getText();
                String contra = new String(contraAdmi.getPassword());

                String connectionString = "mongodb+srv://Walter:Walyfox22@cluster0.p2y1kwu.mongodb.net/POO?retryWrites=true&w=majority";
                MongoClientSettings settings = MongoClientSettings.builder()
                        .applyConnectionString(new ConnectionString(connectionString))
                        .build();
                try (MongoClient mongoClient = MongoClients.create(settings)) {
                    MongoDatabase database = mongoClient.getDatabase("POO");
                    MongoCollection<Document> collection = database.getCollection("usuarios");

                    /*
                    // Inserción de documentos con valores especificados
                    Document admin = new Document("rol", "Administrador")
                            .append("cedula", "1729195444")
                            .append("contraseña", "123456");
                    Document personalMedico = new Document("rol", "Personal médico")
                            .append("correo", "walter005@gmail.com")
                            .append("contraseña", "09876");

                    collection.insertOne(admin);
                    collection.insertOne(personalMedico);


                     */

                    // Búsqueda de usuario con valores proporcionados en la interfaz
                    Document query = new Document("rol", "Administrador")
                            .append("cedula", cedula)
                            .append("contraseña", contra);

                    List<Document> usuarioEncontrado = collection.find(query).into(new ArrayList<>());

                    if (usuarioEncontrado != null) {
                        JOptionPane.showMessageDialog(null, "Acceso permitido para el Administrador");
                        JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(panel11);
                        currentFrame.dispose();
                        JFrame frame = new JFrame("MEDICARE");
                        frame.setContentPane(new RegistroPaciente().panelRegistroPac);
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setSize(2600, 2000);
                        frame.pack();
                        frame.setVisible(true);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Cédula y/o contraseña incorrectas. Acceso denegado. Error de acceso");
                    }

                } catch (Exception e) {
                    System.err.println("Error al conectar a MongoDB Atlas: " + e.getMessage());
                }
            }
        });
    }


}
