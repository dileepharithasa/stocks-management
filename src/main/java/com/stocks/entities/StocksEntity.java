package com.stocks.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@ToString
@Setter
@Getter
@Entity
@Table(name = "stocks")
public class StocksEntity {

    @Column(name= "stock_id")
    @Id
    int stockId;

    @Column(name= "stock_name")
    String stockName;

    @Column(name= "stock_price")
    BigDecimal stockPrice;

    @Column(name= "stock_last_update")
    Date stockLastUpdate;

    public StocksEntity(int stockId, String stockName, BigDecimal stockPrice, Date stockLastUpdate) {
        this.stockId = stockId;
        this.stockName = stockName;
        this.stockPrice = stockPrice;
        this.stockLastUpdate = stockLastUpdate;
    }
}
