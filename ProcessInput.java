package project1;
import java.math.BigInteger;
import java.util.ArrayList;

public class ProcessInput extends Encrypt {
	private String [] before;
	private String [] after;
	private int wordNo;
	private int [] lengthOfEachWord;
	private ArrayList<Integer> [] lengthOfEachChar;
	private String separator;
	public ProcessInput(int e,int n,int ms,String input){
		super(n,e,ms);
		this.wordNo = getNoOfWords(input);
		String b[] = new String[this.wordNo];
		String a[] = new String[this.wordNo];
		int loew[] = new int[this.wordNo];
		for(int i=0;i<this.wordNo;i++){
			b[i] = getWord(input,i+1);
			loew[i] = b[i].length();
			a[i] = encrypt(b[i]);
		}
		this.before = b;
		this.after = a;
		this.lengthOfEachWord = loew;
		ArrayList<Integer> []loec = new ArrayList [this.wordNo];
		for(int i=0;i<this.wordNo;i++)
			loec[i] = new ArrayList<Integer>();
		for(int i=0;i<this.wordNo;i++)
			for(int j=0;j<before[i].length();j++)
				loec[i].add(encryptChar(before[i].charAt(j)).length());
		this.lengthOfEachChar = loec;
		this.separator = this.generateSeparator(5);
	}
	public String[] getBefore(){return before;}
	public String getBefore(int i){return before[i];}
	public String [] getAfter(){return after;}
	public String  getAfter(int i){return after[i];}
    public int [] getloew(){return this.lengthOfEachWord;}
    public int getMaxLength(){return takeMaxLength(before);}
    public int getLoec(int i,int j){return this.lengthOfEachChar[i].get(j);}
    public int gerLengthOfLoec(int i){return this.lengthOfEachChar[i].size();}
    public ArrayList<Integer> getLoec(int i){return this.lengthOfEachChar[i];}
    public int getLoecLength(){return this.lengthOfEachChar.length;}
    public String getSeparator(){return this.separator;}
	public static void main(String[] args) { 
    	ProcessInput p = new ProcessInput(879,979,2,"oooooooo i am so handsome");
    	System.out.println(p.getSeparator());
    	for(int i = 0;i<p.getBefore().length;i++)
    		System.out.println(p.getBefore(i) + " ");
    	for(int i =0;i<p.getAfter().length;i++)
    		System.out.println(p.getAfter(i) + "!!!");
    	System.out.println(p.getloew().length);
    	System.out.println(p.getMaxLength());
    	for(int i=0;i<p.getLoecLength();i++)
    		for(int j=0;j<p.getLoec(i).size();j++)
    			System.out.print(p.getLoec(i, j) + " ");
    	System.out.println(p.encrypt());
    	System.out.println(p.encryptChar('a'));
    }
    public  String generateSeparator(int length){
    	String s = "";
    	//do{
		   	for(int i = 0;i<length;i++)
		   		s+=(int)(Math.random()*9)+1;
		   	//System.out.println("!!");
    	//}while(!checkSeparator(s,Encrypt.getE(),Encrypt.getN(),super.getMoveStep()));
	   	return s;
	}
    public static boolean checkSeparator(String s,int e, int n,int moveStep){
    	BigInteger a;
    	for(int i = moveStep;i < moveStep+26;i++){
    		a = BigInteger.valueOf(i).pow(e);//i µÄ  e ´Î·½
    		String temp = a.toString();
    		if(s.contains(temp)||temp.contains(s))
    			return false;
    	}
    	return true;
    }
	public String encrypt(){
		String s = "";
		s+=super.getMoveStep();
		if(this.wordNo<10)
			s+="0"+this.wordNo;
		else
			s+=this.wordNo;
		s+=this.separator.length();
		for(int i=0;i<this.wordNo;i++){
			s+=this.separator;
			if(this.lengthOfEachWord[i]<10)
				s+="0" + this.lengthOfEachWord[i];
			else
				s+= this.lengthOfEachWord[i];
			for(int j=0;j<this.lengthOfEachChar[i].size();j++)
				s+= this.lengthOfEachChar[i].get(j);
			s+=this.after[i];
		}
		return s;
	}
	public static int takeMaxLength(String[]a){
		int max = a[0].length();
		for(int i=1;i<a.length;i++)
			if(a[i].length()>max)
				max = a[i].length();
		return max;
	}
	public static String getWord(String a,int i){//will return No i word
		int counter = 1;
		String result = "";
		for(int j = 0;j<a.length();j++){
			if(a.charAt(j) == ' ')
				counter++;
			else if(counter == i)
				result += a.charAt(j);
		}
		return result;
	}
	public static int getNoOfWords(String s){//will return no of the words
		int counter = 1;
		for(int i=0;i<s.length();i++)
			if(s.charAt(i)==' ')
				counter++;
		return counter;
	}
}
