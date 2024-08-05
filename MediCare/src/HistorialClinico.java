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

public class HistorialClinico {
    public JPanel panelHistorialClinico;
    public JTextField cedulaBusqueda;
    public JLabel titulo;
    public JTextField motivo;
    public JLabel titBusquedaPac;
    public JTextArea mostrarpacientes;
    public JLabel titMotivo;
    public JButton Aceptar;
    public JButton cancelar;
    public JTextField problema;
    public JLabel titProblema;
    public JTextField sintomas;
    public JLabel titSintomas;
    public JTextField alergias;
    public JLabel titAlergias;
    public JLabel titFamAlergia;
    public JTextField AlergiaFam;
    public JLabel titFrecuencia;
    public JTextField frecuencia;
    public JLabel titPastillas;
    public JCheckBox titSi;
    public JCheckBox titNo;
    public JLabel titSuguro;
    public JButton titBusqueda;
    public JCheckBox titsi2;
    public JCheckBox titNo2;
    public JButton buscarExamenes;

    public HistorialClinico() {

        mostrarpacientes.setLineWrap(true);
        mostrarpacientes.setWrapStyleWord(true);
        mostrarpacientes.setEditable(false);

        titBusqueda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Cedula = cedulaBusqueda.getText();
                String connectionString = "mongodb+srv://Walter:Walyfox22@cluster0.p2y1kwu.mongodb.net/POO?retryWrites=true&w=majority";
                MongoClientSettings settings = MongoClientSettings.builder()
                        .applyConnectionString(new ConnectionString(connectionString))
                        .build();
                try (MongoClient mongoClient = MongoClients.create(settings)) {
                    MongoDatabase database = mongoClient.getDatabase("POO");
                    MongoCollection<Document> collection = database.getCollection("pacientes");
                    Document query = new Document("cedula", Cedula);

                    List<Document> usuarioEncontrado = collection.find(query).into(new ArrayList<>());

                    if (!usuarioEncontrado.isEmpty()) {
                        StringBuilder sb = new StringBuilder();
                        for (Document doc : usuarioEncontrado) {
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
                                sb.append('\n').append ("Profesión del Tutor: ").append(doc.getString("tutor_profesion"));
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
                            sb.append('\n');
                        }
                        mostrarpacientes.setText(sb.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "Paciente no encontrado");
                    }

                } catch (Exception ex) {
                    System.err.println("Error al conectar a MongoDB Atlas: " + ex.getMessage());
                }

            }
        });

        Aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String Motivo = motivo.getText();
                String Problema = problema.getText();
                String Sintomas = sintomas.getText();
                String Alergias = alergias.getText();
                String AlergiasFamilia = AlergiaFam.getText();
                String Frecuencia = frecuencia.getText();
                String Pastilla = titSi.isSelected() ? "Sí" : (titNo.isSelected() ? "No" : "No especificado");
                String SeguroSocial = titsi2.isSelected() ? "Sí" : (titNo2.isSelected() ? "No" : "No especificado");

                String Cedula = cedulaBusqueda.getText();

                String connectionString = "mongodb+srv://Walter:Walyfox22@cluster0.p2y1kwu.mongodb.net/POO?retryWrites=true&w=majority";
                MongoClientSettings settings = MongoClientSettings.builder()
                        .applyConnectionString(new ConnectionString(connectionString))
                        .build();
                try (MongoClient mongoClient = MongoClients.create(settings)) {
                    MongoDatabase database = mongoClient.getDatabase("POO");
                    MongoCollection<Document> collection = database.getCollection("pacientes");

                    Document updatedData = new Document("motivo_de_consulta", Motivo)
                            .append("tiempo_del_problema", Problema)
                            .append("sintomas", Sintomas)
                            .append("alergias", Alergias)
                            .append("alergias_de_familiares", AlergiasFamilia)
                            .append("frecuencia_del_sintoma", Frecuencia)
                            .append("toma_pastilla", Pastilla)
                            .append("tiene_seguro_social", SeguroSocial);

                    Document query = new Document("cedula", Cedula);
                    collection.updateOne(query, new Document("$set", updatedData));
                    JOptionPane.showMessageDialog(null, "Datos actualizados correctamente.");


                    JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(panelHistorialClinico);
                    currentFrame.dispose();

                    JFrame frame = new JFrame("MEDICARE");
                    Tratamiento_y_Medicamento tratamientoYMedicamento = new Tratamiento_y_Medicamento(Cedula);
                    JScrollPane scrollPane = new JScrollPane(tratamientoYMedicamento.panelTratamientoMedicamento);
                    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

                    frame.setContentPane(scrollPane);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(800, 600);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);

                } catch (Exception ex) {
                    System.err.println("Error al conectar a MongoDB Atlas: " + ex.getMessage());
                    JOptionPane.showMessageDialog(null, " No se puedo actualizar los datos correctamente.");
                }
            }
        });
        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(panelHistorialClinico);
                currentFrame.dispose();

                JFrame frame = new JFrame("MEDICARE");
                JPanel panel = new JPanel();
                panel.setLayout(new FlowLayout());
                panel.add(new JLabel("Bienvenido a Medicare"));
                frame.setContentPane(new loginPersonalMedico().panel12);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
        buscarExamenes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Cedula = cedulaBusqueda.getText();
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(panelHistorialClinico);
                currentFrame.dispose();

                JFrame frame = new JFrame("MEDICARE");
                Resultados_examenes2 resultadosExamenes2 = new Resultados_examenes2(Cedula);
                JScrollPane scrollPane = new JScrollPane(resultadosExamenes2.panelResultadosExamenes2);
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
