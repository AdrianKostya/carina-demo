package com.qaprosoft.carina.demo.api.gorest;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

public class DeleteUser extends AbstractApiMethodV2 {

    public  DeleteUser(int id){
        super(null, "api.gorest/_get/_delete/rs.json");
        replaceUrlPlaceholder("base_url", Configuration.getEnvArg("api_url"));
        replaceUrlPlaceholder("id", String.valueOf(id));
        request.header("Authorization", "Bearer 7cc2706fcfa7044adc1a9ec1632ef9c7be7001181f0aa222147e7dd7c046f5f7");

    }
}
