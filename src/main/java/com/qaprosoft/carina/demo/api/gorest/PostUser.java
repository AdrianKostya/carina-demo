package com.qaprosoft.carina.demo.api.gorest;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

public class PostUser extends AbstractApiMethodV2 {
    public PostUser (){
        super("api.gorest/_get/_post/rq.json", "api.gorest/_get/_post/rs.json", "api.gorest/_get/user.properties");
        replaceUrlPlaceholder("base_url", Configuration.getEnvArg("api_url"));
        request.header("Authorization", "Bearer 7cc2706fcfa7044adc1a9ec1632ef9c7be7001181f0aa222147e7dd7c046f5f7");
    }
}
