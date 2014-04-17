package clone2048;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePlayer extends JFrame implements KeyListener{

	private static final long serialVersionUID = -4832686456981813212L;
	
	public BoxController gameBox;
	
	static int currentScore;
	private JLabel scoreLabel;
	
	static int highScore;
	
	private JPanel[][] numberPanels;
	private BorderLayout[][] layoutGrid;
	private JLabel[][] labelGrid;
	
	public static HashMap<Integer, Integer> combinationMap = new HashMap();

	public static void main(String[] args) {

		GamePlayer game = new GamePlayer(4,4);
		game.setVisible(true);

	}

	public GamePlayer(int x, int y) {

		gameBox = new BoxController(x,y);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("2048 Clone");
		setPreferredSize(new Dimension(400,500));
		setLayout(new BorderLayout());
		
		JPanel gridSpace = new JPanel();
		gridSpace.setPreferredSize(new Dimension(200,200));
		Color lightBrown = new Color(228,213,153);
		gridSpace.setBackground(lightBrown);
		add(gridSpace, BorderLayout.CENTER);
		add(new JPanel(), BorderLayout.WEST);
		add(new JPanel(), BorderLayout.EAST);
		add(new JPanel(), BorderLayout.SOUTH);
		
		JPanel topZone = new JPanel();
		topZone.setPreferredSize(new Dimension(1000, 120));
		
		Font numberFont = new Font(Font.SANS_SERIF, 40,40);
		
		scoreLabel = new JLabel("Score: " + currentScore);
		scoreLabel.setFont(numberFont);
		topZone.add(scoreLabel);
		add(topZone, BorderLayout.NORTH);
		
		
		numberPanels = new JPanel[x][y];
		layoutGrid = new BorderLayout[x][y];
		labelGrid = new JLabel[x][y];
		

		
		gridSpace.setLayout(new GridLayout(x,y));
		
		for(int gridY = 0; gridY < x; gridY++) {
			for(int gridX = 0; gridX < y; gridX++) {
				numberPanels[gridY][gridX] = new JPanel();
				layoutGrid[gridY][gridX] = new BorderLayout();
				labelGrid[gridY][gridX] = new JLabel();
				
				numberPanels[gridY][gridX].setLayout(layoutGrid[gridY][gridX]);
				numberPanels[gridY][gridX].add(new JPanel(), BorderLayout.CENTER);
				layoutGrid[gridY][gridX].getLayoutComponent(BorderLayout.CENTER).setBackground(Color.GRAY);
				numberPanels[gridY][gridX].add(new JPanel(), BorderLayout.NORTH);
				layoutGrid[gridY][gridX].getLayoutComponent(BorderLayout.NORTH).setBackground(lightBrown);
				numberPanels[gridY][gridX].add(new JPanel(), BorderLayout.SOUTH);
				layoutGrid[gridY][gridX].getLayoutComponent(BorderLayout.SOUTH).setBackground(lightBrown);
				numberPanels[gridY][gridX].add(new JPanel(), BorderLayout.EAST);
				layoutGrid[gridY][gridX].getLayoutComponent(BorderLayout.EAST).setBackground(lightBrown);
				numberPanels[gridY][gridX].add(new JPanel(), BorderLayout.WEST);
				layoutGrid[gridY][gridX].getLayoutComponent(BorderLayout.WEST).setBackground(lightBrown);
				
				JPanel jp = (JPanel) layoutGrid[gridY][gridX].getLayoutComponent(BorderLayout.CENTER);
				jp.add(	labelGrid[gridY][gridX] );
				labelGrid[gridY][gridX].setFont(numberFont);
				
				if(gameBox.grid[gridY][gridX] != null) {
					jp.setBackground(gameBox.grid[gridY][gridX].getColor());
					labelGrid[gridY][gridX].setText(Integer.toString(gameBox.grid[gridY][gridX].getNumber()));
				}
				gridSpace.add(numberPanels[gridY][gridX]);
			}
		}
		
		addKeyListener(this);
				
		setResizable(false);
		pack();
		
		
		
		

	}

	
	public void refreshDrawnBoxes() {
		
		for(int gridY = 0; gridY < numberPanels[0].length; gridY++) {
			for(int gridX = 0; gridX < numberPanels.length; gridX++) {
				JPanel jp = (JPanel) layoutGrid[gridY][gridX].getLayoutComponent(BorderLayout.CENTER);
				if(gameBox.grid[gridY][gridX] != null) {
					jp.setBackground(gameBox.grid[gridY][gridX].getColor());
					labelGrid[gridY][gridX].setText(Integer.toString(gameBox.grid[gridY][gridX].getNumber()));
				} else {
					layoutGrid[gridY][gridX].getLayoutComponent(BorderLayout.CENTER).setBackground(Color.GRAY);
					labelGrid[gridY][gridX].setText("");
				}
			}
		}
	}
	// Up - 0 - 00; Down - 1 - 01; Left - 2 - 10; Right - 3 - 11; 
	public int findBestMovePoints() {
		int depth = 4;
		
		GamePlayerDataCopy gameCopy = new GamePlayerDataCopy(gameBox);
		int bestHistory = gameCopy.findBestMovePoints(0, depth, 0);
		bestHistory = bestHistory >> (depth*2)-2;
		bestHistory = bestHistory ^ 408;
		switch(bestHistory) {
		case 0:
            gameBox.upMove();
        	refreshDrawnBoxes();
        	scoreLabel.setText("Score: " + currentScore);
			break;
		case 1:
            gameBox.downMove();
        	refreshDrawnBoxes();
        	scoreLabel.setText("Score: " + currentScore);
			break;
		case 2:
            gameBox.leftMove();
        	refreshDrawnBoxes();
        	scoreLabel.setText("Score: " + currentScore);
			break;
		case 3:
            gameBox.rightMove();
        	refreshDrawnBoxes();
        	scoreLabel.setText("Score: " + currentScore);
			break;
		}
		
		
		return 0;
	}
	
	public int findBestMoveComplexity() {
		int depth = 4;
		
		GamePlayerDataCopy gameCopy = new GamePlayerDataCopy(gameBox);
		int bestHistory = gameCopy.findBestMoveComplexity(0, depth, 0);
		bestHistory = bestHistory >> (depth*2)-2;
		bestHistory = bestHistory ^ 408;
		switch(bestHistory) {
		case 0:
            gameBox.upMove();
        	refreshDrawnBoxes();
        	scoreLabel.setText("Score: " + currentScore);
			break;
		case 1:
            gameBox.downMove();
        	refreshDrawnBoxes();
        	scoreLabel.setText("Score: " + currentScore);
			break;
		case 2:
            gameBox.leftMove();
        	refreshDrawnBoxes();
        	scoreLabel.setText("Score: " + currentScore);
			break;
		case 3:
            gameBox.rightMove();
        	refreshDrawnBoxes();
        	scoreLabel.setText("Score: " + currentScore);
			break;
		}
		
		
		return 0;
	}
	
	public int findBestMoveNumberSize() {
		
		return 0;
	}
	

	@Override
	public void keyReleased(KeyEvent e) {
		
		int keyCode = e.getKeyCode();
	    switch( keyCode ) { 
        case KeyEvent.VK_UP:
            gameBox.upMove();
        	refreshDrawnBoxes();
        	scoreLabel.setText("Score: " + currentScore);
            break;
        case KeyEvent.VK_DOWN:
            gameBox.downMove();
        	refreshDrawnBoxes();
        	scoreLabel.setText("Score: " + currentScore);
            break;
        case KeyEvent.VK_LEFT:
            gameBox.leftMove();
        	refreshDrawnBoxes();
        	scoreLabel.setText("Score: " + currentScore);
            break;
        case KeyEvent.VK_RIGHT :
            gameBox.rightMove();
        	refreshDrawnBoxes();
        	scoreLabel.setText("Score: " + currentScore);
            break;
        case KeyEvent.VK_P :
        	findBestMovePoints();
        	break;
        case KeyEvent.VK_C :
        	findBestMoveComplexity();
        	break;
	    }

    }

	
	@Override
	public void keyPressed(KeyEvent e) {

		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
