package com.hello.mapper.pay;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface IPayTransactionMapper {

    Integer insertPayTransaction(Map map);

    Map payTransactionById(Map map);

    Map paymentChannelByCloumn(Map map);
}
