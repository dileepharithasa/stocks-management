package com.stocks.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stocks.entities.StockEntity;
import com.stocks.models.Stock;
import com.stocks.repositories.StocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StockService {

    @Autowired
    private StocksRepository stocksRepository;

    public List<Stock> getStocks() {
        List<StockEntity> stockEntityList = (List<StockEntity>) stocksRepository.findAll();
        final List<Stock> castedList = typeCastList(stockEntityList, Stock.class);
        return castedList;
    }

    static <T> List<T> typeCastList(final Iterable<?> fromList,
                                    final Class<T> instanceClass) {
        final List<T> list = new ArrayList<>();
        final ObjectMapper mapper = new ObjectMapper();
        for (final Object item : fromList) {
            final T entry = item instanceof List<?> ? instanceClass.cast(item) : mapper.convertValue(item, instanceClass);
            list.add(entry);
        }
        return list;
    }
    public static Object objectMapper(Object object){
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        Stock stock = mapper.convertValue(object, Stock.class);
        return stock;
    }

}
