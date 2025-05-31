package mallang;

import java.util.Map;
import mallang.controller.request.SampleRequest;
import mallang.controller.response.SampleResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RestTemplateTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void success() {
        String url = "http://localhost:" + port + "/sample";
        SampleRequest request = new SampleRequest("mallang", "hi");

        SampleResponse response = restTemplate.postForObject(url, request, SampleResponse.class);

        Assertions.assertThat(response.response()).isEqualTo("hi");
    }

    @Test
    void badRequest() {
        String url = "http://localhost:" + port + "/sample";
        SampleRequest request = new SampleRequest("", "hi");

        Map response = restTemplate.postForObject(url, request, Map.class);

        Assertions.assertThat(response.get("status")).isEqualTo(400);
    }
}
