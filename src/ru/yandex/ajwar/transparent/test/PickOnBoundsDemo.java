package ru.yandex.ajwar.transparent.test;

/**
 * Created by Ajwar on 21.03.2017.
 */
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PickOnBoundsDemo extends Application {
    public static void main(String[] args) { Application.launch(args); }
    @Override public void start(Stage stage) {
        final Rectangle back  = new Rectangle(128, 128);
        back.setFill(Color.FORESTGREEN);
        final ImageView front = new ImageView("http://icons.iconarchive.com/icons/aha-soft/free-large-boss/128/Wizard-icon.png");
        // icon: Linkware (Backlink to http://www.aha-soft.com required)

        final StackPane pickArea = new StackPane();
        pickArea.getChildren().addAll(
                back,
                front
        );

        final ToggleButton pickTypeSelection = new ToggleButton("Pick On Bounds");
        final Label pickResult = new Label();

        Bindings.bindBidirectional(front.pickOnBoundsProperty(), pickTypeSelection.selectedProperty());

        front.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent t) {
                pickResult.setText("Front clicked");
            }
        });

        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent t) {
                pickResult.setText("Back clicked");
            }
        });

        VBox layout = new VBox(10);
        layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");
        layout.getChildren().setAll(
                pickArea,
                new Label("Click inside the above area to test mouse picking."),
                pickTypeSelection,
                pickResult
        );
        layout.setAlignment(Pos.CENTER);

        stage.setScene(new Scene(layout));
        stage.show();
    }
}