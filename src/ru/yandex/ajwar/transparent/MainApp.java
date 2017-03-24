package ru.yandex.ajwar.transparent;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ru.yandex.ajwar.transparent.views.TransparentWindowController;


import java.io.IOException;

/**
 * Created by Ajwar on 28.02.17.
 */
public class MainApp /*extends Application*/ {

    private static Stage primaryStage;
    private AnchorPane anchorPane;
    private TransparentWindowController mainWindowController;
    /*@Override*/
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage=primaryStage;
        initMainWindow();
        TransparentHelper.setMainApp(this);
        //BorderlessScene scene=new BorderlessScene(primaryStage,anchorPane);
        //primaryStage.setScene(scene);
        //scene.setMoveControl(mainWindowController.getPane());
        //(//(AnchorPane)scene.getRoot()).setBackground(Background.EMPTY);
        //scene.setFill(Color.TRANSPARENT);
        //Platform.runLater(()->System.out.println(primaryStage.getOwner()));
        //show();
        //primaryStage.show();
    }

/*    public static void main(String[] args) {
        launch(args);
    }*/
    private void initMainWindow() {
        try {
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("views/TransparentWindow.fxml"));
            //primaryStage.getIcons().add(new Image(MainApp.class.getResourceAsStream("/images/IcoMainWindowNew.png")));
            this.anchorPane =(AnchorPane)loader.load();
            //this.anchorPane.setBackground(Background.EMPTY);
            Scene scene=new Scene(this.anchorPane, Color.TRANSPARENT);
            //this.anchorPane.setBackground(Background.EMPTY);
            primaryStage.centerOnScreen();
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            //primaryStage.setOnCloseRequest(event -> System.exit(0));
            mainWindowController=loader.getController();
            mainWindowController.setMainApp(this);
            mainWindowController.setPrimaryStage(primaryStage);
            //scene.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> System.out.println("mouse click detected! " + event.getSource()));
            //scene.setFill(null);
            //scene.setFill(Color.TRANSPARENT);
            //((AnchorPane)scene.getRoot()).setBackground(Background.EMPTY);
            //System.out.println();
            //UndecoratedHelper.addResizeListener(primaryStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Stage getInstanceStage() throws Exception {
        Stage stage=new Stage();
        this.start(stage);
        return primaryStage;
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setPrimaryStage(Stage primaryStage) {
        MainApp.primaryStage = primaryStage;
    }
}
