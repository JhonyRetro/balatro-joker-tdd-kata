package net.jhonyretro.balatroapi.adapters.inbound.rest;

import net.jhonyretro.balatroapi.adapters.inbound.rest.dto.CardRequestDto;
import net.jhonyretro.balatroapi.adapters.inbound.rest.dto.CardResponseDto;
import net.jhonyretro.balatroapi.domain.model.Card;

public class CardRestMapper {

    public Card toDomain(CardRequestDto dto) {
        Card card = new Card();
        card.setName(dto.getName());
        card.setDescription(dto.getDescription());
        return card;
    }

    public CardResponseDto toResponse(Card card) {
        return new CardResponseDto(
                card.getId(),
                card.getName(),
                card.getDescription()
        );
    }
}
