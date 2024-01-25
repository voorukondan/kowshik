package com.example.song.service;

import java.util.ArrayList;
import java.util.List;

import com.example.song.model.Song;
import com.example.song.repository.SongJpaRepository;
import com.example.song.repository.SongRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
public class SongJpaService implements SongRepository {
    @Autowired
    private SongJpaRepository songJpaRepository;

    @Override
    public ArrayList<Song> getSongs() {
        List<Song> songList = songJpaRepository.findAll();
        ArrayList<Song> songs = new ArrayList<>(songList);
        return songs;
    }

    @Override
    public Song getSongById(int songId) {
        try {
            Song song = songJpaRepository.findById(songId).get();
            return song;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpsStatus.NOT_FOUND);
        }
    }

    @Override
    public Song addSong(Song song) {
        songJpaRepository.save(song);
        return song;
    }

    @Override
    public Song updateSong(int songId, Song song) {
        try {
            Song newSong = songJpaRepository.findById(songId).get();
            if (song.getSongName() != null) {
                newSong.setSongName(song.getSongName());
            }
            if (song.getLyricist() != null) {
                newSong.setLyricist(song.getLyricist());
            }
            if (song.getSinger() != null) {
                newSong.setSinger(song.getSinger());
            }
            if (song.getMusicDirector() != null) {
                newSong.setMusicDirector(song.getMusicDirector());
            }
            songJpaRepository.save(newSong);

            return newSong;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpsStatus.NOT_FOUND);
        }

    }

    @Override
    public void deleteSong(int songId){
        try{
            songJpaRepository.deleteById(songId);
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpsStatus.NOT_FOUND);
        }
    
 }