package project1;

import java.math.BigInteger;

public class Encrypt {
	private static int n;
	private static int e;
	private int moveStep;
	public Encrypt(){}
	public Encrypt(int ms){this.moveStep = ms;}
	public Encrypt(int n,int e,int ms){
		this.n = n;
		this.e = e;
		this.moveStep = ms;
	}
	public static int getN(){return n;}
	public static int getE(){return e;}
	public int getMoveStep(){return moveStep;}
	public void setMoveStep(int ms){this.moveStep = ms;}
	public String encrypt(String a){
		String s = "";
		char[] temp = a.toCharArray();
		for(int i=0;i<temp.length;i++)
			s+=encryptChar(temp[i]);
		return s;
	}
	public String encryptChar(char c){
		String a = "";
		int temp;
		BigInteger k,x;
		x = new BigInteger(String.valueOf(n));
		temp = getInt(c)+moveStep;
		k = BigInteger.valueOf(temp).pow(e).mod(x);//原文为temp 加密后为k
		a += k;
		return a;
	}
    private static String getString(int i){//0 is a 
		return String.valueOf((char) ('a' + i));
	}	
	private static int getInt(char i){//a is 0
		return (i - 'a');
	}
}
