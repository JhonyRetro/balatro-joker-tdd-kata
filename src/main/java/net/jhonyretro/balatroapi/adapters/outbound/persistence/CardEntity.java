package net.jhonyretro.balatroapi.adapters.outbound.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    public CardEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setQuantity(String description) {
        this.description = this.description;
    }

    public void setDescription(String description) {
    }
}
