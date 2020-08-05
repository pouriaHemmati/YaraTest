package org.pouria.yara.util;

public class UrlsManager {
    public static String BASE_URL = "http://www.omdbapi.com/";
    public static String getUrl(String url) {
        return BASE_URL + url;
    }

}
