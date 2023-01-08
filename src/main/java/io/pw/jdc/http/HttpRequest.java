package io.pw.jdc.http;

import java.util.Optional;

/**
 * Created by pwykowski
 */
public class HttpRequest {

	private final HttpMethod method;
	private final String path;
	private final String host;
	private final String body;

	private HttpRequest(HttpMethod method, String path, String host, String body) {
		this.method = method;
		this.path = path;
		this.host = host;
		this.body = body;
	}

	public String asText() {
		return String.format("""
				%s %s HTTP/1.1
				Host: %s
				
				%s""", method, path, host, body);
	}

	public static class Builder {
		private HttpMethod method;
		private String path;
		private String host;
		private Optional<String> body = Optional.empty();

		public Builder ofHost(String host) {
			this.host = host;
			return this;
		}

		public Builder GET() {
			this.method = HttpMethod.GET;
			return this;
		}

		public Builder POST() {
			this.method = HttpMethod.POST;
			return this;
		}

		public Builder ofPath(String path) {
			this.path = path;
			return this;
		}

		public Builder withBody(String body) {
			this.body = Optional.ofNullable(body);
			return this;
		}

		public HttpRequest build() {
			return new HttpRequest(method, path, host, body.orElse(""));
		}

	}

}
