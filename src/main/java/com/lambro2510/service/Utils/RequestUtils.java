package com.lambro2510.service.Utils;

import com.lambro2510.service.response.ApiResponse.ShopeeItemResponse;
import com.lambro2510.service.response.ApiResponse.ShoppeeRatingData;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;

public class RequestUtils {
  private static final RestTemplate restTemplate = new RestTemplate();

  public static String SHOPPEE_URL = "https://shopee.vn/api";

  public static  <T> T request(String url, Class<T> responseType, Object requestObject, HttpMethod httpMethod) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<Object> requestEntity = new HttpEntity<>(requestObject, headers);

    ResponseEntity<T> responseEntity = restTemplate.exchange(url, httpMethod, requestEntity, responseType);

    if (responseEntity.getStatusCode() == HttpStatus.OK) {
      return responseEntity.getBody();
    } else {
      throw new RuntimeException("API request failed with status code: " + responseEntity.getStatusCode());
    }
  }

  public static ShopeeItemResponse getShoppeeItem(Integer offset){
    String requestUrl = SHOPPEE_URL + "/v4/homepage/get_daily_discover" +
        "?bundle=" + "daily_discover_main" +
        "&item_card=" + "2" +
        "&limit=" + "100" +
        "&need_tab=" + "false" +
        "&offset=" + offset.toString();
    return request(requestUrl, ShopeeItemResponse.class, null, HttpMethod.GET);
  }

  public static ShoppeeRatingData getRatingData(Long itemId, Long shopId, Integer offset){
    String requestUrl = SHOPPEE_URL + "/v2/item/get_ratings" +
        "?itemid=" + itemId +
        "&shopid=" + shopId +
        "&limit=" + "50" +
        "&offset=" + offset.toString();
    return request(requestUrl, ShoppeeRatingData.class, null, HttpMethod.GET);
  }

}
