package lt.bigbank.dragonsofmugloar.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Item implements Comparable<Item> {
    private String id;
    private String name;
    private int cost;

    @Override
    public int compareTo(Item item) {
        return this.cost - item.getCost();
    }
}
