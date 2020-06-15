package com.example.conversationlisting.retrofitservices;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//  Copyright © 2017 AirFive. All rights reserved.

public abstract class CustomCallback<T> implements Callback<T> {

    /**
     * Called when api call is completed and successful response is received.
     *
     * @param response Successful response from server
     */
    public abstract void onSuccess(@NonNull T response) throws Exception;

    /**
     * Called when api call is either failed or Invalid response received from server.
     *
     * @param completed {@code true} if call was completed; {@code false} otherwise
     * @param response  If received response from server; {@code null} otherwise
     * @param ex        {@code Exception} occurred during call execution or processing response
     */
    public abstract void onFailure(boolean completed, @Nullable T response, @Nullable Exception ex) throws JSONException;

    /**
     * List of tags and objects attached to this {@code Callback} which may be helpful
     * in processing server response.
     */
    protected List<Object> mTagsList;

    /**
     * Creates new {@link Callback} object with default values
     */
    public CustomCallback() {
        mTagsList = new ArrayList<>();
    }

    /**
     * @return Tag List of tags (or any useful information) associated with this {@code Callback}
     */
    public List<Object> getTags() {
        return mTagsList;
    }

    /**
     * Get tag at specific position
     *
     * @param position Position to get tag from
     * @return Tag at specified position (if valid; else {@code null})
     */
    public Object getTagAt(int position) {
        return position >= 0 && position < mTagsList.size() ? mTagsList.get(position) : null;
    }

    /**
     * Remove specified object from tags
     *
     * @param object {@code Object} to remove from Tags List
     * @return {@code true} if removed successfully; {@code false} otherwise
     */
    public boolean removeTag(Object object) {
        return mTagsList.remove(object);
    }

    /**
     * Clear tags list
     */
    public void clearTags() {
        mTagsList.clear();
    }

    /**
     * @param object {@code Object} to add in Tags List
     * @return {@code true} if added successfully; {@code false} otherwise
     */
    public boolean addTag(Object object) {
        return mTagsList.add(object);
    }
    /**
     * Invoked for a received HTTP response.
     * <p>
     * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
     * Call {@link Response#isSuccess()} to determine if the response indicates success.
     */

    /**
     * Overridden method from {@link retrofit2.Retrofit}
     * <p>
     * Invoked for a received HTTP response.
     * <p>
     * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
     * Call {@link Response#isSuccessful()} to determine if the response indicates success.
     *
     * @param call     {@link Call} object with request information
     * @param response Response received from request
     */
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        T body = response.body();
        if (response.code() == 200 && body != null) {
            try {
                onSuccess(body);
            } catch (Exception e) {
                try {
                    onFailure(true, body, e);
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        } else if (response.code() == 201 && body != null) {
            try {
                onSuccess(body);
            } catch (Exception e) {
                try {
                    onFailure(true, body, e);
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        } else if (response.code() == 200 && response.isSuccessful()) {
            try {
                onSuccess(body);
            } catch (Exception e) {
                try {
                    onFailure(true, body, e);
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        } else {
            try {
                onFailure(true, body, null); // Body may be null in this case
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Overridden method from {@link retrofit2.Retrofit}
     * <p>
     * Invoked when a network exception occurred talking to the server or when an unexpected
     * exception occurred creating the request or processing the response.
     *
     * @param call {@link Call} object with request information
     * @param t    {@link Throwable} with information related to {@code call} failure
     */
    @Override
    public void onFailure(Call<T> call, Throwable t) {
        String message = t == null ? "" : t.getMessage();
        Log.e("AirFive", (String.format("Request Fail: %s\n%s", call.request().url(), message)));
        try {
            onFailure(false, null, new Exception(t != null && t.getMessage() != null ? t.getMessage() : "Network problem."));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}