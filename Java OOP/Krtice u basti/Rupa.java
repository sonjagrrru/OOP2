package igra;

import java.awt.*;
import java.awt.event.*;

public class Rupa extends Canvas implements Runnable{
			private Basta basta;
			private volatile Zivotinja zivotinja;
			private volatile int trenutni,ukupni;
			private Thread nit;
			private boolean radi=false;
			private boolean slobodna=true;
			
			public Rupa(Basta b)
			{
				basta=b;
				setBackground(new Color(160,82,45));
			}
			
			public int trenutni() {return trenutni;}
			public int ukupni() {return ukupni;}
			public synchronized void postavi(int n) {ukupni=n;notify();}
			public synchronized void postavi(Zivotinja z) {zivotinja=z;slobodna=false;notify();}
			public boolean radi() {return radi;}
			public Zivotinja zivotinja() {return zivotinja;}
			public synchronized void udari() {if(zivotinja!=null)zivotinja.udari(); }
			
			@Override
			public void run()
			{
				try {
					while(!Thread.interrupted()) {
						synchronized(this) {
							while(zivotinja==null&&ukupni==0)wait();}
						repaint();
						if(zivotinja!=null) {
						if(trenutni!=ukupni+1&&!zivotinja.udarena())
						{
							Thread.sleep(100);
							trenutni++;
						}
						else {
							Thread.sleep(2000);
							zavrsi();
						}
					}}
				}
				catch(InterruptedException e) {}
			}
			
			public synchronized void zavrsi()
			{
				if(nit!=null)nit.interrupt();
				if(zivotinja!=null) 
				{
					if(!zivotinja.udarena())basta.smanji_povrce();
				}
				zivotinja=null;
				trenutni=0;
				repaint();
				radi=false;
				slobodna=true;
			}
			public void stvori() {if(nit!=null)nit.interrupt();nit=new Thread(this);radi=false;}
			public void kreni() {nit.start();radi=true;trenutni=0;}
			public boolean slobodna() {return slobodna;}
			
			@Override
			public void paint(Graphics g)
			{
				if(trenutni==ukupni+1) {
					g.setColor(new Color(160,82,45));
					g.fillRect(0, 0, getWidth()-1,getHeight()-1);
				}
				else if(zivotinja!=null&&ukupni!=0)zivotinja.crtaj();
				else {
					g.setColor(new Color(160,82,45));
					g.fillRect(0, 0, getWidth()-1,getHeight()-1);
				}
			}
			
			
			
}
