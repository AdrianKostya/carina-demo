package com.qaprosoft.carina.demo.api.gorest;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.utils.Configuration;


public class GetUser extends AbstractApiMethodV2 {
    public GetUser (){
        super(null, "api.gorest/_get/rs.json");
        replaceUrlPlaceholder("base_url", Configuration.getEnvArg("api_url"));
    }
}
