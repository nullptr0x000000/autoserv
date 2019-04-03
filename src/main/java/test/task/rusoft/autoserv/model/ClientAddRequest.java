package test.task.rusoft.autoserv.model;

public class ClientAddRequest {

  private String clientName;
  private String clientBirthYear;
  private String carModel;

  public ClientAddRequest(){}

  public boolean valid() {

    if (NullOrEmpty(clientName)){
      return false;
    }

    if (NullOrEmpty(clientBirthYear)){
      return false;
    }

    if (NullOrEmpty(carModel)){
      return false;
    }

    return true;
  }

  public boolean NullOrEmpty(String parameter)
  {
    if (parameter == null)
      return true;
    return parameter.equals("");
  }

  public String getClientName() {
    return clientName;
  }

  public void setClientName(String clientName) {
    this.clientName = clientName;
  }

  public String getClientBirthYear() {
    return clientBirthYear;
  }

  public void setClientBirthYear(String clientBirthYear) {
    this.clientBirthYear = clientBirthYear;
  }

  public String getCarModel() {
    return carModel;
  }

  public void setCarModel(String carModel) {
    this.carModel = carModel;
  }

}
