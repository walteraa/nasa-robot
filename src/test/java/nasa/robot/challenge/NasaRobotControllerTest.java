package nasa.robot.challenge;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import nasa.robot.challenge.controllers.NasaRobotController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.nio.charset.Charset;

import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NasaRobotControllerTest {
	
	 @Autowired
     private NasaRobotController controllerToTest;

     private MockMvc mockMvc;
     
     
     private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
             MediaType.APPLICATION_JSON.getSubtype(),
             Charset.forName("utf8"));
     
     @Before
     public void setup(){
    	 this.mockMvc = MockMvcBuilders.standaloneSetup(controllerToTest).build();
     }
     
	@Test
	public void testValidCommandWithoutRotation() throws Exception{
		this.mockMvc.perform(
				post("/rest/mars/MMM")
				.contentType(contentType))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.y", is(3)))
				.andExpect(jsonPath("$.orientation", is("N")));
		
		this.mockMvc.perform(
				post("/rest/mars/MMMRMMRM")
				.contentType(contentType))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.x", is(2)))
				.andExpect(jsonPath("$.y", is(2)))
				.andExpect(jsonPath("$.orientation", is("S")));
	}
	
	@Test
	public void testOutOfRangeCommand() throws Exception{
		this.mockMvc.perform(
				post("/rest/mars/MMMLM")
				.contentType(contentType))
				.andExpect(status().isBadRequest());
		
		this.mockMvc.perform(
				post("/rest/mars/MMMMM")
				.contentType(contentType))
				.andExpect(status().isBadRequest());
				
	}
	
	@Test
	public void testInvalidCommand() throws Exception{
		this.mockMvc.perform(
				post("/rest/mars/MMW")
				.contentType(contentType))
				.andExpect(status().isBadRequest());
	}
	
	
	
	@Test
	public void testEmptyCommand() throws Exception{
		this.mockMvc.perform(
				post("/rest/mars/")
				.contentType(contentType))
				.andExpect(status().isBadRequest());
	}
	
}
