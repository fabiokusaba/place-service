package br.com.fabiokusaba.placeservice;

import br.com.fabiokusaba.placeservice.api.PlaceRequest;
import br.com.fabiokusaba.placeservice.domain.Place;
import br.com.fabiokusaba.placeservice.domain.PlaceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PlaceServiceApplicationTests {
    public static final Place CENTRAL_PARK = new Place(1L, "Central Park", "central-park", "NY", "NY", null, null);

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    PlaceRepository placeRepository;

    @Test
    public void testCreatePlaceSuccess() {
        final String name = "Valid Name";
        final String city = "Valid City";
        final String state = "Valid State";
        final String slug = "valid-name";

        webTestClient
                .post()
                .uri("/places")
                .bodyValue(new PlaceRequest(name, city, state))
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("name").isEqualTo(name)
                .jsonPath("city").isEqualTo(city)
                .jsonPath("state").isEqualTo(state)
                .jsonPath("slug").isEqualTo(slug)
                .jsonPath("createdAt").isNotEmpty()
                .jsonPath("updatedAt").isNotEmpty();
    }

    @Test
    public void testCreatePlaceFailure() {
        final String name = "";
        final String state = "";
        final String city = "";

        webTestClient
                .post()
                .uri("/places")
                .bodyValue(new PlaceRequest(name, city, state))
                .exchange()
                .expectStatus().isBadRequest();
    }
}

