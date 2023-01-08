package io.pw.jdc.command;

import io.pw.jdc.http.HttpRequest;
import io.pw.jdc.socket.SocketRequest;

/**
 * Created by pwykowski
 */
public class ContainerLogsCmd implements Command {
	@Override
	public String execute(SocketRequest socketRequest) {
		final HttpRequest request = new HttpRequest.Builder()
				.GET()
				.ofHost("localhost")
				.ofPath("/containers/d99ceb0b8f0a/logs")
				.build();
		try {
			return socketRequest.send(request.asText());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
