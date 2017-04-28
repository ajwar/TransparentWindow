package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ru.yandex.ajwar.transparent.MainApp;
import ru.yandex.ajwar.transparent.TransparentHelper;

public class Main extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        MainApp mainApp=new MainApp();
        //this.primaryStage=primaryStage;
        mainApp.start(primaryStage);
        this.primaryStage=mainApp.getPrimaryStage();
        System.out.println(primaryStage.getScene());

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        System.out.println(root.getScene());
        primaryStage.setTitle("Hello World");
        mainApp.setContent(root);
        ((AnchorPane)root).setBackground(Background.EMPTY);
        primaryStage.getScene().setFill(Color.TRANSPARENT);
        primaryStage.show();
        /*primaryStage.addEventHandler(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(event.getSource());
            }
        });*/

       /* Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");*/
        //primaryStage.setScene(new Scene(root, 300, 275));
        //TransparentHelper.addTransparent(primaryStage,root);
        //primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
