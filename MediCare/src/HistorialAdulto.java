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

public class HistorialAdulto {
    public JPanel panelHitorialAdulto;
    public JLabel titHisto;
    public JLabel titFicherp;
    public JTextField fichero;
    public JLabel ttiNomPac;
    public JTextField nomPac;
    public JLabel titDatos;
    public JTextField FechaNaci;
    public JTextField Edad;
    public JTextField ocupacion;
    public JTextField celular;
    public JTextField direccion;
    public JTextField nombreEmergacia;
    public JTextField parentesco;
    public JTextField celularEmergencia;
    public JTextField fechaIngreso;
    public JLabel titFechNaci;
    public JLabel titEdad;
    public JLabel titOcupacion;
    public JLabel titCelu;
    public JLabel titDireccion;
    public JLabel titSector;
    public JTextField sector;
    public JLabel titHijos;
    public JSpinner hijos;
    public JLabel titEmergencia;
    public JLabel titNomEmer;
    public JLabel titParentesco;
    public JLabel titNumEmer;
    public JLabel titIngreso;
    public JButton confirmar;
    public JButton cancelar;
    public JTextField cedula;
    public JLabel titcedula;
    private JLabel imagen1;

    public HistorialAdulto() {
        confirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String Fichero = fichero.getText();
                String Cedula = cedula.getText();
                String NombreAdulto = nomPac.getText();
                String Nacimiento = FechaNaci.getText();
                String EdadPaciente = Edad.getText();
                String Ocupacion = ocupacion.getText();
                String Celular = celular.getText();
                String Direccion = direccion.getText();
                String Sector = sector.getText();
                int Hijos = (int) hijos.getValue();
                String NombreParaEmergencia = nombreEmergacia.getText();
                String Parentesco = parentesco.getText();
                String CelularEmergencia = celularEmergencia.getText();
                String Ingreso = fechaIngreso.getText();

                String connectionString = "mongodb+srv://Walter:Walyfox22@cluster0.p2y1kwu.mongodb.net/POO?retryWrites=true&w=majority";
                MongoClientSettings settings = MongoClientSettings.builder()
                        .applyConnectionString(new ConnectionString(connectionString))
                        .build();
                try (MongoClient mongoClient = MongoClients.create(settings)) {
                    MongoDatabase database = mongoClient.getDatabase("POO");
                    MongoCollection<Document> collection = database.getCollection("pacientes");

                    Document paciente = new Document("fichero", Fichero)
                            .append("cedula", Cedula)
                            .append("nombres", NombreAdulto)
                            .append("fecha_nacimiento", Nacimiento)
                            .append("edad", EdadPaciente)
                            .append("ocupacion", Ocupacion)
                            .append("celular", Celular)
                            .append("direccion", Direccion)
                            .append("sector", Sector)
                            .append("hijos", Hijos)
                            .append("persona_en_caso_emergencia", NombreParaEmergencia)
                            .append("parentesco", Parentesco)
                            .append("celular_emergencia", CelularEmergencia)
                            .append("fecha_ingreso", Ingreso);

                    collection.insertOne(paciente);
                    JOptionPane.showMessageDialog(null, "Paciente ingresado con exito");

                    fichero.setText("");
                    cedula.setText("");
                    nomPac.setText("");
                    FechaNaci.setText("");
                    Edad.setText("");
                    ocupacion.setText("");
                    celular.setText("");
                    direccion.setText("");
                    sector.setText("");
                    hijos.setValue(0);
                    nombreEmergacia.setText("");
                    parentesco.setText("");
                    celularEmergencia.setText("");
                    fechaIngreso.setText("");

                } catch (Exception ex) {
                    System.err.println("Error al conectar a MongoDB Atlas: " + ex.getMessage());
                    JOptionPane.showMessageDialog(null, "Fallo al ingreso del paciente");

                }
            }
        });
        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(panelHitorialAdulto);
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
