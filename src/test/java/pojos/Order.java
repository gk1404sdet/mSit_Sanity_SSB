package pojos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order {
    private Customer customer;
    private String amount;
    private String currency;
    private String capture_method;
    private Map<String, String> metadata;
}
