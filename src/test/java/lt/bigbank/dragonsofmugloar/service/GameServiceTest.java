package lt.bigbank.dragonsofmugloar.service;

import static org.mockito.MockitoAnnotations.openMocks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class GameServiceTest {

    @Mock
    private ApiService apiServiceMock;

    @Mock
    private ShopService shopServiceMock;

    @Mock
    private TaskComparator taskComparatorMock;

    private GameService gameService;

    @BeforeEach
    void setUp() {
        openMocks(this);
        gameService = new GameService(apiServiceMock, shopServiceMock, taskComparatorMock);
    }

    @Test
    void when_then() {

    }

}