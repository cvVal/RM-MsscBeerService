package guru.microservices.msscbeerservice.bootstrap;

import guru.microservices.msscbeerservice.domain.Beer;
import guru.microservices.msscbeerservice.repositories.BeerRepository;
import guru.microservices.msscbeerservice.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class BeerLoader implements CommandLineRunner {

    public static final String BEER_1_UPC = "06312750024001";
    public static final String BEER_2_UPC = "06312750024002";
    public static final String BEER_3_UPC = "06312750024003";

    private final BeerRepository beerRepository;

    @Override
    public void run(String... args) throws Exception {
        if (beerRepository.count() == 0)
            loadBeerObjects();
    }

    private void loadBeerObjects() {

        beerRepository.save(Beer.builder()
                .beerName("Mango Bobs")
                .beerStyle(BeerStyleEnum.GOSE.name())
                .minOnHand(12)
                .quantityToBrew(200)
                .upc(BEER_1_UPC)
                .price(new BigDecimal("23.99"))
                .build());

        beerRepository.save(Beer.builder()
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyleEnum.PALE_ALE.name())
                .minOnHand(12)
                .quantityToBrew(200)
                .upc(BEER_2_UPC)
                .price(new BigDecimal("11.99"))
                .build());

        beerRepository.save(Beer.builder()
                .beerName("Bohemia")
                .beerStyle(BeerStyleEnum.PORTER.name())
                .minOnHand(12)
                .quantityToBrew(200)
                .upc(BEER_3_UPC)
                .price(new BigDecimal("13.99"))
                .build());
    }
}