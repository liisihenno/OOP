import Abi.Kasutaja;
import javafx.scene.control.Label;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Mihkel on 8.05.2016.
 */
public class KirjutaFaili {
    public static ArrayList<Abi.Kasutaja> loefailist(){
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("andmed.bin"))){
            File f = new File("andmed.bin");
            if(f.exists()) {
                ArrayList<Kasutaja> temp = (ArrayList<Kasutaja>) in.readObject();
                return temp;

            }else{
                return new ArrayList<>();
            }
        }catch(Exception e){
            return new ArrayList<>();
        }

    }

    public static void registreeri(String nimi, String parool, Label errorLabel, ArrayList<Kasutaja> kasutajad) {
        errorLabel.setText("");
        boolean test = true;
            if(nimi.length()<3){
                test=false;
                errorLabel.setText("Kasutajanimi liiga lühike");
            }
            if(parool.length()<3){
                test=false;
                errorLabel.setText("Parool liiga lühike");
            }
            for (Kasutaja kasutaja : kasutajad) {
                if (nimi.equals(kasutaja.getAcc())) {
                    errorLabel.setText("Kasutajanimi on juba olemas");
                    test = false;
                }

            }
            if (test) {
                errorLabel.setText("");
                Kasutaja uuskasutaja = new Kasutaja(nimi, parool, kasutajad.size()+1);
                kasutajad.add(uuskasutaja);
                kirjuta(kasutajad);

            }


            System.out.println(kasutajad.toString());



    }
    public static void kirjuta(ArrayList<Kasutaja> kasutajad) {
        try (ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream("andmed.bin"))){

            oout.writeObject(kasutajad);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
