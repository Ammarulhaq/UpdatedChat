package com.example.conversationlisting.webservicesinterfaceshandlers;


import com.example.conversationlisting.retrofitservices.RetrofitJSONResponse;
import com.example.conversationlisting.retrofitservices.RetrofitJSONResponse;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

//  Copyright © 2017 AirFive. All rights reserved.

public interface WebServices {

    @FormUrlEncoded
    @POST("api/Login/LoginUser")
    Call<RetrofitJSONResponse> login(@FieldMap Map<String, Object> parameters);

    @FormUrlEncoded
    @POST("api/Login/SaveRegistration")
    Call<RetrofitJSONResponse> register(@FieldMap Map<String, Object> parameters);

    @FormUrlEncoded
    @POST("login")
    Call<RetrofitJSONResponse> login(@Field("email") String email, @Field("password") String password, @Field("company_id") int companyId);

    @GET("message/{message_id}")
    Call<RetrofitJSONResponse> getMessageDetails(@Path("message_id") String messageId);

    @GET("conversation")
    Call<RetrofitJSONResponse> getAllConversations(@Header("Authorization") String token);

    @GET("message/loadmoremessages")
    Call<RetrofitJSONResponse> loadMoreMessages(@Header("Authorization") String token, @Query("conversation_id") int conversationId,
                                                @Query("last_message_id") int lastMessageId, @Query("limit") int limit);

    @GET("conversationparticipant/{conversation_id}")
    Call<RetrofitJSONResponse> getConversationParticipants(@Header("Authorization") String token, @Path("conversation_id") int conversationId);

    @FormUrlEncoded
    @POST("conversationsetting/toggleadmin")
    Call<RetrofitJSONResponse> toggleConversationAdmin(@Header("Authorization") String token, @Field("conversation_id") int conversationId,
                                                       @Field("resource_id") int resourceId, @Field("admin") boolean isAdmin);

    @FormUrlEncoded
    @POST("conversationsetting/togglefavorite")
    Call<RetrofitJSONResponse> toggleMarkConversationFav(@Header("Authorization") String token, @Field("conversation_id") int conversationId,
                                                         @Field("favorite") boolean markFavourite);

    @GET("conversationsetting/{conversation_id}")
    Call<RetrofitJSONResponse> getConversationSettings(@Header("Authorization") String token, @Path("conversation_id") int conversationId);

    @PATCH("conversation/{conversation_id}")
    Call<RetrofitJSONResponse> updateConversationTitle(@Header("Authorization") String token, @Path("conversation_id") int conversationId,
                                                       @Body String name);

    @FormUrlEncoded
    @POST("conversation")
    Call<RetrofitJSONResponse> createNewConversation(@Header("Authorization") String token, @Field("name") String name,
                                                     @Field("participants[]") ArrayList<Integer> participantsList);

    @GET("resource")
    Call<RetrofitJSONResponse> getCompanyResources(@Header("Authorization") String token, @Query("limit") int limit,
                                                   @Query("offset") int offset);

    @GET("resource")
    Call<RetrofitJSONResponse> getAllCompanyResources(@Header("Authorization") String token);

    @GET("RECO-Portal/api/location/getLocationsByCityId/{cityId}")
    Call<RetrofitJSONResponse> getLocationsWRTCity(@Path("cityId") int cityId);

    /*@POST("RECO-Portal/api/search/searchBuyProperty")
    Call<RetrofitJSONResponse> getSearchedPropertiesListToBuy(@Body PropertySearchRequest propertySearchRequest);*/


    @FormUrlEncoded
    @POST("api/Vegify/GetVegifyRatingAndReviews")
    Call<RetrofitJSONResponse> getVeggiefyRatingsAndReviews(@FieldMap Map<String, Object> parameters);

    @FormUrlEncoded
    @POST("api/Vegify/AddVegifyRatingAndReviews")
    Call<RetrofitJSONResponse> addVeggiefyRatingsAndReviews(@FieldMap Map<String, Object> parameters
            , @Field("Images[]") ArrayList<String> reviewImagesArr);

    @FormUrlEncoded
    @POST("api/Vegify/UpdateVegifyRatingAndReviews")
    Call<RetrofitJSONResponse> updateVeggiefyRatingsAndReviews(@FieldMap Map<String, Object> parameters
            , @Field("Images[]") ArrayList<String> reviewImagesArr);

    @FormUrlEncoded
    @POST("api/Vegify/UnlockAvatarForUser")
    Call<RetrofitJSONResponse> unblockAvatar(@FieldMap Map<String, Object> parameters);

    @GET("api/Vegify/VenuesExplore")
    Call<RetrofitJSONResponse> getNearestRestaurants(@Query("ll") String location, @Query("radius") String radius
            , @Query("offset") String offset, @Query("limit") String limit, @Query("venuePhotos") String venuePhotos, @Query("query") String query
            , @Query("sortByDistance") String sortByDistance);

    @GET("api/Vegify/VenuesSearch")
    Call<RetrofitJSONResponse> getSearchedRestaurants(@Query("ll") String location, @Query("radius") String radius, @Query("query") String query
            , @Query("categoryId") String categoryId);

    @GET("api/Vegify/GetDetailsOfVenueList")
    Call<RetrofitJSONResponse> getFavRestaurants(@Query("ll") String location, @Query("UserId") String userId);

    @GET("api/Vegify/GetDetailsOfRecommendedVenueList")
    Call<RetrofitJSONResponse> getRecommendedRestaurants(@Query("ll") String location, @Query("LIST_ID") String listId, @Query("offset") String offset, @Query("limit") String limit);

    @GET("api/Vegify/GetVegifyRatingAndReviewsList")
    Call<RetrofitJSONResponse> getRestaurantsSortByRatings(@Query("ll") String location);

    @GET("api/Vegify/GetVegifyRatingAndReviewsListInFiftyKM")
    Call<RetrofitJSONResponse> getClassifiedRestaurants(@Query("ll") String location);

    @GET("api/Vegify/GetDetailsOfCertifiedVenueList")
    Call<RetrofitJSONResponse> getVeggiefyCertifiedRestaurants(@Query("ll") String location, @Query("LIST_ID") String listId, @Query("offset") String offset, @Query("limit") String limit);

    @GET("api/Vegify/GetVegifyCategories")
    Call<RetrofitJSONResponse> getCuisineOptionsList();

    @GET("api/Vegify/GetVegifyAvatars")
    Call<RetrofitJSONResponse> getAvatarLevelsData(@Query("UserId") String userId);

    @FormUrlEncoded
    @POST("api/Vegify/AddFavouriteVenue")
    Call<RetrofitJSONResponse> addRestaurantToFavList(@FieldMap Map<String, Object> parameters);

    @FormUrlEncoded
    @POST("api/Vegify/RemoveFavouriteVenue")
    Call<RetrofitJSONResponse> deleteRestaurantFromFavList(@FieldMap Map<String, Object> parameters);

    @FormUrlEncoded
    @POST("api/Vegify/CheckVenueIsFavourite")
    Call<RetrofitJSONResponse> checkIfRestaurantAlreadyInFav(@FieldMap Map<String, Object> parameters);

    @FormUrlEncoded
    @POST("api/Vegify/AddVegifyUsersPoint")
    Call<RetrofitJSONResponse> addUserPoints(@FieldMap Map<String, Object> parameters);

    @GET("api/Vegify/GetVegifyUsersPointsHistory")
    Call<RetrofitJSONResponse> getVeggiefyUserPoints(@Query("UserId") String userId);

    @GET("api/Vegify/VenuesFilters")
    Call<RetrofitJSONResponse> getFilteredRestaurants(@Query("ll") String location, @Query("limit") String limit,
                                                      @Query("offset") String offset, @Query("venuePhotos") String venuePhotos, @Query("query") String query, @Query("sorting") String sorting,
                                                      @Query("radius") String radius, @Query("rating") String veggiefyRating, @Query("certified") boolean isCertified);

    @GET("api/Vegify/GetReviewsAgainstVenueId")
    Call<RetrofitJSONResponse> getReviewsList(@Query("VenueId") String venueId);

    @GET("api/Vegify/GetUserDetailsWithReviewsAndRating")
    Call<RetrofitJSONResponse> getReviewerProfileDetails(@Query("UserId") String userId);

    @GET("api/Vegify/ForgetPassword")
    Call<RetrofitJSONResponse> getForgetPassword(@Query("Email") String email);

    @FormUrlEncoded
    @POST("api/Vegify/CheckVenueIsVegifyRecommended")
    Call<RetrofitJSONResponse> checkIfRestaurantAlreadyVerified(@FieldMap Map<String, Object> parameters);

    @FormUrlEncoded
    @POST("api/Vegify/GetVenueMenues")
    Call<RetrofitJSONResponse> getVenueMenues(@FieldMap Map<String, Object> parameters);

    @FormUrlEncoded
    @POST("api/Vegify/testimage")
    Call<RetrofitJSONResponse> uploadImage(@Body String s);
}