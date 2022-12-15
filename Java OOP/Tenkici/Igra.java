package igra;

import java.awt.*;
import java.awt.event.*;

public class Igra extends Frame {
		private boolean izmena=false;
		private String podloga="trava";
		private Mreza mreza=new Mreza(this);
		private TextField tf=new TextField("17");
		private Label labela=new Label("0");
		private Checkbox trav,zi;
		
		public boolean izmena() {return izmena;}
		public String podloga() {return podloga;}
		public Label labela() {return labela;}
		
		public Igra()
		{
			super("Igra");
			setSize(500,400);
			setLocation(500,250);
			setResizable(true);
			popuni();
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e)
				{
					mreza.zavrsi();dispose();
				}
			});
			setVisible(true);
		}
		
		private void popuni()
		{
			Button b=new Button("Pocni");
			MenuBar mb=new MenuBar();
			Menu meni=new Menu("Rezim");
			mb.add(meni);
			MenuItem mi=new MenuItem("Rezim izmena");
			meni.add(mi);
			mi.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e)
				{
					mreza.zavrsi();
					mreza.crtaj_polje();
					b.setEnabled(false);
					izmena=true;
				}
			});
			
			mi=new MenuItem("Rezim igranje");
			meni.add(mi);
			mi.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e)
				{
					mreza.zavrsi();
					b.setEnabled(true);
					izmena=false;
				}
			});
			b.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e)
				{
					mreza.zavrsi();
					mreza.crtaj_polje();
					mreza.inicijalizacija(Integer.parseInt(tf.getText()));
					mreza.requestFocusInWindow();
					mreza.kreni();
				}
			});
			setMenuBar(mb);
			Panel p=new Panel();
			CheckboxGroup cg=new CheckboxGroup();
			trav=new Checkbox("Trava",cg,true);
			zi=new Checkbox("Zid",cg,false);
			trav.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e)
				{
					podloga="trava";
				}
			});
			zi.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e)
				{
					podloga="zid";
				}
			});
			Panel pomocni=new Panel(new GridLayout(2,1));
			Panel p1=new Panel();
			Panel p2=new Panel();
			p1.setBackground(Color.GREEN);
			p1.add(trav);
			p2.setBackground(Color.LIGHT_GRAY);
			p2.add(zi);
			pomocni.add(p1);
			pomocni.add(p2);
			trav.setBackground(Color.GREEN);
			zi.setBackground(Color.LIGHT_GRAY);
			add(mreza,"Center");
			p.add(new Label("Podloga"));
			p.add(pomocni);
			add(p,"East");
			p=new Panel();
			p.add(new Label("Novcica: "));
			p.add(tf);
			p.add(new Label("Poena: "));
			p.add(labela);
			p.add(b);
			add(p,"South");
		}
		
	public static void main(String[] args) {
		new Igra();
		}

}
