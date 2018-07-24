import dao.CustomerDAO;
import dao.ImplementationType;
import org.junit.Before;
import org.junit.Test;
import pojo.CustomerImplementation;

import java.time.LocalDate;

public class CustomerTest {

    private CustomerDAO customer;

    @Before
    public void setup(){
        customer = new CustomerDAO();
    }

    @Test
    public void shouldCreateACustomer(){
        assert customer.createCustomer(new CustomerImplementation().setCpf("0").setName("Zeca").setType(ImplementationType.CREATE));
    }

}
