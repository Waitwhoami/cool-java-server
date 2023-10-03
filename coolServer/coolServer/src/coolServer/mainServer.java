package coolServer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class mainServer {

	public static void main(String[] args) throws IOException{
		
		
		try {
			ServerSocket ws = new ServerSocket(7979);	//Creating new socket port
			while(true) {
				
				Socket s = ws.accept();		//Accepting socket port
				
				//Initializing input / output stuff
				BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
				PrintWriter out = new PrintWriter(s.getOutputStream(), true);
				
				//Making the IP address look nicer if it's localhost
				String ip = s.getInetAddress().toString();
				if(ip.equals("/0:0:0:0:0:0:0:1")) {
					ip = "localhost";
				}
				out.println("David Menon\n");
				
				//Keeping the connection with a client until it writes "exit"
				String str = "";
				while(!str.equalsIgnoreCase("exit") || !str.equalsIgnoreCase("quit")) {
					
					switch(str) {
					case "date":
					    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  //The server only partially takes echo commands wth im way too lazy to fix that
					    Date date = new Date();  
					    out.println(formatter.format(date));  
					    str = in.readLine();
					    break;
					default:
						str = in.readLine();
						out.println(str);
						break;
					}
					str = in.readLine();
				}
				out.println("Client: " + ip);
				out.println("Porta: " + s.getPort());
				out.println("Tempo connessione: " + System.currentTimeMillis());
					out.close();
					in.close();
					s.close();
			}
		}catch(IOException e) {
			System.err.println("Coglione usa un'altra porta \n");
			e.printStackTrace();
		}
	}

}