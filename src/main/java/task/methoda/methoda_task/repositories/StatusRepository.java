package task.methoda.methoda_task.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import task.methoda.methoda_task.beans.Status;

public interface StatusRepository extends JpaRepository<Status, Integer> {
    Status getByName(String name);

    Status findFirstByIdGreaterThan(int id);

}
