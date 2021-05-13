package com.qaprosoft.carina.demo;

import com.qaprosoft.carina.core.foundation.AbstractTest;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.qaprosoft.carina.demo.api.gorest.DeleteUser;
import com.qaprosoft.carina.demo.api.gorest.GetUser;
import com.qaprosoft.carina.demo.api.gorest.PostUser;
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
    public void postUser(){
        PostUser postUser = new PostUser();
        postUser.expectResponseStatus(HttpResponseStatusType.OK_200);
        postUser.callAPI();
        postUser.validateResponse();
    }

    @Test
    public void deleteUser(){
        DeleteUser deleteUser = new DeleteUser();
        deleteUser.expectResponseStatus(HttpResponseStatusType.OK_200);
        deleteUser.callAPI();
        deleteUser.validateResponse();
    }

}
