//import config.Connect;
//import dao.BookDAO;
//import dao.BookDAO;
//import config.ImplementationType;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import pojo.BookImplementation;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class BookTest {
//
//    private BookDAO book;
//
//    @Before
//    public void setup(){
//        dropBook();
//        book = new BookDAO();
//    }
//
//    @Test
//    public void shouldCreateABook(){
//        assert book.executeOperationForCostumer(new BookImplementation().setCpf("0").setName("Zeca").setType(ImplementationType.CREATE));
//        int size = book.getEventsFromCPF("0").size();
//        Assert.assertEquals("Zeca", book.getEventsFromCPF("0").get(size-1).getName());
//    }
//
//    @Test
//    public void shoudUpdateABook(){
//        shouldCreateABook();
//        assert book.executeOperationForCostumer(new BookImplementation().setCpf("0").setName("Zequinha").setType(ImplementationType.UPDATE));
//        int size = book.getEventsFromCPF("0").size();
//        Assert.assertEquals("Zequinha", book.getEventsFromCPF("0").get(size-1).getName());
//    }
//
//    @Test
//    public void shouldDeleteABook(){
//        shouldCreateABook();
//        assert book.executeOperationForCostumer(new BookImplementation().setCpf("0").setName("Zeca").setType(ImplementationType.DELETE));
//        int size = book.getEventsFromCPF("0").size();
//        Assert.assertEquals("Zeca", book.getEventsFromCPF("0").get(size-1).getName());
//        Assert.assertEquals(ImplementationType.DELETE, book.getEventsFromCPF("0").get(size-1).getType());
//    }
//
//    @Test
//    public void couldNotCreateANUserWithTheSameCPF(){
//        shouldCreateABook();
//        Assert.assertFalse(book.executeOperationForCostumer(new BookImplementation().setCpf("0").setName("Zequinha").setType(ImplementationType.CREATE)));
//    }
//
//    @Test
//    public void couldNotDeleteAnUserThatsNotCreated(){
//        Assert.assertFalse(book.executeOperationForCostumer(new BookImplementation().setCpf("0").setName("Zeca").setType(ImplementationType.DELETE)));
//    }
//
//    @Test
//    public void couldNotUpdateAnUserThatsNotCreated(){
//        Assert.assertFalse(book.executeOperationForCostumer(new BookImplementation().setCpf("0").setName("Zeca").setType(ImplementationType.DELETE)));
//    }
//
//    @Test
//    public void couldNotDeleteTwiceInARow(){
//        shouldDeleteABook();
//        Assert.assertFalse(book.executeOperationForCostumer(new BookImplementation().setCpf("0").setName("Zeca").setType(ImplementationType.DELETE)));
//    }
//
//    @Test
//    public void couldNotUpdateAfterDelete(){
//        shouldDeleteABook();
//        Assert.assertFalse(book.executeOperationForCostumer(new BookImplementation().setCpf("0").setName("Zequinha").setType(ImplementationType.UPDATE)));
//    }
//
//    private void dropBook(){
//        try (Connection conn = Connect.abrir()) {
//            StringBuilder sql = new StringBuilder();
//            sql.append("delete from book;");
//            PreparedStatement comando = conn.prepareStatement(sql.toString());
//            comando.execute();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//}
