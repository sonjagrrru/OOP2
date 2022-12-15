package igra;

public abstract class Figura {
		
		protected Polje polje;
		
		public Figura(Polje p) {polje=p;}
		public void pomeri(Polje p) 
		{
			Polje tmp=polje;
			polje=p;
			tmp.repaint();
		}
		public Polje polje() {return polje;}
		
		@Override
		public boolean equals(Object o)
		{
			if(o instanceof Figura) {
				Figura f=(Figura)o;
				if(f.polje==polje)return true;
			}
			return false;
		}
		
		public abstract void crtaj();
}
