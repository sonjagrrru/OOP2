package igra;
import java.awt.*;
public class Trava extends Polje {
	private Figura figura=null;
	public Trava(Mreza m)
	{
		super(m,Color.GREEN);
	}
	
	@Override
	public boolean dozvoljeno() {return true;}
	public void namesti(Figura f) {figura=f;}
	public Figura figura() {return figura;}
	
}
