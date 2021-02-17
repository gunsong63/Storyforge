package com.larsjacobsen.factstore;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;


public class StoryforgeApplication extends Application
{
	ConfigurableApplicationContext context;

	@Override
	public void init() throws Exception
	{
		ApplicationContextInitializer<GenericApplicationContext> initializer =
				ac -> {
					ac.registerBean(Application.class, ()-> StoryforgeApplication.this);
					ac.registerBean(Parameters.class, this::getParameters);
					ac.registerBean(HostServices.class, this::getHostServices);
				};

		this.context = new SpringApplicationBuilder()
				.sources(Storyforge.class)
				.initializers()
				.run(getParameters().getRaw().toArray(new String[0]));


	}

	@Override
	public void start(Stage stage) throws Exception
	{
		this.context.publishEvent(new StageReadyEvent(stage));
	}

	@Override
	public void stop() throws Exception
	{
		this.context.stop();
		Platform.exit();
	}
}

class StageReadyEvent extends ApplicationEvent
{
	public Stage getStage()
	{
		return Stage.class.cast(getSource());
	}
	public StageReadyEvent(Stage stage)
	{
		super(stage);
	}
}
