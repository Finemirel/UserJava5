package finemirel.user;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class UserChat {
	private final static int PORT = 8085;

	Scanner sc = new Scanner(System.in);
	InetAddress addr;
	Socket socket;
	
	
	public UserChat() {
		try {
			addr = InetAddress.getByName("localhost");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			socket = new Socket(addr, UserChat.PORT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void startUser() {
		Exit.setNeedExit(false);
		Thread tr = new Thread(new OutputMessage(socket));
		tr.start();
		InputMessage input = new InputMessage(socket, tr);
		input.startInput();
	}
}
