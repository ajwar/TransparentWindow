/*
package ru.yandex.ajwar.transparent;

import com.sun.javafx.application.PlatformImpl;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

*/
/**
 * Created by Ajwar on 21.03.2017.
 *//*

public class TempTransparentHelper {
    private static TransparentHelper transparentHelper;
    private static ExecutorService executorServiceLoad;
    private static Stage primaryStage;

    public static void addTransparent(Stage appStage, Node appNode) throws Exception {
       // appStage=
        //TransparentWindowController transparentWindowController = new TransparentWindowController(appStage,appNode);

        */
/*Platform.runLater(() -> {
            try {
                transparentWindowController.getTransparentHelper().start(appStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });*//*

    }

    public static void main(String[] args) throws Exception {
        TempTransparentHelper.createAndConfigureExecutorsLoadService();
        transparentHelper =new TransparentHelper();
        */
/**Два способа запуска JavaFx приложения,если они не запущены*//*

        PlatformImpl.startup(()->{});
        //new JFXPanel();
        //Application.launch(TransparentHelper.class);
*/
/*        Platform.runLater(() -> {
            try {
                transparentHelper.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });*//*

        //System.out.println(TransparentHelper.getPrimaryStage());
        while (transparentHelper ==null){
            Thread.currentThread().sleep(100);
        }
        System.out.println(Platform.isImplicitExit());
        Platform.setImplicitExit(false);
        System.out.println(transparentHelper);
        Platform.runLater(()->{
            primaryStage= transparentHelper.getPrimaryStage();

            //transparentHelper.getPrimaryStage().show();
        });
        while (primaryStage==null){
            Thread.currentThread().sleep(100);
        }
        primaryStage.show();
        System.out.println(primaryStage);

        */
/*TransparentHelper app=new TransparentHelper();
        Stage stage=StageBuilder.create().build();*//*


        */
/*final Stage[] stage = new Stage[1];
        Platform.runLater(() -> {
            try {
                stage[0] = app.getInstanceStage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        stage[0].show();*//*

        */
/*app.init();
        System.out.println(app.getPrimaryStage());
        app.show();*//*

    }
    */
/**Инициализация кэшированного пула потоков*//*

    private static void createAndConfigureExecutorsLoadService() {
        executorServiceLoad = Executors.newCachedThreadPool(r -> {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            return thread;
        });
    }

    public static TransparentHelper getTransparentHelper() {
        return transparentHelper;
    }

    public static void setTransparentHelper(TransparentHelper transparentHelper) {
        TempTransparentHelper.transparentHelper = transparentHelper;
    }
}
*/
