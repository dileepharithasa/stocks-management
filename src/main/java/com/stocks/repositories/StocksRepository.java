package com.stocks.repositories;

import com.stocks.entities.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StocksRepository extends JpaRepository<StockEntity, Integer> {

}
