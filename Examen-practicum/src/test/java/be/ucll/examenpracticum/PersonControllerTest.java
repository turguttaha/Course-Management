package be.ucll.examenpracticum;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;

@ExtendWith(SpringExtension.class)
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerTest {
    @Autowired
    private WebTestClient client;

    private static class PersonBodyValue {
        public String firstName;
        public String lastName;
        public String email;
        public String phone;

        public PersonBodyValue(String firstName, String lastName, String email, String phone) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.phone = phone;
        }
    }
    @Test
    public void validateThatAPersonCanBeCreated(){
        PersonBodyValue value = new PersonBodyValue("testFN","testLN","test@Test","test049..");

        client.post()
                .uri("/api/v1/person")
                .bodyValue(value)
                .exchange()
                .expectStatus().isCreated()
                .expectBody().json("{\"id\":12,\"firstName\":\"testFN\",\"lastName\":\"testLN\",\"email\":\"test@Test\",\"phone\":\"test049..\"}");


    }

    @Test
    public void validateThatAPersonCanBeEdited(){
        PersonBodyValue value = new PersonBodyValue("testFNEdited","testLNEdited","test@TestEdited","test049..Edited");

        client.put()
                .uri("/api/v1/person/1")
                .bodyValue(value)
                .exchange()
                .expectStatus().isOk()
                .expectBody().json("{\"id\":1,\"firstName\":\"testFNEdited\",\"lastName\":\"testLNEdited\",\"email\":\"test@TestEdited\",\"phone\":\"test049..Edited\"}");
    }

    @Test
    public void validateThatA404IsReturnedWhenNonExistingPersonIsRequested(){

        client.get()
                .uri("/api/v1/person/15")
                .exchange()
                .expectStatus()
                .isNotFound();

    }

    @Test
    public void validateGetPeopleWithQueryParameters(){
        client.get()
                .uri("/api/v1/person?page=0&size=3")
                .exchange()
                .expectStatus().isOk()
                .expectBody().json("[{ \"id\": 1,\"firstName\": \"taha1\", \"lastName\": \"TURGUT\",\"email\": \"taha@gmail\", \"phone\": \"0493000000\"},"+
                                                "{\"id\": 2,\"firstName\": \"taha2\",\"lastName\": \"TURGUT\",\"email\": \"taha@gmail\",\"phone\": \"0493000000\"},"+
                                                "{\"id\": 3,\"firstName\": \"taha3\",\"lastName\": \"TURGUT\",\"email\": \"taha@gmail\",\"phone\": \"0493000000\"}]");



    }

}
