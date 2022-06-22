package lt.bigbank.dragonsofmugloar.service;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import lt.bigbank.dragonsofmugloar.model.GameStats;
import lt.bigbank.dragonsofmugloar.model.Item;
import lt.bigbank.dragonsofmugloar.model.ShopResponse;

class ShopServiceTest {
    @Mock
    private ApiService apiServiceMock;

    private ShopService shopService;

    @BeforeEach
    void setUp() {
        openMocks(this);
        shopService = new ShopService(apiServiceMock);

        when(apiServiceMock.getItems(anyString())).thenReturn(createShopItems());
    }

//    @AfterAll
//    void tearDown() {
//
//    }

    @Test
    void whenMoneyIsLow_thenDoNothing() {

        shopService.shop(createGameStats(10, 3));

        verify(apiServiceMock, never()).buyItem(eq("TEST_ID"), anyString());
        verify(apiServiceMock, times(1)).getItems(anyString());
    }

    @Test
    void whenHPIsLow_thenBuyHP() {
        when(apiServiceMock.buyItem("TEST_ID", "hpot")).thenReturn(new ShopResponse());

        var gameStats = createGameStats(60, 2);
        shopService.shop(gameStats);

        verify(apiServiceMock, times(1)).buyItem("TEST_ID","hpot");
        verify(apiServiceMock, times(1)).getItems(anyString());

    }

    @Test
    void whenHPIsLowAndMoneyIsHigh_thenBuyHPAndItem() {
        var shopResponse = new ShopResponse();
        shopResponse.setGold(210);
        shopResponse.setLives(3);
        when(apiServiceMock.buyItem("TEST_ID", "hpot")).thenReturn(shopResponse);
        when(apiServiceMock.buyItem("TEST_ID", "1")).thenReturn(new ShopResponse());

        var gameStats = createGameStats(260, 2);
        shopService.shop(gameStats);

        verify(apiServiceMock, times(1)).buyItem("TEST_ID","hpot");
        verify(apiServiceMock, times(1)).buyItem("TEST_ID","1");
        verify(apiServiceMock, times(1)).getItems(anyString());

    }

    @Test
    void whenHPIsVeryLowAndMoneyIsHigh_thenBuyHP() {
        var shopResponse = new ShopResponse();
        shopResponse.setGold(210);
        shopResponse.setLives(2);
        when(apiServiceMock.buyItem("TEST_ID", "hpot")).thenReturn(shopResponse);

        var gameStats = createGameStats(260, 1);
        shopService.shop(gameStats);

        verify(apiServiceMock, times(1)).buyItem("TEST_ID","hpot");
        verify(apiServiceMock, never()).buyItem("TEST_ID","1");
        verify(apiServiceMock, times(1)).getItems(anyString());

    }

    @Test
    void whenMoneyIsHigh_thenBuyItem() {
        when(apiServiceMock.buyItem("TEST_ID", "1")).thenReturn(new ShopResponse());

        var gameStats = createGameStats(260, 3);
        shopService.shop(gameStats);

        verify(apiServiceMock, never()).buyItem("TEST_ID","hpot");
        verify(apiServiceMock, times(1)).buyItem("TEST_ID","1");
        verify(apiServiceMock, times(1)).getItems(anyString());

    }

    private GameStats createGameStats(int gold, int lives) {
        var gameStats = new GameStats();
        gameStats.setGold(gold);
        gameStats.setLives(lives);
        gameStats.setGameId("TEST_ID");
        return gameStats;
    }

    private List<Item> createShopItems() {
        var items = new ArrayList<Item>();
        var hp = new Item();
        hp.setId("hpot");
        hp.setCost(50);
        hp.setName("Healing potion");
        items.add(hp);

        var item1 = new Item();
        item1.setId("1");
        item1.setCost(100);
        item1.setName("Some item");
        items.add(item1);

        var item2 = new Item();
        item2.setId("2");
        item2.setCost(150);
        item2.setName("Some item");
        items.add(item2);

        return items;
    }
}