package repository;

import model.Instrument;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface InstrumentRepository extends ListCrudRepository<Instrument, Integer>, PagingAndSortingRepository<Instrument, Integer> {
}
