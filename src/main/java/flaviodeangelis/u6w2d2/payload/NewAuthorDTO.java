package flaviodeangelis.u6w2d2.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record NewAuthorDTO(
        @NotEmpty(message = "il nome è un campo obbligatorio")
        String name,
        @NotEmpty(message = "il nome è un campo obbligatorio")
        String surname,
        @NotEmpty(message = "il nome è un campo obbligatorio")
        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "L'email inserita non è valida")
        String email, LocalDate birthDate) {
}
