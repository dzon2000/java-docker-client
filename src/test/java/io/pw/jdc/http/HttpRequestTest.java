package io.pw.jdc.http;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by pwykowski
 */
class HttpRequestTest {
	@Test
	public void shouldCreateGETRequestWithEmptyBody() {
		// given
		final HttpRequest client = new HttpRequest.Builder()
				.GET()
				.ofHost("localhost")
				.ofPath("/api/yolo")
				.build();
		// when
		String result = client.asText();
		// then
		assertEquals("""
                    GET /api/yolo HTTP/1.1
                    Host: localhost

                    """, result);
	}

	@Test
	public void shouldCreatePOSTRequestWithSimpleBody() {
		// given
		final HttpRequest client = new HttpRequest.Builder()
				.POST()
				.ofHost("localhost")
				.ofPath("/api/yolo")
				.withBody("a=b&c=d")
				.build();
		// when
		String result = client.asText();
		// then
		assertEquals("""
POST /api/yolo HTTP/1.1
Host: localhost

a=b&c=d""", result);
	}

}