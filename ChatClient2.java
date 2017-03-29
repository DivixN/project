package project1;
import java.io.DataInputStream;  
import java.io.DataOutputStream;  
import java.io.IOException;  
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;  
public class ChatClient2 { 
	private String host = "localhost";// 默认连接到本机  
	private int port = 8189;// 默认连接到端口8189    
	public ChatClient2() {}  
	public ChatClient2(String host, int port) {  
		this.host = host;
		this.port = port;
	}
	public void chat() throws UnknownHostException, IOException {
		Socket socket = new Socket(host, port);  
		DataInputStream in = new DataInputStream(socket.getInputStream());  
		DataOutputStream out = new DataOutputStream(socket.getOutputStream());  
		Scanner scanner = new Scanner(System.in);  
		while (true){
			out.writeUTF("!");
    		int e=Integer.parseInt(in.readUTF());
    		int n=Integer.parseInt(in.readUTF());
    		int ms = (int)(Math.random()*9+1);
    		String send = scanner.nextLine();
    		ProcessInput pi = new ProcessInput(e,n,ms,send);
    		String s=pi.encrypt();
    		//System.out.println(s);
    		System.out.println("Clinet：" + send);
    		out.writeUTF(s);
    		//generate the key
    		GenerateKey a = new GenerateKey(100);
    		int d = a.getD();
    		e=a.getE();
    		n=a.getN();
    		String sendKeyE = "";
    		String sendKeyN = "";
    		sendKeyE+=e;
    		sendKeyN+=n;
    		//send key
    		out.writeUTF(sendKeyE);
    		out.writeUTF(sendKeyN);
    		String accpet = in.readUTF();
    		ProcessCiphertext p = new ProcessCiphertext(d,n,accpet);
    		s = p.decrypt();
    		System.out.println("Server：" + s);
		}
	}
	public static void main(String[] args) throws UnknownHostException, IOException {  
		new ChatClient2().chat();  
	}  
}  

