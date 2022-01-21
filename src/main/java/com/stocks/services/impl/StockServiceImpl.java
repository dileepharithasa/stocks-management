package com.stocks.services.impl;

import com.stocks.entities.StockEntity;
import com.stocks.exceptions.RecordNotFoundException;
import com.stocks.models.Stock;
import com.stocks.repositories.StocksRepository;
import com.stocks.services.StockService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StocksRepository stocksRepository;

    @Override
    public Stock addStock(Stock stock) throws Exception {
        StockEntity stockEntity = new DozerBeanMapper().map(stock, StockEntity.class);
        stockEntity = stocksRepository.save(stockEntity);
        Stock stockSaved = new DozerBeanMapper().map(stockEntity, Stock.class);
        return stockSaved;
    }

    @Override
    public List<Stock> getStocks(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<StockEntity> stockEntityList = (Page<StockEntity>) stocksRepository.findAll(paging);
        if (stockEntityList.hasContent()) {
            ArrayList stockList = new DozerBeanMapper().map(stockEntityList.getContent(), new ArrayList<Stock>().getClass());
            return stockList;
        } else {
            return new ArrayList<Stock>();
        }

    }

    @Override
    public Stock getStockByStockId(Integer stockId) throws Exception {
        Optional<StockEntity> stockEntityOption = stocksRepository.findById(stockId);
        if (!stockEntityOption.isPresent())
            throw new RecordNotFoundException("Record not found");
        Stock stock = new DozerBeanMapper().map(stockEntityOption.get(), Stock.class);
        return stock;
    }

    @Override
    public Stock updateStock(Stock stock) throws Exception {
        StockEntity stockEntity = stocksRepository.getById(stock.getStockId());
        if (Objects.isNull(stockEntity))
            throw new RecordNotFoundException("Record not found");
        StockEntity stockEntityToUpdate = new DozerBeanMapper().map(stock, StockEntity.class);
        StockEntity entityUpdated = stocksRepository.save(stockEntityToUpdate);
        Stock stockUpdated = new DozerBeanMapper().map(entityUpdated, Stock.class);
        return stockUpdated;

    }

    @Override
    public void deleteStockByStockId(Integer stockId) throws Exception {
        StockEntity stockEntity = stocksRepository.getById(stockId);
        if (Objects.isNull(stockEntity))
            throw new RecordNotFoundException("Record not found");
        stocksRepository.deleteById(stockId);
    }

}