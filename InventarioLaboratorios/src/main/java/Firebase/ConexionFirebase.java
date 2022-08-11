/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author Ludwing
 */
public class ConexionFirebase {
    public static Firestore bd;
    public Firestore iniciarFirebase(){
        
        FileInputStream serviceAccount;
        try{
            serviceAccount = new FileInputStream("inventariolabs-firebase-adminsdk-93lym-1b3667193e.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://inventariolabs-default-rtdb.firebaseio.com/")
                    .build();
            
            FirebaseApp.initializeApp(options);
            bd = FirestoreClient.getFirestore();
            System.out.println("Se conectó");
        }
        catch(Exception ex){
            System.out.println("" + ex);
        }
        
        
//        try {
//            FirebaseOptions options = new FirebaseOptions.Builder()
//                    .setCredentials(GoogleCredentials.fromStream(getClass().getResourceAsStream("inventariolabs-firebase-adminsdk-93lym-1b3667193e.json")))
//                    .setDatabaseUrl("https://inventariolabs-default-rtdb.firebaseio.com")
//                    .build();
//            
//            FirebaseApp.initializeApp(options);
//            
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
        return FirestoreClient.getFirestore();
    }

}
