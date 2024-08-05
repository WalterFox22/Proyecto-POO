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

public class BorrarPersonalMedico {
    public JPanel panelBorrarMedico;
    public JLabel tit;
    public JLabel tit2;
    public JLabel titCorreo;
    public JTextField correo;
    public JButton Eliminar;
    public JButton Cancelar;

    public BorrarPersonalMedico() {
        Eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String CorreoEliminar = correo.getText();

                String connectionString = "mongodb+srv://Walter:Walyfox22@cluster0.p2y1kwu.mongodb.net/POO?retryWrites=true&w=majority";
                MongoClientSettings settings = MongoClientSettings.builder()
                        .applyConnectionString(new ConnectionString(connectionString))
                        .build();
                try (MongoClient mongoClient = MongoClients.create(settings)) {
                    MongoDatabase database = mongoClient.getDatabase("POO");
                    MongoCollection<Document> collection = database.getCollection("usuarios");

                    Document query = new Document("correo", CorreoEliminar);
                    long deletedCount = collection.deleteMany(query).getDeletedCount();

                    if (deletedCount > 0) {
                        JOptionPane.showMessageDialog(null, "Registro eliminado correctamente.");
                        correo.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró ningún registro con ese correo.");
                    }
                } catch (Exception ex) {
                    System.err.println("Error al conectar a MongoDB Atlas: " + ex.getMessage());
                    JOptionPane.showMessageDialog(null, "No se pudieron eliminar los registros.");
                }
            }

        });
        Cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(panelBorrarMedico);
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
