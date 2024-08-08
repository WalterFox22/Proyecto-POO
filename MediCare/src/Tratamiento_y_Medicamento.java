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

public class Tratamiento_y_Medicamento {
    public JPanel panelTratamientoMedicamento;
    public JLabel tit;
    public JLabel tit2;
    public JLabel titDiagnostico;
    public JTextField diagnostico;
    public JLabel titTratamiento;
    public JTextField tratamiento;
    public JTextField FechaTratamiento;
    public JLabel titFechTrata;
    public JLabel titDuracion;
    public JTextField duracion;
    public JLabel tit3;
    public JTextField medicamento;
    public JTextField Dosis;
    public JTextField frecuenciaDosis;
    public JTextField administracion;
    public JLabel titMedicamento;
    public JLabel titDosis;
    public JLabel titAdministracion;
    public JButton Confirmar;
    public JButton Volver;
    private JLabel imagen1;

    private String cedulaPaciente;


    public Tratamiento_y_Medicamento(String cedula) {
        this.cedulaPaciente = cedula;

        Confirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String Diagnostico = diagnostico.getText();
                String Tratamiento = tratamiento.getText();
                String Fecha_tratamiento = FechaTratamiento.getText();
                String Duracion = duracion.getText();
                String Medicamento = medicamento.getText();
                String DOSIS = Dosis.getText();
                String Administracion = administracion.getText();

                String connectionString = "mongodb+srv://Walter:Walyfox22@cluster0.p2y1kwu.mongodb.net/POO?retryWrites=true&w=majority";
                MongoClientSettings settings = MongoClientSettings.builder()
                        .applyConnectionString(new ConnectionString(connectionString))
                        .build();
                try (MongoClient mongoClient = MongoClients.create(settings)) {
                    MongoDatabase database = mongoClient.getDatabase("POO");
                    MongoCollection<Document> collection = database.getCollection("pacientes");

                    Document updatedData = new Document("diagnostico_paciente", Diagnostico)
                            .append("tratamiento", Tratamiento)
                            .append("fecha_inicio_tratamiento", Fecha_tratamiento)
                            .append("duracion_tratamiento", Duracion)
                            .append("medicamento_recetado", Medicamento)
                            .append("dosis_medicamento", DOSIS)
                            .append("tipo_de_administracion", Administracion);

                    Document query = new Document("cedula", cedulaPaciente);
                    collection.updateOne(query, new Document("$set", updatedData));
                    JOptionPane.showMessageDialog(null, "Tratamiento guardado correctamente.");

                    JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(panelTratamientoMedicamento);
                    currentFrame.dispose();

                    JFrame frame = new JFrame("MEDICARE");
                    Resultados_examenes2 resultadosExamenes2 = new Resultados_examenes2(cedulaPaciente);
                    JScrollPane scrollPane = new JScrollPane(resultadosExamenes2.panelResultadosExamenes2);
                    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

                    frame.setContentPane(scrollPane);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(800, 600);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);

                } catch (Exception ex) {
                    System.err.println("Error al conectar a MongoDB Atlas: " + ex.getMessage());
                    JOptionPane.showMessageDialog(null, " No se ingresar el tratamiento correctamente.");
                }
            }
        });
    }
}
