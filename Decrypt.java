package project1;

import java.math.BigInteger;

public class Decrypt {
	private static int n;
	private static int d;
	private int moveStep;
	public Decrypt(){}
	public Decrypt(int ms){this.moveStep = ms;}
	public Decrypt(int n,int d){
		this.n = n;
		this.d = d;
	}
	public static int getN(){return n;}
	public static int getD(){return d;}
	public int getMoveStep(){return moveStep;}
	public void setMoveStep(int ms){this.moveStep = ms;}
	public String decryptChar(String s){
		int a = Integer.parseInt(s);//转换成int
		int temp;
		BigInteger k,x;
		x = new BigInteger(String.valueOf(n));
		k = BigInteger.valueOf(a).pow(d).mod(x);//密文为a 解密后为k
		temp = k.intValue()-this.getMoveStep();
		return getString(temp);
	}
    private static String getString(int i){//0 is a 
		return String.valueOf((char) ('a' + i));
	}	
	private static int getInt(char i){//a is 0
		return (i - 'a');
	}
}
