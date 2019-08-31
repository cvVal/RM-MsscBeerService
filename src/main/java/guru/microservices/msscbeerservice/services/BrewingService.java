package guru.microservices.msscbeerservice.services;

import guru.microservices.msscbeerservice.config.JmsConfig;
import guru.microservices.msscbeerservice.domain.Beer;
import guru.microservices.msscbeerservice.events.BrewBeerEvent;
import guru.microservices.msscbeerservice.mappers.BeerMapper;
import guru.microservices.msscbeerservice.repositories.BeerRepository;
import guru.microservices.msscbeerservice.services.inventory.BeerInventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrewingService {

    private final BeerInventoryService beerInventoryService;
    private final BeerRepository beerRepository;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper beerMapper;

    @Scheduled(fixedRate = 5000)
    public void checkForLowInventory() {

        List<Beer> beers = beerRepository.findAll();

        beers.forEach(beer -> {
            Integer invQOH = beerInventoryService.getOnHandInventory(beer.getId());

            log.debug("Min onHand is: {}", beer.getMinOnHand());
            log.debug("Inventory is: {}", invQOH);

            if (beer.getMinOnHand() >= invQOH) {
                jmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE, new BrewBeerEvent(beerMapper.beerToBeerDto(beer)));
            }
        });
    }
}
