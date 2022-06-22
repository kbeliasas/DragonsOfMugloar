package lt.bigbank.dragonsofmugloar.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Task {

    private String adId;
    private String message;
    private int reward;
    private int expiresIn;
    private String probability;

    public String toString() {
        var str = """
                
                taskId: %s
                reward: %s
                probability: %s
                                
                """;
        return String.format(str, adId, reward, probability);
    }
}
