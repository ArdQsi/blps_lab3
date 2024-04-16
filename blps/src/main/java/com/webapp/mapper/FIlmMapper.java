package com.webapp.mapper;

import com.webapp.dto.FilmDto;
import com.webapp.dto.GenreDto;
import com.webapp.model.FilmEntity;
import com.webapp.model.GenreEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {GenreSetMapper.class, GenreMapper.class})
public interface FIlmMapper {
    FIlmMapper MAPPER = Mappers.getMapper(FIlmMapper.class);

    FilmDto toDTO(FilmEntity film);
}
