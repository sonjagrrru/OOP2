package igra;
import java.awt.*;
public class Tenk extends Figura implements Runnable {
	
	private Thread nit;
	
	public Tenk(Polje p) {super(p);}
	
	@Override
	public void crtaj()
	{
		Graphics g=polje.getGraphics();
		g.setColor(Color.BLACK);
		g.drawLine(0, 0, (int)polje.getWidth(), (int)polje.getHeight());
		g.drawLine((int)polje.getWidth(),0, 0, (int)polje.getHeight());
	}
	
	public void kreni() {if(nit!=null)nit.interrupt();nit=new Thread(this);nit.start();}
	public void zaustavi(){if(nit!=null)nit.interrupt();}
	
	@Override
	public void run()
	{
		try {
			while(!Thread.interrupted())
			{
				Thread.sleep(500);
				Polje []niz= {polje.pomereno(0, 1),polje.pomereno(1, 0),polje.pomereno(-1, 0),polje.pomereno(0, -1)};
				Polje p=null;
				while(p==null||p instanceof Zid) {p=niz[(int)(Math.random()*4)];}
				Polje tmp=polje;
				polje=p;
				crtaj();
				tmp.repaint();
			}
		}
		catch(InterruptedException e) {}
	}
}
