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
			// Data should be sent in chunks of 1000 bytes and the data should be all zeros. 
			byte[] chunks = new byte[1000];
			int chunks_size = chunks.length;
			long startTime = System.nanoTime();
			long endTime = startTime + time_i;
			int sent_times = 0;

			// Allow the remaining part of the 1000 byte chunk to be sent and then close the socket connection.
			while (System.nanoTime() < endTime) {
				out.write(chunks, 0, chunks_size);
				sent_times++;
			}

			out.close();
			Soc.close();

			double rate = (sent_times / 1000 * 8 / time);

			System.out.println("sent=" + sent_times + " KB rate="+ rate + " Mbps");
			
		} catch (UnknownHostException e) {
			// Checking that hostname is a valid address
			System.err.println("Don't know about host " + host_i);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to " +
				host_i);
			System.exit(1);
		}
	}
}
