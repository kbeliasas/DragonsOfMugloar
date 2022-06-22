package lt.bigbank.dragonsofmugloar.model;

import java.util.Arrays;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TaskProbability {
    PIECE_OF_CAKE("Piece of cake", 1),
    WALK_IN_THE_PARK("Walk in the park", 1),
    SURE_THING("Sure thing", 1),
    QUITE_LIKELY("Quite likely", 2),
    GAMBLE("Gamble", 3),
    RATHER_DETRIMENTAL("Rather detrimental", 4),
    RISKY("Risky", 4),
    HMMM("Hmmm....", 4),
    PLAYING_WITH_FIRE("Playing with fire", 4),
    SUICIDE_MISSION("Suicide mission", 5),
    UNKNOWN("", 6);

    private final String probability;
    private final int priority;

    public static int getPriority(String probability) {
        var taskProbability = Arrays.stream(TaskProbability.values())
                .filter(taskProb -> taskProb.probability.equalsIgnoreCase(probability))
                .findFirst();
        return taskProbability.orElse(UNKNOWN).priority;
    }
}
