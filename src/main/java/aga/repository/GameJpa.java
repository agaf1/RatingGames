package aga.repository;

import org.springframework.data.repository.CrudRepository;

public interface GameJpa extends CrudRepository<GameEntity, Integer> {
}
