package com.stocks.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@ToString
@Setter
@Getter
@Entity
@Table(name = "stocks")
public class StockEntity {

    @Column(name= "stock_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer stockId;

    @Column(name= "stock_name")
    String stockName;

    @Column(name= "stock_price")
    BigDecimal stockPrice;

    @Column(name= "stock_last_update")
    Date stockLastUpdate;

    public StockEntity(int stockId, String stockName, BigDecimal stockPrice, Date stockLastUpdate) {
        this.stockId = stockId;
        this.stockName = stockName;
        this.stockPrice = stockPrice;
        this.stockLastUpdate = stockLastUpdate;
    }
}
