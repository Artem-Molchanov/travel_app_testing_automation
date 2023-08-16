package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import java.sql.Connection;
import java.sql.DriverManager;

public class SQLHelper {
    private static final QueryRunner runner = new QueryRunner();

    private SQLHelper() {
    }

    @SneakyThrows
    public static Connection getConn() {
        var url = System.getProperty("url");
        var username = System.getProperty("username");
        var password = System.getProperty("password");
        return DriverManager.getConnection(url,username,password);

    }

    @SneakyThrows
    public static String getStatusInfoPayment() {
        var conn = getConn();
        var codeSQL = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        return runner.query(conn, codeSQL, new ScalarHandler<>());

    }

    @SneakyThrows
    public static String getStatusInfoCredit() {
        var codeSQL = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        var conn = getConn();
        return runner.query(conn, codeSQL, new ScalarHandler<>());
    }

    @SneakyThrows
    public static String getPaymentTransId() {
        var codeSQL = "SELECT transaction_id FROM payment_entity ORDER BY created DESC LIMIT 1";
        var conn = getConn();
        return runner.query(conn, codeSQL, new ScalarHandler<>());
    }

    @SneakyThrows
    public static String getCreditReqBankId() {
        var codeSQL = "SELECT bank_id FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        var conn = getConn();
        return runner.query(conn, codeSQL, new ScalarHandler<>());
    }

    @SneakyThrows
    public static String getOrderPaymentId() {
        var codeSQL = "SELECT payment_id FROM order_entity ORDER BY created DESC LIMIT 1";
        var conn = getConn();
        return runner.query(conn, codeSQL, new ScalarHandler<>());
    }

    @SneakyThrows
    public static String getOrderCreditId() {
        var codeSQL = "SELECT credit_id FROM order_entity ORDER BY created DESC LIMIT 1";
        var conn = getConn();
        return runner.query(conn, codeSQL, new ScalarHandler<>());
    }

    @SneakyThrows
    public static void cleanDatabase() {
        var connection = getConn();
        runner.execute(connection, "DELETE FROM credit_request_entity");
        runner.execute(connection, "DELETE FROM order_entity");
        runner.execute(connection, "DELETE FROM payment_entity");
    }
}
