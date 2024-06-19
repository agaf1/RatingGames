package aga.repository;

import aga.service.domain.Game;

public interface GameRepository {

    Game save(Game game);

    Game findByIdOrThrow(Integer id);
}
