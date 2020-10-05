package engine;


public class GameLoop extends Thread {
	private static final int FPS = 60;
	private GameEngine game;
	private GameEngineSM gameSM;
	private GameEngineCM gameCM;
	private int tipo;
	public GameLoop(GameEngine game) {
		super();
		this.game = game;
		tipo=1;
	}
	public GameLoop(GameEngineSM game) {
		super();
		this.gameSM = game;
		tipo=2;// TODO Auto-generated constructor stub
	}
	public GameLoop(GameEngineCM game) {
		super();
		this.gameCM = game;
		tipo=3;
	}
	void game() {
		try {
			while (game.isRunning) {
				long time = System.currentTimeMillis();
				game.update();
				game.draw();
				
				// delay for each frame - time it took for one frame
				time = (1000 / FPS) - (System.currentTimeMillis() - time);
				
				if (time > 0) {
					try {
						Thread.sleep(time);
					} catch (Exception e) {
					}
				}
				}
			game.setVisible(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	void gameSM() {
		try {
			while (gameSM.isRunning) {
				long time = System.currentTimeMillis();

				gameSM.update();
				gameSM.draw();

				// delay for each frame - time it took for one frame
				time = (1000 / FPS) - (System.currentTimeMillis() - time);

				if (time > 0) {
					try {
						Thread.sleep(time);
					} catch (Exception e) {
					}
				}
			}
			gameSM.setVisible(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void gameCM() {
		try {
			while (gameCM.isRunning) {
				long time = System.currentTimeMillis();
				
				gameCM.update();
				gameCM.draw();

				// delay for each frame - time it took for one frame
				time = (1000 / FPS) - (System.currentTimeMillis() - time);

				if (time > 0) {
					try {
						Thread.sleep(time);
					} catch (Exception e) {
					}
				}
			}
			gameCM.setVisible(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		switch (tipo) {
		case 1:
			game();
			break;
		case 2:
			gameSM();
			break;
		case 3:
			gameCM();
			break;
		default:
			break;
		}
	}
}
