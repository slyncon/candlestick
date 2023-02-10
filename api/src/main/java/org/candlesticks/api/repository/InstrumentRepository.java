package org.candlesticks.api.repository;

import org.candlesticks.api.model.Instrument;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstrumentRepository extends ListCrudRepository<Instrument, Integer>, PagingAndSortingRepository<Instrument, Integer> {
}
