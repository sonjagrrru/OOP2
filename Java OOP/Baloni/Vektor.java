package igra;

public class Vektor {
		private double x,y;
		
		public Vektor(double xx,double yy) {x=xx;y=yy;}
		public Vektor pomnozi(double a) {return new Vektor(a*x,a*y);}
		public Vektor saberi(Vektor v) {x+=v.x;y+=v.y;return this;}
		public double x() {return x;}
		public double y() {return y;}
		
		@Override
		public Vektor clone()
		{
			try {
				return (Vektor)super.clone();
			}
			catch(CloneNotSupportedException e) {return null;}
		}
}
