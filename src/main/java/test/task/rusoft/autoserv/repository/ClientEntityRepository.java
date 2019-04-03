package test.task.rusoft.autoserv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.task.rusoft.autoserv.model.ClientEntity;

public interface ClientEntityRepository extends JpaRepository<ClientEntity, Integer> {
  ClientEntity getByName(String name);
}
