package com.example.conversationlisting.retrofitservices;

import android.content.Context;

import com.example.conversationlisting.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import okhttp3.MediaType;
import okhttp3.ResponseBody;

//  Copyright © 2017 AirFive. All rights reserved.


public class RetrofitConfigurator {
    public static final int LOG_LEVEL_NONE = 0;
    public static final int LOG_LEVEL_BASIC = 1;
    public static final int LOG_LEVEL_EXCEPTIONS = 2;
    public static final int LOG_LEVEL_HEADERS = 3;
    public static final int LOG_LEVEL_FULL = 4;

    public static final Charset UTF_8 = StandardCharsets.UTF_8;
    public static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    public static final Gson GSON = new GsonBuilder().disableHtmlEscaping().create();

    private static int LOG_LEVEL = LOG_LEVEL_BASIC;
    public static String NETWORK_ERROR_MESSAGE = null;

    /**
     * Initialize the retrofit Configurator
     *
     * @param context Application Context
     */
    public static void initConfigurator(Context context) {
        NETWORK_ERROR_MESSAGE = context.getString(R.string.network_error_message);
    }

    /**
     * @return Log level for Http calls
     */
    public static int getLogLevel() {
        return LOG_LEVEL;
    }

    /**
     * Send a VERBOSE log message if logging enabled.
     *
     * @param message String to log
     */
    public static void LOG(String message) {

    }

    /**
     * Reads bytes from {@link ResponseBody} and converts it to {@link String}
     *
     * @param value {@link ResponseBody} to be converted
     * @return Converted {@link String}
     * @throws IOException If any error occurred while reading {@link ResponseBody}
     */
    public static String toString(ResponseBody value) throws IOException {
        /*
        The code is commented because it often crashes on several devices and throws {@link ClassNotFoundException}.
        So, to avoid this, longer but safer way is implemented next to it
        */
        //  return CharStreams.toString(value.charStream());
        BufferedReader r = new BufferedReader(new InputStreamReader(value.byteStream()));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }
}