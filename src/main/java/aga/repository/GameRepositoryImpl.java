package aga.repository;

import aga.service.domain.Game;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Repository
@RequiredArgsConstructor
public class GameRepositoryImpl implements GameRepository {

    private final GameJpa gameJpa;
    private final MapperGame mapperGame;

    @Override
    @Transactional
    public Game save(Game game) {
        GameEntity gameEntity = mapperGame.mapToGameEntity(game);
        GameEntity savedGameEntity = gameJpa.save(gameEntity);
        return mapperGame.mapToGame(savedGameEntity);
    }

    @Override
    public Game findByIdOrThrow(Integer id) {
        GameEntity gameEntity = gameJpa.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("The game with id=%d does not exist", id)));
        return mapperGame.mapToGame(gameEntity);
    }

}
