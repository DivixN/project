package project1;

import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application{
		
	String username=new String("admin");
	String password=new String("123456");
	String out="";
	public void start(Stage primaryStage) {  //Login Page Stage 
		//set login page GUI format
		primaryStage.setTitle("Encryption Chatting Application");
	    GridPane grid = new GridPane(); //use gridlayout to build the basic frame
	    grid.setAlignment(Pos.CENTER);//base on center 
	    grid.setHgap(10);//row
	    grid.setVgap(30);//col
	    grid.setPadding(new Insets(25, 25, 25, 25));//distance between end
	    Text scenetitle = new Text("Login Page");
	    scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
	    grid.add(scenetitle, 0, 0);//x, y
	    Label userName = new Label("User Name:");  //Usetname imput area
	    grid.add(userName, 0, 1);
	    TextField userTextField = new TextField();
	    grid.add(userTextField, 1, 1);
	    Label pw = new Label("Password:");	//Password imput area
	    grid.add(pw, 0, 2);
	    PasswordField pwBox = new PasswordField();
	    grid.add(pwBox, 1, 2);
	    Button btn = new Button("Sign in"); //Sign Button
	    HBox hbBtn = new HBox(10);
	    hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
	    hbBtn.getChildren().add(btn);
	    grid.add(hbBtn, 1, 4);
        //actiontarget
	    final Text actiontarget = new Text();
	    grid.add(actiontarget, 1, 6);
	    btn.setOnAction(new EventHandler<ActionEvent>() { //Sign button action handler
	    public void handle(ActionEvent e) {
	        String user1=userTextField.getText();
	        String pws1=pwBox.getText();
	        if(user1.equals(username)&&pws1.equals(password)){//if imput matches backgrounddata,login successful.
	            actiontarget.setFill(Color.FIREBRICK);
	            actiontarget.setText("Login successful.");
	            primaryStage.close();
	            chattingStage(); // get in to the chatting room       
	        }
	        else {
	        	actiontarget.setFill(Color.FIREBRICK);
	           	actiontarget.setText("Invalid account,\t\nplease try again.");
                //when the password and username are wrong 
                //set the textfield empty
                userTextField.setText("");
                pwBox.setText("");
	        }//else access denied.
	         
	        
	    }
	    });
	    Scene scene = new Scene(grid,300, 500);
	    primaryStage.setScene(scene);
	    primaryStage.show();
	}
	public void chattingStage(){  //Chatting Room Page Stage
		Stage chatting=new Stage();
		 //set chatting room GUI format	
		BorderPane border=new BorderPane();//use borderlayout to build the frame
		border.setPadding(new Insets(25,25, 25, 25));
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25,25, 25));
		chatting.setTitle("Welcome to Ecryption Chatting Room.");
		Text scenetitle = new Text("Chatting Room");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
		scenetitle.setFill(Color.CORAL);
		border.setTop(scenetitle);
		
		TextArea room = new TextArea();//here is the area show your chatting records
		room.setEditable(false);
		VBox vbox = new VBox(room);
		border.setLeft(vbox);
		
		Label Im = new Label("Imput:");
		grid.add(Im, 0, 0);
		
		TextField chatBox = new TextField();//here is the area to imput your chatting content
		grid.add(chatBox, 1, 0);
		
		//actiontarget
		final Text actiontarget = new Text();
		grid.add(actiontarget, 1, 3);
		
		Button btn = new Button("submit");
		btn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String im1=chatBox.getText();
				boolean isGood=true;
				for(int i=0;i<im1.length();i++){
					char c=im1.charAt(0);
                  //check for lower case
					if(!Character.isLowerCase(c)){isGood=false;}//not lower case               
                  //check for num
					if(Character.isDigit(c)){isGood=false;}//is num
                  //check for Punctuation
					if(!Character.isLetter(c)&&!Character.isDigit(c)){isGood=false;}//is punctuation
               }
				if(isGood==true){
					actiontarget.setText("");
					Date d = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//get the instance date
					out=out+sdf.format(d)+":"+im1+"\t\n";
					room.setText(out);
					chatBox.setText("");//return the chatting records
				}
               else{
               actiontarget.setFill(Color.FIREBRICK);
               actiontarget.setText("Please enter the words by lower case!");
               chatBox.setText("");
               }
               }
		});
		grid.add(btn, 1, 1);
	    border.setBottom(grid);
		Scene chatscene = new Scene(border,500, 344);
	    chatting.setScene(chatscene);
	    chatting.show();
	 }
	 
		public static void main(String[] args){
			launch(args);//using javafx 
		}
}
