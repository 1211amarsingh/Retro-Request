package com.kv.sample;

public class Config {

    public static String getBaseUrl() {

        String env = "dev";
        String url = "http://192.168.43.43:3000/";

        if (env.equalsIgnoreCase("dev")) {
            url = "http://192.168.43.43:3000/";
        } else if (env.equalsIgnoreCase("qa")) {
            url = "http://192.168.43.43:3001/";
        } else if (env.equalsIgnoreCase("uat")) {
            url = "http://192.168.43.43:3002/";
        } else if (env.equalsIgnoreCase("live")) {
            url = "http://www.kvinfotech.com:3000";
        }
        return url;
    }
}
