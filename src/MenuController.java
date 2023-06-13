import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MenuController 
{
    private Client client;

    private Stage stage;

    @FXML
    private Button doubleButton;

    @FXML
    private Button integerButton;

    @FXML
    private Button stringButton;

    public void initData(Stage stage)
    {
        this.client = new Client();
        this.stage = stage;
    }

    @FXML
    private void doubleButtonClicked(ActionEvent event) throws IOException 
    {
        client.sendMessageToServer("Double");
        stage.setTitle("Double Binary Search Tree");
        switchSceneToMainScene();
    }

    @FXML
    private void integerButtonClicked(ActionEvent event) throws IOException 
    {
        client.sendMessageToServer("Integer");
        stage.setTitle("Integer Binary Search Tree");
        switchSceneToMainScene();
    }

    @FXML
    private void stringButtonClicked(ActionEvent event) throws IOException 
    {
        client.sendMessageToServer("String");
        stage.setTitle("String Binary Search Tree");
        switchSceneToMainScene();
    }

    private void switchSceneToMainScene() throws IOException
    {
        FXMLLoader newSceneLoader = new FXMLLoader(getClass().getResource("MainScene.fxml"));
        // System.out.println("probuje przeslac controllera");
        Parent root = newSceneLoader.load();
        // System.out.println("probuje przeslac controllera");
        Scene mainScene = new Scene(root);
        MainSceneController mainSceneController = newSceneLoader.getController();
        // System.out.println("probuje przeslac controllera");
        client.setMainSceneController(mainSceneController);
        mainSceneController.initData(client, stage);
        stage.setScene(mainScene);
        stage.show();
    }

}
