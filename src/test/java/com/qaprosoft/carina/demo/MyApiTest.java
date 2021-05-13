package com.qaprosoft.carina.demo;

import com.qaprosoft.carina.core.foundation.AbstractTest;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.qaprosoft.carina.demo.api.gorest.DeleteUser;
import com.qaprosoft.carina.demo.api.gorest.GetUser;
import com.qaprosoft.carina.demo.api.gorest.PostUser;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

public class MyApiTest extends AbstractTest {
    @Test
    public void getUser(){
        GetUser getUser = new GetUser();
        getUser.expectResponseStatus(HttpResponseStatusType.OK_200);
        getUser.callAPI();
        getUser.validateResponse();
    }

    @Test
    public void postAndDeleteUser(){
        PostUser postUser = new PostUser();
        postUser.expectResponseStatus(HttpResponseStatusType.OK_200);
        String rs = postUser.callAPI().asString();
        postUser.validateResponse();
        System.out.println("Recieve is :"+rs);
        String id = new  JsonPath(rs).getString("data.id");

        System.out.println("ID is : "+id);
        /*DeleteUser deleteUser = new DeleteUser(id);
        deleteUser.expectResponseStatus(HttpResponseStatusType.OK_200);
        deleteUser.callAPI();
        deleteUser.validateResponse();*/
    }

    @Test
    public void deleteUser(){
       /* DeleteUser deleteUser = new DeleteUser();
        deleteUser.expectResponseStatus(HttpResponseStatusType.OK_200);
        deleteUser.callAPI();
        deleteUser.validateResponse();*/
    }

}
