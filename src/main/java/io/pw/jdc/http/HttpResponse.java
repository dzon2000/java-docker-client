package io.pw.jdc.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pwykowski
 */
public record HttpResponse(int statusCode, Map<String, String> headers, String body) {

	public static HttpResponse fromRaw(String rawHttp) {
		try (BufferedReader reader = new BufferedReader(new StringReader(rawHttp))) {
			String line = reader.readLine();
			int status = getStatus(line);
			Map<String, String> headers = getHeaders(reader);
			String body;
			if ("chunked".equals(headers.get("Transfer-Encoding"))) {
				body = getChunkedBody(reader);
			} else {
				body = getBody(reader);
			}
			return new HttpResponse(status, headers, body);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	static String getBody(BufferedReader reader) {
		try {
			String line = reader.readLine();
			return line;
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
	static String getChunkedBody(BufferedReader reader) {
		String line;
		StringBuilder body = new StringBuilder();
		try {
			for (line = reader.readLine(); !"0".equals(line) && !"".equals(line); line = reader.readLine()) {
				int byteCount = Integer.parseInt(line, 16);
				char[] content = new char[byteCount];
				reader.read(content);
				body.append(content);
				int endOfLine = reader.read();
			}
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
		return body.toString();
	}

	static Map<String, String> getHeaders(BufferedReader reader) {
		Map<String, String> headers = new HashMap<>();
		String line;
		try {
			line = reader.readLine();
			while (!line.isBlank()) {
				String[] header = line.split(":");
				headers.put(header[0], header[1].trim());
				line = reader.readLine();
			}
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
		return headers;
	}

	public static int getStatus(String line) {
		final Pattern pattern = Pattern.compile("\\d{3}");
		final Matcher matcher = pattern.matcher(line);
		if (matcher.find()) {
			return Integer.parseInt(matcher.group());
		}
		return 0;
	}

}
