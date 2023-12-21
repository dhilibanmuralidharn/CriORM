package com.example.player.service;

import java.util.*;

import com.example.player.model.Player;
import com.example.player.repository.PlayerJpaRepository;
import com.example.player.repository.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
public class PlayerJpaService implements PlayerRepository {

	@Autowired
	private PlayerJpaRepository playerRepository;

	@Override
	public ArrayList<Player> getPlayers() {
		List<Player> playerList = playerRepository.findAll();
		ArrayList<Player> players = new ArrayList<>(playerList);
		return players;
	}

	@Override
	public Player getPlayerById(int playerId) {
		try {
			Player player = playerRepository.findById(playerId).get();
			return player;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Player addPlayer(Player player) {
		playerRepository.save(player);
		return player;
	}

	@Override
	public Player updatePlayer(int playerId, Player player) {
		try {
			Player newPlayer = playerRepository.findById(playerId).get();
			if (player.getPlayerName() != null) {
				newPlayer.setPlayerName(player.getPlayerName());
			}
			if (player.getJerseyNumber() != 0) {
				newPlayer.setJerseyNumber(player.getJerseyNumber());
			}
			if (player.getRole() != null) {
				newPlayer.setRole(player.getRole());
			}
			playerRepository.save(newPlayer);
			return newPlayer;

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public void deletePlayer(int playerId) {
		try {
			playerRepository.deleteById(playerId);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

	}
}