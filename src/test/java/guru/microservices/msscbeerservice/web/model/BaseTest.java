package guru.microservices.msscbeerservice.web.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.microservices.msscbeerservice.bootstrap.BeerLoader;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

public class BaseTest {

    @Autowired
    ObjectMapper objectMapper;

    BeerDto getBeerDto() {
        return BeerDto.builder()
                .beerName("Corona")
                .beerStyle(BeerStyleEnum.LAGER)
                .id(UUID.randomUUID())
                .createdDate(OffsetDateTime.now())
                .lastModifiedDate(OffsetDateTime.now())
                .localDate(LocalDate.now())
                .price(new BigDecimal("12.99"))
                .upc(BeerLoader.BEER_2_UPC)
                .build();
    }
}
