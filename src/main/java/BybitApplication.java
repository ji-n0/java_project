import com.bybit.api.client.domain.CategoryType;
import com.bybit.api.client.domain.position.request.PositionDataRequest;
import com.bybit.api.client.restApi.BybitApiAsyncPositionRestClient;
import com.bybit.api.client.restApi.BybitApiAsyncTradeRestClient;
import com.bybit.api.client.restApi.BybitApiPositionRestClient;
import com.bybit.api.client.service.BybitApiClientFactory;
import config.YamlConfigLoader;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BybitApplication {
    // API Key와 Secret
    private static final String API_KEY = "FwEirBC0l1lkE2pH6D";
    private static final String API_SECRET = "smlBX2BNqw5Al7K7PM80cWX0mS8bdKGbbDSk";
    private static final BybitApiClientFactory factory = BybitApiClientFactory.newInstance(API_KEY, API_SECRET);
    private static final BybitApiAsyncTradeRestClient client = factory.newAsyncTradeRestClient();
    public static void main(String[] args) {
//        setLeverage("1000000BABYDOGEUSDT",2,2);
//        //short open / long close -> side : Sell, short close / long open -> side : Buy
//        makeOrder("1000000BABYDOGEUSDT","Sell",8800);
        DatabaseConnector dbConnector = new DatabaseConnector();
        String query = "SELECT * FROM coin_selected ORDER BY datetime DESC LIMIT 1";  // 원하는 테이블명을 입력하세요

        List<Map<String, Object>> results = dbConnector.executeSelectQuery(query);

        // 결과 출력
        for (Map<String, Object> row : results) {
            System.out.println(row);
        }
    }

    public static void setLeverage(String symbol, int buyLeverage, int sellLeverage){
        var positionClient = BybitApiClientFactory.newInstance(API_KEY, API_SECRET).newAsyncPositionRestClient();
        var setLeverageRequest = PositionDataRequest.builder().category(CategoryType.LINEAR).symbol(symbol).buyLeverage(Integer.toString(buyLeverage)).sellLeverage(Integer.toString(sellLeverage)).build();
        positionClient.setPositionLeverage(setLeverageRequest, System.out::println);
    }

    public static String makeOrder(String symbol, String side, int qty){
        String result = "SUCCESS";
        Map<String, Object> order =Map.of(
                "category", "linear",
                "symbol", symbol,
                "side", side,
                "orderType", "Market",
                "qty", Integer.toString(qty),
                "reduceOnly", false,
                "closeOnTrigger", false
        );
        client.createOrder(order, System.out::println);
        return result;
    }

}
