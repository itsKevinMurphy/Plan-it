package com.plan_it.mobile.plan_it;

/**
 * Created by Kristian on 09/11/2015.
 */
import com.loopj.android.http.*;

public class RestClient {
    private static final String BASE_URL = "http://planit.lukefarnell.ca:3000/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.addHeader("x-access-token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfaWQiOiI1NjQ5NTA3NjI2MWE2YTQxN2JiOWYwNmEiLCJVc2VySUQiOjE5LCJmcmllbmRseU5hbWUiOiJUZXN0IiwiZW1haWwiOiJUZXN0QGdtYWlsLmNvbSIsImhhc2hQYXNzd29yZCI6IiQyYSQxMCRmWEUzbzRMekNkcElTemIwS3FORGR1LlJTNy5xYkxDejR4d1J1M3FhakxsMUIxd1RPMkpsRyIsImZpcnN0TmFtZSI6IlRlc3QiLCJsYXN0TmFtZSI6IlVzZXIiLCJfX3YiOjAsImV2ZW50cyI6W10sImZyaWVuZExpc3QiOltdfQ.HfKiDjbqDxfsAFao41BjaiE9pMFB0CLVIIgg_asGUCk");
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.addHeader("x-access-token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfaWQiOiI1NjQ5NTA3NjI2MWE2YTQxN2JiOWYwNmEiLCJVc2VySUQiOjE5LCJmcmllbmRseU5hbWUiOiJUZXN0IiwiZW1haWwiOiJUZXN0QGdtYWlsLmNvbSIsImhhc2hQYXNzd29yZCI6IiQyYSQxMCRmWEUzbzRMekNkcElTemIwS3FORGR1LlJTNy5xYkxDejR4d1J1M3FhakxsMUIxd1RPMkpsRyIsImZpcnN0TmFtZSI6IlRlc3QiLCJsYXN0TmFtZSI6IlVzZXIiLCJfX3YiOjAsImV2ZW50cyI6W10sImZyaWVuZExpc3QiOltdfQ.HfKiDjbqDxfsAFao41BjaiE9pMFB0CLVIIgg_asGUCk");
        client.post(getAbsoluteUrl(url), params, responseHandler);

    }
    public static void put(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.put(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void delete(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.delete(getAbsoluteUrl(url), params, responseHandler);

    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}