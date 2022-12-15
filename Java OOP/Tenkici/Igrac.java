package igra;
import java.awt.*;

public class Igrac extends Figura {
		
		public Igrac(Polje p) {super(p);}
		
		@Override
		public void crtaj()
		{
			Graphics g=polje.getGraphics();
			g.setColor(Color.RED);
			g.drawLine((int)(polje.getWidth()/2), 0, (int)(polje.getWidth()/2), (int)polje.getHeight());
			g.drawLine(0,(int)(polje.getHeight()/2), (int)polje.getWidth(), (int)(polje.getHeight()/2));
		}
}
