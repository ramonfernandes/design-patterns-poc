package dao;

import pojo.CustomerImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {
    public boolean addTransaction(CustomerImplementation customer) {
        return createCustomer(customer);
    }

    public boolean createCustomer(CustomerImplementation customer) {
        try (Connection conn = Connect.abrir()) {
            if (compareImplementationType(customer, ImplementationType.DELETE)) {
                StringBuilder sql = new StringBuilder();
                sql.append("INSERT INTO customer(event_id, customer_id, name) VALUES ('" +
                        getLastEventIndexFromCPF(customer.getCpf()) + "','" +
                        customer.getCpf() + "','" +
                        customer.getName() +"');");
                PreparedStatement comando = conn.prepareStatement(sql.toString());
                comando.execute();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean compareImplementationType(CustomerImplementation customer, ImplementationType toCompare) {
        try (Connection conn = Connect.abrir()) {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT event_type FROM customer WHERE customer_id = " + customer.getCpf() + " ORDER BY event_id DESC LIMIT 1;");
            PreparedStatement comando = conn.prepareStatement(sql.toString());
            ResultSet resultado = comando.executeQuery();
            if (resultado.equals(toCompare))
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private int getLastEventIndexFromCPF(String cpf) {
        try (Connection conn = Connect.abrir()) {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT event_id FROM customer WHERE customer_id = " + cpf + " ORDER BY event_id DESC LIMIT 1;");
            PreparedStatement comando = conn.prepareStatement(sql.toString());
            ResultSet resultado = comando.executeQuery();
            if(resultado.next())
                return resultado.getInt(0)+1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
