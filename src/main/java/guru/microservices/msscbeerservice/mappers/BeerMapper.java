package guru.microservices.msscbeerservice.mappers;

import guru.microservices.msscbeerservice.domain.Beer;
import guru.microservices.msscbeerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDto beerDto);
}
