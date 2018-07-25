package pojo;

import config.ImplementationType;

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

    public String getName() {
        return name;
    }

    public String getAutor() {
        return autor;
    }

    public ImplementationType getType() {
        return type;
    }
}
