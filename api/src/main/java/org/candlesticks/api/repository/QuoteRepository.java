package org.candlesticks.api.repository;

import org.candlesticks.api.model.Quote;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends ListCrudRepository<Quote, Integer>, PagingAndSortingRepository<Quote, Integer> {

    public List<Quote> findByIsin(String isin);
}
