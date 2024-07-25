import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class loginAdmi {
    public JPanel panel11;
    public JLabel titulo;
    public JLabel ingresoID;
    public JTextField cedulaAdmi;
    public JLabel ingresoContra;
    public JPasswordField contraAdmi;
    public JButton ingresar;

    public loginAdmi() {
        ingresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ex) {
                String cedula = cedulaAdmi.getText();
                char[] cont = contraAdmi.getPassword();
                String contra = new String(cont);

                String connectionString = "mongodb+srv://Walter:Walyfox22@cluster0.p2y1kwu.mongodb.net/<dbname>?retryWrites=true&w=majority";
                MongoClientSettings settings = MongoClientSettings.builder()
                        .applyConnectionString(new ConnectionString(connectionString))
                        .build();
                try (MongoClient mongoClient = MongoClients.create(settings)) {
                    MongoDatabase database = mongoClient.getDatabase("POO");
                    MongoCollection<Document> collection = database.getCollection("usuarios");

                    Document query = new Document("rol", "Administrador")
                            .append("cedula", cedula)
                            .append("contraseña", contra);

                    System.out.println("Query: " + query.toJson());
                    Document usuarioEncontrado = collection.find(query).first();

                    if (usuarioEncontrado != null) {
                        System.out.println("Acceso permitido para el administrador: " + usuarioEncontrado.toJson());

                    } else {
                        System.out.println("Cédula y/o contraseña incorrectas. Acceso denegado.");

                    }

                } catch (Exception e) {
                    System.err.println("Error al conectar a MongoDB Atlas: " + e.getMessage());
                }

                // Limpiar la contraseña para seguridad
                for (int i = 0; i < cont.length; i++) {
                    cont[i] = '\0';
                }

            }
        });
    }
}
