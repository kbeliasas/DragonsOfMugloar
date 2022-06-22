package lt.bigbank.dragonsofmugloar.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskStatus {
    private boolean success;
    private int lives;
    private int gold;
    private int score;
    private int highScore;
    private int turn;
    private String message;
}
