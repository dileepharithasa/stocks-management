package com.stocks.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "stocks")
public class StockEntity implements Serializable {


    @Column(name = "stock_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stockId;

    @Column(name = "stock_name")
    private String stockName;

    @Column(name = "stock_price")
    private BigDecimal stockPrice;

    @Column(name = "stock_last_update")
    private Date stockLastUpdate;

}
