public class Iperfer {

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Error: missing or additional arguments");
			System.exit(-1);
		}

		String mode = args[0];

		if (mode.equals("-c")) {
			if ((args.length == 7) && args[1].equals("-h") && args[3].equals("-p") && args[5].equals("-t")) {
				int port = 10000;
				double time = 30;
				try {
					port = Integer.parseInt(args[4]);
					time = Double.parseDouble(args[6]);	
				} catch (Exception e) {
					System.err.println("Parsing argument failed");
					System.exit(1);
				}

				if ((port >= 1024) && (port <= 65535)) {
					IperfClient Iperf_Server = new IperfClient(args[2], port, time);
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

		else if (mode.equals("-s")) {
			if ((args.length == 3) && args[1].equals("-p")) {
				int port = 10000;
				try {
					port = Integer.parseInt(args[2]);	
				} catch (Exception e) {
					System.err.println("Parsing argument failed");
					System.exit(1);
				}
				
				if ((port >= 1024) && (port <= 65535)) {
					IperfServer Iperf_Server = new IperfServer(port);
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
