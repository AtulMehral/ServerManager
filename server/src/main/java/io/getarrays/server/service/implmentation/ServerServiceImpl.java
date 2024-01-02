package io.getarrays.server.service.implmentation;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.getarrays.server.exceptions.ServerNotFoundException;
import io.getarrays.server.model.Server;
import io.getarrays.server.model.Status;
import io.getarrays.server.repository.ServerRepository;
import io.getarrays.server.service.ServerService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ServerServiceImpl implements ServerService {

	@Autowired private final ServerRepository serverRepository;
	
	@Override
	public Server create(Server server) {	
		log.info("Saving new Server, "+server.getName());
		server.setImageUrl(setServerImageUrl());
		return serverRepository.save(server);
	}

	private String setServerImageUrl() {
		String[] imageNames= {"server1.png","server2.png","server3.png","server4.png"};
		return ServletUriComponentsBuilder
				.fromCurrentContextPath()
				.path("/server/image/"+imageNames[new Random().nextInt(imageNames.length)]).toUriString();
	}

	@Override
	public Server getServer(Long id) {
		log.info("Fetching server by Id "+id);
		checkServerifExists(id);
		return serverRepository.findById(id).get();
	}

	@Override
	public Server updateServer(Server server) {
		log.info("Updating Server, "+server.getName());
		return serverRepository.save(server);
		
	}

	@Override
	public Boolean deleteServer(Long id) {
		log.info("Fetching server by Id "+id);
		checkServerifExists(id);
		serverRepository.deleteById(id);
		return true;
	}

	@Override
	public Collection<Server> listServers(int limit) {
		log.info("Fetching All servers ");
		 return serverRepository.findAll(PageRequest.of(0, limit)).toList();		
	}

	@Override
	public Server ping(String ipAddress) throws IOException, UnknownHostException {
		log.info("Pinging Server IP: "+ipAddress);
		 Server server = serverRepository.findByIpAddress(ipAddress);
		 InetAddress address=InetAddress.getByName(ipAddress);
		 server.setStatus(address.isReachable(10000)?Status.SERVER_UP:Status.SERVER_DOWN);
		 serverRepository.save(server);
		 return server;
	}
	
	
	
	
	public Boolean checkServerifExists(Long id) {
				
		try {
			serverRepository.findById(id);
			return true;
		}catch (Exception e) {
			System.out.println(new ServerNotFoundException("No server found with Id: "+id));
			return false;
		}
		
	}

}
