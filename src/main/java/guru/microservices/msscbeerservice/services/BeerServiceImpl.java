package guru.microservices.msscbeerservice.services;

import guru.microservices.msscbeerservice.domain.Beer;
import guru.microservices.msscbeerservice.exceptions.NotFoundException;
import guru.microservices.msscbeerservice.mappers.BeerMapper;
import guru.microservices.msscbeerservice.repositories.BeerRepository;
import guru.microservices.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {


    private final BeerMapper beerMapper;
    private final BeerRepository beerRepository;

    @Override
    public BeerDto getById(UUID beerId) {
        return beerMapper.beerToBeerDto(beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {

        Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);

        beer.setBeerName("Corona");
        beer.setBeerStyle("Light");
        beer.setPrice(BigDecimal.valueOf(9.99));
        beer.setUpc(1238993889991L);

        return beerMapper.beerToBeerDto(beerRepository.save(beer));
    }
}