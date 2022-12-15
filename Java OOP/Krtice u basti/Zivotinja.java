package igra;
import java.awt.*;
public abstract class Zivotinja {
		protected Rupa rupa;
		protected volatile boolean udarena=false;
		
		public Zivotinja(Rupa r) {rupa=r;}
		
		public abstract void crtaj();
		public abstract void udari();
		public boolean udarena() {return udarena;}
		
}
