package igra;

import java.awt.*;
import java.awt.event.*;

public class Scena extends Canvas implements Runnable {
			private Thread nit;
			private Elem prvi=null,poslednji=null;
			private Igra igra;
			private class Elem
			{
				KruznaFigura k;
				Elem sledeci,prethodni;
				Elem(KruznaFigura kk)
				{
					k=kk;
					if(prvi==null)prvi=this;
					else poslednji.sledeci=this;
					prethodni=poslednji;sledeci=null;
					poslednji=this;
				}
			}
			public Scena(Igra i)
			{
				igra=i;
				new Elem(new Igrac(new Vektor(igra.getWidth()/2,igra.getHeight()-60),30,this));	
				setBackground(Color.WHITE);
				setFocusable(true);
				addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent e)
					{
						if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				            ((Igrac)prvi.k).pomeri_igraca(20);
						}
						if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				            ((Igrac)prvi.k).pomeri_igraca(-20);
						}
					}
				});
			}
			
			
			public void kreni() {if(nit!=null)nit.interrupt();nit=new Thread(this);nit.start();}
			public void zaustavi() {if(nit!=null)nit.interrupt();}
			
			public void run()
			{
				try {
					while(!Thread.interrupted())
					{
						Thread.sleep(60);
						double r=Math.random();
						if(r<0.1) {
						double x=-10+Math.random()*20;
						double y=Math.random()*20;
						new Elem(new Balon(new Vektor(Math.random()*getWidth(),10),20,this,new Vektor(x,y)));}
						proveri_preklapanje();
						pomeri();
						repaint();
					}
				}
				catch(InterruptedException e) {}
			}
			
			private void proveri_preklapanje() 
			{
				for(Elem tmp=prvi;tmp!=null;tmp=tmp.sledeci)
					for(Elem tmp2=tmp.sledeci;tmp2!=null;tmp2=tmp2.sledeci)
					{
						if(Krug.preklapajuSe(tmp.k, tmp2.k))
						{
							tmp.k.preklopi();
							tmp2.k.preklopi();
						}
					}
			}
			
			private void pomeri()
			{
				for(Elem tmp=prvi.sledeci;tmp!=null;tmp=tmp.sledeci)
					tmp.k.pomeri(0.6);
			}
			
			@Override
			public void paint(Graphics g)
			{
				for(Elem tmp=prvi;tmp!=null;tmp=tmp.sledeci)
					tmp.k.crtaj(this);					
			}
			
			public void izbaci(KruznaFigura f)
			{
				if(poslednji.k==f) {poslednji=poslednji.prethodni;poslednji.sledeci=null;}
				else
				{
					Elem tmp=prvi.sledeci;
					for(;tmp!=null;tmp=tmp.sledeci)
						if(tmp.k==f)break;
					if(tmp!=null)
					{
						tmp.prethodni.sledeci=tmp.sledeci;
						tmp.sledeci.prethodni=tmp.prethodni;
					}
				}
				
			}
}
