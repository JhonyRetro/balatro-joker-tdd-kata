package net.jhonyretro.balatroapi.application.port.inbound;

import net.jhonyretro.balatroapi.domain.model.Card;

import java.util.List;

public interface CardUseCase {

    List<Card> getAll();

    Card create(Card card);

    Card getById(Long id);

    void delete(Long id);
}
