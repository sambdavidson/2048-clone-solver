package clone2048;

import java.awt.Color;

public class NumberBox {

	private int number;
	private Color color;
	private boolean isCopy;
	
	public NumberBox(boolean _isCopy) {
		isCopy = _isCopy;
		
		number = 2;
		if(System.nanoTime() % 2 == 0)
			number = 4;
		
		recalculateColor();
	}
	public NumberBox(int _number) {
		
		isCopy = true;
		
		number = _number;
		
		recalculateColor();
	}
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int newNum) {
		number = newNum;
	}
	public void doubleNumber() {
		number = number * 2;
		if(!isCopy)
			GamePlayer.currentScore += number;
		recalculateColor();
	}
	public Color getColor() {
		return color;
	}
	private void recalculateColor() {

		if(number <= 128)
			color = new Color(Color.HSBtoRGB((float)((log(number, 2))%7)/7.0f, 0.5f, 1.0f));
		else
			switch(number) {
			case 256: 
				color = Color.GRAY;
				break;
			case 512:
				color = Color.LIGHT_GRAY;
				break;
			case 1024:
				color = Color.DARK_GRAY;
				break;
			case 2048:
				color = Color.WHITE;
				break;
			default:
				color = Color.PINK;
			}
			
		
	}
	private int log(int x, int base)
	{
	    return (int) (Math.log(x) / Math.log(base));
	}
	

	
}
