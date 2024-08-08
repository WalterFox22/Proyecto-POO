import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ReportesEstadisticos {
    public JPanel panelEstadisticos;
    public JTextArea reportes;
    public JButton volver;
    public JLabel tit;
    public JLabel tit2;
    private JLabel imagen1;

    public ReportesEstadisticos() {
        reportes.setEditable(false);
        reportes.setLineWrap(true);
        reportes.setWrapStyleWord(true);

        String connectionString = "mongodb+srv://Walter:Walyfox22@cluster0.p2y1kwu.mongodb.net/POO?retryWrites=true&w=majority";
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .build();

        try (MongoClient mongoClient = MongoClients.create(settings)) {
            MongoDatabase database = mongoClient.getDatabase("POO");
            MongoCollection<Document> collection = database.getCollection("pacientes");

            // Contar el número total de pacientes
            long totalPacientes = collection.countDocuments();

            // Contar el número total de tratamientos ingresados
            long totalTratamientos = collection.countDocuments(new Document("diagnostico_paciente", new Document("$exists", true)));

            // Obtener información sobre los pacientes
            List<Document> pacientes = collection.find().into(new ArrayList<>());

            StringBuilder sb = new StringBuilder();
            sb.append("Total de Pacientes: ").append(totalPacientes).append("\n");
            sb.append("Total de Tratamientos Registrados: ").append(totalTratamientos).append("\n");
            sb.append("Detalles de los Pacientes:\n");

            for (Document paciente : pacientes) {
                sb.append("Cédula: ").append(paciente.getString("cedula")).append("\n");
                sb.append("Nombres: ").append(paciente.getString("nombres")).append("\n");
                sb.append("Diagnóstico: ").append(paciente.getString("diagnostico_paciente")).append("\n");
                sb.append("Tratamiento: ").append(paciente.getString("tratamiento")).append("\n");
                sb.append("---------------------\n");
            }

            reportes.setText(sb.toString());
        } catch (Exception ex) {
            System.err.println("Error al conectar a MongoDB Atlas: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "No se pudieron generar los reportes estadísticos.");
        }

        volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(panelEstadisticos);
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
