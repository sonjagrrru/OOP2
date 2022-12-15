package igra;
import java.awt.*;
public class KruznaFigura extends Krug {
			protected Scena scena;
			protected Vektor brzina;
			
			public KruznaFigura(Vektor v,double R,Color c,Scena s,Vektor b) {super(v,R,c);scena=s;brzina=b;}
			public void pomeri(double t)
			{
				centar.saberi(brzina.pomnozi(t));
				if(centar.x()<0||centar.y()<0||centar.x()>scena.getWidth()||centar.y()>scena.getHeight())scena.izbaci(this);
			}
			public void preklopi() {preklopljena=true;}
			
			@Override
			public void crtaj(Scena s)
			{
				Graphics g=s.getGraphics();
				g.setColor(boja);
				g.fillOval((int)(centar.x()-R/2), (int)(centar.y()-R/2), (int)R, (int)R);
			}
}
