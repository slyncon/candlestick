package org.candlesticks.repository;

import org.candlesticks.model.Instrument;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstrumentRepository extends ListCrudRepository<Instrument, Integer>, PagingAndSortingRepository<Instrument, Integer> {
}
