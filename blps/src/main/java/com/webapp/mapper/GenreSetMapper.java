package com.webapp.mapper;

import com.webapp.dto.GenreDto;
import com.webapp.model.GenreEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(uses = GenreMapper.class)
public interface GenreSetMapper {

    GenreSetMapper MAPPER = Mappers.getMapper(GenreSetMapper.class);

    Set<String> toDTOSet(Set<GenreEntity> genres);
}
