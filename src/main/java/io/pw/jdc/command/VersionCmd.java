package io.pw.jdc.command;

import io.pw.jdc.http.HttpRequest;
import io.pw.jdc.socket.SocketRequest;

/**
 * Created by pwykowski
 */
public class VersionCmd implements Command {
	@Override
	public String execute(SocketRequest socketRequest) {
		final HttpRequest request = new HttpRequest.Builder()
				.GET()
				.ofHost("localhost")
				.ofPath("/version")
				.build();
		try {
			String response = socketRequest.send(request.asText());
			final int lastEnter = response.lastIndexOf("\n");
			int lastIdx = 0;
			int idx;
			while ((idx = response.indexOf("\n", lastIdx)) < lastEnter) {
				lastIdx = idx + 1;
			}
			return response.substring(lastIdx);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
