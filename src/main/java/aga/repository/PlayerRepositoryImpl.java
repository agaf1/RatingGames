package aga.repository;

import aga.service.domain.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Repository
@RequiredArgsConstructor
public class PlayerRepositoryImpl implements PlayerRepository {

    private final PlayerJpa playerJpa;
    private final MapperPlayer mapperPlayer;

    @Override
    @Transactional
    public Player save(Player player) {
        PlayerEntity playerEntity = mapperPlayer.mapToPlayerEntity(player);
        PlayerEntity savedPlayerEntity = playerJpa.save(playerEntity);
        return mapperPlayer.mapToPlayer(savedPlayerEntity);
    }

    @Override
    public Player findByIdOrThrow(Integer id) {
        PlayerEntity playerEntity = playerJpa.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("The player with id=%d does not exist",id)));
        return mapperPlayer.mapToPlayer(playerEntity);
    }
}
