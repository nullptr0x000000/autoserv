package test.task.rusoft.autoserv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "clientsTable")
public class ClientEntity {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  @Column(name = "clientId", nullable = false)
  private int id;

  @Column(name = "name", length = 50,  unique = true, nullable = false)
  private String name;

  @Column(name = "birthYear", length = 10, nullable = false)
  private String birthYear;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "carId", referencedColumnName = "carId")
  private CarEntity carEntity;

  ClientEntity(){}

  public ClientEntity(String name, String birthYear)
  {
    this.name = name;
    this.birthYear = birthYear;
  }

  @Override
  public String toString()
  {
    return "ID:" + id + ";Name:" + name + ";birthYear" + birthYear + ";car:" + carEntity.getModel() + ";";
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBirthYear() {
    return birthYear;
  }

  public void setBirthYear(String birthYear) {
    this.birthYear = birthYear;
  }

  public CarEntity getCarEntity() {
    return carEntity;
  }

  public void setCarEntity(CarEntity carEntity) {
    this.carEntity = carEntity;
  }

}
