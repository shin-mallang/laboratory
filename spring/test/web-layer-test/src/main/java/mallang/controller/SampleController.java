package mallang.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mallang.controller.request.SampleRequest;
import mallang.controller.response.SampleResponse;
import mallang.service.SampleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class SampleController {

    private final SampleService service;

    @PostMapping("/sample")
    public SampleResponse hi(
            @Valid @RequestBody SampleRequest request
    ) {
        log.info("Controller: {}: {}", request.name(), request.message());
        service.hi(request.name(), request.message());
        return new SampleResponse(request.message());
    }
}
