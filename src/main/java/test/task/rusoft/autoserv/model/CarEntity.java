package test.task.rusoft.autoserv.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "carsTable")
public class CarEntity implements Serializable {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  @Column(name = "carId", nullable = false)
  private int carId;

  @Column(name = "model", length = 50, unique = true, nullable = false)
  private String model;

  @Column(name = "releaseYear", length = 10, nullable = false)
  private String releaseYear;

  @Column(name = "clientName", length = 50)
  private String clientName;

  @OneToOne(optional = false, mappedBy = "carEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private ClientEntity clientEntity;

  public CarEntity(){}

  public CarEntity(String model, String releaseYear)
  {
    this.model = model;
    this.releaseYear = releaseYear;
  }

  public void addClient(ClientEntity client){
    client.setCarEntity(this);
    setClientName(client.getName());
    this.clientEntity = client;
  }

  public void removeClient(){
    if(clientEntity!=null){
      clientName = null;
      clientEntity.setCarEntity(null);
      this.clientEntity = null;
    }
  }

  public int getCarId() {
    return carId;
  }

  public void setCarId(int carId) {
    this.carId = carId;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getReleaseYear() {
    return releaseYear;
  }

  public void setReleaseYear(String releaseYear) {
    this.releaseYear = releaseYear;
  }

  public String getClientName() {
    return clientName;
  }

  public void setClientName(String clientName) {
    this.clientName = clientName;
  }

  public ClientEntity getClientEntity() {
    return clientEntity;
  }

  public void setClientEntity(ClientEntity clientEntity) {
    this.clientEntity = clientEntity;
  }

}
