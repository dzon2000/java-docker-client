package io.pw.jdc.command;

import io.pw.jdc.socket.SocketRequest;

import java.util.Optional;

/**
 * Created by pwykowski
 */
public interface Command {

	Optional<String> execute(SocketRequest socketRequest);

}
