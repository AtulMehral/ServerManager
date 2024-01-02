package io.getarrays.server.serviceTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import io.getarrays.server.ServerApplication;
import io.getarrays.server.model.Server;
import io.getarrays.server.service.ServerService;
import io.getarrays.server.service.implmentation.ServerServiceImpl;
import lombok.AllArgsConstructor;


@AllArgsConstructor
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = ServerApplication.class)
public class ServerServiceTest {

	private ServerService serverService;
		
	
	@Test
	public void testGetAllServers() {
		System.out.println("Testing Get All servers");
		Collection<Server> listServers = serverService.listServers(20);
		assertThat(listServers.size()).isNotEqualTo(0);
		assertThat(listServers.size()).isEqualTo(4);
	}
}
