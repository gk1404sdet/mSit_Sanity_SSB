package pojos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customer {
    private String email;
    private String contact_email;
    private String first_name;
    private String last_name;
    private String phone;
    private String id;
}
