package mallang.controller.request;

import jakarta.validation.constraints.NotBlank;

public record SampleRequest(
        @NotBlank(message = "이름은 공백이어서는 안됩니다.")
        String name,
        String message
) {
}
