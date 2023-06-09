package org.users.test;

import com.google.gson.Gson;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import org.testng.annotations.Test;
import org.users.base.Base;
import org.users.constant.Parameters;
import org.users.test.expectedData.GetUserExp;
import org.users.test.expectedData.UpdateUserInfoExp;
import org.users.test.request.SwitchUserActivity;
import org.users.test.request.UserInfoRequest;
import org.users.test.response.*;
import org.users.test.request.UserRequest;

import static org.testng.Assert.assertTrue;

public class User extends Base {
    private APIRequestContext getapiRequestContext() {
        return playwright.request().newContext(new APIRequest.NewContextOptions()
                .setBaseURL(Parameters.USERS)
        );
    }

    @Test(description = "Get Users",priority = 1)
    public void getUsers() {
        GetUserExp getUserExp=new GetUserExp();
        String response = getapiRequestContext().get(Parameters.USERS).text();
        GetUsersResponse[] usersResponseArray = new Gson().fromJson(response, GetUsersResponse[].class);
        try {
            if (usersResponseArray[0].username.equals(getUserExp.username_0) && usersResponseArray[1].username.equals(getUserExp.username_1)) {
                System.out.println("API response contains username1: doejj and username2 = johnthesavior");
            } else {
                System.out.println("API response does not contain the expected username=doejj and username2 = johnthesavior");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    @Test(description = "Create Users",priority = 0)
    public void createUsers() {
        UpdateUserInfoExp userInfoExp=new UpdateUserInfoExp();
        UserRequest userRequest = new UserRequest();
        userRequest.setUserName("Muhammed_Yıldırım");
        userRequest.setFirstName("Muhammed");
        userRequest.setLastName("Yıldırım");
        userRequest.setPassword("Test123");
        String response = getapiRequestContext().post(Parameters.USERS).text();
        CreateUserResponse createUserResponse = new Gson().fromJson(response, CreateUserResponse.class);
        try {
            if (createUserResponse.getUserId().equals(userInfoExp.userId)) {
                System.out.println("API response contains user_id:c4f6c088-f91b-494e-b7f0-a08f48df3180");
            } else {
                System.out.println("API response does not contain the expected user_id:c4f6c088-f91b-494e-b7f0-a08f48df3180");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }


    }

    @Test(description = "GetUserById",priority = 1)
    public void getUserById() {
        GetUserExp getUserExp=new GetUserExp();
        String response = getapiRequestContext().get(Parameters.USERS_ID).text();
        GetUserByIdResponse getUserByIdResponse = new Gson().fromJson(response, GetUserByIdResponse.class);
        try {
            if (getUserByIdResponse.getUsername().equals(getUserExp.username_0) && getUserByIdResponse.getId().equals(getUserExp.id_0)) {
                System.out.println("API response contains username: doejj and user_id = c4f6c088-f91b-494e-b7f0-a08f48df3180");
            } else {
                System.out.println("API response does not contain the expected username=doejj and user_id = c4f6c088-f91b-494e-b7f0-a08f48df3180");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }


    @Test(description ="Remove User",priority = 3)
    public void removeUser(){
        String response=getapiRequestContext().delete(Parameters.USERS_ID).text();
        System.out.println("-User Delete-");
    }
    @Test(description =" Switch User Activity")
    public void switchUserActivity(){
        SwitchUserActivity switchUserActivity=new SwitchUserActivity();
        switchUserActivity.setActive(true);
        String response=getapiRequestContext().patch(Parameters.USERS_ID_ACTIVITY).text();
        SwitchUserActivityResponse switchUserActivityResponse=new Gson().fromJson(response, SwitchUserActivityResponse.class);
        if (switchUserActivityResponse.isActive()) {
            System.out.println("switchUserActivityResponse isActive: true");
        } else {
            System.out.println("switchUserActivityResponse isActive: false");
        }
    }
    @Test(description ="Update User",priority = 2)
    public void updateUserInfo(){
        UpdateUserInfoExp userInfoExp=new UpdateUserInfoExp();
        UserInfoRequest userInfoRequest=new UserInfoRequest();
        userInfoRequest.setFirstName("Muhammed");
        userInfoRequest.setLastName("Yıldırım");
        String response=getapiRequestContext().put(Parameters.USERS_ID).text();
        UpdateUserInfoResponse updateUserInfoResponse=new Gson().fromJson(response, UpdateUserInfoResponse.class);
        if(updateUserInfoResponse.userId.equals(userInfoExp.userId)) {
            System.out.println("API response contains user id: c4f6c088-f91b-494e-b7f0-a08f48df3180");
        }
        else{
            System.out.println("API response does not contain the expected user id: c4f6c088-f91b-494e-b7f0-a08f48df3180");
        }

    }
}
