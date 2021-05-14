package com.qaprosoft.carina.demo;

import com.qaprosoft.carina.core.foundation.AbstractTest;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.qaprosoft.carina.demo.api.gorest.DeleteUser;
import com.qaprosoft.carina.demo.api.gorest.GetUser;
import com.qaprosoft.carina.demo.api.gorest.PostUser;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Order;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MyApiTest extends AbstractTest {
    int id=0;

    @Test
    public void postUser(){
        PostUser postUser = new PostUser();
        postUser.expectResponseStatus(HttpResponseStatusType.OK_200);
        String rs = postUser.callAPI().asString();
        postUser.validateResponse();
         id = Integer.parseInt(new  JsonPath(rs).getString("data.id"));

    }

    @Test
    public void getUser(){
        GetUser getUser = new GetUser();
        getUser.expectResponseStatus(HttpResponseStatusType.OK_200);
        getUser.callAPI();
        getUser.validateResponse();
    }

    @Test
    public void deleteUser(){
        DeleteUser deleteUser = new DeleteUser(id);
        deleteUser.expectResponseStatus(HttpResponseStatusType.OK_200);
        deleteUser.callAPI();
        deleteUser.validateResponse();
    }



}
