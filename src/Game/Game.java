package Game;

import UI.iLogging;

public class Game {
	private iLogging console;
	private boolean running;
	private String input;

	public Game(iLogging console) {
		this.console = console;
		init();
		start();

	}

	/**
	 * Instantiate everything
	 */
	private void init() {
		console.write("Instantiating game");

	}

	/**
	 * gameloop
	 */
	private void start() {
		console.write("- - - - - Game starting - - - - -");
		running = true;
		int turn = 1;
		while (running) {
			console.write("* * * * TURN " + turn);
			phase1();
			phase2();
			phase3();
			phase4();
			phase5();
			if (turn == 10) {
				running = false;
				console.write("- - - - - Game end - - - - -");
			}
			turn++;
		}
	}

	private void phase1() {
		console.write("* * PHASE1");
		input = console.askInput("Wat wil je doen in phase 3?");

	}

	private void phase2() {
		console.write("* * PHASE2");

	}

	private void phase3() {
		console.write("* * PHASE3");
		console.write("Dus jij wil nu '" + input + "' doen?");
	}

	private void phase4() {
		console.write("* * PHASE4");

	}

	private void phase5() {
		console.write("* * PHASE5");

	}

}
