package test.task.rusoft.autoserv.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import test.task.rusoft.autoserv.exception.InvalidPostRequestException;
import test.task.rusoft.autoserv.model.CarEntity;
import test.task.rusoft.autoserv.model.ClientAddRequest;
import test.task.rusoft.autoserv.service.DBService;

@Api(value = "Clients controller", description = "Adding and deleting clients")
@RestController
@RequestMapping("clients")
public class ClientsController {

  private final DBService dbService;

  private void addCarsForTests()
  {
    dbService.addCarEntity(new CarEntity("Chevy Impala", "1995"));
    dbService.addCarEntity(new CarEntity("Toyota Chaser", "1993"));
    dbService.addCarEntity(new CarEntity("Toyota Mark II", "1989"));
  }

  @Autowired
  public ClientsController(DBService dbService){
    this.dbService = dbService;
    addCarsForTests();
  }

  @ApiOperation(value = "Adding clients")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Ok. User added."),
      @ApiResponse(code = 400, message = "Bad request. Details in message.")
  })
  @PostMapping (consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> addClient(@RequestBody ClientAddRequest client)
  {
    if(client.valid()) {
      dbService.addClientEntity(client.getClientName(), client.getClientBirthYear(), client.getCarModel());
    }
    else{
      throw new InvalidPostRequestException();
    }

    return new ResponseEntity<Void>(HttpStatus.OK);
  }

  @ApiOperation(value = "Removing clients")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Ok. User deleted."),
      @ApiResponse(code = 400, message = "Bad request. Details in message.")
  })
  @DeleteMapping
  public ResponseEntity<Void> deleteClient(@RequestParam String clientName,
                                           @RequestParam String carModel)
  {
    dbService.removeClient(clientName, carModel);
    return new ResponseEntity<Void>(HttpStatus.OK);
  }

}
