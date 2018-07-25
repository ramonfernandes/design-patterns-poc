package pojo;

import config.ImplementationType;


public class BookImplementation {

    private int eventId;
    private String name;
    private String autor;
    private int quantity_avaliable;
    private ImplementationType type;

    public BookImplementation setName(String name) {
        this.name = name;
        return this;
    }

    public BookImplementation setAutor(String autor) {
        this.autor = autor;
        return this;
    }

    public BookImplementation setType(ImplementationType type){
        this.type = type;
        return this;
    }

    public BookImplementation setEventId(int eventId) {
        this.eventId = eventId;
        return this;
    }

    public BookImplementation setQuantity_avaliable(int quantity_avaliable) {
        this.quantity_avaliable = quantity_avaliable;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getAutor() {
        return autor;
    }

    public ImplementationType getType() {
        return type;
    }

    public int getEventId() {
        return eventId;
    }

    public int getQuantity_avaliable() {
        return quantity_avaliable;
    }
}
