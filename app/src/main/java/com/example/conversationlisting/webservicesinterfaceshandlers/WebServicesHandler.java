package com.example.conversationlisting.webservicesinterfaceshandlers;


import android.util.Log;

import com.example.conversationlisting.retrofitservices.CustomCallback;
import com.example.conversationlisting.retrofitservices.RetrofitGSONConverter;
import com.example.conversationlisting.retrofitservices.RetrofitJSONResponse;

import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;

//  Copyright © 2017 AirFive. All rights reserved.

public class WebServicesHandler {

    //    public static final String BASE_URL = "http://api.admin.encodetechsolutions.com/api/";
    public static final String BASE_URL = "https://staging.wurqi.com/datalayer/services/";
    public static WebServicesHandler instance = new WebServicesHandler();
    private WebServices webServices;

    private WebServicesHandler() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.readTimeout(120, TimeUnit.SECONDS);
        httpClient.connectTimeout(120, TimeUnit.SECONDS);
        httpClient.writeTimeout(120, TimeUnit.SECONDS);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(RetrofitGSONConverter.create())
                .client(httpClient.build());
//                .client(getUnsafeOkHttpClient());//to get secured urls

        Retrofit retrofit = builder.build();
        webServices = retrofit.create(WebServices.class);
    }

    private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                            Log.d("-------- authtype", authType);
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                            Log.d("-------- authtype", authType);
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void login(String email, String password, CustomCallback<RetrofitJSONResponse> callback) {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("Username", email);
        parameters.put("Password", password);
//        parameters.put("USER_TYPE", 4);

        Call<RetrofitJSONResponse> call = webServices.login(parameters);
        call.enqueue(callback);

    }

    public void register(String fName, String lName, String email, String password, String confirmPassword, String RegistrationType, CustomCallback<RetrofitJSONResponse> callback) {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("FirstName", fName);
        parameters.put("LastName", lName);
        parameters.put("Email", email);
        parameters.put("Password", password);
        parameters.put("ConfirmPassword", confirmPassword);
        parameters.put("RegistrationType", RegistrationType);
        parameters.put("LanguageCode", "en-US");
        parameters.put("IsActive", true);

        Call<RetrofitJSONResponse> call = webServices.register(parameters);
        call.enqueue(callback);

    }

    public void login(String email, String password, int companyId, CustomCallback<RetrofitJSONResponse> callback) {
        Call<RetrofitJSONResponse> call = webServices.login(email, password, companyId);
        call.enqueue(callback);
    }

    public void createNewConversation(String token, String name, ArrayList<Integer> participantsList, CustomCallback<RetrofitJSONResponse> callback) {
        Call<RetrofitJSONResponse> call = webServices.createNewConversation(token, name, participantsList);
        call.enqueue(callback);
    }

    public void getAllConversations(String token, CustomCallback<RetrofitJSONResponse> callback) {
        Call<RetrofitJSONResponse> call = webServices.getAllConversations(token);
        call.enqueue(callback);
    }

    public void loadMoreMessages(String token, int conversationId, int lastMessageId, int limit, CustomCallback<RetrofitJSONResponse> callback) {
        Call<RetrofitJSONResponse> call = webServices.loadMoreMessages(token, conversationId, lastMessageId, limit);
        call.enqueue(callback);
    }

    public void getConversationParticipants(String token, int conversationId, CustomCallback<RetrofitJSONResponse> callback) {
        Call<RetrofitJSONResponse> call = webServices.getConversationParticipants(token, conversationId);
        call.enqueue(callback);
    }

    public void toggleConversationAdmin(String token, int conversationId, int resourceId, boolean isAdmin, CustomCallback<RetrofitJSONResponse> callback) {
        Call<RetrofitJSONResponse> call = webServices.toggleConversationAdmin(token, conversationId, resourceId, isAdmin);
        call.enqueue(callback);
    }

    public void toggleMarkConversationFav(String token, int conversationId, boolean markFav, CustomCallback<RetrofitJSONResponse> callback) {
        Call<RetrofitJSONResponse> call = webServices.toggleMarkConversationFav(token, conversationId, markFav);
        call.enqueue(callback);
    }

    public void getConversationSettings(String token, int conversationId, CustomCallback<RetrofitJSONResponse> callback) {
        Call<RetrofitJSONResponse> call = webServices.getConversationSettings(token, conversationId);
        call.enqueue(callback);
    }

    public void updateConversationTitle(String token, int conversationId, String updatedTitle, CustomCallback<RetrofitJSONResponse> callback) {
        Call<RetrofitJSONResponse> call = webServices.updateConversationTitle(token, conversationId, updatedTitle);
        call.enqueue(callback);
    }

    public void getCompanyResources(String token, int limit, int offset, CustomCallback<RetrofitJSONResponse> callback) {
        Call<RetrofitJSONResponse> call = webServices.getCompanyResources(token, limit, offset);
        call.enqueue(callback);
    }

    public void getAllCompanyResources(String token, CustomCallback<RetrofitJSONResponse> callback) {
        Call<RetrofitJSONResponse> call = webServices.getAllCompanyResources(token);
        call.enqueue(callback);
    }


    public void getMessageDetails(CustomCallback<RetrofitJSONResponse> callback) {
        Call<RetrofitJSONResponse> call = webServices.getMessageDetails("5687");
        call.enqueue(callback);
    }

    public void getLocationsWRTCity(int cityId, CustomCallback<RetrofitJSONResponse> callback) {
        Call<RetrofitJSONResponse> call = webServices.getLocationsWRTCity(cityId);
        call.enqueue(callback);
    }


    public void getVeggiefyRatingAndReview(String venueId, String userProfileId, CustomCallback<RetrofitJSONResponse> callback) {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("VenueId", venueId);
        parameters.put("UserProfileId", userProfileId);

        Call<RetrofitJSONResponse> call = webServices.getVeggiefyRatingsAndReviews(parameters);
        call.enqueue(callback);
    }

    public void addVeggiefyRatingAndReview(String userProfileId, String venueId, String overAllRating, String recommendRestaurant, String varietyOfDishes, String overAllQuality,
                                           String flexibilityOfDishes, String infoOfIngredients, String comments, String pros, String cons,
                                           ArrayList<String> reviewImagesList, CustomCallback<RetrofitJSONResponse> callback) {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("UserProfileId", userProfileId);
        parameters.put("VenueId", venueId);
        parameters.put("OverAllRating", overAllRating);
        parameters.put("RecommendRestaurant", recommendRestaurant);
        parameters.put("VarietyOfDishes", varietyOfDishes);
        parameters.put("OverAllQuality", overAllQuality);
        parameters.put("FlexibilityOfDishes", flexibilityOfDishes);
        parameters.put("InfoOfIngredients", infoOfIngredients);
        parameters.put("Comments", comments);
        parameters.put("Pros", pros);
        parameters.put("Cons", cons);

        Call<RetrofitJSONResponse> call = webServices.addVeggiefyRatingsAndReviews(parameters, reviewImagesList);
        call.enqueue(callback);

    }

    public void updateVeggiefyRatingAndReview(String userProfileId, String venueId, String overAllRating, String recommendRestaurant, String varietyOfDishes, String overAllQuality,
                                              String flexibilityOfDishes, String infoOfIngredients, String comments, String pros, String cons,
                                              ArrayList<String> reviewImagesList, CustomCallback<RetrofitJSONResponse> callback) {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("UserProfileId", userProfileId);
        parameters.put("VenueId", venueId);
        parameters.put("OverAllRating", overAllRating);
        parameters.put("RecommendRestaurant", recommendRestaurant);
        parameters.put("VarietyOfDishes", varietyOfDishes);
        parameters.put("OverAllQuality", overAllQuality);
        parameters.put("FlexibilityOfDishes", flexibilityOfDishes);
        parameters.put("InfoOfIngredients", infoOfIngredients);
        parameters.put("Comments", comments);
        parameters.put("Pros", pros);
        parameters.put("Cons", cons);

        Call<RetrofitJSONResponse> call = webServices.updateVeggiefyRatingsAndReviews(parameters, reviewImagesList);
        call.enqueue(callback);

    }

    public void unblockTheLevel(String userProfileId, String avatarId, CustomCallback<RetrofitJSONResponse> callback) {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("UserProfileId", userProfileId);
        parameters.put("AvatarId", avatarId);

        Call<RetrofitJSONResponse> call = webServices.unblockAvatar(parameters);
        call.enqueue(callback);

    }

    public void getNearestRestaurantsList(String location, String radius, String offset, String limit, CustomCallback<RetrofitJSONResponse> callback) {

        Call<RetrofitJSONResponse> call = webServices.getNearestRestaurants(location, radius, offset, limit, "1", "Food", "1");
        call.enqueue(callback);
    }

    public void getFavRestaurantsList(String location, String userId, CustomCallback<RetrofitJSONResponse> callback) {

        Call<RetrofitJSONResponse> call = webServices.getFavRestaurants(location, userId);
        call.enqueue(callback);
    }

    public void getRestaurantsSortByRatingsList(String location, CustomCallback<RetrofitJSONResponse> callback) {

        Call<RetrofitJSONResponse> call = webServices.getRestaurantsSortByRatings(location);
        call.enqueue(callback);
    }

    public void getClassifiedRestaurantsList(String location, CustomCallback<RetrofitJSONResponse> callback) {

        Call<RetrofitJSONResponse> call = webServices.getClassifiedRestaurants(location);
        call.enqueue(callback);
    }

    public void getRecommendedRestaurantsList(String location, String listId, String offset, String limit, CustomCallback<RetrofitJSONResponse> callback) {

        Call<RetrofitJSONResponse> call = webServices.getRecommendedRestaurants(location, listId, offset, limit);
        call.enqueue(callback);
    }

    public void getVeggiefyCertifiedRestaurantsList(String location, String listId, String offset, String limit, CustomCallback<RetrofitJSONResponse> callback) {

        Call<RetrofitJSONResponse> call = webServices.getVeggiefyCertifiedRestaurants(location, listId, offset, limit);
        call.enqueue(callback);
    }

    public void getCuisineList(CustomCallback<RetrofitJSONResponse> callback) {

        Call<RetrofitJSONResponse> call = webServices.getCuisineOptionsList();
        call.enqueue(callback);
    }

    public void getSearchedRestaurantsList(String location, String radius, String query, CustomCallback<RetrofitJSONResponse> callback) {

        Call<RetrofitJSONResponse> call = webServices.getSearchedRestaurants(location, radius, query, "4d4b7105d754a06374d81259");
        call.enqueue(callback);
    }

    public void getAvatarLevels(String userId, CustomCallback<RetrofitJSONResponse> callback) {

        Call<RetrofitJSONResponse> call = webServices.getAvatarLevelsData(userId);
        call.enqueue(callback);
    }

    public void addRestaurantToFavList(String userId, String venueId, CustomCallback<RetrofitJSONResponse> callback) {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("UserId", userId);
        parameters.put("VenueId", venueId);

        Call<RetrofitJSONResponse> call = webServices.addRestaurantToFavList(parameters);
        call.enqueue(callback);
    }

    public void deleteRestaurantFromFavList(String listId, String venueId, CustomCallback<RetrofitJSONResponse> callback) {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ListId", listId);
        parameters.put("VenueId", venueId);

        Call<RetrofitJSONResponse> call = webServices.deleteRestaurantFromFavList(parameters);
        call.enqueue(callback);
    }

    public void checkIfRestaurantAlreadyInFavList(String listId, String venueId, CustomCallback<RetrofitJSONResponse> callback) {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ListId", listId);
        parameters.put("VenueId", venueId);

        Call<RetrofitJSONResponse> call = webServices.checkIfRestaurantAlreadyInFav(parameters);
        call.enqueue(callback);
    }

    public void addVeggiefyUserPoints(String userId, String pointsFor, String points, String createdDate, CustomCallback<RetrofitJSONResponse> callback) {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("UserId", userId);
        parameters.put("PointsFor", pointsFor);
        parameters.put("Points", points);
        parameters.put("CreatedDate", createdDate);

        Call<RetrofitJSONResponse> call = webServices.addUserPoints(parameters);
        call.enqueue(callback);
    }

    public void getVeggiefyPointsHistory(String userId, CustomCallback<RetrofitJSONResponse> callback) {

        Call<RetrofitJSONResponse> call = webServices.getVeggiefyUserPoints(userId);
        call.enqueue(callback);
    }

    public void getFilteredRestaurants(String location, String limit, String offset, String sorting, String selectedCuisines, String radius, String veggiefyRating, boolean isCertified, CustomCallback<RetrofitJSONResponse> callback) {

        Call<RetrofitJSONResponse> call = webServices.getFilteredRestaurants(location, limit, offset, "1", selectedCuisines, sorting, radius, veggiefyRating, isCertified);
        call.enqueue(callback);
    }

    public void getAllReviewsList(String venueId, CustomCallback<RetrofitJSONResponse> callback) {

        Call<RetrofitJSONResponse> call = webServices.getReviewsList(venueId);
        call.enqueue(callback);
    }

    public void getReviewerProfile(String userId, CustomCallback<RetrofitJSONResponse> callback) {

        Call<RetrofitJSONResponse> call = webServices.getReviewerProfileDetails(userId);
        call.enqueue(callback);
    }

    public void getForgetPassword(String email, CustomCallback<RetrofitJSONResponse> callback) {

        Call<RetrofitJSONResponse> call = webServices.getForgetPassword(email);
        call.enqueue(callback);
    }

    public void checkIfRestaurantVerified(String listId, String venueId, CustomCallback<RetrofitJSONResponse> callback) {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ListId", listId);
        parameters.put("VenueId", venueId);

        Call<RetrofitJSONResponse> call = webServices.checkIfRestaurantAlreadyVerified(parameters);
        call.enqueue(callback);
    }

    public void getRestaurantMenues(String venueId, CustomCallback<RetrofitJSONResponse> callback) {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("VenueId", venueId);

        Call<RetrofitJSONResponse> call = webServices.getVenueMenues(parameters);
        call.enqueue(callback);
    }

    public void uploadImage(String imageString, CustomCallback<RetrofitJSONResponse> callback) {

        Call<RetrofitJSONResponse> call = webServices.uploadImage(imageString);
        call.enqueue(callback);
    }
}