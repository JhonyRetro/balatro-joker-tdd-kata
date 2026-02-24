package net.jhonyretro.balatroapi.adapters.inbound.rest;

import net.jhonyretro.balatroapi.adapters.inbound.rest.dto.CardRequestDto;
import net.jhonyretro.balatroapi.adapters.inbound.rest.dto.CardResponseDto;
import net.jhonyretro.balatroapi.application.port.inbound.CardUseCase;
import net.jhonyretro.balatroapi.domain.model.Card;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jokers")
public class CardController {

    private final CardUseCase useCase;
    private final CardRestMapper mapper = new CardRestMapper();

    public CardController(CardUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public List<CardResponseDto> getAll() {
        return useCase.getAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CardResponseDto create(@RequestBody CardRequestDto request) {
        Card card = mapper.toDomain(request);
        Card created = useCase.create(card);
        return mapper.toResponse(created);
    }

    @GetMapping("/{id}")
    public CardResponseDto getById(@PathVariable Long id) {
        Card order = useCase.getById(id);
        return mapper.toResponse(order);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        useCase.delete(id);
    }
}
