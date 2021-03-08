package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.github.sparkmuse.wiremock.Wiremock;
import com.github.sparkmuse.wiremock.WiremockExtension;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.verification.LoggedRequest;

@ExtendWith(WiremockExtension.class)
class AppTest {

    @Wiremock
    private WireMockServer wireMock;

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final HttpRequest defaultRequest = HttpRequest.newBuilder(new URI("http://localhost:8080/test")).GET().build();

    AppTest() throws URISyntaxException {
    }

    /**
     * One of the tests will fail. depends of the order, second executed will fail
     */

    @Test
    void test1() throws IOException, InterruptedException {
        httpClient.send(defaultRequest, HttpResponse.BodyHandlers.ofString());

        // here we've made only one call

        // as we stubbed nothing -- all requests will be unmatched
        List<LoggedRequest> allUnmatchedRequests = wireMock.findAllUnmatchedRequests();
        Assertions.assertEquals(1, allUnmatchedRequests.size());
    }

    @Test
    void test2() throws IOException, InterruptedException {
        httpClient.send(defaultRequest, HttpResponse.BodyHandlers.ofString());

        // here we've made only one call

        // as we stubbed nothing -- all requests will be unmatched
        List<LoggedRequest> allUnmatchedRequests = wireMock.findAllUnmatchedRequests();
        Assertions.assertEquals(1, allUnmatchedRequests.size());
    }
}