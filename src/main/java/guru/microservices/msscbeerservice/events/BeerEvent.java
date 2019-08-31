package guru.microservices.msscbeerservice.events;

import guru.microservices.msscbeerservice.web.model.BeerDto;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@RequiredArgsConstructor
public class BeerEvent implements Serializable {

    static final long serialVersionUID = 6458513208959179611L;

    private final BeerDto beerDto;
}
