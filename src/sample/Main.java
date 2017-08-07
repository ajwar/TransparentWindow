package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import ru.yandex.ajwar.transparent.TransparentHelper;

import java.io.IOException;

public class Main extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage){
        TransparentHelper transparentHelper =new TransparentHelper();
        //this.primaryStage=primaryStage;
        try {
            transparentHelper.start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(primaryStage.getScene());

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        root.setStyle("-fx-background-color: #cb1a33;");
        System.out.println(root.getScene());
        System.out.println("Backgrounfd Root==="+((Region)root).getBackground());
        primaryStage.setTitle("Hello World");
        transparentHelper.setContent(root);
        transparentHelper.setBackground(((Region)root).getBackground());
        //((AnchorPane)root).setBackground(Background.EMPTY);
        //primaryStage.getScene().setFill(Color.RED);
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
