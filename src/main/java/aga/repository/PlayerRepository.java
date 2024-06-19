package aga.repository;

import aga.service.domain.Player;

public interface PlayerRepository {

    Player save(Player player);

    Player findByIdOrThrow(Integer id);
}
