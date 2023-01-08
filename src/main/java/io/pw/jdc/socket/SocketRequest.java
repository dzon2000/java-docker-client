package io.pw.jdc.socket;

import java.io.IOException;
import java.net.UnixDomainSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by pwykowski
 */
public class SocketRequest {
	private static final UnixDomainSocketAddress SOCKET_ADDRESS = UnixDomainSocketAddress.of("/var/run/docker.sock");

	public String send(String request) {
		try (var channel = SocketChannel.open(SOCKET_ADDRESS)) {
			ByteBuffer data = ByteBuffer.wrap(request.getBytes());
			channel.write(data);
			ByteBuffer resp = ByteBuffer.allocate(1024 * 1024);
			int bytesRead = channel.read(resp);
			byte[] bytes = new byte[bytesRead];
			resp.flip();
			resp.get(bytes);
			return new String(bytes);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

}
