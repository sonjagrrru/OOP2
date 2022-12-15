package igra;

import java.awt.*;

public class Krtica extends Zivotinja {
			
	public Krtica(Rupa r) {super(r);}
	
	@Override
	public synchronized void crtaj()
	{
		Graphics g=rupa.getGraphics();
		g.setColor(Color.DARK_GRAY);
		int x=(int)(rupa.getWidth()/2/rupa.ukupni()*(rupa.ukupni()-rupa.trenutni()));
		int y=(int)(rupa.getHeight()/2/rupa.ukupni()*(rupa.ukupni()-rupa.trenutni()));
		int debljina=(int)(rupa.getWidth()/rupa.ukupni()*rupa.trenutni());
		int visina=(int)(rupa.getHeight()/rupa.ukupni()*rupa.trenutni());
		g.fillOval(x, y, debljina, visina);
	}
	
	@Override
	public synchronized void udari() {udarena=true;rupa.zavrsi();}
}
