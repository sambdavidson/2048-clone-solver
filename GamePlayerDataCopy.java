package clone2048;


/**
 * Use for predicting best moves. Its a miniature version of GamePlayer that holds its own grid and performs its own private moves
 * without drawing anything. 
 * @author Samuel Davidson
 *
 */
public class GamePlayerDataCopy {
	
	public BoxController gameBox;
	
	public int currentScore;
	
	private int x;
	private int y;
	
	
	public GamePlayerDataCopy(BoxController parentBoxes) {
		
		
		y = parentBoxes.grid.length;
		x = parentBoxes.grid[0].length;
		gameBox = new BoxController(x,y,true);
		gameBox.grid = cloneGrid(parentBoxes.grid);
	}
	public GamePlayerDataCopy(BoxController parentBoxes, int score) {
		
		currentScore = score;
		
		y = parentBoxes.grid.length;
		x = parentBoxes.grid[0].length;
		gameBox = new BoxController(x,y,true);
		gameBox.grid = cloneGrid(parentBoxes.grid);
	}
	/**
	 * Recursively calls to the desired depth. Testing every possible combination of moves and storing the move history to an int
	 * @param currentDepth
	 * @param desiredDepth
	 * @param history
	 * @return
	 */
	public int findBestMoveComplexity(int currentDepth, int desiredDepth, int history) {
		//Check if we're just starting
		if(currentDepth == 0) {
			history = 102;
		}
		//Check if depth has been met
		if(currentDepth >= desiredDepth) {
			GamePlayer.combinationMap.put(history, gameBox.getNumberAvailable());
			return -1;
		}
		
		
		//Perform the Up Move
		int upHistory = history;
		upHistory = (history << 2) | 0;
		BoxController upBox = new BoxController(x,y,true);
		upBox.grid = cloneGrid(gameBox.grid);
		upBox.upMove();
		new GamePlayerDataCopy(upBox).findBestMoveComplexity(currentDepth + 1, desiredDepth, upHistory);
		
		//Perform the Down Move
		int downHistory = history;
		downHistory = (history << 2) | 1;
		BoxController downBox = new BoxController(x,y,true);
		downBox.grid = cloneGrid(gameBox.grid);
		downBox.downMove();
		new GamePlayerDataCopy(downBox).findBestMoveComplexity(currentDepth + 1, desiredDepth, downHistory);
		
		//Perform the Left Move
		int leftHistory = history;
		leftHistory = (history << 2) | 2;
		BoxController leftBox = new BoxController(x,y,true);
		leftBox.grid = cloneGrid(gameBox.grid);
		leftBox.leftMove();
		new GamePlayerDataCopy(leftBox).findBestMoveComplexity(currentDepth + 1, desiredDepth, leftHistory);
		
		//Perform the Right Move
		int rightHistory = history;
		rightHistory = (history << 2) | 3;
		BoxController rightBox = new BoxController(x,y,true);
		rightBox.grid = cloneGrid(gameBox.grid);
		rightBox.rightMove();
		new GamePlayerDataCopy(rightBox).findBestMoveComplexity(currentDepth + 1, desiredDepth, rightHistory);
		
		if(currentDepth == 0) {
			// Done with all of the recursion, figure out the best move.
			int currentLowest = 0;
			int bestHistory = 0;

			for(int key : GamePlayer.combinationMap.keySet()) {
				
				if(GamePlayer.combinationMap.get(key) > currentLowest) {
					bestHistory = key;
					currentLowest = GamePlayer.combinationMap.get(key);
				}
					
			}
			GamePlayer.combinationMap.clear();
			return bestHistory;

		}
		
		return 0;
	}
	
	public int findBestMovePoints(int currentDepth, int desiredDepth, int history) {
		//Check if we're just starting
				if(currentDepth == 0) {
					history = 102;
				}
				//Check if depth has been met
				if(currentDepth >= desiredDepth) {
					GamePlayer.combinationMap.put(history, this.currentScore);
					return -1;
				}
				
				
				//Perform the Up Move
				int upHistory = history;
				upHistory = (history << 2) | 0;
				BoxController upBox = new BoxController(x,y,true,this);
				upBox.grid = cloneGrid(gameBox.grid);
				upBox.upMove();
				new GamePlayerDataCopy(upBox,this.currentScore).findBestMoveComplexity(currentDepth + 1, desiredDepth, upHistory);
				
				//Perform the Down Move
				int downHistory = history;
				downHistory = (history << 2) | 1;
				BoxController downBox = new BoxController(x,y,true,this);
				downBox.grid = cloneGrid(gameBox.grid);
				downBox.downMove();
				new GamePlayerDataCopy(downBox,this.currentScore).findBestMoveComplexity(currentDepth + 1, desiredDepth, downHistory);
				
				//Perform the Left Move
				int leftHistory = history;
				leftHistory = (history << 2) | 2;
				BoxController leftBox = new BoxController(x,y,true,this);
				leftBox.grid = cloneGrid(gameBox.grid);
				leftBox.leftMove();
				new GamePlayerDataCopy(leftBox,this.currentScore).findBestMoveComplexity(currentDepth + 1, desiredDepth, leftHistory);
				
				//Perform the Right Move
				int rightHistory = history;
				rightHistory = (history << 2) | 3;
				BoxController rightBox = new BoxController(x,y,true,this);
				rightBox.grid = cloneGrid(gameBox.grid);
				rightBox.rightMove();
				new GamePlayerDataCopy(rightBox,this.currentScore).findBestMoveComplexity(currentDepth + 1, desiredDepth, rightHistory);
				
				if(currentDepth == 0) {
					// Done with all of the recursion, figure out the best move.
					int currentHighest = 0;
					int bestHistory = 0;

					for(int key : GamePlayer.combinationMap.keySet()) {
						
						if(GamePlayer.combinationMap.get(key) > currentHighest) {
							bestHistory = key;
							currentHighest = GamePlayer.combinationMap.get(key);
						}
							
					}
					GamePlayer.combinationMap.clear();
					return bestHistory;

				}
				
				return 0;
	}
	
	private NumberBox[][] cloneGrid(NumberBox[][] gridToClone) {
		
		NumberBox[][] returningGrid = new NumberBox[gridToClone.length][gridToClone[0].length];
		for(int i = 0; i < gridToClone.length; i++) {
			for(int j = 0; j < gridToClone[0].length; j++) {
				if(gridToClone[i][j] != null) {
					returningGrid[i][j] = new NumberBox(gridToClone[i][j].getNumber());
				}
			}
		}
		return returningGrid;
	}
	

	

}
