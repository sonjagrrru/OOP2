package igra;

import java.awt.*;
import java.awt.event.*;

public class Basta extends Panel implements Runnable{
			private Rupa[][]rupe;
			private volatile int povrce=100;
			private int dt;
			private volatile int korak;
			private boolean radi=false;
			private Thread nit=new Thread(this);
			private Label labela=new Label(Integer.toString(povrce));
			
			public Basta(int m,int n)
			{
				rupe=new Rupa[m][n];
				setLayout(new GridLayout(m,n,10,10));
				for(int i=0;i<m;i++)
					for(int j=0;j<n;j++)
						{
							rupe[i][j]=new Rupa(this);
							add(rupe[i][j]);
							rupe[i][j].addMouseListener(new MouseAdapter() {
								public void mouseClicked(MouseEvent e)
								{
									Rupa r=(Rupa)e.getSource();
									r.udari();
								}
							});
						}
				setBackground(Color.GREEN);
				labela.setFont(new Font(null,Font.BOLD,20));
			}
			
			public synchronized void postavi_korak(int n) 
			{
				korak=n;
				for(int i=0;i<rupe.length;i++)
					for(int j=0;j<rupe[0].length;j++)
						rupe[i][j].postavi(n);
				notify();
			}
			public int korak() {return korak;}
			public void smanji_povrce() {if(povrce>0)povrce--;else nit.interrupt();}
			
			@Override
			public void run()
			{
				try {
					while(!Thread.interrupted())
					{
						if(povrce!=0) {
						Rupa r;
						while(!(r=rupe[(int)(Math.random()*rupe.length)][(int)(Math.random()*rupe[0].length)]).slobodna());
						r.stvori();
						r.postavi(new Krtica(r));
						Thread.sleep(dt);
						r.kreni();
						dt=(int)(0.99*dt);
						labela.setText(Integer.toString(povrce));
					}}
				}
				catch(InterruptedException e) {}
			}
			
			public void kreni() 
			{
				if(nit!=null)nit.interrupt();
				nit=new Thread(this);
				povrce=100;
				nit.start();
			}
			public void zaustavi() 
			{
				if(nit!=null)
					{
						nit.interrupt();
						for(int i=0;i<rupe.length;i++)
							for(int j=0;j<rupe[0].length;j++)
								rupe[i][j].zavrsi();
					}
			}
			public void postavi_dt(int t) {dt=t;}
			public Label labela() {return labela;}
			
			
}
