package br.com.fabiokusaba.placeservice.util;

import br.com.fabiokusaba.placeservice.domain.Place;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

public class QueryBuilder {
    private QueryBuilder() {

    }

    public static Example<Place> makeQuery(Place planet) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase().withIgnoreNullValues();
        return Example.of(planet, exampleMatcher);
    }
}
