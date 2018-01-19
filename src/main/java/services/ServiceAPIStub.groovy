package services

import com.github.tomakehurst.wiremock.client.WireMock
import entities.Customer

class ServiceAPIStub {

    final static String BASE_URL = "http://localhost:8089"
    final static String CUSTOMER_ENDPOINT = "/rest/api/customer"


    static void postCustomerStub(Customer customer) {
        WireMock.stubFor(WireMock.post(WireMock.urlEqualTo(CUSTOMER_ENDPOINT))
                .withRequestBody(WireMock.equalToJson(customer.getCustomerJSONString(customer)))
                .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withStatus(201)
                .withBody("{\n \"id\" : $customer.id \n, \"status\" : \"successfully created\"}")))
    }

    static void postCustomerStubNegative(Customer customer) {
        WireMock.stubFor(WireMock.post(WireMock.urlEqualTo(CUSTOMER_ENDPOINT))
                .withRequestBody(WireMock.equalToJson(customer.getCustomerJSONString(customer)))
                .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withStatus(400)
                .withBody("{\"status\" : \"Bad Request\"}")))
    }


    static void getCustomerStub(Customer customer) {
        WireMock.stubFor(WireMock.get(WireMock.urlMatching(CUSTOMER_ENDPOINT + "/[0-9]+"))
                .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withStatus(200)
                .withBody(customer.getCustomerJSONString(customer))))

    }

    static void getCustomerStubNegative() {
        WireMock.stubFor(WireMock.get(WireMock.urlMatching(CUSTOMER_ENDPOINT + "/[0-9]+"))
                .willReturn(WireMock.aResponse()
                .withStatus(404)))
    }
}
