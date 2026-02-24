package net.jhonyretro.balatroapi.adapters.outbound.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCardRepository extends JpaRepository<CardEntity, Long> {
}
