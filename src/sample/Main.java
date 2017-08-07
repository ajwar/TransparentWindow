package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import ru.yandex.ajwar.transparent.TransparentHelper;

import java.io.IOException;

public class Main extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.primaryStage=primaryStage;
        root.setStyle("-fx-background-color: #cb1a33;");
        System.out.println(root.getScene());
        System.out.println("Backgrounfd Root==="+((Region)root).getBackground());
        this.primaryStage.setTitle("Hello World");
        this.primaryStage.setScene(new Scene(root));
        //((AnchorPane)root).setBackground(Background.EMPTY);
        //primaryStage.getScene().setFill(Color.RED);
        TransparentHelper transparentHelper =new TransparentHelper();

        this.primaryStage=transparentHelper.start(primaryStage);
        this.primaryStage.show();
        /*primaryStage.addEventHandler(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(event.getSource());
            }
        });*/

       /* Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");*/
        //primaryStage.setScene(new Scene(root, 300, 275));
        //TempTransparentHelper.addTransparent(primaryStage,root);
        //primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
