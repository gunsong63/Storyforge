package com.larsjacobsen.factstore;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;

@Component
public class MainWindowUIController
{
    @FXML
    public Label Label;
    @FXML
    public Button Button;
    @FXML
    public VBox Vbox;
}
