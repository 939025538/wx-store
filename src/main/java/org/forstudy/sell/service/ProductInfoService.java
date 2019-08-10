package org.forstudy.sell.service;


import org.forstudy.sell.dataobject.ProductInfo;
import org.forstudy.sell.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductInfoService {

    Page<ProductInfo> findAll(Pageable pageable);

    List<ProductInfo> findByProductStatus(Integer productStatus);

    ProductInfo save(ProductInfo productInfo);

    ProductInfo findOne(String productId);

    //增加库存
    void increaseStock(List<CartDTO> cartDTOList);

    //减少库存
    void dcreaseStock(List<CartDTO> cartDTOList);

    //上架
    ProductInfo onSale(String productId);

    //下架
    ProductInfo offSale(String productId);
}
