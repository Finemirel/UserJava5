package finemirel.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class InputMessage{
	Socket socket;
	Thread tr;

	public InputMessage(Socket socket, Thread tr) {
		this.socket = socket;
		this.tr = tr;
	}

	public void startInput() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			do {
				if (in.ready()) {
					String response = in.readLine();
					System.out.println(response);
				}
			} while (!Exit.isNeedExit());

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (!socket.isClosed()) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
