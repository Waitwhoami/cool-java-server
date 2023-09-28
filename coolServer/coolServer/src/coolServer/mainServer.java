package coolServer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

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
				String str = in.readLine();
				while(str.equalsIgnoreCase("exit") == false) {
					str = in.readLine();
					out.println(str);	
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
