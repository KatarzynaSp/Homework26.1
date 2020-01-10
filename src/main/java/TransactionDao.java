import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.sql.DriverManager.getConnection;

public class TransactionDao {
    private Connection connection;

    public TransactionDao() {
        connect();
    }


    public void delete(Long id) {
        try {
            String sqlQuery = "DELETE from Transactions WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Transaction transaction) {
        try {
            String sqlQuery = "UPDATE Transactions SET type=?, description=?, amount=?, date=? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, transaction.getType().toString());
            preparedStatement.setString(2, transaction.getDescription());
            preparedStatement.setDouble(3, transaction.getAmount());
            preparedStatement.setString(4, transaction.getDate());
            preparedStatement.setLong(5, transaction.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Transaction> read(Type type) {
        try {
            String sqlQuery = "SELECT id, type, description, amount, date FROM Transactions WHERE type = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, type.name());
            ResultSet result = preparedStatement.executeQuery();
            List<Transaction> list = new ArrayList<>();
            while (result.next()) {
                Transaction transaction = new Transaction();
                transaction.setId(result.getLong(1));
                transaction.setType(type);
                transaction.setDescription(result.getString(3));
                transaction.setAmount(result.getDouble(4));
                transaction.setDate(result.getString(5));
                list.add(transaction);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insert(Transaction transaction) {
        try {
            String sqlQuery = "INSERT INTO Transactions(type, description, amount, date) VALUES(?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, String.valueOf(transaction.getType()));
            preparedStatement.setString(2, transaction.getDescription());
            preparedStatement.setDouble(3, transaction.getAmount());
            preparedStatement.setString(4, transaction.getDate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Niepowodzenie przy zapisie");
            e.printStackTrace();
        }
    }

    public void createTable() {
        try {
            String sqlQuery = "CREATE TABLE IF NOT EXISTS Transactions (" +
                    "id INTEGER auto_increment PRIMARY KEY, " +
                    "type ENUM('EXPENSE', 'INCOME'), " +
                    "description VARCHAR (30), " +
                    "amount INTEGER , " +
                    "date VARCHAR (30) )";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Niepowodzenie przy tworzeniu tabeli");
            e.printStackTrace();
        }
    }

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/budgethome?charakterEncoding=utf8&serverTimezone=UTC&useSSL=false";
            String username = "root";
            String password = "admin";
            this.connection = getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Błąd podczas zamykania połączenia" + e.getMessage());
            e.printStackTrace();
        }
    }
}