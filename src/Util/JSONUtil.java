package Util;

import com.google.gson.Gson;

public class JSONUtil {
    private static final Gson gson = new Gson();

    public static <T> String serialize(T obj) {
        return gson.toJson(obj);
    }

    public static <T> T deSerialize(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }
}
