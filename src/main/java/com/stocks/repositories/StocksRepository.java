package com.stocks.repositories;

import com.stocks.entities.StocksEntity;
import org.springframework.data.repository.CrudRepository;

public interface StocksRepository extends CrudRepository<StocksEntity, Integer> {
}
