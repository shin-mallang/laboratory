package mallang.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SampleService {

    public void hi(String name, String message) {
        log.info("Service: {}: {}", name, message);
    }
}
