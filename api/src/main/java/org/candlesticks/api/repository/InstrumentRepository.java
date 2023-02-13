package org.candlesticks.api.repository;

import org.candlesticks.api.model.Instrument;
import org.candlesticks.api.model.Quote;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstrumentRepository extends ListCrudRepository<Instrument, Integer>, PagingAndSortingRepository<Instrument, Integer> {
    public List<Instrument> findByIsin(String isin);
}
