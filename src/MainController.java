
public class MainController {
	
	ShitHeadGUI gui;
	ShitModel shitModel;
	GameController gameController;
	ShitServerModel shitServerModel;
	ServerController serverController;
	
	public MainController() {
		gui = new ShitHeadGUI();
		shitModel = new ShitModel();
		gameController = new GameController(gui.tablePanel, shitModel);
		
		shitServerModel = new ShitServerModel();
		serverController = new ServerController(shitServerModel, gui.serverPanel);
		gui.serverPanel.setController(serverController);

		gameController.startGame();
	}
}
