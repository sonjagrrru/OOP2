package igra;

import java.awt.*;
import java.awt.event.*;

public class Igra extends Frame {
			private Scena s; 
			public Igra()
			{
				setSize(500,400);
				setLocation(500,250);
				setResizable(false);
				s=new Scena(this);
				s.kreni();
				add(s,"Center");
				addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e)
					{
						s.zaustavi();dispose();
					}
				});
				setVisible(true);
			}
			
			public static void main(String[]args)
			{
				new Igra();
			}
}
