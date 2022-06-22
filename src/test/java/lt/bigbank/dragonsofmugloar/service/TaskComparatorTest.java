package lt.bigbank.dragonsofmugloar.service;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lt.bigbank.dragonsofmugloar.model.Task;

class TaskComparatorTest {

    private TaskComparator taskComparator;

    @BeforeEach
    void setUp() {
        taskComparator = new TaskComparator();
    }

    @Test
    void whenSortingTasks_thenSortByProbabilityAndReward() {
        var tasks = new ArrayList<Task>();
        var piece_of_cake = createTask("Piece of cake", 10);
        tasks.add(piece_of_cake);
        var sure_thing = createTask("Sure thing", 100);
        tasks.add(sure_thing);
        var hmmm = createTask("Hmmm....", 300);
        tasks.add(hmmm);

        tasks.sort(taskComparator);

        assertThat(tasks).containsExactly(sure_thing, piece_of_cake, hmmm);

    }

    private Task createTask(String probability, int reward) {
        var task = new Task();
        task.setProbability(probability);
        task.setReward(reward);
        return task;
    }

}