package lt.bigbank.dragonsofmugloar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.bigbank.dragonsofmugloar.model.GameStats;
import lt.bigbank.dragonsofmugloar.model.Item;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShopService {

    private final ApiService apiService;

    void shop(GameStats gameStats) {
        var items = apiService.getItems(gameStats.getGameId());
        var healingPotionPrice = priceOfHealingPotion(items);
        if (gameStats.getLives() < 3 && healingPotionPrice != -1 && healingPotionPrice < gameStats.getGold()) {
            log.info("Buying healing potion for: " + healingPotionPrice);
            var shopResponse = apiService.buyItem(gameStats.getGameId(), "hpot");
            gameStats.updateGameStats(shopResponse);
        }
        var optionalItem = getCheapestItem(items);
        if (optionalItem.isPresent() && gameStats.getLives() == 3 && gameStats.getGold() - optionalItem.get().getCost() > healingPotionPrice) {
            var item = optionalItem.get();
            log.info("Buying " + item.getName() + " for: " + item.getCost());
            var shopResponse = apiService.buyItem(gameStats.getGameId(), item.getId());
            gameStats.updateGameStats(shopResponse);
        }
        log.info("Money left after shopping: " + gameStats.getGold());
    }

    private int priceOfHealingPotion(List<Item> items) {
        var price = items.stream()
                .filter(item -> item.getId().equalsIgnoreCase("hpot"))
                .map(Item::getCost)
                .findFirst();
        return price.orElse(-1);
    }

    private Optional<Item> getCheapestItem(List<Item> items) {
        return items.stream()
                .filter(item -> !item.getId().equalsIgnoreCase("hpot"))
                .min(Item::compareTo);
    }
}
