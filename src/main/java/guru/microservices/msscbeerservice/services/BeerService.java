package guru.microservices.msscbeerservice.services;

import guru.microservices.msscbeerservice.web.model.BeerDto;
import guru.microservices.msscbeerservice.web.model.BeerPagedList;
import guru.microservices.msscbeerservice.web.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {

    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand);
    BeerDto getById(UUID beerId, Boolean showInventoryOnHand);
    BeerDto getByUpc(String upc);
    BeerDto saveNewBeer(BeerDto beerDto);
    BeerDto updateBeer(UUID beerId, BeerDto beerDto);
}