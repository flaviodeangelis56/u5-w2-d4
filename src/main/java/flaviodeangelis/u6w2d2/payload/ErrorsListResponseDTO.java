package flaviodeangelis.u6w2d2.payload;


import java.util.Date;
import java.util.List;

public record ErrorsListResponseDTO(String message,
                                    Date timestamp,
                                    List<String> errorsList) {
}
