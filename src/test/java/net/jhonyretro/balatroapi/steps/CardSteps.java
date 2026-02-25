package net.jhonyretro.balatroapi.steps;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class CardSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    private Map<String, Object> orderRequest;
    private Long cardId;
    private ResponseEntity<?> response;

    // ---------- GIVEN ----------

    @Given("a card with name {string} and description {string}")
    public void givenCard(String name, String description) {
        orderRequest = new HashMap<>();
        assertThat(name).isNotNull();
        assertThat(description).isNotNull();
        orderRequest.put("name", name);
        orderRequest.put("description", description);
    }

    @Given("an existing card with name {string} and description {string}")
    public void givenExistingCard(String name, String description) {
        Map<String, Object> request = new HashMap<>();

        request.put("name", name);
        request.put("description", description);

        ResponseEntity<Map> createResponse =
                restTemplate.postForEntity("/jokers", request, Map.class);

        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(createResponse.getBody()).isNotNull();

        this.cardId = ((Number) createResponse.getBody().get("id")).longValue();
    }

    // ---------- WHEN ----------

    @When("the card is saved")
    public void whenCardIsSaved() {
        ResponseEntity<Map> createResponse =
                restTemplate.postForEntity("/jokers", orderRequest, Map.class);

        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(createResponse.getBody()).isNotNull();

        this.cardId = ((Number) createResponse.getBody().get("id")).longValue();
    }

    @When("the card is deleted")
    public void whenCardIsDeleted() {
        restTemplate.delete("/jokers/" + cardId);
    }

    @When("I request a card with id {int}")
    public void whenRequestCardById(Integer id) {
        response = restTemplate.getForEntity("/jokers/{id}", String.class, id);
    }

    @When("I delete a card with id {int}")
    public void whenDeleteCardById(Integer id) {
        restTemplate.delete("/jokers/{id}", id);
        response = restTemplate.getForEntity("/jokers/{id}", String.class, id);
    }

    @When("I request all cards")
    public void whenRequestAllCards() {
        response = restTemplate.getForEntity("/jokers", Object[].class);
    }

    @When("I create a card with invalid payload")
    public void whenCreateInvalidOrder() {
        response = restTemplate.postForEntity("/jokers", "invalid-json", String.class);
    }

    // ---------- THEN ----------

    @Then("the card is persisted successfully")
    public void thenCardPersistedSuccessfully() {
        ResponseEntity<Map> getResponse =
                restTemplate.getForEntity("/jokers/" + cardId, Map.class);

        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getResponse.getBody()).isNotNull();
        assertThat(((Number) getResponse.getBody().get("id")).longValue())
                .isEqualTo(cardId);
    }

    @Then("the card is not created")
    public void thenCardNotCreated() {
        assertThat(cardId).isNull();
    }

    @Then("the card no longer exists")
    public void thenCardNoLongerExists() {
        ResponseEntity<String> getResponse =
                restTemplate.getForEntity("/jokers/" + cardId, String.class);

        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Then("the response status should be {int}")
    public void thenResponseStatusShouldBe(Integer status) {
        assertThat(response.getStatusCode().value()).isEqualTo(status);
    }
}
