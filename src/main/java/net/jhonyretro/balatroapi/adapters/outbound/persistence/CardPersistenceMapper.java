package net.jhonyretro.balatroapi.adapters.outbound.persistence;

import net.jhonyretro.balatroapi.domain.model.Card;
import org.springframework.stereotype.Component;

@Component
public class CardPersistenceMapper {

    public CardEntity toEntity(Card domain) {
        if (domain == null) {
            return null;
        }

        CardEntity entity = new CardEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setDescription(domain.getDescription());
        return entity;
    }

    public Card toDomain(CardEntity entity) {
        if (entity == null) {
            return null;
        }

        Card domain = new Card();
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        domain.setDescription(entity.getDescription());
        return domain;
    }
}
