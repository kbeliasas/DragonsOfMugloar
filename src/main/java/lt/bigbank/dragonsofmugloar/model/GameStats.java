package lt.bigbank.dragonsofmugloar.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class GameStats {
    private String gameId;
    private int lives;
    private int gold;
    private int level;
    private int score;
    private int highScore;
    private int turn;

    public void updateGameStats(TaskStatus taskStatus) {
        lives = taskStatus.getLives();
        gold = taskStatus.getGold();
        score = taskStatus.getScore();
        turn = taskStatus.getTurn();
    }

    public void updateGameStats(ShopResponse shopResponse) {
        gold = shopResponse.getGold();
        lives = shopResponse.getLives();
        level = shopResponse.getLevel();
        turn = shopResponse.getTurn();
    }
}
