package com.stocks.services;

import com.stocks.entities.StockEntity;
import com.stocks.models.Stock;
import com.stocks.repositories.StocksRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StocksRepository stocksRepository;

    public Stock addStock(Stock stock) {
        StockEntity stockEntity = new DozerBeanMapper().map(stock, StockEntity.class);
        stockEntity = stocksRepository.save(stockEntity);
        Stock stockSaved = new DozerBeanMapper().map(stockEntity, Stock.class);
        return stockSaved;
    }

    public List<Stock> getStocks(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<StockEntity> stockEntityList = (Page<StockEntity>) stocksRepository.findAll(paging);

        if(stockEntityList.hasContent()) {
            ArrayList stockList = new DozerBeanMapper().map(stockEntityList.getContent(), new ArrayList<Stock>().getClass());
            return stockList;
        } else {
            return new ArrayList<Stock>();
        }

    }

    public Stock getStockByStockId(Integer stockId) {
        Optional<StockEntity> stockEntityOption = stocksRepository.findById(stockId);
        Stock stock = new DozerBeanMapper().map(stockEntityOption.get(), Stock.class);
        return stock;
    }

    public Stock updateStock(Stock stock) {
        StockEntity stockEntity = new DozerBeanMapper().map(stock, StockEntity.class);
        StockEntity entityUpdated = stocksRepository.save(stockEntity);
        Stock stockUpdated = new DozerBeanMapper().map(entityUpdated, Stock.class);
        return stockUpdated;

    }

    public void deleteStockByStockId(Integer stockId) {
        stocksRepository.deleteById(stockId);
    }

}