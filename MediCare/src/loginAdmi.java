import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

import static com.mongodb.client.model.Filters.eq;

public class loginAdmi {
    public JPanel panel11;
    public JLabel titulo;
    public JLabel ingresoID;
    public JTextField cedulaAdmi;
    public JLabel ingresoContra;
    public JPasswordField contraAdmi;
    public JButton ingresar;
    private JButton volver;

    public loginAdmi() {
        ingresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ex) {
                String cedula = cedulaAdmi.getText();
                String contra = contraAdmi.toString();

                String connectionString = "mongodb+srv://Walter:Walyfox22@cluster0.p2y1kwu.mongodb.net/<dbname>?retryWrites=true&w=majority";
                MongoClientSettings settings = MongoClientSettings.builder()
                        .applyConnectionString(new ConnectionString(connectionString))
                        .build();
                try (MongoClient mongoClient = MongoClients.create(settings)) {
                    MongoDatabase database = mongoClient.getDatabase("POO");


                    /*
                    MongoCollection<Document> collection = database.getCollection("usuarios");
                    JSONArray arreglo =new JSONArray(collection);
                    JSONObject objeto =  arreglo.getJSONObject(0);
                    String ROL = objeto.getString("rol");
                    String CEDULA = objeto.getString("cedula");
                    String PASSWORD = objeto.getString("contraseña");

                    // Crear el query usando los valores obtenidos
                    Document query = new Document("rol", ROL)
                            .append("cedula", CEDULA)
                            .append("contraseña", PASSWORD);

                    System.out.println("Query: " + query.toJson());
                    Document usuarioEncontrado = collection.find(query).first();


                    List<Document> documents = collection.find().into(new ArrayList<>());
                    JSONArray arreglo = new JSONArray();

                    for (Document doc : documents) {
                        arreglo.put(new JSONObject(doc.toJson()));
                    }

                    // Aquí puedes buscar el usuario específico
                    boolean userFound = false;
                    for (int i = 0; i < arreglo.length(); i++) {
                        JSONObject objeto = arreglo.getJSONObject(i);
                        String rol = objeto.getString("rol");
                        String cedulaDB = objeto.getString("cedula");
                        String contraDB = objeto.getString("contraseña");

                        if (rol.equals("Administrador") && cedulaDB.equals(cedula) && contraDB.equals(contra)) {
                            System.out.println("Acceso permitido para el administrador: " + objeto.toString());
                            userFound = true;
                            break;
                        }
                    }

                    if (!userFound) {
                        System.out.println("Cédula y/o contraseña incorrectas. Acceso denegado.");
                    }


                } catch (Exception e) {
                    System.err.println("Error al conectar a MongoDB Atlas: " + e.getMessage());
                }
                */


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



            }
        });
    }
}
