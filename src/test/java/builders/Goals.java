package builders;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
public class Goals {
   private int clockTime;
   private boolean confirmed;
   private int id;
   private boolean ownGoal;
   private boolean penalty;
   private String period;
   private int playerId;
   private String teamId;
}
