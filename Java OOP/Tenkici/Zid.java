package igra;

import java.awt.Color;
import java.awt.Graphics;

public class Zid extends Polje {
	
	public Zid(Mreza m)
	{
		super(m,Color.LIGHT_GRAY);
	}
	
	@Override
	public boolean dozvoljeno() {return false;}
}
