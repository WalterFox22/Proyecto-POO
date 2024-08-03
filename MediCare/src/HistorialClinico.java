import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HistorialClinico {
    public JPanel panelHistorialClinico;
    public JTextField cedulaBusqueda;
    public JLabel titulo;
    public JTextField motivo;
    public JLabel titBusquedaPac;
    public JLabel mostrarpacientes;
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

    public HistorialClinico() {
        titBusqueda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Cedula = cedulaBusqueda.getText();
                String connectionString = "mongodb+srv://Walter:Walyfox22@cluster0.p2y1kwu.mongodb.net/POO?retryWrites=true&w=majority";
                MongoClientSettings settings = MongoClientSettings.builder()
                        .applyConnectionString(new ConnectionString(connectionString))
                        .build();




            }
        });
    }
}
