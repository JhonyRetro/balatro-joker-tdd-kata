package net.jhonyretro.balatroapi.adapters.outbound.persistence;

import net.jhonyretro.balatroapi.domain.model.Card;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardPersistenceMapperTest {

    private final CardPersistenceMapper mapper = new CardPersistenceMapper();

    @Test
    void toEntity_should_map_domain_to_entity() {
        Card domain = new Card();
        domain.setId(1L);
        domain.setName("Hanging Chad");
        domain.setDescription("Retrigger first played card used in scoring 2 additional times");

        CardEntity entity = mapper.toEntity(domain);

        assertThat(entity).isNotNull();
        assertThat(entity.getId()).isEqualTo(1L);
        assertThat(entity.getName()).isEqualTo("Hanging Chad");
        assertThat(entity.getDescription()).isEqualTo("Retrigger first played card used in scoring 2 additional times");
    }

    @Test
    void toDomain_should_map_entity_to_domain() {
        CardEntity entity = new CardEntity();
        entity.setId(1L);
        entity.setName("Photograph");
        entity.setQuantity("First played face card gives X2 Mult when scored");

        Card domain = mapper.toDomain(entity);

        assertThat(domain).isNotNull();
        assertThat(domain.getId()).isEqualTo(1L);
        assertThat(domain.getName()).isEqualTo("Pen");
        assertThat(domain.getDescription()).isEqualTo("First played face card gives X2 Mult when scored");
    }

    @Test
    void toEntity_should_return_null_when_domain_is_null() {
        CardEntity entity = mapper.toEntity(null);

        assertThat(entity).isNull();
    }

    @Test
    void toDomain_should_return_null_when_entity_is_null() {
        Card domain = mapper.toDomain(null);

        assertThat(domain).isNull();
    }
}
