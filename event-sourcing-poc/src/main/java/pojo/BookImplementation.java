package pojo;

import dao.ImplementationType;

public class BookImplementation {

    private String name;
    private String autor;
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
}
