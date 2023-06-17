package com.example.demo.mapper;


import com.example.demo.models.Transaction;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionMapper {

    @Select("select * from tb_transaction where IBAN=#{IBAN} and month=#{month}")
    List<Transaction> selectTransaction(String IBAN, int month);

    @Insert("insert into table tb_transaction(" +
            "moneyType, num, IBAN, month, description) " +
            "values(#{moneyType}, #{num}, #{IBAN}, #{month}, #{description})")
    int insert(Transaction transaction);

}
