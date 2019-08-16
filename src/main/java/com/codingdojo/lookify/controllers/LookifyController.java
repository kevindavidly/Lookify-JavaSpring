package com.codingdojo.lookify.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.lookify.models.Song;
import com.codingdojo.lookify.services.LookifyService;

@Controller


public class LookifyController {
	@Autowired
	LookifyService lookifyService;
	
//  ----------------------------------------------------------------
//  Find all
//  ----------------------------------------------------------------
	
	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		List<Song> songs = lookifyService.allSongs();
		model.addAttribute("songs", songs);
		return "/lookify/index.jsp";
	}
	
//  ----------------------------------------------------------------
//   Show one
//  ----------------------------------------------------------------
	
	@GetMapping("/songs/{id}")
	public String show (Model model, @PathVariable("id") Long song_id) {
		Song song = lookifyService.findSong(song_id);
		model.addAttribute("song", song);
		return "/lookify/show.jsp";
	}
	
//  ----------------------------------------------------------------
//  Find by Artist Name
//  ----------------------------------------------------------------
	@GetMapping("/songs/search")
	public String findByArtist(Model model, @RequestParam(value="search") String artist) {
		List<Song> songs = lookifyService.findSongsByArtist(artist);
		model.addAttribute("songs", songs);
		return "/lookify/search.jsp";
	}
	
//  ----------------------------------------------------------------
//  Find all, order by rating (desc)
//  ----------------------------------------------------------------
	@GetMapping("/songs/topten")
	public String topTen(Model model) {
		List<Song> songs = lookifyService.findTopTen();
		model.addAttribute("songs", songs);
		return "/lookify/topten.jsp";
	}
	
//  ----------------------------------------------------------------
//  Render Create Form
//  ----------------------------------------------------------------
	
	@GetMapping("/new")
	public String newSong(@ModelAttribute("song") Song song) {
		return "/lookify/new.jsp";
	}
	
//  ----------------------------------------------------------------
//  POST Create
//  ----------------------------------------------------------------
	
	@PostMapping("/new/process")
	public String processNew(@Valid @ModelAttribute("song") Song song, BindingResult result) {
		if (result.hasErrors()) {
			return "/lookify/new.jsp";
		} else {
			lookifyService.createSong(song);
			return "redirect:/dashboard";
		}
	}
	
//  ----------------------------------------------------------------
//  Delete
//  ----------------------------------------------------------------
	@DeleteMapping("songs/{id}/delete")
	public String destroy(@PathVariable("id") Long id) {
		lookifyService.deleteSong(id);
		return "redirect:/dashboard";
	}

	
}