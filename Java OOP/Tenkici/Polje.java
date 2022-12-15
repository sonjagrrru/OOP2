package igra;

import java.awt.*;

public abstract class Polje extends Canvas {
		private Mreza mreza;
		protected Color boja;
		
		public Polje(Mreza m) {this(m,Color.YELLOW);}
		public Polje(Mreza m,Color c) {mreza=m;boja=c;}
		public Mreza mreza() {return mreza;}
		public int[]pozicija(){return mreza.pozicija(this);}
		public abstract boolean dozvoljeno();
		public void paint(Graphics g)
		{
			g.setColor(boja);
			g.fillRect(0, 0, getWidth()-1, getHeight()-1);
		}
		public Polje pomereno(int m,int n)
		{
			int []niz=pozicija();
			if(niz!=null)
				return mreza.polje(niz[0]+m,niz[1]+n);
			return null;
		}
		
		@Override
		public final String toString()
		{
			if(pozicija()!=null)
				return pozicija()[0]+" "+pozicija()[1];
			return "";
		}
}
