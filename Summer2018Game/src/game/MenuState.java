package game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class MenuState extends State {

	private State prevState;
	
	private UIImageButton saveGameButton = new UIImageButton(game.getWidth() / 2 - 50, game.getHeight() / 2 - 75, 100, 50, Assets.saveGame, Assets.saveGameHover, new ClickListener(){

		@Override
		public void onClick() {
			if(game.isPrimaryWindow()) {
				game.setPrimaryWindow(false);
				SaveWindow saveWindow = new SaveWindow(game);
				saveWindow.show();
			}
		}});
	
	private UIImageButton closeMenuButton = new UIImageButton(game.getWidth() / 2 - 50, game.getHeight() / 2 + 25, 100, 50, Assets.closeMenu, Assets.closeMenuHover, new ClickListener(){

		@Override
		public void onClick() {
			if(game.isPrimaryWindow()) {
				State.setState(prevState);
			}
		}});
	
	/**
	 * Constructs a new MenuState
	 *
	 * @param game the Game object for this running instance
	 * @param prevState the previous state we were in, saved in case the user decides to close the menu, in which case we return the player to where they were
	 */
	public MenuState(Game game, State prevState) {
		super(game);
		this.prevState = prevState;
	}

	@Override
	public void tick() {
		saveGameButton.tick();
		closeMenuButton.tick();
		if(game.getKeyManager().keyJustPressed(KeyEvent.VK_M)) {
			State.setState(prevState);
		}
	}

	@Override
	public void render(Graphics graphics) {
		saveGameButton.render(graphics);
		closeMenuButton.render(graphics);
	}

	@Override
	public ArrayList<UIObject> getUIOBjects() {
		ArrayList<UIObject> uiObjects = new ArrayList<UIObject>();
		uiObjects.add(saveGameButton);
		uiObjects.add(closeMenuButton);
		return uiObjects;
	}
	
	public Array2D<Integer> getData() {
		if(prevState.isDungeonState()) {
			DungeonState dungeonState = (DungeonState) prevState;
			return dungeonState.getDungeon().getData();
		} else {
			TownState townState = (TownState) prevState;
			return townState.getData();
		}
	}
	
	public State getPrevState() {
		return prevState;
	}

	public Array2D<Boolean> getSeen() {
		if(prevState.isDungeonState()) {
			DungeonState dungeonState = (DungeonState) prevState;
			return dungeonState.getDungeon().getSeen();
		} else {
			TownState townState = (TownState) prevState;
			return townState.getSeen();
		}
	}

	@Override
	public int getDifficulty() {
		return prevState.getDifficulty();
	}
}
