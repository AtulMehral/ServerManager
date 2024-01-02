package io.getarrays.server.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author akumar757
 *@version	
 *@since
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Server {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(unique = true)
	@NotEmpty(message = "IP Address can't be empty or null")
	private String ipAddress;
	
	private String name;
	private String memory;
	private String type;
	private String imageUrl;
	private Status status;
}
