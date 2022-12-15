package igra;

import java.awt.*;
import java.awt.event.*;

public class Mreza extends Panel implements Runnable{
			private Polje [][]matrica;
			private int m=17;
			private Igra igra;
			private int broj_novcica=0,ukupno;
			private Thread nit;
			private Igrac igrac;
			
			public Mreza(Igra i, int mm) 
			{
				matrica=new Polje[mm][mm];
				m=mm;
				igra=i;
				setLayout(new GridLayout(m,m));
				setFocusable(true);
				requestFocusInWindow();
				addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent e)
					{
						if(!igra.izmena()&&igrac!=null) {
						if(e.getKeyCode()==KeyEvent.VK_A)
						{
							Polje p=igrac.polje().pomereno(0, -1);
							if(p!=null)
								if(!(p instanceof Zid))
									igrac.pomeri(p);
						}
						if(e.getKeyCode()==KeyEvent.VK_D)
						{
							Polje p=igrac.polje().pomereno(0, 1);
							if(p!=null)
								if(!(p instanceof Zid))
									igrac.pomeri(p);
						}
						if(e.getKeyCode()==KeyEvent.VK_W)
						{
							Polje p=igrac.polje().pomereno(-1, 0);
							if(p!=null)
								if(!(p instanceof Zid))
										igrac.pomeri(p);
						}
						if(e.getKeyCode()==KeyEvent.VK_S)
						{
							Polje p=igrac.polje().pomereno(1, 0);
							if(p!=null)
								if(!(p instanceof Zid))
									igrac.pomeri(p);
						}
					}
					}
				});
				int broj=(int)(m*m*0.8);
				Polje p=null;
				for(int j=0;j<broj;j++)
				{
					int s=0;
					int k=0;
					while(true)
					{
						s=(int)(Math.random()*m);
						k=(int)(Math.random()*m);
						p=matrica[s][k];
						if(p==null)break;
					}
					matrica[s][k]=new Trava(this);
				}
				for(int j=0;j<m;j++)
					for(int k=0;k<m;k++)
					{
						if(matrica[j][k]==null) {matrica[j][k]=new Zid(this);}
						add(matrica[j][k]);
						matrica[j][k].addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e)
							{
								if(igra.izmena())
								{
									Polje p=(Polje)e.getSource();
									int []niz=p.pozicija();
									if(niz!=null) {
									switch(igra.podloga()) {
									case "trava":
										Mreza.this.remove(matrica[niz[0]][niz[1]]);
										matrica[niz[0]][niz[1]]=new Trava(Mreza.this);
										Mreza.this.add(matrica[niz[0]][niz[1]],niz[0]*m+niz[1]);
										Mreza.this.validate();
										break;
									case "zid":
										Mreza.this.remove(matrica[niz[0]][niz[1]]);
										matrica[niz[0]][niz[1]]=new Zid(Mreza.this);
										Mreza.this.add(matrica[niz[0]][niz[1]],niz[0]*m+niz[1]);
										Mreza.this.validate();
										break;
									}
								}
								}
							}
						});
					}
				
			}
			public Mreza(Igra i) {this(i,17);}
			private void dodaj()
			{
				for(int s=0;s<m;s++)
					for(int k=0;k<m;k++)
					{
						add(matrica[s][k]);
					}
			}
			
			public int[] pozicija(Polje p)
			{
				int []niz=new int[2];
				for(int i =0;i<m;i++)
				{
					for(int j=0;j<m;j++)
					{
						if(p==matrica[i][j]) 
						{
							niz[0]=i;
							niz[1]=j;
							return niz;
						}
					}
				}
				return null;
			}
			public int m() {return m;}
			public Polje polje(int mm, int nn)
			{
				if(mm<0||mm>=m||nn<0|nn>=m)return null;
				return matrica[mm][nn];
			}
			
			private Nov prvi,poslednji;
			private class Nov
			{
				Novcic novcic;
				Nov sledeci,prethodni;
				public Nov(Novcic n)
				{
					novcic=n;
					if(prvi==null)prvi=this;
					else poslednji.sledeci=this;
					prethodni=poslednji;
					poslednji=this;
				}
				public void izbaci()
				{
					if(prvi==this)
						{
							prvi=sledeci;
							if(prvi!=null)prvi.prethodni=null;
							else poslednji=null;
						}
					else if(poslednji==this)
					{
						poslednji=prethodni;
						if(poslednji!=null)poslednji.sledeci=null;
						else prvi=null;
					}
					else {
						prethodni.sledeci=sledeci;
						sledeci.prethodni=prethodni;
					}
					
				}
			}
			
			private Tnk first,last;
			private class Tnk
			{
				Tenk tenk;
				Tnk sledeci,prethodni;
				public Tnk(Tenk n)
				{
					tenk=n;
					if(first==null)first=this;
					else last.sledeci=this;
					prethodni=last;
					last=this;
				}
				public void izbaci()
				{
					if(first==this)
						{
							first=sledeci;
							if(first!=null)first.prethodni=null;
							else last=null;
						}
					else if(last==this)
					{
						last=prethodni;
						if(last!=null)last.sledeci=null;
						else first=null;
					}
					else {
						prethodni.sledeci=sledeci;
						sledeci.prethodni=prethodni;
					}
					
				}
			}
			
			public void inicijalizacija(int n)
			{
				if(!igra.izmena())
				{
					ukupno=n;
					for(int i=0;i<n;i++)
					{
						Polje p=null;
						while(true)
						{
							p=matrica[(int)(Math.random()*m)][(int)(Math.random()*m)];
							if(p instanceof Trava)
							{
								if(((Trava)p).figura()==null)break;
							}
						}
						Novcic nov=new Novcic(p);
						((Trava)p).namesti(nov);
						new Nov(nov);
					}
					int k=(int)(0.33*n);
					for(int i=0;i<k;i++)
					{
						Polje p=null;
						while(true)
						{
							p=matrica[(int)(Math.random()*m)][(int)(Math.random()*m)];
							if(p instanceof Trava)break;
						}
						Tenk tnk=new Tenk(p);
						new Tnk(tnk);
						tnk.kreni();
					}
					Polje p=null;
					while(true)
					{
						p=matrica[(int)(Math.random()*m)][(int)(Math.random()*m)];
						if(p instanceof Trava)break;
					}
					igrac=new Igrac(p);
				}
			}
			
			@Override
			public void run()
			{
				try {
					while(!Thread.interrupted())
					{
						Thread.sleep(40);
						crtaj();
						proveri();
						obracunaj();
						igra.labela().setText(Integer.toString(broj_novcica));
					}
				}
				catch(InterruptedException e) {}
			}
			
			private void crtaj()
			{
				for(Nov tmp=prvi;tmp!=null;tmp=tmp.sledeci)tmp.novcic.crtaj();
				for(Tnk tmp=first;tmp!=null;tmp=tmp.sledeci)tmp.tenk.crtaj();
				if(igrac !=null)igrac.crtaj();
			}
			
			private void obracunaj()
			{
				for(Nov tmp=prvi;tmp!=null;tmp=tmp.sledeci)
					if(tmp.novcic.polje()==igrac.polje()) 
					{
						broj_novcica++;
						tmp.izbaci();
						if(broj_novcica==ukupno)zavrsi();
					}
			}
			
			public void kreni() {if(nit!=null)nit.interrupt();nit=new Thread(this);nit.start();broj_novcica=0;}
			public synchronized void zavrsi() 
			{
				if(nit!=null)nit.interrupt();
				for(Tnk tmp=first;tmp!=null;tmp=tmp.sledeci) {tmp.tenk.zaustavi();tmp.izbaci();}
				for(Nov tmp=prvi;tmp!=null;tmp=tmp.sledeci)tmp.izbaci();
				igrac=null;
				crtaj();
			}
			private void proveri()
			{
				for(Tnk tmp=first;tmp!=null;tmp=tmp.sledeci)
						if(tmp.tenk.polje()==igrac.polje()) 
						{
							zavrsi();
						}
					
				
			}
		
			public void crtaj_polje()
			{
				for(int i =0;i<m;i++)
				{
					for(int j=0;j<m;j++)
					{
						matrica[i][j].repaint();
					}
				}
			}
}
