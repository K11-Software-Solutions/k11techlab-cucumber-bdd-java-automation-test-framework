package bdd.apitests.googleapis.stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.containsInAnyOrder;

import java.util.Map;
import java.util.Properties;

import io.appium.java_client.AppiumDriver;
import org.apache.commons.lang3.math.NumberUtils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;

import org.k11techlab.bdd.coreframework.configurationmanager.*;
import org.k11techlab.bdd.webuitestframework.configManager.ConfigurationManager;

public class GoogleAPIGetBooksByISBNSteps {

	private Properties properties;
	private final String endpointGetBookByIsbn= "https://www.googleapis.com/books/v1/volumes";
	private Response response;
	private RequestSpecification request;

	@Given("^there is a book with the ISBN ([0-9X\\-]+)$")
	public void a_book_exists_with_isbn(String isbn) {
		int timeout = 5000; // Timeout in milliseconds

		// Configuring the timeouts using RestAssuredConfig
		RestAssuredConfig config = RestAssuredConfig.config().httpClient(HttpClientConfig.httpClientConfig()
				.setParam("http.connection.timeout", timeout)
				.setParam("http.socket.timeout", timeout));

		request = given()
				.config(config)
				.param("q", "isbn:" + isbn);
	}

	@When("the book is fetched by its ISBN")
	public void a_user_retrieves_the_book_by_isbn() {
		try {
			response = request.when().get(endpointGetBookByIsbn);
		} catch (Exception e) {
			System.err.println("Failed to connect to the API: " + e.getMessage());
			throw e; // Re-throwing to ensure the test fails appropriately.
		}
	}


	@Then("the status code is {int}")
	public void verify_status_code(int statusCode) {
		response.then().statusCode(statusCode);
	}

	@And("the response should contain:")
	public void response_includes(Map<String, String> responseFields) {
		responseFields.forEach((key, value) ->
				response.then().body(key, equalTo(NumberUtils.isCreatable(value) ? Integer.parseInt(value) : value))
		);
	}

	@And("response includes the following details in any order:")
	public void response_includes_in_any_order(Map<String, String> responseDetails) {
		responseDetails.forEach((path, value) ->
				response.then().body(path, containsInAnyOrder(value)) // This line may need adjustment depending on actual API response structure
		);
	}

	@And("the book has the following details:")
	public void the_book_has_the_following_details(Map<String, String> details) {
		details.forEach((key, value) -> {
			response.then().assertThat().body(key, equalTo(value),
					"Expected " + key + " to be " + value + " but was " + response.path(key));
		});
	}
}
