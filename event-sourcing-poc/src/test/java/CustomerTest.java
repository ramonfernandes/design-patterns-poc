import config.Connect;
import dao.CustomerDAO;
import config.ImplementationType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pojo.CustomerImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerTest {

    private CustomerDAO customer;

    @Before
    public void setup(){
        dropCustomer();
        customer = new CustomerDAO();
    }

    @Test
    public void shouldCreateACustomer(){
        assert customer.executeOperationForCostumer(new CustomerImplementation().setCpf("0").setName("Zeca").setType(ImplementationType.CREATE));
        int size = customer.getEventsFromCPF("0").size();
        Assert.assertEquals("Zeca", customer.getEventsFromCPF("0").get(size-1).getName());
    }

    @Test
    public void shoudUpdateACustomer(){
        shouldCreateACustomer();
        assert customer.executeOperationForCostumer(new CustomerImplementation().setCpf("0").setName("Zequinha").setType(ImplementationType.UPDATE));
        int size = customer.getEventsFromCPF("0").size();
        Assert.assertEquals("Zequinha", customer.getEventsFromCPF("0").get(size-1).getName());
    }

    @Test
    public void shouldDeleteACustomer(){
        shouldCreateACustomer();
        assert customer.executeOperationForCostumer(new CustomerImplementation().setCpf("0").setName("Zeca").setType(ImplementationType.DELETE));
        int size = customer.getEventsFromCPF("0").size();
        Assert.assertEquals("Zeca", customer.getEventsFromCPF("0").get(size-1).getName());
        Assert.assertEquals(ImplementationType.DELETE, customer.getEventsFromCPF("0").get(size-1).getType());
    }

    private void dropCustomer(){
        try (Connection conn = Connect.abrir()) {
                StringBuilder sql = new StringBuilder();
                sql.append("delete from customer;");
                PreparedStatement comando = conn.prepareStatement(sql.toString());
                comando.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
