package br.com.eduardo.springbootapiexampledevops;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class SpringBootApiExampleDevopsApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getsAllRides() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/theme-parks-ride/ride")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();
	}

	@Test
	public void getsSingleRide() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/theme-parks-ride/ride/2")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();
	}

	@Test
	public void returnsNotFoundForInvalidSingleRide() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/theme-parks-ride/ride/4")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andReturn();
	}

	@Test
	public void addsNewRide() throws Exception {
		String newRide = "{\"name\":\"Monorail\",\"description\":\"Sedate travelling ride.\",\"thrillFactor\":2,\"vomitFactor\":1}";
		mockMvc.perform(MockMvcRequestBuilders.post("/theme-parks-ride/ride")
				.contentType(MediaType.APPLICATION_JSON)
				.content(newRide)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();
	}

}
