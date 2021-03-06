package nasa.robot.challenge;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import nasa.robot.challenge.DTO.PositionDTO;
import nasa.robot.challenge.exceptions.*;
import nasa.robot.challenge.services.RobotService;

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

	// Testing invalid moviments
	@Test(expected = BadMovimentRequestException.class)
	public void testInvalidMoveOutOfRangeWhitoutRotation()
			throws EmptyMovimentRequestException, InvalidCommandException, BadMovimentRequestException {
		PositionDTO payload = robotService.move("MMMMM");
	}

	@Test(expected = BadMovimentRequestException.class)
	public void testInvalidMovimentOutOfRangeWithLeftRotation()
			throws EmptyMovimentRequestException, InvalidCommandException, BadMovimentRequestException {
		PositionDTO payload = robotService.move("MMLM");
	}

	@Test(expected = BadMovimentRequestException.class)
	public void testInvalidMovimentOutOfRangeWithRightRotation()
			throws EmptyMovimentRequestException, InvalidCommandException, BadMovimentRequestException {
		PositionDTO payload = robotService.move("MMRMMMMM");
	}

	@Test(expected = EmptyMovimentRequestException.class)
	public void testEmptyMovimentRequest()
			throws EmptyMovimentRequestException, InvalidCommandException, BadMovimentRequestException {
		PositionDTO payload = robotService.move("");
	}

	@Test(expected = InvalidCommandException.class)
	public void testInvalidCommandException()
			throws EmptyMovimentRequestException, InvalidCommandException, BadMovimentRequestException {
		PositionDTO payload = robotService.move("MMWM");
	}

	// Testing valid Moviments
	@Test
	public void testValidMovimentWithoutRotation()
			throws EmptyMovimentRequestException, InvalidCommandException, BadMovimentRequestException {
		PositionDTO payload = robotService.move("MMMM");

		Assert.assertEquals("Response content incorrect.", new PositionDTO(0, 4, "N"), payload);
	}

	@Test
	public void testValidMovimentWithRightRotation()
			throws EmptyMovimentRequestException, InvalidCommandException, BadMovimentRequestException {
		PositionDTO payload = robotService.move("MMRM");
		Assert.assertEquals("Response content incorrect.", new PositionDTO(1, 2, "E"), payload);
	}

	
	@Test
	public void testValidMovimentAndRobotStateLess()
			throws EmptyMovimentRequestException, InvalidCommandException, BadMovimentRequestException {
		PositionDTO payload = robotService.move("MMM");

		Assert.assertEquals("Response content incorrect.", new PositionDTO(0, 3, "N"), payload);
		
		payload = robotService.move("RMM");

		Assert.assertEquals("Response content incorrect.", new PositionDTO(2, 0 , "E"), payload);
	}
	
	@Test
	public void testValidMovimentWithLeftRotation()
			throws EmptyMovimentRequestException, InvalidCommandException, BadMovimentRequestException {
		PositionDTO payload = robotService.move("RMLMM");
		Assert.assertEquals("Response content incorrect.", new PositionDTO(1, 2, "N"), payload);
	}

	@Test
	public void testValidMovimentWithRotationOnly()
			throws EmptyMovimentRequestException, InvalidCommandException, BadMovimentRequestException {
		PositionDTO payload = robotService.move("RLRLRL");
		Assert.assertEquals("Response content incorrect.", new PositionDTO(0, 0, "N"), payload);
		
		payload = robotService.move("RLRLR");
		Assert.assertEquals("Response content incorrect.", new PositionDTO(0, 0, "E"), payload);
	}

}
