package test.task.rusoft.autoserv.service;

import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import test.task.rusoft.autoserv.model.CarEntity;
import test.task.rusoft.autoserv.model.ClientEntity;
import test.task.rusoft.autoserv.repository.CarEntityRepository;
import test.task.rusoft.autoserv.repository.ClientEntityRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DBServiceTest {

  @Autowired
  private DBService clientService;

  @Autowired
  private CarEntityRepository carEntityRepository;

  @Autowired
  private ClientEntityRepository clientEntityRepository;

  private CarEntity carEntity;
  private CarEntity assertedCarEntity;
  private ClientEntity clientEntity;

  private static final String CLIENT_NAME = "Smith";
  private static final String CLIENT_BIRTH_YEAR = "1984";
  private static final String CAR_MODEL = "Nissan Skyline";
  private static final String CAR_RELEASE_YEAR = "2000";

  @Before
  public void setUp(){
    carEntity = new CarEntity(CAR_MODEL, CAR_RELEASE_YEAR);
    assertedCarEntity = new CarEntity (CAR_MODEL, CAR_RELEASE_YEAR);
    assertedCarEntity.setClientName(null);
    assertedCarEntity.setCarId(5);
    clientEntity = new ClientEntity(CLIENT_NAME, CLIENT_BIRTH_YEAR);
  }

  @Test
  public void addCar() {
    carEntityRepository.save(carEntity);
    assertThat(carEntityRepository.getByModel(CAR_MODEL), samePropertyValuesAs(assertedCarEntity));
  }

  @Test
  public void addClientAndUpdateCar() {
    CarEntity carEntity = carEntityRepository.getByModel(CAR_MODEL);
    clientEntity.setCarEntity(carEntity);
    carEntity.addClient(clientEntity);

    clientEntityRepository.save(clientEntity);
    carEntityRepository.save(carEntity);

    assertNotNull(carEntity.getClientEntity());
    assertThat(clientEntity, samePropertyValuesAs(carEntity.getClientEntity()));
  }

  @Test
  public void removeClient() {
    CarEntity carEntity = carEntityRepository.getByModel(CAR_MODEL);
    carEntity.removeClient();

    carEntityRepository.save(carEntity);

    assertNull(carEntity.getClientEntity());
    assertNull(clientEntityRepository.getByName(CLIENT_NAME));
  }

}
