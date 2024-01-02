package io.getarrays.server.resource;

import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.getarrays.server.model.Response;
import io.getarrays.server.model.Server;
import io.getarrays.server.model.Status;
import io.getarrays.server.service.implmentation.ServerServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/server")
public class ServerResource {

	private final ServerServiceImpl serverServiceImpl;
	
	@GetMapping("/list")
	public ResponseEntity<Response> getServers(){
		return ResponseEntity.ok(
				Response.builder()
				.timeStamp(LocalDateTime.now())
				.status(HttpStatus.OK)
				.message("Servers Retrived")
				.statusCode(HttpStatus.OK.value())
				.data(Map.of("servers",serverServiceImpl.listServers(30)))				
				.build()
				);
	}
	
	@GetMapping("/ping/{ipAddress}")
	public ResponseEntity<Response> pingServers(@PathVariable("ipAddress") String ipAddress) throws UnknownHostException, IOException{
		Server server = serverServiceImpl.ping(ipAddress);
		
		return ResponseEntity.ok(
				Response.builder()
				.timeStamp(LocalDateTime.now())
				.status(HttpStatus.OK)
				.message(server.getStatus()==Status.SERVER_UP ?"Ping success":"Ping failure")
				.statusCode(HttpStatus.OK.value())
				.data(Map.of("server",server))				
				.build()
				);
	}
	
	@PostMapping("/save")
	public ResponseEntity<Response> saveServers(@RequestBody @Valid Server server){
		
		return ResponseEntity.ok(
				Response.builder()
				.timeStamp(LocalDateTime.now()) 
				.status(HttpStatus.CREATED)
				.message("Created new Server")
				.statusCode(HttpStatus.CREATED.value())
				.data(Map.of("server",serverServiceImpl.create(server)))				
				.build()
				);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Response> getServer(@PathVariable("id") Long id) {		
		return ResponseEntity.ok(
				Response.builder()
				.timeStamp(LocalDateTime.now())
				.status(HttpStatus.OK)
				.message("Retrived Server")
				.statusCode(HttpStatus.OK.value())
				.data(Map.of("server",serverServiceImpl.getServer(id)))				
				.build()
				);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Response> deleteServer(@PathVariable("id") Long id) {		
		return ResponseEntity.ok(
				Response.builder()
				.timeStamp(LocalDateTime.now())
				.status(HttpStatus.OK)
				.message("Deleted Server")
				.statusCode(HttpStatus.OK.value())
				.data(Map.of("deleted",serverServiceImpl.deleteServer(id)))				
				.build()
				);
	}

	@GetMapping(path="/image/{fileName}",produces = MediaType.IMAGE_PNG_VALUE)
	public byte[] getServerImage(@PathVariable("fileName") String fileName) throws IOException{		
		String path=System.getProperty("user.home")+"/Downloads/img/"+fileName;		
		return Files.readAllBytes(Paths.get(path));
	}
}





