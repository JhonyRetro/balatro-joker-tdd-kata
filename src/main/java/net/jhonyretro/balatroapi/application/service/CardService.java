package net.jhonyretro.balatroapi.application.service;

import net.jhonyretro.balatroapi.application.port.inbound.CardUseCase;
import net.jhonyretro.balatroapi.domain.exception.CardNotFoundException;
import net.jhonyretro.balatroapi.domain.model.Card;
import net.jhonyretro.balatroapi.domain.repository.CardRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService implements CardUseCase {

    private final CardRepositoryPort repository;

    public CardService(CardRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public List<Card> getAll() {
        return repository.findAll();
    }

    @Override
    public Card create(Card card) {
        return repository.save(card);
    }

    @Override
    public Card getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CardNotFoundException(id));
    }

    @Override
    public void delete(Long id) {
        Card card = repository.findById(id)
                .orElseThrow(() -> new CardNotFoundException(id));
        repository.delete(card);
    }
}
