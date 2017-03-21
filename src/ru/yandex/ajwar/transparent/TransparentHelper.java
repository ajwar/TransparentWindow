package ru.yandex.ajwar.transparent;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.yandex.ajwar.transparent.views.TransparentWindowController;

/**
 * Created by Ajwar on 21.03.2017.
 */
public class TransparentHelper {
    public static void addTransparent(Stage appStage, Node appNode) throws Exception {
       // appStage=
        //TransparentWindowController transparentWindowController = new TransparentWindowController(appStage,appNode);

        /*Platform.runLater(() -> {
            try {
                transparentWindowController.getMainApp().start(appStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });*/
    }

}
