package cs3220.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import cs3220.model.UserEntry;

public interface UserRepository extends CrudRepository<UserEntry, Integer>{
	List<UserEntry> findByEmail(String email);
	Optional<UserEntry> findById(Integer id);
	

}
