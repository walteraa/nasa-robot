package nasa.robot.challenge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nasa.robot.challenge.DTO.PositionDTO;
import nasa.robot.challenge.exceptions.BadMovimentRequestException;
import nasa.robot.challenge.exceptions.EmptyMovimentRequestException;
import nasa.robot.challenge.exceptions.InvalidCommandException;
import nasa.robot.challenge.services.RobotService;

@RestController
public class NasaRobotController {

	@Autowired
	private RobotService robotService;
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/rest/mars/{command}")
	public ResponseEntity<PositionDTO> move(@PathVariable("command") String command){
		try {
			PositionDTO finalPosition = robotService.move(command);
			return new ResponseEntity<PositionDTO>(finalPosition,HttpStatus.OK);
			
		} catch (EmptyMovimentRequestException | InvalidCommandException | BadMovimentRequestException e) {
			return new ResponseEntity<PositionDTO>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
}
