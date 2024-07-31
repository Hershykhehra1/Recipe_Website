package cs3220.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import cs3220.model.RecepieEntry;

public interface RecepieRepository extends CrudRepository<RecepieEntry, Integer>{
	Optional<RecepieEntry> findById(Integer id);

}
