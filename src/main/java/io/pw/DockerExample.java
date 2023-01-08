package io.pw;

import io.pw.jdc.command.VersionCmd;
import io.pw.jdc.http.HttpResponse;
import io.pw.jdc.socket.SocketRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pwykowski
 */
public class DockerExample {

	public static void main(String[] args) {
//		new VersionCmd().execute(new SocketRequest());
		Map<String, String> headers = new HashMap<>();
		StringBuilder body = new StringBuilder();
		try (BufferedReader reader = Files.newBufferedReader(Path.of("raw.txt"))) {
			String line = reader.readLine();
			final int status = HttpResponse.getStatus(line);
			line = reader.readLine();
			while (!line.isBlank()) {
				String[] header = line.split(":");
				headers.put(header[0], header[1].trim());
				line = reader.readLine();
			}
			for (line = reader.readLine(); !"0".equals(line) && !"".equals(line); line = reader.readLine()) {
				int byteCount = Integer.parseInt(line, 16);
				char[] content = new char[byteCount];
				final int read = reader.read(content);
				body.append(content);
			}
			System.out.printf("Status: %d%nHeaders: %s%nBody: %s%n", status, headers, body);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
