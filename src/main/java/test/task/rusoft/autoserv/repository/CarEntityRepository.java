package test.task.rusoft.autoserv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.task.rusoft.autoserv.model.CarEntity;

@Repository
public interface CarEntityRepository extends JpaRepository<CarEntity, Integer> {
  CarEntity getByModel(String model);
}
