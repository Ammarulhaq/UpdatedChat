package com.example.conversationlisting.retrofitservices;

//  Copyright © 2017 AirFive. All rights reserved.


import androidx.annotation.Nullable;

import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

public class RetrofitJSONResponse extends JSONObject {

    /**
     * Gets value from given JSONObject using the specified key.
     * For Generic Types; use {@link TypeToken}
     * <br/>e.g. {@code new TypeToken<List<Model>>(){}.getType()}
     *
     * @param jsonObject JSON Object to retrieve value from
     * @param key        Key value to find object against
     * @param type       Type of expected object class
     * @param <T>        Generic type of value
     * @return Value found against the key or null if not found
     */
    @Nullable
    public static <T> T get(JSONObject jsonObject, String key, Type type) {
        return get(jsonObject, key, null, type);
    }

    /**
     * Gets value from JSONObject using the specified key
     * To get Generic Types (e.g. {@link java.util.List}), see {@link #get(String, Type)}
     *
     * @param jsonObject JSON Object to retrieve value from
     * @param key        Key value to find object against
     * @param clazz      Class of expected object
     * @param <T>        Generic type of value
     * @return Value found against the key or null if not found
     */
    @Nullable
    public static <T> T get(JSONObject jsonObject, String key, Class<? extends T> clazz) {
        return get(jsonObject, key, null, clazz);
    }

    /**
     * Gets value from given JSONObject using the specified key.
     * For Generic Types; use {@link TypeToken}
     * <br/>e.g. {@code new TypeToken<List<Model>>(){}.getType()}
     * <br/>Use {@code @SuppressWarnings("ConstantConditions")} to suppress Nullable warning;
     * if you are sure that you have not passed null in default value
     *
     * @param jsonObject JSON Object to retrieve value from
     * @param key        Key value to find object against
     * @param defValue   Default value if no value found.
     * @param type       Type of expected object class
     * @param <T>        Generic type of value
     * @return Value found against the key or defValue if not found
     */
    @SuppressWarnings("unchecked")
    public static <T> T get(JSONObject jsonObject, String key, @Nullable T defValue, Type type) {
        try {
            Object value = jsonObject.has(key) ? jsonObject.get(key) : null;
            if (value != null) {
                if (value.getClass() == type) {
                    return (T) value;
                } else if (value instanceof String || value instanceof JSONObject || value instanceof JSONArray) {
                    return RetrofitConfigurator.GSON.fromJson(value.toString(), type);
                }
            }
        } catch (JSONException | JsonSyntaxException e) {
            RetrofitConfigurator.LOG(e.getMessage());
        }
        return defValue;
    }

    /**
     * Gets value from JSONObject using the specified key.
     * To get Generic Types (e.g. {@link java.util.List}), see {@link #get(JSONObject, String, Object, Type)}
     * <br/>Use {@code @SuppressWarnings("ConstantConditions")} to suppress Nullable warning;
     * if you are sure that you have not passed null in default value
     *
     * @param jsonObject JSON Object to retrieve value from
     * @param key        Key value to find object against
     * @param defValue   Default value if no value found.
     * @param clazz      Class of expected object
     * @param <T>        Generic type of value
     * @return Value found against the key or defValue if not found
     */
    public static <T> T get(JSONObject jsonObject, String key, @Nullable T defValue, Class<? extends T> clazz) {
        try {
            Object value = jsonObject.has(key) ? jsonObject.get(key) : null;
            if (value != null) {
                if (clazz.isAssignableFrom(value.getClass())) {
                    return clazz.cast(value);
                } else if (value instanceof String || value instanceof JSONObject || value instanceof JSONArray) {
                    return RetrofitConfigurator.GSON.fromJson(value.toString(), clazz);
                }
            }
        } catch (JSONException | JsonSyntaxException e) {
            RetrofitConfigurator.LOG(e.getMessage());
        }
        return defValue;
    }

    /**
     * Created Mapping of JSON object using the provided json string
     *
     * @param json JSON String to create object from
     * @throws JSONException If invalid json string provided
     */
    public RetrofitJSONResponse(String json) throws JSONException {
        super(json);
    }

    /**
     * Gets value from JSONObject using the specified key.
     * For Generic Types; use {@link TypeToken}
     * <br/>e.g. {@code new TypeToken<List<Model>>(){}.getType()}
     *
     * @param key  Key value to find object against
     * @param type Type of expected object class
     * @param <T>  Generic type of value
     * @return Value found against the key or null if not found
     */
    @Nullable
    public <T> T get(String key, Type type) {
        return get(this, key, null, type);
    }

    /**
     * Gets value from JSONObject using the specified key.
     * For Generic Types; use {@link TypeToken}
     * <br/>e.g. {@code new TypeToken<List<Model>>(){}.getType()}
     * <br/>Use {@code @SuppressWarnings("ConstantConditions")} to suppress Nullable warning;
     * if you are sure that you have not passed null in default value
     *
     * @param key      Key value to find object against
     * @param defValue Default value if no value found.
     * @param type     Type of expected object class
     * @param <T>      Generic type of value
     * @return Value found against the key or defValue if not found
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String key, @Nullable T defValue, Type type) {
        return get(this, key, defValue, type);
    }

    /**
     * Gets value from JSONObject using the specified key
     * To get Generic Types (e.g. {@link java.util.List}), see {@link #get(String, Type)}
     *
     * @param key   Key value to find object against
     * @param clazz Class of expected object
     * @param <T>   Generic type of value
     * @return Value found against the key or null if not found
     */
    @Nullable
    public <T> T get(String key, Class<? extends T> clazz) {
        return get(this, key, null, clazz);
    }

    /**
     * Gets value from JSONObject using the specified key
     * To get Generic Types (e.g. {@link java.util.List}), see {@link #get(String, Object, Type)}
     * <br/>Use {@code @SuppressWarnings("ConstantConditions")} to suppress Nullable warning;
     * if you are sure that you have not passed null in default value
     *
     * @param key      Key value to find object against
     * @param defValue Default value if no value found.
     * @param clazz    Class of expected object
     * @param <T>      Generic type of value
     * @return Value found against the key or defValue if not found
     */
    public <T> T get(String key, @Nullable T defValue, Class<? extends T> clazz) {
        return get(this, key, defValue, clazz);
    }

    /**
     * Returns message received from server in "message" parameter
     *
     * @return Message received from server or default message if null or not present
     */
    public String message() {
        return get(this, "Message", "Invalid response from server", String.class);
    }

    /**
     * Returns status received from server in "status" parameter
     *
     * @return Status received from server or false if null or not present
     */
    public Boolean status() {
        return get(this, "Status", 0, Integer.class) != 0;
    }
}