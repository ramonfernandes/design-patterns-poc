package pojo;

import dao.ImplementationType;

import java.time.LocalDate;

public class CustomerImplementation {

    private String cpf;
    private String name;
    private ImplementationType type;

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

    public String getName() {
        return name;
    }

    public ImplementationType getType() {
        return type;
    }

    public String getCpf() {
        return cpf;
    }
}
