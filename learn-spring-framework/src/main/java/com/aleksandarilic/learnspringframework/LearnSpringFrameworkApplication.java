package com.aleksandarilic.learnspringframework;

import com.aleksandarilic.learnspringframework.game.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class LearnSpringFrameworkApplication {

	public static void main(String[] args) {

		
		// Classic way (pure java - without spring)

		// Game mario = new MarioGame();
		// Game superContra = new SuperContraGame();
		//Game pacman = new PacmanGame();

		// GameRunner gameRunner = new GameRunner(mario);
		// gameRunner.run();

		// gameRunner.setGame(superContra);
		// gameRunner.run();
		// gameRunner.setGame(pacman);


		// Spring

		ConfigurableApplicationContext context =
				SpringApplication.run(LearnSpringFrameworkApplication.class, args);

		GameRunner gameRunner = context.getBean(GameRunner.class);
		gameRunner.run();
	}

}
