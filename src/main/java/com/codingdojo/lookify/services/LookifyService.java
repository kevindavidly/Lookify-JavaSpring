package com.codingdojo.lookify.services;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.lookify.models.Song;
import com.codingdojo.lookify.repositories.LookifyRepository;

@Service
public class LookifyService {
	
@Autowired
LookifyRepository lookifyRepository;

//----------------------------------------------------------------
// Find all songs
//----------------------------------------------------------------

	public List<Song> allSongs() {
		return lookifyRepository.findAll();
	}

//----------------------------------------------------------------
// Find one song
//----------------------------------------------------------------

	public Song findSong(Long id) {
		Optional<Song> optionalSong = lookifyRepository.findById(id);
		if (optionalSong.isPresent()) {
			return optionalSong.get();
		} else {
			return null;
		}
	}
	
//----------------------------------------------------------------
// Find songs by artist
//----------------------------------------------------------------	
	public List<Song> findSongsByArtist(String artist) {
//		System.out.println(lookifyRepository.findByArtistContaining(artist));
		return lookifyRepository.findByArtistContaining(artist);
	}
	
//----------------------------------------------------------------
// Find Top 10
//----------------------------------------------------------------	
	
	public List<Song> findTopTen() {
		System.out.println(lookifyRepository.findTop10ByOrderByRatingDesc());
		return lookifyRepository.findTop10ByOrderByRatingDesc();
	}
	
	
//----------------------------------------------------------------
// Create song
//----------------------------------------------------------------

	public Song createSong(Song song) {
		return lookifyRepository.save(song);
	}

//----------------------------------------------------------------
// Update song
//----------------------------------------------------------------

	public void updateSong(@Valid Song song) {
		lookifyRepository.save(song);
	}
	
//----------------------------------------------------------------
// Delete song
//----------------------------------------------------------------
	public void deleteSong(Long id) {
		lookifyRepository.deleteById(id);
	}


}