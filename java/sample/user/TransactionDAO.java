/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.user;

/**
 *
 * @author 84878
 */
import java.util.List;

public interface TransactionDAO {
    TransactionDTO getTransactionById(String transactionId);

    List<TransactionDTO> getAllTransactions();

    List<TransactionDTO> getTransactionsByWalletId(String walletId);

    void createTransaction(TransactionDTO transaction);

    void updateTransaction(TransactionDTO transaction);

    void deleteTransaction(String transactionId);
}
