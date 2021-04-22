package com.qaprosoft.carina.demo.gui.services;

import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.demo.gui.components.UserGSM;

public class UserService {

    public UserGSM getUser(){
        UserGSM user = new UserGSM();
        user.setNickname(R.TESTDATA.get("user1.nickname"));
        user.setEmail(R.TESTDATA.get("user1.email"));
        user.setPassword(R.TESTDATA.get("user1.password"));
        return user;
    }

}
