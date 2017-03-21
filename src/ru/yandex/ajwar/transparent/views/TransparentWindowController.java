package ru.yandex.ajwar.transparent.views;

import borderless.Delta;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ru.yandex.ajwar.transparent.MainApp;

import java.net.URL;
import java.util.ResourceBundle;
import static ru.yandex.ajwar.transparent.util.Constant.*;


/**
 * Created by Ajwar on 28.02.17.
 */
public class TransparentWindowController implements Initializable{

    private static double yStage = 0;
    //private static double heightImg=0;
    private Stage primaryStage;
    private MainApp mainApp;
    private Scene primaryScene;
    private Node primaryNode;
    //protected Delta prevSize;
    //protected Delta prevPos;
    //protected boolean maximised;
    //private boolean snapped;

    @FXML
    private AnchorPane mainPane;
    @FXML
    private AnchorPane pane;
    @FXML
    private ImageView mainImg;
    @FXML
    private ImageView maxImg;
    @FXML
    private ImageView minImg;
    @FXML
    private ImageView restoreImg;
    @FXML
    private ImageView closeImg;
    @FXML
    private Pane leftPane;
    @FXML
    private Pane rightPane;
    @FXML
    private Pane topPane;
    @FXML
    private Pane bottomPane;
    @FXML
    private Pane topLeftPane;
    @FXML
    private Pane topRightPane;
    @FXML
    private Pane bottomLeftPane;
    @FXML
    private Pane bottomRightPane;

    public TransparentWindowController(Stage appStage, Node appNode){
        this.primaryStage=appStage;
        this.primaryNode=appNode;
            //prevSize = new Delta();
            //prevPos = new Delta();
            //maximised = false;
            //snapped = false;
    }

    public TransparentWindowController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setResizeControl(leftPane, "left");
        setResizeControl(rightPane, "right");
        setResizeControl(topPane, "top");
        setResizeControl(bottomPane, "bottom");
        setResizeControl(topLeftPane, "top-left");
        setResizeControl(topRightPane, "top-right");
        setResizeControl(bottomLeftPane, "bottom-left");
        setResizeControl(bottomRightPane, "bottom-right");
        //System.out.println(bottomPane.isPickOnBounds());
        //mainPane.setBackground(null);
        //Platform.runLater(()->System.out.println(testBorderPane.getBackground().isEmpty()));
        //mainPane.setPickOnBounds(false);
        //System.out.println(topPane.isPickOnBounds());
        //testBorderPane.setStyle("-fx-background-color: #cb1a33;");
        //Platform.runLater(()->System.out.println(testBorderPane.getBackground().isEmpty()));
       // topPane.setPickOnBounds(false);
        /*Platform.runLater(()-> {
            testBorderPane.setPickOnBounds(false);
            //anchorPane.setPickOnBounds(false);
        });*/
        /*topPane.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("z tuta");
            }
        });*/

        //Platform.runLater(this::listenerPrimaryStageMousePressedAndDragged);
        //Platform.runLater(()->EffectUtilities.makeDraggable(primaryStage,pane));
        //Platform.runLater(()->UndecoratedHelper.makeDraggable(primaryStage,pane));
        pane.setStyle("-fx-background-color: #2d56cb;");
        //heightImg=mainImg.getFitHeight();
        listenerImageViewAll();
        setMoveControl(pane);

    }

    @FXML
    private void minimize(){
        if (!primaryStage.isIconified()){
            primaryStage.setIconified(true);
        }
    }
    @FXML
    private void maximize(){
        AnchorPane.setTopAnchor(mainImg,0.0D);
        if (primaryStage.isMaximized()) {
            maxImg.setVisible(true);
            restoreImg.setVisible(false);
            AnchorPane.setTopAnchor(pane,SIZE_IMAGE_HALF);
            AnchorPane.setTopAnchor(pane,SIZE_IMAGE_HALF);
            AnchorPane.setTopAnchor(topLeftPane,SIZE_IMAGE_HALF);
            AnchorPane.setTopAnchor(topPane,SIZE_IMAGE_HALF);
            AnchorPane.setTopAnchor(topRightPane,SIZE_IMAGE_HALF);
            primaryStage.setMaximized(false);
            if (primaryStage.getY()<-SIZE_IMAGE_HALF ) AnchorPane.setTopAnchor(mainImg,SIZE_IMAGE_HALF);
            else if (primaryStage.getY()>SIZE_IMAGE_HALF){
                fullImageSize(mainImg,SIZE_IMAGE_HALF*2,true);
            }else resizeImage();
        } else {
            maxImg.setVisible(false);
            restoreImg.setVisible(true);
            fullImageSize(mainImg,SIZE_IMAGE_HALF*2,false);
            AnchorPane.setTopAnchor(pane,0.0D);
            AnchorPane.setTopAnchor(topLeftPane,0.0D);
            AnchorPane.setTopAnchor(topPane,0.0D);
            AnchorPane.setTopAnchor(topRightPane,0.0D);
            System.out.println(primaryStage.getX());
            //Rectangle2D bounds = getWindowScreen().getVisualBounds();
            //primaryStage.setX(0.0D);
            //primaryStage.setY(0.0D);
            // Get current screen of the stage
            Rectangle2D rect=new Rectangle2D(primaryStage.getX(), primaryStage.getY(), primaryStage.getWidth(), primaryStage.getHeight());
            ObservableList<Screen> screens = Screen.getScreensForRectangle(rect);
            // Change stage properties
            Rectangle2D bounds = screens.get(0).getVisualBounds();
            primaryStage.setMaximized(true);
            primaryStage.setX(bounds.getMinX());
            primaryStage.setY(bounds.getMinY());
        }
    }
    protected void setMoveControl(final Node node) {
        final Delta delta = new Delta();
        //final Delta eventSource = new Delta();

        // Record drag deltas on press.
        node.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.isPrimaryButtonDown()) {
                    delta.x = mouseEvent.getX();
                    if (!primaryStage.isMaximized()) delta.y = mouseEvent.getY()+SIZE_IMAGE_HALF;
                        else delta.y = mouseEvent.getY();
                    //eventSource.x = mouseEvent.getScreenX();
                    //eventSource.y = node.prefHeight(primaryStage.getHeight());
                }
                mouseEvent.consume();
            }
        });

        // Dragging moves the application around.
        node.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.isPrimaryButtonDown()) {
                    // Move x axis.
                    primaryStage.setX(mouseEvent.getScreenX() - delta.x);
                        primaryStage.setY(mouseEvent.getScreenY() - delta.y);
                        if (!primaryStage.isMaximized()) resizeImage();
                }
            }
        });

        // Maximise on double click.
        node.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                if ((mouseEvent.getButton().equals(MouseButton.PRIMARY)) && (mouseEvent.getClickCount() == 2)) {
                    //maximise();
                    maximize();
                }
            }
        });
    }
/*    protected void isMaximised(Boolean maximised) {
        this.maximised = maximised;
        setResizable(!maximised);
    }*/

    protected void setResizable(Boolean bool){
        leftPane.setDisable(!bool);
        rightPane.setDisable(!bool);
        topPane.setDisable(!bool);
        bottomPane.setDisable(!bool);
        topLeftPane.setDisable(!bool);
        topRightPane.setDisable(!bool);
        bottomLeftPane.setDisable(!bool);
        bottomRightPane.setDisable(!bool);
    }
    public MainApp getMainApp() {
        return mainApp;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    private void setResizeControl(Pane pane, final String direction) {
        pane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.isPrimaryButtonDown()) {
                    double width = primaryStage.getWidth();
                    double height = primaryStage.getHeight();

                    // Horizontal resize.
                    if (direction.endsWith("left")) {
                        if ((width > primaryStage.getMinWidth()) || (mouseEvent.getX() < 0)) {
                            primaryStage.setWidth(width - mouseEvent.getScreenX() + primaryStage.getX());
                            primaryStage.setX(mouseEvent.getScreenX());
                        }
                    } else if ((direction.endsWith("right"))
                            && ((width > primaryStage.getMinWidth()) || (mouseEvent.getX() > 0))) {
                        primaryStage.setWidth(width + mouseEvent.getX());
                    }

                    // Vertical resize.
                    if (direction.startsWith("top")) {
                        /*if (snapped) {
                            primaryStage.setHeight(prevSize.y);
                            snapped = false;
                        } else*/ if ((height > primaryStage.getMinHeight()) || (mouseEvent.getY() < 0)) {
                            primaryStage.setHeight(height - mouseEvent.getScreenY() + primaryStage.getY());
                            primaryStage.setY(mouseEvent.getScreenY());
                        }
                    } else if (direction.startsWith("bottom")) {
                        /*if (snapped) {
                            primaryStage.setY(prevPos.y);
                            snapped = false;
                        } else */if ((height > primaryStage.getMinHeight()) || (mouseEvent.getY() > 0)) {
                            primaryStage.setHeight(height + mouseEvent.getY());
                        }
                    }
                }
                mouseEvent.consume();
            }

        });

        // Record application height and y position.
       /* pane.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                if ((mouseEvent.isPrimaryButtonDown()) && (!snapped)) {
                    prevSize.y = primaryStage.getHeight();
                    prevPos.y = primaryStage.getY();
                }
                mouseEvent.consume();
            }
        });*/

        // Aero Snap Resize.
        /*pane.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                if ((mouseEvent.getButton().equals(MouseButton.PRIMARY)) && (!snapped)) {
                    Rectangle2D screen = ((Screen) Screen.getScreensForRectangle(mouseEvent.getScreenX(),
                            mouseEvent.getScreenY(), 1, 1).get(0)).getVisualBounds();

                    if ((primaryStage.getY() <= screen.getMinY()) && (direction.startsWith("top"))) {
                        primaryStage.setHeight(screen.getHeight());
                        primaryStage.setY(screen.getMinY());
                        snapped = true;
                    }

                    if ((mouseEvent.getScreenY() >= screen.getMaxY()) && (direction.startsWith("bottom"))) {
                        primaryStage.setHeight(screen.getHeight());
                        primaryStage.setY(screen.getMinY());
                        snapped = true;
                    }
                }
                mouseEvent.consume();
            }
        });*/

        // Aero Snap resize on double click.
        /*pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                if ((mouseEvent.getButton().equals(MouseButton.PRIMARY)) && (mouseEvent.getClickCount() == 2)
                        && ((direction.equals("top")) || (direction.equals("bottom")))) {
                    Rectangle2D screen = ((Screen) Screen.getScreensForRectangle(primaryStage.getX(), primaryStage.getY(),
                            primaryStage.getWidth() / 2, primaryStage.getHeight() / 2).get(0)).getVisualBounds();

                    if (snapped) {
                        primaryStage.setHeight(prevSize.y);
                        primaryStage.setY(prevPos.y);
                        snapped = false;
                    } else {
                        prevSize.y = primaryStage.getHeight();
                        prevPos.y = primaryStage.getY();
                        primaryStage.setHeight(screen.getHeight());
                        primaryStage.setY(screen.getMinY());
                        snapped = true;
                    }
                }
                mouseEvent.consume();
            }
        });*/
    }

    private void listenerPrimaryStageMousePressedAndDragged(){
        //UndecoratedHelper.addListenerDragAndPressNode(pane,primaryStage);
/*        pane.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        pane.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
            if (!primaryStage.isMaximized()) resizeImage();
        });*/
        pane.setOnMouseClicked(event -> {
            if (event.getClickCount()==2 && event.getButton()== MouseButton.PRIMARY){
                maximize();
            }
        });
    }
    private void resizeImage(){
        yStage=primaryStage.getY();
        if (yStage>-SIZE_IMAGE_HALF && yStage<SIZE_IMAGE_HALF /*&& !primaryStage.isMaximized()*/) {
            mainImg.setFitWidth(SIZE_IMAGE_HALF*3/2+yStage/2);
            mainImg.setFitHeight(SIZE_IMAGE_HALF*3/2+yStage/2);
            AnchorPane.setTopAnchor(mainImg,SIZE_IMAGE_HALF/2-yStage/2);
        }
    }
    private void listenerImageViewAll(){
        listenerHover(closeImg,closeImgPathResource,closeImgBackPathResource);
        listenerHover(minImg,minImgPathResource,minImgBackPathResource);
        listenerHover(maxImg,maxImgPathResource,maxImgBackPathResource);
        listenerHover(restoreImg,restoreImgPathResource,restoreImgBackPathResource);
        closeImg.setOnMouseClicked(event -> System.exit(0));
        Platform.runLater(()->{primaryStage.iconifiedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    Platform.runLater(()->{
                        primaryStage.hide();
                        minImg.setImage(new Image(getClass().getResourceAsStream(minImgPathResource)));
                        primaryStage.show();
                    });
                }
            }
        });
        });
    }

    private void listenerHover(ImageView img,String pathImg,String pathImgHover){
        img.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                img.setImage(new Image(getClass().getResourceAsStream(pathImgHover)));
            }
        });
        img.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                img.setImage(new Image(getClass().getResourceAsStream(pathImg)));
            }
        });
    }
    private void fullImageSize(ImageView img,double height,boolean bool){
        if (bool){
            img.setFitWidth(height);
            img.setFitHeight(height);
        }else {
            img.setFitWidth(height/2);
            img.setFitHeight(height/2);
        }
    }
    public  Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public ImageView getMinImg() {
        return minImg;
    }

    public void setMinImg(ImageView minImg) {
        this.minImg = minImg;
    }

    public AnchorPane getPane() {
        return pane;
    }

    public void setPane(AnchorPane pane) {
        this.pane = pane;
    }


}
