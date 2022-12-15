package igra;
import java.awt.*;
public class Krug {
		protected volatile Vektor centar;
		protected double R;
		protected Color boja;
		protected boolean preklopljena=false;
		
		public Krug(Vektor v, double RR,Color c) {centar=v;R=RR;boja=c;}
		
		public static boolean preklapajuSe(Krug k1,Krug k2)
		{
			double rastojanje=Math.sqrt((k1.centar.y()-k2.centar.y())*(k1.centar.y()-k2.centar.y())+
					(k1.centar.x()-k2.centar.x())*(k1.centar.x()-k2.centar.x()));
			if(rastojanje<=(k1.R/2+k2.R/2))return true;
			return false;
		}
		
		public void crtaj(Scena s)
		{
			Graphics g=s.getGraphics();
			g.setColor(boja);
			g.fillOval((int)(centar.x()-R/2), (int)(centar.y()-R/2), (int)R, (int)R);
		}
}
