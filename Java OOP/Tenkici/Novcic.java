package igra;

import java.awt.*;
public class Novcic extends Figura {

	public Novcic(Polje p) {super(p);}
	
	@Override
	public void crtaj()
	{
		Graphics g=polje.getGraphics();
		g.setColor(Color.YELLOW);
		g.fillOval((int)(polje.getWidth()/4), (int)(polje.getHeight()/4), (int)(polje.getWidth()/2), (int)(polje.getHeight()/2));
	}
}
