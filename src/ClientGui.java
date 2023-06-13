import java.io.IOException;
import java.lang.ModuleLayer.Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClientGui extends Application
{
    public static void main(String[] args)
    {
        launch();
    }
    
    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuGui.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

        MenuController menuController = loader.getController();
        menuController.initData(stage);
    }
}
