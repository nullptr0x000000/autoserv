package test.task.rusoft.autoserv.service;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.task.rusoft.autoserv.exception.CarDoesNotExistException;
import test.task.rusoft.autoserv.exception.CarIsOccupiedException;
import test.task.rusoft.autoserv.exception.ClientAlreadyExistsException;
import test.task.rusoft.autoserv.exception.ClientDoesNotExistException;
import test.task.rusoft.autoserv.exception.ClientHasNoSuchCarException;
import test.task.rusoft.autoserv.model.CarEntity;
import test.task.rusoft.autoserv.model.ClientEntity;
import test.task.rusoft.autoserv.repository.CarEntityRepository;
import test.task.rusoft.autoserv.repository.ClientEntityRepository;

@Service
public class DBService {

  private CarEntityRepository carEntityRepository;
  private ClientEntityRepository clientEntityRepository;

  @Autowired
  public DBService(ClientEntityRepository clientEntityRepository, CarEntityRepository carEntityRepository) {
    this.clientEntityRepository = clientEntityRepository;
    this.carEntityRepository = carEntityRepository;
  }

  @Transactional
  public void addCarEntity(CarEntity carEntity)
  {
    carEntityRepository.save(carEntity);
  }

  @Transactional
  public void addClientEntity(String name, String birthYear, String model)
  {
    CarEntity carEntity = carEntityRepository.getByModel(model);
    ClientEntity clientEntity = clientEntityRepository.getByName(name);

    if (carEntity == null){
      throw new CarDoesNotExistException();
    }

    if (clientEntity != null){
      throw new ClientAlreadyExistsException();
    }

    if (carEntity.getClientName() != null){
      throw new CarIsOccupiedException();
    }

    clientEntity = new ClientEntity(name, birthYear);
    clientEntity.setCarEntity(carEntity);
    carEntity.addClient(clientEntity);
    clientEntityRepository.save(clientEntity);
    carEntityRepository.save(carEntity);
  }

  @Transactional
  public void removeClient(String name, String model) {
    ClientEntity clientEntity = clientEntityRepository.getByName(name);
    CarEntity carEntity = carEntityRepository.getByModel(model);

    if (clientEntity == null){
      throw new ClientDoesNotExistException();
    }

    if (carEntity == null){
      throw new CarDoesNotExistException();
    }

    if (!clientEntity.getCarEntity().getModel().equals(carEntity.getModel())){
      throw new ClientHasNoSuchCarException();
    }

    carEntity.removeClient();
    carEntityRepository.save(carEntity);
  }

}
