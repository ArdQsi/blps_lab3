package com.webapp.service;

import com.webapp.dto.GenreDto;
import com.webapp.dto.MessageDto;
import com.webapp.exceptioin.ResourceAlreadyExistsException;
import com.webapp.model.GenreEntity;
import com.webapp.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    public MessageDto addGenre(GenreDto genreDto) {
        GenreEntity genreEntity = genreRepository.findByName(genreDto.getName());

        if (genreEntity != null) {
            throw new ResourceAlreadyExistsException("This genre already exists");
        }
        GenreEntity newGenreEntity = new GenreEntity();
        newGenreEntity.setName(genreDto.getName());

        genreRepository.save(newGenreEntity);
        return new MessageDto("Genre added successfully");
    }

}
