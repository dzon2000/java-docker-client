package io.pw.jdc.command;

import io.pw.jdc.socket.SocketRequest;

/**
 * Created by pwykowski
 */
public interface Command {

	String execute(SocketRequest socketRequest);

}
