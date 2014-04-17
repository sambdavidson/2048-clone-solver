package clone2048;

import java.util.ArrayList;

public class BoxController {

	private ArrayList<Coordinate> availableSpots;
	
	public NumberBox[][] grid;
	
	private boolean isCopy;
	
	private GamePlayerDataCopy copyGamePlayer; 

	public BoxController(int x, int y) {
		grid = new NumberBox[x][y];
		clearGrid();
		availableSpots = new ArrayList<Coordinate>();
		isCopy = false;

		newGame();
	}
	
	public BoxController(int x, int y, boolean _isCopy) {
		grid = new NumberBox[x][y];
		clearGrid();
		availableSpots = new ArrayList<Coordinate>();
		isCopy = _isCopy;
		
		if(!isCopy)
			newGame();
	}
	
	public BoxController(int x, int y, boolean _isCopy, GamePlayerDataCopy _copyGamePlayer) {
		copyGamePlayer = _copyGamePlayer;
		grid = new NumberBox[x][y];
		clearGrid();
		availableSpots = new ArrayList<Coordinate>();
		isCopy = _isCopy;
		
		if(!isCopy)
			newGame();
	}

	public void newGame() {

		createNewNumber();
		createNewNumber();

	}

	private void createNewNumber() {
		
		calculateAvailableSpots();
		
		
		if(availableSpots.size() <= 0) {
			gameOver();
			return;
		}

		Coordinate newPosition = availableSpots.get((int)(Math.random() * availableSpots.size()));
		
		grid[newPosition.x][newPosition.y] = new NumberBox(isCopy);
		

	}
	
	public void calculateAvailableSpots() {
		availableSpots.clear();
		//Search available spots;
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[0].length; j++){
				if(grid[i][j] == null) {
					availableSpots.add(new Coordinate(i,j));
				}
			}
				
		}
	}
	
	public int getNumberAvailable() {
		calculateAvailableSpots();
		return availableSpots.size();
	}
	
	public void clearGrid() {
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[0].length; j++){
				grid[i][j] = null;
			}
				
		}
	}
	
	public void gameOver() {
		if(!isCopy)
			System.out.println("GAME OVER");
	}
	
	public void leftMove() {
		
		for(int x = 0; x < grid[0].length; x++) {
			for(int y = 0; y < grid.length; y++){
				NumberBox box = grid[y][x];
				if(box != null) {
					int disp = 0;
					while(true) {
						if(x-disp-1 >= 0) {
							if(grid[y][x-disp-1] == null) {
								if(x-disp-1 == 0) {
									NumberBox tmp = grid[y][x];
									grid[y][x] = null;
									grid[y][x-disp-1] = tmp;
									break;
								}
								disp++;
							} else if(grid[y][x-disp-1].getNumber() == box.getNumber()) {
								grid[y][x-disp-1].doubleNumber();
								grid[y][x] = null;
								
								if(copyGamePlayer != null)
									copyGamePlayer.currentScore += grid[y][x-disp-1].getNumber();
								
								break;
							} else if(grid[y][x-disp-1].getNumber() != box.getNumber()) {
								NumberBox tmp = grid[y][x];
								grid[y][x] = null;
								grid[y][x-disp] = tmp;
								break;
							}
						} else 
							break;
					}
				}
			}
		}
		createNewNumber();


	}

	public void rightMove() {
		
		for(int x = grid[0].length - 1; x >= 0 ; x--) {
			for(int y = 0; y < grid.length; y++){
				NumberBox box = grid[y][x];
				if(box != null) {
					int disp = 0;
					while(true) {
						if(x+disp+1 < grid[0].length) {
							if(grid[y][x+disp+1] == null) {
								if(x+disp+1 == grid[0].length-1) {
									NumberBox tmp = grid[y][x];
									grid[y][x] = null;
									grid[y][x+disp+1] = tmp;
									break;
								}
								disp++;
							} else if(grid[y][x+disp+1].getNumber() == box.getNumber()) {
								grid[y][x+disp+1].doubleNumber();
								grid[y][x] = null;
								
								if(copyGamePlayer != null)
									copyGamePlayer.currentScore += grid[y][x+disp+1].getNumber();
								
								break;
							} else if(grid[y][x+disp+1].getNumber() != box.getNumber()) {
								NumberBox tmp = grid[y][x];
								grid[y][x] = null;
								grid[y][x+disp] = tmp;
								break;
							}
						} else 
							break;
					}
				}
			}
		}
		if(!isCopy)
			createNewNumber();


	}

	public void upMove() {
		
		for(int y = 0; y < grid.length; y++) {
			for(int x = 0; x < grid[0].length; x++){
				NumberBox box = grid[y][x];
				if(box != null) {
					int disp = 0;
					while(true) {
						if(y-disp-1 >= 0) {
							if(grid[y-disp-1][x] == null) {
								if(y-disp-1 == 0) {
									NumberBox tmp = grid[y][x];
									grid[y][x] = null;
									grid[y-disp-1][x] = tmp;
									break;
								}
								disp++;
							} else if(grid[y-disp-1][x].getNumber() == box.getNumber()) {
								grid[y-disp-1][x].doubleNumber();
								grid[y][x] = null;
								
								if(copyGamePlayer != null)
									copyGamePlayer.currentScore += grid[y-disp-1][x].getNumber();
								
								break;
							} else if(grid[y-disp-1][x].getNumber() != box.getNumber()) {
								NumberBox tmp = grid[y][x];
								grid[y][x] = null;
								grid[y-disp][x] = tmp;
								break;
							}
						} else 
							break;
					}
				}
			}
		}
		if(!isCopy)
			createNewNumber();

	}

	public void downMove() {
		
		for(int y = grid.length - 1; y >= 0; y--) {
			for(int x = 0; x < grid[0].length; x++){
				NumberBox box = grid[y][x];
				if(box != null) {
					int disp = 0;
					while(true) {
						if(y+disp+1 < grid.length) {
							if(grid[y+disp+1][x] == null) {
								if(y+disp+1 == grid.length - 1) {
									NumberBox tmp = grid[y][x];
									grid[y][x] = null;
									grid[y+disp+1][x] = tmp;
									break;
								}
								disp++;
							} else if(grid[y+disp+1][x].getNumber() == box.getNumber()) {
								grid[y+disp+1][x].doubleNumber();
								grid[y][x] = null;
								
								if(copyGamePlayer != null)
									copyGamePlayer.currentScore += grid[y+disp+1][x].getNumber();
								
								break;
							} else if(grid[y+disp+1][x].getNumber() != box.getNumber()) {
								NumberBox tmp = grid[y][x];
								grid[y][x] = null;
								grid[y+disp][x] = tmp;
								break;
							}
						} else 
							break;
					}
				}
			}
		}
		if(!isCopy)
			createNewNumber();


	}
	
	/**
	 * A class to hold coordinates.
	 * 
	 * @author Samuel Davidson	
	 * @version 4/4/2014
	 */
	private class Coordinate {
		
		private int x;
		private int y;
				
		Coordinate(int _x, int _y) {
			x = _x;
			y = _y;
		}
	}
	
	public String toString() {
		String str = "";
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[0].length; j++) {
				if(grid[i][j] != null) {
					str += grid[i][j].getNumber() + "\t";
				} else {
					str += "#\t";
				}
			}
			str += "\n";
		}
		str += "Score: " + GamePlayer.currentScore + "\n";
		return str;
	}
	

	
}
