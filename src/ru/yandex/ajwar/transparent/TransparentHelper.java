package ru.yandex.ajwar.transparent;

import com.sun.javafx.application.PlatformImpl;
import com.sun.javafx.stage.StageHelper;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;
import ru.yandex.ajwar.transparent.views.TransparentWindowController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Ajwar on 21.03.2017.
 */
public class TransparentHelper{
    private static MainApp mainApp;
    private static ExecutorService executorServiceLoad;
    private static Stage primaryStage;

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

    public static void main(String[] args) throws Exception {
        TransparentHelper.createAndConfigureExecutorsLoadService();
        mainApp=new MainApp();
        /**Два способа запуска JavaFx приложения,если они не запущены*/
        PlatformImpl.startup(()->{});
        //new JFXPanel();
        //Application.launch(MainApp.class);
        Platform.runLater(() -> {
            try {
                mainApp.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        //System.out.println(MainApp.getPrimaryStage());
        while (mainApp==null){
            Thread.currentThread().sleep(100);
        }
        System.out.println(Platform.isImplicitExit());
        Platform.setImplicitExit(false);
        System.out.println(mainApp);
        Platform.runLater(()->{
            primaryStage=mainApp.getPrimaryStage();

            //mainApp.getPrimaryStage().show();
        });
        while (primaryStage==null){
            Thread.currentThread().sleep(100);
        }
        primaryStage.show();
        System.out.println(primaryStage);

        /*MainApp app=new MainApp();
        Stage stage=StageBuilder.create().build();*/

        /*final Stage[] stage = new Stage[1];
        Platform.runLater(() -> {
            try {
                stage[0] = app.getInstanceStage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        stage[0].show();*/
        /*app.init();
        System.out.println(app.getPrimaryStage());
        app.show();*/
    }
    /**Инициализация кэшированного пула потоков*/
    private static void createAndConfigureExecutorsLoadService() {
        executorServiceLoad = Executors.newCachedThreadPool(r -> {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            return thread;
        });
    }

    public static MainApp getMainApp() {
        return mainApp;
    }

    public static void setMainApp(MainApp mainApp) {
        TransparentHelper.mainApp = mainApp;
    }
}
