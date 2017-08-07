package ru.yandex.ajwar.transparent;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ru.yandex.ajwar.transparent.views.TransparentWindowController;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static ru.yandex.ajwar.transparent.util.Constant.SIZE_IMAGE_HALF;

/**
 * Created by Ajwar on 28.02.17.
 */
public class TransparentHelper {

    private Stage primaryStage;
    private AnchorPane anchorPane;
    private TransparentWindowController mainWindowController;

    public Stage start(Stage oldStage) throws Exception {
        this.primaryStage=new Stage();
        Node node=oldStage.getScene().getRoot();
        initMainWindow();
        setContent(node);
        this.anchorPane.getChildren().addListener(init());
        getAllNodes(anchorPane);
        Platform.runLater(() -> setBackground(((Region)node).getBackground()));
        this.primaryStage.setMinHeight(oldStage.getMinHeight());
        this.primaryStage.setMinWidth(oldStage.getMinWidth());
        return this.primaryStage;
    }

    private void initMainWindow() {
        try {
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(TransparentHelper.class.getResource("views/TransparentWindow.fxml"));
            this.anchorPane = loader.load();
            Scene scene=new Scene(this.anchorPane, Color.TRANSPARENT);
            this.anchorPane.setBackground(Background.EMPTY);
            primaryStage.centerOnScreen();
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            mainWindowController=loader.getController();
            mainWindowController.setTransparentHelper(this);
            mainWindowController.setPrimaryStage(primaryStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setBackground(Background bg){
        mainWindowController.getPane().setBackground(bg);
    }

    public void setContent(Node node){
        System.out.println(node.toString());
        //setPickOnBounds делает прозрачный для событий node для остальных вложений
        //node.setPickOnBounds(true);
        mainWindowController.getPane().getChildren().add(node);
        AnchorPane.setTopAnchor(node,SIZE_IMAGE_HALF);
        AnchorPane.setBottomAnchor(node,Double.MIN_VALUE);
        AnchorPane.setLeftAnchor(node,Double.MIN_VALUE);
        AnchorPane.setRightAnchor(node,Double.MIN_VALUE);
        //new Rectangle2D(0.0,0.0,1.0,1.0);

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
        ArrayList<Node> nodes = new ArrayList<>();
        addAllDescendents(root, nodes);
        return nodes;
    }

    private static void addAllDescendents(Parent parent, ArrayList<Node> nodes) {
        for (Node node : parent.getChildrenUnmodifiable()) {
            node.setPickOnBounds(true);
            nodes.add(node);
            if (node instanceof Parent)
                addAllDescendents((Parent)node, nodes);
        }
    }
}
