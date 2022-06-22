package lt.bigbank.dragonsofmugloar.service;

import java.util.Comparator;

import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;
import lt.bigbank.dragonsofmugloar.model.Task;
import lt.bigbank.dragonsofmugloar.model.TaskProbability;

@NoArgsConstructor
@Component
public class TaskComparator implements Comparator<Task> {

    @Override
    public int compare(Task task1, Task task2) {
        var compareProbability = TaskProbability.getPriority(task1.getProbability()) - TaskProbability.getPriority(task2.getProbability());
        if (compareProbability == 0) {
            return task2.getReward() - task1.getReward();
        } else {
            return compareProbability;
        }
    }
}
