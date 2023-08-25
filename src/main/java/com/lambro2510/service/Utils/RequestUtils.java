package com.lambro2510.service.Utils;

import com.lambro2510.service.response.ApiResponse.EasyPeasyTextResponse;
import com.lambro2510.service.response.ApiResponse.ShopeeItemResponse;
import com.lambro2510.service.response.ApiResponse.ShoppeeRatingData;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class RequestUtils {
  private static final RestTemplate restTemplate = new RestTemplate();

  public static String SHOPPEE_URL = "https://shopee.vn/api";

  public static String EASY_PEASY_URL = "https://easy-peasy.ai";

  public static String EASY_PEASY_TOKEN = "eyJhbGciOiJIUzI1NiIsImtpZCI6IkJwNkZwajFydlhlenFNZ1QiLCJ0eXAiOiJKV1QifQ.eyJhdWQiOiJhdXRoZW50aWNhdGVkIiwiZXhwIjoxNjkyOTQ3NDY4LCJpYXQiOjE2OTI5NDM4NjgsImlzcyI6Imh0dHBzOi8vaHR0cHM6Ly9mZGN6dnhtd3dqd3B3YmVlcWN0aC5zdXBhYmFzZS5jby9hdXRoL3YxIiwic3ViIjoiNmJkMTRmMTYtMDU4NC00NWIxLWJmMjYtZTE2Yzc1N2JiZDcyIiwiZW1haWwiOiJsYW1icm8yNTEwQGdtYWlsLmNvbSIsInBob25lIjoiIiwiYXBwX21ldGFkYXRhIjp7InByb3ZpZGVyIjoiZ29vZ2xlIiwicHJvdmlkZXJzIjpbImdvb2dsZSJdfSwidXNlcl9tZXRhZGF0YSI6eyJhdmF0YXJfdXJsIjoiaHR0cHM6Ly9saDMuZ29vZ2xldXNlcmNvbnRlbnQuY29tL2EvQUFjSFR0Y2t2VExzQWVmckdlRmdFdkZRa1RHNkNSaUtlS2Vza09NaGtSdTJVTzRFPXM5Ni1jIiwiZW1haWwiOiJsYW1icm8yNTEwQGdtYWlsLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJmdWxsX25hbWUiOiJMw6ogxJDDrG5oIEzDom0iLCJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJuYW1lIjoiTMOqIMSQw6xuaCBMw6JtIiwicGljdHVyZSI6Imh0dHBzOi8vbGgzLmdvb2dsZXVzZXJjb250ZW50LmNvbS9hL0FBY0hUdGNrdlRMc0FlZnJHZUZnRXZGUWtURzZDUmlLZUtlc2tPTWhrUnUyVU80RT1zOTYtYyIsInByb3ZpZGVyX2lkIjoiMTA3NjUxMjI4NzYzNzA4Nzc3NDA0Iiwic3ViIjoiMTA3NjUxMjI4NzYzNzA4Nzc3NDA0In0sInJvbGUiOiJhdXRoZW50aWNhdGVkIiwiYWFsIjoiYWFsMSIsImFtciI6W3sibWV0aG9kIjoib2F1dGgiLCJ0aW1lc3RhbXAiOjE2OTI5MzkyMzl9XSwic2Vzc2lvbl9pZCI6Ijg3YTc1MjkzLTdmOTktNGU1OS1iNGExLWEyYjU3MDE1ZmMyNSJ9.QDetaiTYJ5ZnqGKcSsLCI0IO9ZQ3a8t_BYs6S6kaPLY";
  public static  <T> T request(String url, Class<T> responseType,HttpHeaders headers, Object requestObject, HttpMethod httpMethod) {
    if(headers == null){
      headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
    }

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
    return request(requestUrl, ShopeeItemResponse.class,null, null, HttpMethod.GET);
  }

  public static ShoppeeRatingData getRatingData(Long itemId, Long shopId, Integer offset){
    String requestUrl = SHOPPEE_URL + "/v2/item/get_ratings" +
        "?itemid=" + itemId +
        "&shopid=" + shopId +
        "&limit=" + "50" +
        "&offset=" + offset.toString();
    return request(requestUrl, ShoppeeRatingData.class, null,null, HttpMethod.GET);
  }

  public static List<EasyPeasyTextResponse> getText(String keyword, String extra1, String tone){
    String requestUrl = EASY_PEASY_URL + "/api/generate";
    Map<String, Object> body = new HashMap<>();
    body.put("keyword", keyword);
    body.put("extra1", extra1);
    body.put("tone", tone);
    body.put("length", "long");
    body.put("preset", "paragraph-writer");
    body.put("outputs", 1);
    body.put("language", "Vietnamese");
    HttpHeaders headers = new HttpHeaders();
    headers.put("authorization", Collections.singletonList(EASY_PEASY_TOKEN));
    String data = request(requestUrl, String.class,headers,  body, HttpMethod.GET);
    return JsonParser.arrayList(data, EasyPeasyTextResponse.class);
  }

  public static void main(String[] args) {
    System.out.println(RequestUtils.getText("Viết 1 bình luận tồi tệ về 1 sản phẩm tivi", "", "funny"));
  }
}
