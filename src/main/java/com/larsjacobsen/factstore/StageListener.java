package com.larsjacobsen.factstore;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.net.URL;

@Component
public class StageListener implements ApplicationListener<StageReadyEvent>
{
    private final String applicationTitle;
    private final Resource fxml;
    private final ApplicationContext applicationContext;

    public StageListener(@Value("${spring.application.ui.title}") String _applicationTitle,
                         @Value("classpath:/fxml/Windows/MainWindow.fxml") Resource _resource,
                         ApplicationContext applicationContext)
    {
        applicationTitle = _applicationTitle;
        this.fxml = _resource;
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent stageReadyEvent)
    {
        Stage stage = stageReadyEvent.getStage();
        try
        {
            URL url = this.fxml.getURL();
            FXMLLoader loader = new FXMLLoader(url);
            loader.setControllerFactory(applicationContext::getBean);
            Parent root = loader.load();
            Scene scene = new Scene(root,600,600);
            stage.setScene(scene);
            stage.setTitle(this.applicationTitle);
            stage.show();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
