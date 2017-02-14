package nasa.robot.challenge;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import nasa.robot.challenge.service.RobotService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class RobotServiceTest {

	@Configuration
	static class RobotServiceTestConfiguration {
		@Bean
		public RobotService robotService() {
			return new RobotService();
		}
	}
	
	@Autowired
	private RobotService robotService;
	
	@Test
	public void testBasicInvalidMove(){
		fail();
	}

}
