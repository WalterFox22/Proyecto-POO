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

                String connectionString = "mongodb+srv://Walter:<password>@cluster0.p2y1kwu.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
                ServerApi serverApi = ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build();
                MongoClientSettings settings = MongoClientSettings.builder()
                        .applyConnectionString(new ConnectionString(connectionString))
                        .serverApi(serverApi)
                        .build();
                try (MongoClient mongoClient = MongoClients.create(settings)) {
                    try {
                        MongoDatabase database = mongoClient.getDatabase("Administrador");
                        database.runCommand(new Document("ping", 1));
                        System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
                    } catch (MongoException e) {
                        e.printStackTrace();
                    }
                }
                catch (MongoException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
