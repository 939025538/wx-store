package org.forstudy.sell.service;

import org.forstudy.sell.dataobject.SellerInfo;

public interface SellerInfoService {
    SellerInfo findBySellerInfoOpenid(String openid);
}
