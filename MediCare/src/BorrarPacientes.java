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

public class BorrarPacientes {
    public JPanel panelBorrarPacientes;
    public JLabel tit;
    public JLabel tit2;
    public JLabel titCedula;
    public JTextField cedula;
    public JButton Eliminar;
    public JButton cancelar;
    private JLabel imagen1;

    public BorrarPacientes() {
        Eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String CedulaEliminar =cedula.getText();

                String connectionString = "mongodb+srv://Walter:Walyfox22@cluster0.p2y1kwu.mongodb.net/POO?retryWrites=true&w=majority";
                MongoClientSettings settings = MongoClientSettings.builder()
                        .applyConnectionString(new ConnectionString(connectionString))
                        .build();
                try (MongoClient mongoClient = MongoClients.create(settings)) {
                    MongoDatabase database = mongoClient.getDatabase("POO");
                    MongoCollection<Document> collection = database.getCollection("pacientes");

                    Document query = new Document("correo", CedulaEliminar);
                    long deletedCount = collection.deleteMany(query).getDeletedCount();

                    if (deletedCount > 0) {
                        JOptionPane.showMessageDialog(null, "Registro eliminado correctamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró ningún registro con ese correo.");
                    }
                } catch (Exception ex) {
                    System.err.println("Error al conectar a MongoDB Atlas: " + ex.getMessage());
                    JOptionPane.showMessageDialog(null, "No se pudieron eliminar los registros.");
                }
            }
        });
        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(panelBorrarPacientes);
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
