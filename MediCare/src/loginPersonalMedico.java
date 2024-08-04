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
                String contra = new String(contraMedico.getPassword());

                String connectionString = "mongodb+srv://Walter:Walyfox22@cluster0.p2y1kwu.mongodb.net/POO?retryWrites=true&w=majority";
                MongoClientSettings settings = MongoClientSettings.builder()
                        .applyConnectionString(new ConnectionString(connectionString))
                        .build();
                try (MongoClient mongoClient = MongoClients.create(settings)) {
                    MongoDatabase database = mongoClient.getDatabase("POO");
                    MongoCollection<Document> collection = database.getCollection("usuarios");


                    // Búsqueda de usuario con valores proporcionados en la interfaz
                    Document query = new Document("rol", "Personal médico")
                            .append("correo", correo)
                            .append("contraseña", contra);

                    List<Document> usuariosEncontrados = collection.find(query).into(new ArrayList<>());

                    if (usuariosEncontrados != null) {
                        JOptionPane.showMessageDialog(null, "Acceso permitido para Personal Medico");
                        JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(panel12);
                        currentFrame.dispose();

                        JFrame frame = new JFrame("MEDICARE");
                        HistorialClinico historialClinico = new HistorialClinico();
                        JScrollPane scrollPane = new JScrollPane(historialClinico.panelHistorialClinico);
                        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

                        frame.setContentPane(scrollPane);
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setSize(800, 600);
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);

                    } else {
                        JOptionPane.showMessageDialog(null, "Cédula y/o contraseña incorrectas. Acceso denegado. Error de acceso");
                    }

                } catch (Exception ex) {
                    System.err.println("Error al conectar a MongoDB Atlas: " + ex.getMessage());
                }
            }



        });
        volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(panel12);
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
