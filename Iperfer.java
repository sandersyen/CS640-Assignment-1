public class Iperfer {

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Error: missing or additional arguments");
			System.exit(-1);
		}
	    
		String mode = args[0];
		
		if (mode.equals("-c")) {
			
		}
		else if (mode.equals("-c")) {
			if (args.length == 3) & (args[1].equals("-p")) {
				int port = Integer.parseInt(arg[2]);
				if (port >= 1024) & (port <= 65535) {
					Server Iperf_Server= new Server(port);
					Iperf_Server.execute();
				}
				else {
					System.out.println("Error: port number must be in the range 1024 to 65535");
					System.exit(-1);
				}
			}
			else {
				System.out.println("Error: missing or additional arguments");
				System.exit(-1);
			}

		}
		
		else {
			System.out.println("Error: missing or additional arguments");
			System.exit(-1);
		}
	}

}
