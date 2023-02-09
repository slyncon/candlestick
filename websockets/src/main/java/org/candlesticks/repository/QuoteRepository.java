package org.candlesticks.repository;

import org.candlesticks.model.Quote;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends ListCrudRepository<Quote, Integer>, PagingAndSortingRepository<Quote, Integer> {
}
