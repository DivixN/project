package project1;

import java.util.ArrayList;

public class ProcessCiphertext extends Decrypt {
	private ArrayList<String> [] before;
	private ArrayList<String> [] after;
	private String separator;
	private int wordsNo;
	private int [] lengthOfEachWord;
	private ArrayList<Integer> [] lengthOfEachChar;
	public ProcessCiphertext(int d,int n,String cipherText){
		super(n,d);
		int counter = 0;
		int lengthOfWord=0;
		int temp= 0;
		super.setMoveStep(Character.getNumericValue(cipherText.charAt(0)));
		counter++;
		wordsNo = Character.getNumericValue(cipherText.charAt(counter))*10+Character.getNumericValue(cipherText.charAt(counter+1));
		counter += 2;
		int lengthOfSeparator = Character.getNumericValue(cipherText.charAt(counter));
		counter ++;
		separator = cipherText.substring(counter, lengthOfSeparator+counter);
		counter += lengthOfSeparator;
		lengthOfEachWord = new int[wordsNo];
		lengthOfEachChar = new ArrayList [wordsNo];
		before = new ArrayList [wordsNo];
		for(int i=0;i<wordsNo;i++){
			lengthOfEachChar[i] = new ArrayList<Integer>();
			before[i] = new ArrayList<String>();
		}
		for(int k=0;k<this.wordsNo;k++){
			lengthOfWord = Character.getNumericValue(cipherText.charAt(counter))*10+Character.getNumericValue(cipherText.charAt(counter+1));
			counter += 2;
			for(int i=0;i<lengthOfWord;i++){
				lengthOfEachChar[k].add(Character.getNumericValue(cipherText.charAt(counter)));
				counter++;
			}
			String temp1 = "";
			//读取所有加密后字母
			for(int i=0;i<lengthOfEachChar[k].size();i++){
				for(int j=0;j<lengthOfEachChar[k].get(i);j++){
					temp1 +=  cipherText.charAt(counter);
					counter++;
				}
				before[k].add(temp1);
				temp1="";
			}
			counter += lengthOfSeparator;
		}
		//for(int i=0;i<before.length;i++)
			//System.out.println(before[i]);
	}
	public String decrypt(){
		this.after = new ArrayList [this.before.length];
		for(int i=0;i<this.before.length;i++){
			after[i] = new ArrayList<String>();
			for(int j=0;j<this.before[i].size();j++)
				after[i].add(this.decryptChar(before[i].get(j)));
		}
		String temp = "";
		for(int i=0;i<this.before.length;i++){
			for(int j=0;j<this.before[i].size();j++)
				temp+=after[i].get(j);
			temp+=" ";
		}
		return temp;
	}
	public static void main(String[] args) { 
		ProcessCiphertext p = new ProcessCiphertext(13859,4757,"50559347208444444442003200320032003200320032003200393472013366934720244190316799347202441241200393472084444444427751903185017841241200316793700");
		System.out.println(p.decrypt());
	}
}
