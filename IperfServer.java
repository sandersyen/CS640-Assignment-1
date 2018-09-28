import java.net.*;
import java.io.InputStream;

public class IperfServer {

	private int port;
	private ServerSocket server_socket;

	public IperfServer(int listen_port) {

		port = listen_port;

		try {
			// initialization
			server_socket = new ServerSocket(port);
			Socket client_socket = server_socket.accept();
			InputStream input_stream = client_socket.getInputStream();
			byte chunks[] = new byte[1000];

			// receive data and count
			long i;
			long data_received = 0;
			long time = System.currentTimeMillis();
			while ((i = input_stream.read(chunks)) != -1) {
				data_received += i;
			}

			// calculate rate & output
			time = System.currentTimeMillis() - time;
			data_received /= 1000;
			double rate = ((double)data_received / 1000) * 8 / (time / 1000);

			System.out.println("received=" + data_received + " KB rate=" + String.format("%.3f", rate) + " Mbps" );

			client_socket.close();
			server_socket.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
