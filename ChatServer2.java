package project1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatServer2 {
    private int port = 8189;// 默认服务器端口  
    
    public ChatServer2() {  
    }  
    public ChatServer2(int port) {  
        this.port = port;  
    }  
    public void service() throws IOException{  
    	ServerSocket server = new ServerSocket(port);  
    	Socket socket = server.accept();  
    	DataInputStream in = new DataInputStream(socket.getInputStream());  
    	DataOutputStream out = new DataOutputStream(socket.getOutputStream());  
    	Scanner scanner = new Scanner(System.in);  
    	while (true) {
    		String accpet = in.readUTF();
    		//generate the key
    		GenerateKey a = new GenerateKey(100);
    		int d = a.getD(),e=a.getE(),n=a.getN(),ms;
    		String sendKeyE = "";
    		String sendKeyN = "";
    		sendKeyE+=e;
    		sendKeyN+=n;
    		//send key
    		out.writeUTF(sendKeyE);
    		out.writeUTF(sendKeyN);
    		accpet = in.readUTF();
    		//System.out.println(accpet);
    		ProcessCiphertext p = new ProcessCiphertext(d,n,accpet);
    		String s = p.decrypt();
    		System.out.println("Client:" + s);
    		e=Integer.parseInt(in.readUTF());
    		n=Integer.parseInt(in.readUTF());
    		ms = (int)(Math.random()*9+1);
    		String send = scanner.nextLine();
    		ProcessInput pi = new ProcessInput(e,n,ms,send);
    		s=pi.encrypt();
    		System.out.println("Server：" + send);
    		out.writeUTF(s);
    	}
    }
    public static void main(String[] args) throws IOException {  
        new ChatServer2().service();  
    }  
}
