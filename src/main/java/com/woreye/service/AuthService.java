package com.woreye.service;

import com.woreye.pojo.vo.MyAuthVo;

public interface AuthService {

    String getTokenId(MyAuthVo myAuthVo);

    String CheckToken(String token);

}
