package net.jhonyretro.balatroapi.domain.model;

public class Card {

    private Long id;
    private String name;
    private String description;

    public Card() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription (String description) {
        this.description = description;
    }
}
