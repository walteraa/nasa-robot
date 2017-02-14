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
import nasa.robot.challenge.exceptions.BadMovimentRequestException;

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
	
	
	//Testing invalid moviments
	@Test(expected = BadMovimentRequestException.class)
	public void testInvalidMoveOutOfRangeWhitoutRotation(){
		String payload = robotService.move("MMMMM");
	}
	
	@Test(expected = BadMovimentRequestException.class)
	public void testInvalidMovimentOutOfRangeWithLeftRotation(){
		String payload = robotService.move("MMLM");
	}

	@Test(expected = BadMovimentRequestException.class)
	public void testInvalidMovimentOutOfRangeWithRightRotation(){
		String payload = robotService.move("MMRMMMMM");
	}
	
	@Test(expected = EmptyMovimentRequestException.class)
	public void testEmptyMovimentRequest(){
		String payload = robotService.move("");
	}
	
	
	//Testing valid Moviments
	@Test
	public void testValidMovimentWithoutRotation(){
		String payload = robotService.move("MMMM");
		
	}
	
	@Test
	public void testValidMovimentWithRightRotation(){
		String payload = robotService.move("MMRM");
	}
	
	@Test
	public void testValidMovimentWithLeftRotation(){
		String payload = robotService.move("RMLMM");
	}
	
	@Test
	public void testValidMovimentWithRotationOnly(){
		String payload = robotService.move("RLRLRL");
	}
	
}
