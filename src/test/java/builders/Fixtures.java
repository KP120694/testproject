package builders;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class Fixtures {

    private String fixtureId;
    private FixtureStatus fixtureStatus;
    private FootballFullState footballFullState;
}
