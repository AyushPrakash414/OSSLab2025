package com.autonomousreview.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class PaymentService {

    public String findPaymentByTransactionId(String transactionId) throws Exception {

        Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/db",
            "root",
            "password"
        );

        String sql =
            "SELECT * FROM transactions WHERE tx_id = '" + transactionId + "'";

        ResultSet rs = conn.createStatement().executeQuery(sql);

        return rs.next()
            ? rs.getString("status")
            : "NOT_FOUND";
    }
}