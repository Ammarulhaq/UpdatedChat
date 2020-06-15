package com.example.conversationlisting.retrofitservices;

import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.ByteString;
import retrofit2.Converter;
import retrofit2.Retrofit;

//  Copyright © 2017 AirFive. All rights reserved.

public class RetrofitGSONConverter extends Converter.Factory {

    public static RetrofitGSONConverter create() {
        return new RetrofitGSONConverter();
    }

    private RetrofitGSONConverter() {
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new RetrofitResponseBodyConverter<>(type, RetrofitConfigurator.GSON.getAdapter(TypeToken.get(type)));
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return new RetrofitRequestBodyConverter<>(type, RetrofitConfigurator.GSON.getAdapter(TypeToken.get(type)));
    }

    private static final class RetrofitResponseBodyConverter<T> implements Converter<ResponseBody, T> {

        private final Type type;
        private final TypeAdapter<T> adapter;

        private RetrofitResponseBodyConverter(Type _type, TypeAdapter<T> _adapter) {
            type = _type;
            adapter = _adapter;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T convert(ResponseBody value) throws IOException {
            T response = null;
            String result = null;
            Exception exception = null;
            try {
                result = RetrofitConfigurator.toString(value);
                if (type == RetrofitJSONResponse.class) {
                    try {
                        response = (T) new RetrofitJSONResponse(result);
                    } catch (JSONException e) {
                        response = null;
                    }
                } else {
                    response = adapter.fromJson(result);
                }
            } catch (Exception e) {
                exception = e;
            } finally {
                value.close();
            }
            if (RetrofitConfigurator.getLogLevel() >= RetrofitConfigurator.LOG_LEVEL_FULL) {
                RetrofitConfigurator.LOG(result);
            } else if (response == null && RetrofitConfigurator.getLogLevel() >= RetrofitConfigurator.LOG_LEVEL_EXCEPTIONS) {
                RetrofitConfigurator.LOG(result);
            }
            if (exception != null) {
                exception.printStackTrace();
            }
            return response;
        }
    }

    private static final class RetrofitRequestBodyConverter<T> implements Converter<T, RequestBody> {

        private final Type type;
        private final TypeAdapter<T> adapter;

        private RetrofitRequestBodyConverter(Type _type, TypeAdapter<T> _adapter) {
            type = _type;
            adapter = _adapter;
        }

        @Override
        public RequestBody convert(T value) throws IOException {
            Buffer buffer = new Buffer();
            Writer writer = new OutputStreamWriter(buffer.outputStream(), RetrofitConfigurator.UTF_8);
            if (value != null && value instanceof JSONObject || value instanceof JSONArray) {
                if (RetrofitConfigurator.getLogLevel() >= RetrofitConfigurator.LOG_LEVEL_HEADERS) {
                    RetrofitConfigurator.LOG(value.toString());
                }
                return RequestBody.create(RetrofitConfigurator.MEDIA_TYPE, ByteString.encodeUtf8(value.toString()));
            } else {
                JsonWriter jsonWriter = RetrofitConfigurator.GSON.newJsonWriter(writer);
                adapter.write(jsonWriter, value);
                jsonWriter.close();
                if (RetrofitConfigurator.getLogLevel() >= RetrofitConfigurator.LOG_LEVEL_HEADERS) {
                    RetrofitConfigurator.LOG(RetrofitConfigurator.GSON.toJson(value));
                }
                return RequestBody.create(RetrofitConfigurator.MEDIA_TYPE, buffer.readByteString());
            }
        }
    }
}
