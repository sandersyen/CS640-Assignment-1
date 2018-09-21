/*
	CS640-Assignment IperfClient
*/
import java.io.*;
import java.net.*;

public class IperfClient {
	
	String host_i = "127.0.0.1";
	int portNumber_i = 0;
	long time_i = 0;

	public IperfClient (String host, int portNumber, double time) {
		
		host_i = host;
		portNumber_i = portNumber;
		// need to convert to ns
		time_i = (long)time * 1000000000;
		
		try (
			// Create a socket that connects to the server (identified by the host name and port number)
			Socket Soc = new Socket(host_i, portNumber_i);
			// Get handles to the output stream of the socket
			DataOutputStream out = new DataOutputStream(Soc.getOutputStream());
		)
		{
			byte[] chunks = new byte[1000];
			int chunks_size = chunks.length;
			long startTime = System.nanoTime();
			long endTime = startTime + time_i;
			int sent_times = 0;

			while (System.nanoTime() < endTime) {
				out.write(chunks, 0, chunks_size);
				sent_times++;
			}

			out.close();
			Soc.close();

			System.out.println("sent=" + sent_times + " KB rate="+ (sent_times / 1000 * 8 / time) + " Mbps");
			
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + host_i);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to " +
				host_i);
			System.exit(1);
		}
	}
}