package pojos;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class OrderDetails {

    private String email;
    private String contact_email;
    private String firstName;
    private String lastName;
    private String contactNumber;
    private String amount;
    private String currency;
    private String captureMode;
    private String usernameKey;
    private String passwordKey;
    private Map<String, String> metadata;
}
