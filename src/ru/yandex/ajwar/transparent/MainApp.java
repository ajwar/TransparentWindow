package ru.yandex.ajwar.transparent;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ru.yandex.ajwar.transparent.views.TransparentWindowController;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ajwar on 28.02.17.
 */
public class MainApp /*extends Application*/ {

    private Stage primaryStage;
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
            this.anchorPane.setBackground(Background.EMPTY);
            this.anchorPane.getChildren().addListener(init());
            primaryStage.centerOnScreen();
            primaryStage.setScene(scene);
            System.out.println(primaryStage.getScene());
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

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    public void setContent(Node node){
        node.setPickOnBounds(false);
        this.anchorPane.getChildren().add(node);
        new Rectangle2D(0.0,0.0,1.0,1.0);
    }
    public ListChangeListener init(){
        ListChangeListener<Node> listChangeListener= new ListChangeListener<Node>() {
            @Override
            public void onChanged(Change<? extends Node> c) {

                while (c.next()) {
                    if (c.wasAdded()) {
                        List<? extends Node> list=c.getAddedSubList();
                        for (Node nod :list) {
                            for (Node o :((Parent)nod).getChildrenUnmodifiable()) {
                                o.addEventHandler(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        System.out.println("Hello, I'm "+event.getSource());
                                    }
                                });
                                System.out.println("abracadabra "+o);
                            }

                            System.out.println(((Parent)nod).getChildrenUnmodifiable().size());
                            System.out.println(nod+"tut");
                        }


                        System.out.println("добавили");
                        System.out.println(c.getAddedSize());
                        System.out.println(c);
                        System.out.println(c.getAddedSubList().get(0).isPickOnBounds());
                        System.out.println(c.getList());
                    }
                    //c.wasUpdated()
                    if (c.wasRemoved()) {
                        System.out.println("elfkffgh");
                    }
                }
            }
        };
        return listChangeListener;
    }


    //нахождение всех нодов
    public static ArrayList<Node> getAllNodes(Parent root) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        addAllDescendents(root, nodes);
        return nodes;
    }

    private static void addAllDescendents(Parent parent, ArrayList<Node> nodes) {
        for (Node node : parent.getChildrenUnmodifiable()) {
            nodes.add(node);
            if (node instanceof Parent)
                addAllDescendents((Parent)node, nodes);
        }
    }
}
