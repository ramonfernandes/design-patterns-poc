package dao;

import config.Connect;
import config.ImplementationType;
import pojo.CustomerImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    public boolean executeOperationForCostumer(CustomerImplementation customer){
        CustomerImplementation customerImplementation = getLastElementFromList(getEventsFromCPF(customer.getCpf()));
        try (Connection conn = Connect.abrir()) {
            if (validadeOperation(customer, customerImplementation)) {
                StringBuilder sql = new StringBuilder();
                sql.append("INSERT INTO customer(event_id, customer_id, name, event_type) VALUES ('" +
                        (customerImplementation.getEventId() + 1) + "','" +
                        customer.getCpf() + "', '" +
                        customer.getName() +
                        "', '"+customer.getType()+"');");
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

    public CustomerImplementation getLastElementFromList(List<CustomerImplementation> list) {
        int size = list.size();
        if (size == 0)
            return new CustomerImplementation().setType(ImplementationType.UNEXISTENT).setEventId(0);
        return list.get(size-1);
    }

    private boolean compareEvents(CustomerImplementation customer, CustomerImplementation toCompare){
        return (customer.getName().equals(toCompare.getName())
                && customer.getCpf().equals(toCompare.getCpf()));
    }

    private boolean validadeOperation(CustomerImplementation newEvent, CustomerImplementation lastEvent){
        ImplementationType condition1;
        ImplementationType condition2;
        if(newEvent.getType() == ImplementationType.CREATE) {
            condition1 = ImplementationType.DELETE;
            condition2 = ImplementationType.UNEXISTENT;
        }else{
            condition1 = ImplementationType.CREATE;
            condition2 = ImplementationType.UPDATE;
        }
        return ((lastEvent.getType().equals(condition1)
                || lastEvent.getType().equals(condition2))
                && (compareEvents(newEvent, lastEvent)
                || lastEvent.getType() == ImplementationType.UNEXISTENT
                || newEvent.getType() == ImplementationType.UPDATE));
    }
}

