package com.stocks.repositories;

import com.stocks.entities.StockEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StocksRepository extends CrudRepository<StockEntity, Integer> {
}
