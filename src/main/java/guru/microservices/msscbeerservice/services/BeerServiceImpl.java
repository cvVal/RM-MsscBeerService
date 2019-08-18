package guru.microservices.msscbeerservice.services;

import guru.microservices.msscbeerservice.bootstrap.BeerLoader;
import guru.microservices.msscbeerservice.domain.Beer;
import guru.microservices.msscbeerservice.exceptions.NotFoundException;
import guru.microservices.msscbeerservice.mappers.BeerMapper;
import guru.microservices.msscbeerservice.repositories.BeerRepository;
import guru.microservices.msscbeerservice.web.model.BeerDto;
import guru.microservices.msscbeerservice.web.model.BeerPagedList;
import guru.microservices.msscbeerservice.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerMapper beerMapper;
    private final BeerRepository beerRepository;

    @Override
    public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest) {

        BeerPagedList beerPagedList;
        Page<Beer> beerPage;

        if (!StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle))
            beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);

        else if (!StringUtils.isEmpty(beerName) && StringUtils.isEmpty(beerStyle))
            beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);

        else if (StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle))
            beerPage = beerRepository.findAllByBeerStyle(beerStyle, pageRequest);

        else
            beerPage = beerRepository.findAll(pageRequest);

        beerPagedList = new BeerPagedList(beerPage.getContent()
                .stream()
                .map(beerMapper::beerToBeerDto)
                .collect(Collectors.toList()),
                PageRequest.of(beerPage.getPageable().getPageNumber(), beerPage.getPageable().getPageSize()),
                beerPage.getTotalElements());

        return beerPagedList;
    }

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
        beer.setUpc(BeerLoader.BEER_1_UPC);

        return beerMapper.beerToBeerDto(beerRepository.save(beer));
    }
}