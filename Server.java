import java.net.Socket;
import java.net.ServerSocket;
import java.io.InputStream;

public class Server {
	
	private int port;
	private ServerSocket server_socket;
	
	public Server(int listen_port) {
		port = listen_port;
	}
	
	void execute() {
		try {
			// initialization
			server_socket = new ServerSocket(port);
			Socket client_socket = server_socket.accept();
			long time = System.currentTimeMillis();
			byte data[] = new byte[1000];
			
            // receive data and count		
			InputStream input_stream = client_socket.getInputStream();
			long i;
			long data_received = 0;
			while ((i = input_stream.read(data)) != -1) {
				data_received += i;
			}
	        
			// calculate rate & output
			time = (System.currentTimeMillis() - time)/1000;
			double rate = (data_received /1000) / time;
			System.out.println("received=" + data_received + " KB rate=" + rate + " Mbps" );
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
