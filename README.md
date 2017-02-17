## Description

This project is a ContaAzul challenge that consist in a  REST which receives a command to simulate a robot movement in Mars, the land has a area 5x5. It was implemented in a TDD process using Spring boot and Maven as dependency injection.

## Challenge requirements

* The land should be 5x5 area.
* The robot starts in the position (0,0,N).
* Should be possible to send a command to robot which returns the final position of robot.
* The robot should not movement out of the specified area.
* The robot should be STATELESS.


*NOTICE:* I decided follow the REST API references about the response. Instead `(x, y, [N | S | E | W])` I will return a JSON as below: 
```
{
  "x": 2,
  "y": 0,
  "orientation": "[N | S | E | W]"
}
```

## Execution examples

Command:

`curl -s --request POST http://localhost:8080/rest/mars/MMRMMRMM`

Output:

```
{
  "x": 2,
  "y": 0,
  "orientation": "S"
}
```
Command:

`curl -s --request POST http://localhost:8080/rest/mars/MMRMMRMMMM`

Output:

`400 BAD REQUEST.(Status only)`

Command:
`http://localhost:8080/rest/mars/RLRLRLRLR`

Output:

```
{
  "x": 0,
  "y": 0,
  "orientation": "E"
}
```
