package io.pw.jdc.command;

import io.pw.jdc.http.HttpRequest;
import io.pw.jdc.socket.SocketRequest;

import java.util.Optional;

/**
 * Created by pwykowski
 */
public class VersionCmd implements Command {
	@Override
	public Optional<String> execute(SocketRequest socketRequest) {
		final HttpRequest request = new HttpRequest.Builder()
				.GET()
				.ofHost("localhost")
				.ofPath("/version")
				.build();
		try {
			String response = socketRequest.send(request.asText());
			return Optional.ofNullable(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}
}
