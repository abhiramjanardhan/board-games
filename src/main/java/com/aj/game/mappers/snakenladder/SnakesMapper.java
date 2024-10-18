package com.aj.game.mappers.snakenladder;

import com.aj.game.constants.snakenladder.SnakeNLadderConstants;
import com.aj.game.models.snakenladder.Snake;
import com.aj.game.models.snakenladder.config.SnakeNLadderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SnakesMapper {
    SnakesMapper INSTANCE = Mappers.getMapper(SnakesMapper.class);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "start", target = "start")
    @Mapping(source = "end", target = "end")
    @Mapping(target = "type", constant = SnakeNLadderConstants.SNAKE)
    Snake detailToSnakeData(SnakeNLadderDetail detail);

    List<Snake> detailsToSnakesData(List<SnakeNLadderDetail> details);
}
