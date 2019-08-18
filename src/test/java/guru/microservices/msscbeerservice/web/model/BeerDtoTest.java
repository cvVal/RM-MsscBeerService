package guru.microservices.msscbeerservice.web.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import java.io.IOException;

@JsonTest
public class BeerDtoTest extends BaseTest {

    @Test
    void testSerializeDto() throws JsonProcessingException {

        BeerDto beerDto = getBeerDto();

        String jsonString = objectMapper.writeValueAsString(beerDto);

        System.out.println(jsonString);
    }

    @Test
    void testDeserialize() throws IOException {

        String json = "{\"beerId\":\"9e27c934-a9b9-4a8c-a248-687c9e554cf5\",\"version\":null,\"createdDate\":\"2019-08-04T18:51:44-0400\",\"lastModifiedDate\":\"2019-08-16T18:51:44-0400\",\"beerName\":\"Corona\",\"beerStyle\":\"LAGER\",\"upc\":123987123123,\"price\":\"12.99\",\"quantityOnHand\":null}";

        BeerDto dto = objectMapper.readValue(json, BeerDto.class);

        System.out.println(dto);
    }
}
