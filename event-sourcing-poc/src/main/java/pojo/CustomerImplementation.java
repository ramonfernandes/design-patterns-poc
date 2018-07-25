package pojo;

import config.ImplementationType;

public class CustomerImplementation {

    private String cpf;
    private String name;
    private ImplementationType type;
    private int eventId;

    public CustomerImplementation setName(String name) {
        this.name = name;
        return this;
    }

    public CustomerImplementation setType(ImplementationType type){
        this.type = type;
        return this;
    }

    public CustomerImplementation setCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public CustomerImplementation setEventId(int id) {
        this.eventId = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ImplementationType getType() {
        return type;
    }

    public String getCpf() {
        return cpf;
    }

    public int getEventId() {
        return eventId;
    }
}
