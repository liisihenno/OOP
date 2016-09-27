
import Abi.Kasutaja;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by Mihkel on 13.04.2016.
 */
public class Põhiaken extends Application {
    ArrayList<Abi.Kasutaja> kasutajad = KirjutaFaili.loefailist();
    public static void main(String[] args){
        launch(args);
    }

    public void start(Stage peaLava){
        Group juur = new Group();
        peaLava.setTitle("Logi sisse");
        Scene stseen1 = new Scene(juur, 250, 250);
        peaLava.setResizable(false);
        peaLava.setScene(stseen1);
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);


        //kasutajanimi
        Label usernameLabel = new Label("Kasutaja: ");
        GridPane.setConstraints(usernameLabel, 0, 0);
        TextField usernameTfield = new TextField();
        usernameTfield.setPromptText("kasutajanimi");
        GridPane.setConstraints(usernameTfield, 1, 0);

        //parool
        Label passwordLabel = new Label("Parool: ");
        GridPane.setConstraints(passwordLabel, 0, 1);
        PasswordField passwordTfield = new PasswordField();
        passwordTfield.setPromptText("parool");
        GridPane.setConstraints(passwordTfield, 1, 1);

        //errorlabelid
        Label errorLabel = new Label("");
        GridPane.setConstraints(errorLabel, 1, 6);



        //nupud
        Button loginButton = new Button("Logi sisse");
        GridPane.setConstraints(loginButton, 1, 2);
        loginButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new Käsitleja(usernameTfield, passwordTfield, kasutajad, peaLava, errorLabel));




        //registreerimine
        Label registerLabel = new Label("Pole kasutaja?");
        GridPane.setConstraints(registerLabel, 1, 4);
        Button registerButton = new Button("Registreeri");
        GridPane.setConstraints(registerButton, 1, 5);
        registerButton.setOnAction(event -> KirjutaFaili.registreeri(usernameTfield.getText(), passwordTfield.getText(), errorLabel, kasutajad));



        grid.getChildren().addAll(usernameLabel, usernameTfield, passwordLabel, passwordTfield, loginButton, registerButton, registerLabel,  errorLabel);
        juur.getChildren().add(grid);
        peaLava.show();

    }

}
