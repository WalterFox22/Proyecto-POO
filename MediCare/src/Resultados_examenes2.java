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

public class Resultados_examenes2 {
    public JPanel panelResultadosExamenes2;
    public JLabel tit;
    public JLabel tit2;
    public JTextArea mostrarExamenes;
    public JButton imprimir;
    public JButton volver;

    public Resultados_examenes2(String cedulaPaciente) {
        mostrarExamenes.setEditable(false);
        mostrarExamenes.setLineWrap(true);
        mostrarExamenes.setWrapStyleWord(true);

        String connectionString = "mongodb+srv://Walter:Walyfox22@cluster0.p2y1kwu.mongodb.net/POO?retryWrites=true&w=majority";
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .build();
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            MongoDatabase database = mongoClient.getDatabase("POO");
            MongoCollection<Document> collection = database.getCollection("pacientes");

            Document query = new Document("cedula", cedulaPaciente);
            List<Document> examenesEncontrados = collection.find(query).into(new ArrayList<>());

            StringBuilder sb = new StringBuilder();
            for (Document doc : examenesEncontrados) {
                sb.append('\n').append("Fichero: ").append(doc.getString("fichero"));
                sb.append('\n').append("Cédula: ").append(doc.getString("cedula"));
                sb.append('\n').append("Nombres: ").append(doc.getString("nombres"));
                sb.append('\n').append("Fecha de Nacimiento: ").append(doc.getString("fecha_nacimiento"));
                sb.append('\n').append("Edad: ").append(doc.get("edad"));
                sb.append('\n').append("Dirección: ").append(doc.getString("direccion"));
                sb.append('\n').append("Sector: ").append(doc.getString("sector"));
                sb.append('\n').append("Fecha de Ingreso: ").append(doc.getString("fecha_ingreso"));
                sb.append('\n').append("Hijos: ").append(doc.get("hijos"));

                if (doc.containsKey("tutor_nombre")) {
                    // Es un paciente menor
                    sb.append('\n').append("Nombre del Tutor: ").append(doc.getString("tutor_nombre"));
                    sb.append('\n').append("Profesión del Tutor: ").append(doc.getString("tutor_profesion"));
                    sb.append('\n').append("Celular del Tutor: ").append(doc.getString("tutor_celular"));
                    sb.append('\n').append("Nombre del Trabajo del Tutor: ").append(doc.getString("tutor_nombre_trabajo"));
                    sb.append('\n').append("Autorización: ").append(doc.getBoolean("autorizacion"));
                } else {
                    // Es un paciente mayor
                    sb.append('\n').append("Ocupación: ").append(doc.getString("ocupacion"));
                    sb.append('\n').append("Celular: ").append(doc.getString("celular"));
                    sb.append('\n').append("Persona en caso de emergencia: ").append(doc.getString("persona_en_caso_emergencia"));
                    sb.append('\n').append("Parentesco: ").append(doc.getString("parentesco"));
                    sb.append('\n').append("Celular de emergencia: ").append(doc.getString("celular_emergencia"));
                }

                // Añade información del tratamiento
                sb.append("\nTratamiento:\n");
                sb.append("Diagnóstico del paciente: ").append(doc.getString("diagnostico_paciente")).append("\n");
                sb.append("Tratamiento: ").append(doc.getString("tratamiento")).append("\n");
                sb.append("Fecha de Inicio: ").append(doc.getString("fecha_inicio_tratamiento")).append("\n");
                sb.append("Duración: ").append(doc.getString("duracion_tratamiento")).append("\n");
                sb.append("Medicamento Recetado: ").append(doc.getString("medicamento_recetado")).append("\n");
                sb.append("Dosis: ").append(doc.getString("dosis_medicamento")).append("\n");
                sb.append("Administración: ").append(doc.getString("tipo_de_administracion")).append("\n");

                // Si se encontraron exámenes, los muestra; de lo contrario, muestra un mensaje
                if (sb.length() > 0) {
                    mostrarExamenes.setText(sb.toString());
                } else {
                    mostrarExamenes.setText("No se encontraron resultados de exámenes.");
                }
            }
        } catch (Exception ex) {
            System.err.println("Error al conectar a MongoDB Atlas: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "No se pudieron cargar los resultados de los exámenes.");
        }
        volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(panelResultadosExamenes2);
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
            }
        });
    }
}

