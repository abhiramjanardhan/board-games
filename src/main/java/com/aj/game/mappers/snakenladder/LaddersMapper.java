package com.aj.game.mappers.snakenladder;

import com.aj.game.constants.snakenladder.SnakeNLadderConstants;
import com.aj.game.models.snakenladder.Ladder;
import com.aj.game.models.snakenladder.config.SnakeNLadderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface LaddersMapper {
    LaddersMapper INSTANCE = Mappers.getMapper(LaddersMapper.class);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "start", target = "start")
    @Mapping(source = "end", target = "end")
    @Mapping(target = "type", constant = SnakeNLadderConstants.LADDER)
    Ladder detailToLadderData(SnakeNLadderDetail detail);

    List<Ladder> detailsToLaddersData(List<SnakeNLadderDetail> details);
}
