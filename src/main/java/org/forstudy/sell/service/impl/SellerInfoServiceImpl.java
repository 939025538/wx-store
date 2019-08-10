package org.forstudy.sell.service.impl;

import org.forstudy.sell.dataobject.SellerInfo;
import org.forstudy.sell.repository.SellerInfoRepository;
import org.forstudy.sell.service.SellerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerInfoServiceImpl implements SellerInfoService {

    @Autowired
    private SellerInfoRepository repository;

    @Override
    public SellerInfo findBySellerInfoOpenid(String openid) {

        return repository.findByOpenid(openid);
    }
}
