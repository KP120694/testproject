package builders;


import lombok.*;

@Builder
@Setter
@ToString
public class FixtureStatus {
    private boolean displayed;
    private boolean suspended;
}
