import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainSceneController 
{
    private Client client;

    private Stage stage;

    @FXML
    private Button searchButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button drawButton;

    @FXML
    private Button insertButton;

    @FXML
    private TextArea outputLabel;

    @FXML
    private TextField textField;

    @FXML
    private Button exitButton;

    public void initData(Client client, Stage stage)
    {
        this.client = client;
        this.stage = stage;
    }

    @FXML
    private void deleteButtonClicked(ActionEvent event) 
    {
        client.sendMessageToServer("DELETE");
        changeControlsMode();
    }

    @FXML
    private void drawButtonClicked(ActionEvent event) 
    {
        client.sendMessageToServer("DRAW");
        // changeControlsMode();
    }

    @FXML
    private void insertButtonClicked(ActionEvent event) 
    {
        client.sendMessageToServer("INSERT");
        changeControlsMode();
    }

    @FXML
    private void searchButtonClicked(ActionEvent event) 
    {
        client.sendMessageToServer("SEARCH");
        changeControlsMode();
    }

    @FXML
    private void textFieldClicked(ActionEvent event)
    {
        changeControlsMode();
        client.sendMessageToServer(textField.getText());
        textField.clear();
    }

    @FXML
    private void exitButtonClicked(ActionEvent event)
    {
        client.closeEverything();
        stage.close();
    }

    public void displayMessage(String messages)
    {
        // System.out.println("szmule chca cos pisac");
        System.out.println(messages);
        
        outputLabel.setText(messages);
    }

    private void changeControlsMode()
    {
        insertButton.setDisable(!insertButton.isDisabled());
        searchButton.setDisable(!searchButton.isDisabled());
        deleteButton.setDisable(!deleteButton.isDisabled());
        drawButton.setDisable(!drawButton.isDisable());
        textField.setDisable(!textField.isDisabled());
    }

}
