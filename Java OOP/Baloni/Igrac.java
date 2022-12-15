package igra;

import java.awt.Color;
import java.awt.Graphics;

public class Igrac extends KruznaFigura {
			public Igrac(Vektor v,double R,Scena s) {super(v,R,Color.GREEN,s,new Vektor(0,0));}
			
			@Override
			public void crtaj(Scena s)
			{
				Graphics g=s.getGraphics();
				g.setColor(Color.GREEN);
				g.fillOval((int)(centar.x()-R/2), (int)(centar.y()-R/2), (int)R, (int)R);
				g.setColor(Color.BLUE);
				g.fillOval((int)(centar.x()-R/4), (int)(centar.y()-R/4), (int)(R/2), (int)(R/2));
			}
			
			@Override
			public void preklopi() {preklopljena=true; scena.zaustavi();}

			public void pomeri_igraca(int dx)
			{
				if(centar.x()+dx<0||centar.x()+dx>scena.getWidth())return;
				centar.saberi(new Vektor(dx,0));
			}
}
