/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.user;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAOImpl implements TransactionDAO {
    private static final String DB_URL = "jdbc:sqlserver://SMKiosk132.mssql.somee.com:1433;databaseName=SMKiosk132;user=nhuttran09_SQLLogin_4;password=xn6wm3en6u";

    @Override
    public TransactionDTO getTransactionById(String transactionId) {
        TransactionDTO transaction = null;
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM transactions WHERE transaction_id = ?")) {
            stmt.setString(1, transactionId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                transaction = extractTransactionFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transaction;
    }

    @Override
    public List<TransactionDTO> getAllTransactions() {
        List<TransactionDTO> transactions = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM transactions")) {
            while (rs.next()) {
                TransactionDTO transaction = extractTransactionFromResultSet(rs);
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    @Override
    public List<TransactionDTO> getTransactionsByWalletId(String walletId) {
        List<TransactionDTO> transactions = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM transactions WHERE wallet_id = ?")) {
            stmt.setString(1, walletId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TransactionDTO transaction = extractTransactionFromResultSet(rs);
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    @Override
    public void createTransaction(TransactionDTO transaction) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO transactions (transaction_id, order_id, type, amount, wallet_id, date) VALUES (?, ?, ?, ?, ?, ?)")) {
            stmt.setString(1, transaction.getTransactionId());
            stmt.setString(2, transaction.getOrderId());
            stmt.setString(3, transaction.getType());
            stmt.setDouble(4, transaction.getAmount());
            stmt.setString(5, transaction.getWalletId());
            stmt.setDate(6, new java.sql.Date(transaction.getDate().getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTransaction(TransactionDTO transaction) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(
                     "UPDATE transactions SET order_id = ?, type = ?, amount = ?, wallet_id = ?, date = ? WHERE transaction_id = ?")) {
            stmt.setString(1, transaction.getOrderId());
            stmt.setString(2, transaction.getType());
            stmt.setDouble(3, transaction.getAmount());
            stmt.setString(4, transaction.getWalletId());
            stmt.setDate(5, new java.sql.Date(transaction.getDate().getTime()));

            stmt.setString(6, transaction.getTransactionId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTransaction(String transactionId) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM transactions WHERE transaction_id = ?")) {
            stmt.setString(1, transactionId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private TransactionDTO extractTransactionFromResultSet(ResultSet rs) throws SQLException {
        String transactionId = rs.getString("transaction_id");
        String orderId = rs.getString("order_id");
        String type = rs.getString("type");
        double amount = rs.getDouble("amount");
        String walletId = rs.getString("wallet_id");
        java.util.Date date = new java.util.Date(rs.getDate("date").getTime());
        return new TransactionDTO(transactionId, orderId, type, amount, walletId, date);
    }
}
