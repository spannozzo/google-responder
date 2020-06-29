package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

import static org.hamcrest.MatcherAssert.assertThat; 

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

import java.util.Arrays;

import org.acme.entity.QueryResult;

@QuarkusTest
public class ExampleResourceTest {

    @Test
    public void testHelloEndpoint() {
     
    	QueryResult[] results=
        given()
        .when().get("/ask/who is the president of france")
        .then()
           .statusCode(200)
           .body("size()", is(greaterThanOrEqualTo(3))
                   
           )
           .time(lessThanOrEqualTo(3000L))
           .extract().as(QueryResult[].class);
    	
    	Arrays.stream(results).forEach(e->{
    		assertThat(e.getResponse(),is(not("")));
    		assertThat(e.getQuestion(),is(not("")));
    	});
    	
    	assertThat(results[0].getResponse(),is("Emmanuel Macron"));
    	
    }

}
