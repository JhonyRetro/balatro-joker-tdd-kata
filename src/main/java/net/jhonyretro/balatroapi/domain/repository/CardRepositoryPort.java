package net.jhonyretro.balatroapi.domain.repository;

import net.jhonyretro.balatroapi.domain.model.Card;

import java.util.List;
import java.util.Optional;

public interface CardRepositoryPort {
    List<Card> findAll();

    Optional<Card> findById(Long id);

    Card save(Card card);

    void delete(Card card);
}
