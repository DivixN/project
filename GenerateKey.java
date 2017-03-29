package project1;
import java.math.BigInteger;
public class GenerateKey {
	private int d,p,q,n,e,r;
	private int[] prime={2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97};//25 primes
	public GenerateKey(int a){
		p = this.generatePrime(a);
		do{
			q = this.generatePrime(a);
		}while(q==p);
		n=p*q;
		r=(p-1)*(q-1);
		e=r-1;//get a co-prime and it is less than r 
		int temp = r*((int)(Math.random()*5)+2);
		do{
			d=(int)(Math.random()*(temp+2))+2;//get a random number
		}while(!this.isInverse(d)||d==e);
	}
	public GenerateKey(){
		p = prime[(int)(Math.random()*26)];
		System.out.println("p = " + p);
		do{
			q=prime[(int)(Math.random()*26)];
			System.out.println("q=" + q);
		}while(q==p);
		n=p*q;
		r=(p-1)*(q-1);
		e=r-1;
		d=e;
	}
	public int getD(){return d;}
	public int getE(){return e;}
	public int getN(){return n;}
	public boolean isPrime(int a){
		if(a==0)
			return false;
		if(a==1)
			return false;
		if(a==2)
			return true;
		for(int i=2;i<a;i++)
			if(a%i==0)
				return false;
		return true;
	}
	public int generatePrime(int a){
		int b = 1;
		//System.out.println("GP now");
		while(!isPrime(b))
			b=(int)(Math.random()*a);
		return b;
	}
	public boolean isInverse(int a){
		//System.out.println("is inverse now");
		return (e*a-1)%r==0;
	}
    public static void main(String[] args) {  
    	GenerateKey a = new GenerateKey(100);
	   	System.out.println(a.getD());
	   	System.out.println(a.getE());
	   	System.out.println(a.getN());
    }
    //extra main method to do
}




