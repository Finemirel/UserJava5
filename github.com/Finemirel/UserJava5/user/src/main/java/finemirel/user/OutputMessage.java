package finemirel.user;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class OutputMessage implements Runnable {
	
	private static final String EXIT = "/exit";
	Scanner sc = new Scanner(System.in);
	Socket socket;
	
	public OutputMessage(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
			while (true) {
				String msg = sc.nextLine();
				out.println(msg);
				if(msg.equals(EXIT)) {
					Exit.setNeedExit(true);
					sc.close();
					socket.close();
					break;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				sc.close();
				if (!socket.isClosed()) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
