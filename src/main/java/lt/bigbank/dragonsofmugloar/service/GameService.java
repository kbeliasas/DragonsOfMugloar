package lt.bigbank.dragonsofmugloar.service;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.bigbank.dragonsofmugloar.model.Task;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameService implements CommandLineRunner {

    private final ApiService apiService;
    private final ShopService shopService;
    private final TaskComparator taskComparator;

    @Override
    public void run(String... args) {

        var gameStats = apiService.startGame();

        log.info("GAMEID: " + gameStats.getGameId());

        while (gameStats.getLives() >= 1) {
            shopService.shop(gameStats);
            var tasks = apiService.getTasks(gameStats.getGameId());
            var task = chooseTask(tasks);

            log.info("Starting task with probability: " + task.getProbability() + " and reward: " + task.getReward());
            var taskStatus = apiService.completeTask(gameStats.getGameId(), task.getAdId());
            log.info("Task status: " + taskStatus.getMessage());

            gameStats.updateGameStats(taskStatus);

        }
        log.info(gameStats.toString());
        //System.exit(200);
    }

    private Task chooseTask(List<Task> tasks) {
        tasks.sort(taskComparator);
        return tasks.get(0);
    }
}
