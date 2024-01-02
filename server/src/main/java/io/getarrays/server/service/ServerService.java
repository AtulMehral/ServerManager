package io.getarrays.server.service;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Collection;

import io.getarrays.server.exceptions.ServerNotFoundException;
import io.getarrays.server.model.Server;

public interface ServerService {

	Server create(Server server);
	Server getServer(Long id);
	Server updateServer(Server server);
	Boolean deleteServer(Long id) ;
	
	Server ping(String ipAddress) throws IOException,UnknownHostException;
	
	Collection<Server> listServers(int limit);
}
