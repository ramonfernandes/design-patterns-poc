package dao;

import pojo.CustomerImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    public boolean addTransaction(CustomerImplementation customer) {
        return createCustomer(customer);
    }

    public boolean createCustomer(CustomerImplementation customer) {
        CustomerImplementation customerImplementation = getLastElementFromList(getEventsFromCPF(customer.getCpf()));
        try (Connection conn = Connect.abrir()) {
            if (compareImplementationType(customerImplementation, ImplementationType.DELETE) || compareImplementationType(customerImplementation, ImplementationType.UNEXISTENT)) {
                StringBuilder sql = new StringBuilder();
                sql.append("INSERT INTO customer(event_id, customer_id, name, event_type) VALUES (" +
                        customerImplementation.getEventId() + 1 + ",'" +
                        customer.getCpf() + "', '" +
                        customer.getName() +
                        "', 'CREATE');");
                PreparedStatement comando = conn.prepareStatement(sql.toString());
                comando.execute();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateCostumer(CustomerImplementation customer) {
        CustomerImplementation customerImplementation = getLastElementFromList(getEventsFromCPF(customer.getCpf()));
        try (Connection conn = Connect.abrir()) {
            if (compareImplementationType(customerImplementation, ImplementationType.CREATE) || compareImplementationType(customerImplementation, ImplementationType.UPDATE)) {
                StringBuilder sql = new StringBuilder();
                sql.append("INSERT INTO customer(event_id, customer_id, name, event_type) VALUES ('" +
                        customerImplementation.getEventId() + 1 + "','" +
                        customer.getCpf() + "', '" +
                        customer.getName() +
                        "', 'UPDATE');");
                PreparedStatement comando = conn.prepareStatement(sql.toString());
                comando.execute();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteCostumer(CustomerImplementation customer){
        CustomerImplementation customerImplementation = getLastElementFromList(getEventsFromCPF(customer.getCpf()));
        try (Connection conn = Connect.abrir()) {
            if (compareImplementationType(customerImplementation, ImplementationType.CREATE) || compareImplementationType(customerImplementation, ImplementationType.UPDATE)) {
                StringBuilder sql = new StringBuilder();
                sql.append("INSERT INTO customer(event_id, customer_id, name, event_type) VALUES ('" +
                        customerImplementation.getEventId() + 1 + "','" +
                        customer.getCpf() + "', '" +
                        customer.getName() +
                        "', 'DELETE');");
                PreparedStatement comando = conn.prepareStatement(sql.toString());
                comando.execute();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<CustomerImplementation> getEventsFromCPF(String cpf) {
        List<CustomerImplementation> result = new ArrayList<CustomerImplementation>();
        try (Connection conn = Connect.abrir()) {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM customer WHERE customer_id = " + cpf + " ORDER BY customer_id, event_id;");
            PreparedStatement comando = conn.prepareStatement(sql.toString());
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {
                result.add(new CustomerImplementation()
                        .setEventId(resultado.getInt("event_id"))
                        .setCpf(resultado.getString("customer_id"))
                        .setName(resultado.getString("name"))
                        .setType(ImplementationType.valueOf(resultado.getString("event_type"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private CustomerImplementation getLastElementFromList(List<CustomerImplementation> list) {
        int size = list.size();
        if (size == 0)
            return new CustomerImplementation().setType(ImplementationType.UNEXISTENT).setEventId(0);
        return list.get(size-1);
    }

    private boolean compareImplementationType(CustomerImplementation customer, ImplementationType toCompare) {
        return customer.getType().equals(toCompare);
    }
}

