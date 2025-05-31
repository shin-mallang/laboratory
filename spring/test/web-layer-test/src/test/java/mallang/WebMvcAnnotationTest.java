package mallang;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import mallang.controller.request.SampleRequest;
import mallang.service.SampleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest
public class WebMvcAnnotationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean // WebMvcTest 에서는 @Service 빈은 등록되지 않기 때문에 모킹해주지 않으면 예외 발생
    private SampleService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void success() throws Exception {
        SampleRequest request = new SampleRequest("mallang", "hi");

        mockMvc.perform(MockMvcRequestBuilders.post("/sample")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response").value("hi"));
    }

    @Test
    void badRequest() throws Exception {
        SampleRequest request = new SampleRequest("", "hi");

        mockMvc.perform(MockMvcRequestBuilders.post("/sample")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}
