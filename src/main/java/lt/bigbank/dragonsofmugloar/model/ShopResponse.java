package lt.bigbank.dragonsofmugloar.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ShopResponse {
    private boolean shoppingSuccess;
    private int gold;
    private int lives;
    private int level;
    private int turn;
}
