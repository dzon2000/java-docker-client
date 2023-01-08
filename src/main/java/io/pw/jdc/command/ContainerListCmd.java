package io.pw.jdc.command;

import io.pw.jdc.http.HttpRequest;
import io.pw.jdc.socket.SocketRequest;

/**
 * Created by pwykowski
 */
public class ContainerListCmd implements Command {
	@Override
	public String execute(SocketRequest socketRequest) {
		final HttpRequest request = new HttpRequest.Builder()
				.GET()
				.ofHost("localhost")
				.ofPath("/containers/json")
				.build();
		try {
			String response = socketRequest.send(request.asText());
			return response;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
