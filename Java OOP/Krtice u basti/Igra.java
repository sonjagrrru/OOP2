package igra;

import java.awt.*;
import java.awt.event.*;

public class Igra extends Frame implements ItemListener{
	private Basta basta=new Basta(4,4);
	private Checkbox []cb=new Checkbox[3];
	private static final String[]niz= {"Lako","Srednje","Tesko"};
	
	public Igra()
	{
		setSize(400,300);
		setLocation(500,250);
		popuni_prozor();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {basta.zaustavi();dispose();}
		});
		setVisible(true);
	}
	
	private void popuni_prozor()
	{
		add(basta,"Center");
		Panel p=new Panel(new GridLayout(6,1));
		add(p,"East");
		Label lab=new Label("Tezina:");
		lab.setFont(new Font(null,Font.BOLD,20));
		p.add(lab);
		CheckboxGroup cg=new CheckboxGroup();
		for(int i=0;i<cb.length;i++)
		{
			cb[i]=new Checkbox(niz[i],cg,i==0);
			p.add(cb[i]);
			cb[i].addItemListener(this);
		}
		Button b=new Button("Kreni");
		p.add(b);
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				switch(((Button)e.getSource()).getLabel())
				{
				case "Kreni":basta.kreni();
							b.setLabel("Stani");
							for(int i=0;i<cb.length;i++)
							{
								cb[i].setEnabled(false);
							}break;
				case "Stani": b.setLabel("Kreni");
							  basta.zaustavi();
							  for(int i=0;i<cb.length;i++)
								{
									cb[i].setEnabled(true);
								}break;
				}
			}
		});
		Label labela=basta.labela();
		p.add(labela);
		
	}
	
	@Override
	public void itemStateChanged(ItemEvent e)
	{
		switch(((Checkbox)(e.getSource())).getLabel())
		{
		case "Lako":basta.postavi_korak(10);
					basta.postavi_dt(1000);break;
		case "Srednje":basta.postavi_dt(750);
						basta.postavi_korak(8);break;
		case "Tesko": basta.postavi_dt(500);
					basta.postavi_korak(6);break;
		}
	}
	
	public static void main(String[] args) {
		new Igra();
	}

}
