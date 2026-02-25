package net.jhonyretro.balatroapi.adapters.outbound.persistence;

import net.jhonyretro.balatroapi.application.service.CardService;
import net.jhonyretro.balatroapi.domain.model.Card;
import net.jhonyretro.balatroapi.domain.repository.CardRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CardRepositoryAdapter implements CardRepositoryPort {

    private final JpaCardRepository jpaRepository;
    private final CardPersistenceMapper mapper;

    public CardRepositoryAdapter(JpaCardRepository jpaRepository, CardPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Card> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Card> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Card save(Card card) {
        CardEntity entity = mapper.toEntity(card);
        CardEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public void delete(Card card) {
        CardEntity entity = mapper.toEntity(card);
        jpaRepository.delete(entity);
    }
}
