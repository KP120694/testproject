package builders;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@Getter
@Setter
@Builder
public class Teams {
    private String association;
    private String name;
    private String teamId;
}
