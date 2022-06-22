package lt.bigbank.dragonsofmugloar.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import lt.bigbank.dragonsofmugloar.model.GameStats;
import lt.bigbank.dragonsofmugloar.model.Item;
import lt.bigbank.dragonsofmugloar.model.ShopResponse;
import lt.bigbank.dragonsofmugloar.model.Task;
import lt.bigbank.dragonsofmugloar.model.TaskStatus;

@Service
public class ApiService {
    private final WebClient webClient = WebClient.builder()
            .baseUrl("https://dragonsofmugloar.com/api/v2/")
            .build();

    public GameStats startGame() {
        return webClient
                .post()
                .uri("game/start")
                .body(BodyInserters.empty())
                .retrieve()
                .bodyToMono(GameStats.class)
                .block();
    }

    public List<Task> getTasks(String gameId) {
        var tasks = webClient
                .get()
                .uri(gameId + "/messages")
                .retrieve()
                .bodyToMono(Task[].class)// TODO investigate how to get List here
                .block();
        if (tasks != null) {
            return Arrays.asList(tasks);
        }
        return List.of();
    }

    public TaskStatus completeTask(String gameId, String adId) {
        return webClient
                .post()
                .uri(gameId + "/solve/" + adId)
                .body(BodyInserters.empty())
                .retrieve()
                .bodyToMono(TaskStatus.class)
                .block();
    }

    public List<Item> getItems(String gameId) {
        var items = webClient
                .get()
                .uri(gameId + "/shop")
                .retrieve()
                .bodyToMono(Item[].class)
                .block();
        if (items != null) {
            return Arrays.asList(items);
        }
        return List.of();
    }

    public ShopResponse buyItem(String gameId, String itemId) {
        return webClient
                .post()
                .uri(gameId + "/shop/buy/" + itemId)
                .body(BodyInserters.empty())
                .retrieve()
                .bodyToMono(ShopResponse.class)
                .block();
    }
}
