package steps

import com.jayway.restassured.RestAssured
import com.jayway.restassured.response.ValidatableResponse
import com.jayway.restassured.specification.RequestSpecification
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import entities.Customer
import entities.Properties
import services.ServiceAPIStub

class CustomerSteps {

    private RequestSpecification request
    private ValidatableResponse response
    private ServiceAPIStub serviceAPIStub = new ServiceAPIStub()

    private static Customer customer = new Customer()
    private static Properties properties = new Properties()

    private static String customerName = "vitaliy"
    private static String customerLastName = "vorona"
    private static String customerId = 1684
    private static String properitesAge = 34
    private static String properitesActive = true
    private static String properitesDOB = 160184


    @Given('^The positive scenario$')
    void given_the_positive_scenario() {
        request = RestAssured.with()
    }

    @Given('^The negative scenario$')
    void given_the_negative_scenario() {
        request = RestAssured.with()
    }


    @Given('^the positive GET Customer scenario$')
    public void the_positive_GET_Customer_scenario() {
        customer.setId(customerId)
        customer.setFirst_name(customerName)
        customer.setLast_name(customerLastName)
        properties.setAge(properitesAge)
        properties.setDate_of_birth(properitesDOB)
        properties.setActive(properitesActive)
        customer.setProperties(properties)
        serviceAPIStub.getCustomerStub(customer)
        request = RestAssured.with()
    }

    @Given('^the negative GET Customer scenario$')
    public void the_negative_GET_Customer_scenario() {
        request = RestAssured.with()
        serviceAPIStub.getCustomerStubNegative()
    }

    @When('^I provide Customers mandatory fields$')
    static void provide_Customers_mandatory_fields() {
        customer.setId(customerId)
        customer.setFirst_name(customerName)
        customer.setLast_name(customerLastName)
        ServiceAPIStub.postCustomerStub(customer)
    }

    @When('^I make a POST request to the target endpoint$')
    void make_a_POST_request_to_the_target_endpoint() {
        response = request.
                given().
                contentType("application/json").
                body(customer).
                when().
                post(ServiceAPIStub.BASE_URL + ServiceAPIStub.CUSTOMER_ENDPOINT).
                then()
    }

    @When('^I dont provide or its invalid one of Customers id \"([^\"]*)\" First Name \"([^\"]*)\" or Last Name \"([^\"]*)\" parameters$')
    static void provide_one_of_Customers_id_first_name_or_last_name_parameter(String id, String firstName, String lastName) {
        customer.setId(id)
        customer.setFirst_name(firstName)
        customer.setLast_name(lastName)
        properties.setAge(properitesAge)
        properties.setDate_of_birth(properitesDOB)
        properties.setActive(properitesActive)
        customer.setProperties(properties)
        ServiceAPIStub.postCustomerStubNegative(customer)
    }

    @When('^I make a Get request to the system with an existing id$')
    public void make_a_Get_request_to_the_system_with_an_existing_id() {
        response = request.
                given().
                contentType("application/json").
                body(customer).
                when().
                get(ServiceAPIStub.BASE_URL + ServiceAPIStub.CUSTOMER_ENDPOINT +"/"+ customerId).
                then()
    }

    @When('^I make a Get request to the system with a non existing (\\d+) id$')
    public void make_a_Get_request_to_the_system_with_an_non_existing_id(int invalidId) {
        response = request.
                given().
                contentType("application/json").
                body(customer).
                when().
                get(ServiceAPIStub.BASE_URL + ServiceAPIStub.CUSTOMER_ENDPOINT +"/"+ invalidId).
                then()
    }

    @Then('^I should get (\\d+) response code and \"([^\"]*)\" response message$')
    void should_get_successful_response_message(int responseStatusCode, String responseMessage) {
        response.statusCode(responseStatusCode)
        response.toString().contains(responseMessage)
    }

    @Then('^I should get (\\d+) Bad Request response message$')
    void should_get_Bad_Request_message(int responseStatusCode) {
        response.statusCode(responseStatusCode)
        response.toString().contains("Bad Request")
    }

    @Then('^I should get (\\d+) response code and correct full information$')
    public void should_get_response_code_and_correct_full_information(int responseStatusCode) {
        response.statusCode(responseStatusCode)
        response.toString().contains(customer.getCustomerJSONString(customer))
    }

    @Then('^I should get (\\d+) Not Found response code$')
    public void should_get_Not_Found_response_code(int responseStatusCode) {
        response.statusCode(responseStatusCode)
    }

}
