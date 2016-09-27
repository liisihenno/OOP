
import Abi.Kasutaja;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.lwjgl.LWJGLException;

import java.util.ArrayList;

class Käsitleja implements EventHandler<MouseEvent> {
    private TextField acc;
    private PasswordField pw;
    private ArrayList<Kasutaja> kasutajad = new ArrayList<>();
    private Stage peaLava;
    private Label errorLabel;



    public Käsitleja(TextField acc, PasswordField pw, ArrayList<Kasutaja> kasutajad, Stage peaLava, Label errorLabel) {
        this.acc = acc;
        this.pw = pw;
        this.kasutajad = kasutajad;
        this.peaLava = peaLava;
        this.errorLabel = errorLabel;

    }

    public void handle(MouseEvent me) {
        boolean test = true;
        errorLabel.setText("");
        try {

            for (Kasutaja kasutaja : kasutajad) {

                if (acc.getText().equals(kasutaja.getAcc()) && pw.getText().equals(kasutaja.getPw())) {
                    peaLava.hide();
                    new Main(kasutaja);
                    test = false;
                }
            }
            if (test) {
                errorLabel.setText("kasutaja või parool on vale");
            }
        } catch (LWJGLException e){
            throw new RuntimeException(e);
        }
    }
}