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

public class HistorialMenor {
    public JPanel panelHisMenor;
    public JLabel tit;
    public JLabel titFichero;
    public JTextField fichero;
    public JTextField nombres;
    public JLabel titDatos;
    public JLabel titNom;
    public JCheckBox autorizacion;
    public JButton titConfirmar;
    public JButton titCancelar;
    public JTextField fechaNaci;
    public JTextField edadMenor;
    public JTextField direccion;
    public JTextField sector;
    public JTextField fechIngreso;
    public JTextField NomTutor;
    public JTextField profesion;
    public JTextField celular;
    public JTextField nomTrabajo;
    public JLabel titFechNaci;
    public JLabel titEdad;
    public JLabel titTutorolLegal;
    public JLabel titAuto;
    public JTextField cedulaMenor;
    public JLabel titcedula;
    public JLabel titDirecc;
    public JLabel titSector;
    public JLabel titFechaIngreso;
    public JLabel titNomTutor;
    public JLabel titProfesion;
    public JLabel titCelular;
    public JLabel titTrabajo;
    public JLabel titHijos;
    public JSpinner hijos;

    public HistorialMenor() {
        titConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String Fichero = fichero.getText();
                String Nombres = nombres.getText();
                String FechaNaci = fechaNaci.getText();
                String EdadMenor = edadMenor.getText();
                String Direccion = direccion.getText();
                String Sector = sector.getText();
                String FechIngreso = fechIngreso.getText();
                String NOmTutor = NomTutor.getText();
                String Profesion = profesion.getText();
                String Celular = celular.getText();
                String NomTrabajo = nomTrabajo.getText();
                String CedulaMenor = cedulaMenor.getText();
                boolean Autorizacion = autorizacion.isSelected();
                int Hijos = (int) hijos.getValue();

                String connectionString = "mongodb+srv://Walter:Walyfox22@cluster0.p2y1kwu.mongodb.net/POO?retryWrites=true&w=majority";
                MongoClientSettings settings = MongoClientSettings.builder()
                        .applyConnectionString(new ConnectionString(connectionString))
                        .build();
                try (MongoClient mongoClient = MongoClients.create(settings)) {
                    MongoDatabase database = mongoClient.getDatabase("POO");
                    MongoCollection<Document> collection = database.getCollection("pacientes");

                    Document paciente = new Document("fichero", Fichero)
                            .append("cedula", CedulaMenor)
                            .append("nombres", Nombres)
                            .append("fecha_nacimiento", FechaNaci)
                            .append("edad", EdadMenor)
                            .append("direccion", Direccion)
                            .append("sector", Sector)
                            .append("fecha_ingreso", FechIngreso)
                            .append("tutor_nombre", NOmTutor)
                            .append("tutor_profesion", Profesion)
                            .append("tutor_celular", Celular)
                            .append("tutor_nombre_trabajo", NomTrabajo)
                            .append("hijos", Hijos)
                            .append("autorizacion", Autorizacion);

                    collection.insertOne(paciente);
                    JOptionPane.showMessageDialog(null, "Paciente ingresado con exito");

                    fichero.setText("");
                    nombres.setText("");
                    fechaNaci.setText("");
                    edadMenor.setText("");
                    direccion.setText("");
                    sector.setText("");
                    fechIngreso.setText("");
                    NomTutor.setText("");
                    profesion.setText("");
                    celular.setText("");
                    nomTrabajo.setText("");
                    cedulaMenor.setText("");
                    autorizacion.setSelected(false);
                    hijos.setValue(0);


                } catch (Exception ex) {
                    System.err.println("Error al conectar a MongoDB Atlas: " + ex.getMessage());
                    JOptionPane.showMessageDialog(null, "Fallo al ingreso del paciente");

                }
            }
        });
        titCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(panelHisMenor);
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
