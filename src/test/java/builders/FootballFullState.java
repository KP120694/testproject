package builders;


import lombok.*;

import java.util.ArrayList;


@Builder
@Getter
@Setter
@ToString
public class FootballFullState {
    private String homeTeam;
    private String awayTeam;
    private boolean finished;
    private int gameTimeInSeconds;
    private Goals goals;
    private String period;
    private String possibles;
    private String corners;
    private String redCards;
    private String yellowCards;
    private String startDateTime;
    private boolean started;
    private ArrayList<Teams> teams;

}
