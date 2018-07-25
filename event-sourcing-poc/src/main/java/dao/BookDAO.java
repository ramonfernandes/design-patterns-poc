package dao;

import config.Connect;
import config.ImplementationType;
import pojo.BookImplementation;
import pojo.CustomerImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.
import java.util.List;

public class BookDAO {

    public boolean executeOperationForBook(BookImplementation newEvent){
        BookImplementation lastEvent = getLastElementFromList(getEventsFromName(newEvent.getName())));
        try (Connection conn = Connect.abrir()) {
            if (validadeOperation(newEvent, lastEvent)) {
                StringBuilder sql = new StringBuilder();
                sql.append("INSERT INTO book(event_id, name, event_type, autor, quantity_avaliable) VALUES ('" +
                        (lastEvent.getEventId() + 1) + "','" +
                        newEvent.getName() + "','" +
                        newEvent.getAutor() + "','" +
                        newEvent.getQuantity_avaliable() + "','" +
                        "', '"+newEvent.getType()+"');");
                PreparedStatement comando = conn.prepareStatement(sql.toString());
                comando.execute();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

        public BookImplementation getLastElementFromList(List<BookImplementation> list) {
            int size = list.size();
            if (size == 0)
                return new BookImplementation().setType(ImplementationType.UNEXISTENT).setEventId(0);
            return list.get(size-1);
        }

    private boolean validadeOperation(BookImplementation newEvent, BookImplementation lastEvent) {
    }

    private Object getEventsFromName(String name) {
    }

}
