package com.example.demo.mapper;

import com.example.demo.model.StockModel;
import com.example.demo.model.TransactionDetailModel;
import com.example.demo.model.TransactionModel;
import com.example.demo.model.request.DepositMoney;
import com.example.demo.model.request.GetTransactionID;
import com.example.demo.model.request.SellPayment;
import com.example.demo.model.response.Balance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface TransactionMapper {
    @Select(value = """
           CALL depositmoney(#{customerID},#{transactionMoney},#{description});
            """)
    int depositMoney(DepositMoney depositMoney);
    @Select(value = """
           CALL buy_payment(#{customerID},#{transactionMoney},#{description},#{stockname});
            """)
    int buyPayment(TransactionModel transactionModel);
    @Select(value = """
           CALL sell_payment(#{customerID},#{description},#{transactionID});
            """)
    int sellPayment(SellPayment sellPayment);
    @Select(value = """
           CALL get_all_transaction(#{transactionType});
            """)
    List<TransactionDetailModel> getTransaction(int transactionType);
    @Select(value = """
            CALL get_transaction_by_ID(#{customerID},#{transactionType});
            """)
    List<TransactionDetailModel> getTransactionByID(GetTransactionID getTransactionID);
    @Select("""
            CALL get_allstock();
            """)
    List<StockModel> getAllStock();
    @Select(value = """
           CALL deposit_not_approval();
            """)
    List<TransactionDetailModel> depositNotAproval();
    @Select("SELECT Amount FROM customer WHERE customerID = #{customerID}")
    int getAmountbyID(int customerID);
    @Select("SELECT holdAmount,sellAmount FROM balance WHERE customerID = #{customerID}")
    List<Balance> getBalance(int customerID);
}
