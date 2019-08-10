package org.forstudy.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.forstudy.sell.VO.ProductInfoVo;
import org.forstudy.sell.VO.ProductVO;
import org.forstudy.sell.VO.ResultVO;
import org.forstudy.sell.dataobject.ProductCategory;
import org.forstudy.sell.dataobject.ProductInfo;
import org.forstudy.sell.enums.ProductInfoEnums;
import org.forstudy.sell.enums.ResultVOEnums;
import org.forstudy.sell.service.impl.CategoryServiceImpl;
import org.forstudy.sell.service.impl.ProductInfoServiceImpl;
import org.forstudy.sell.utils.ResultVoUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
@Slf4j
public class BuyerProductController {

    @Autowired
    private ProductInfoServiceImpl productInfoService;

    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping("/list")
    public ResultVO list(){
        log.info("访问了list方法");
        //查询所有上架的商品
        List<ProductInfo> productInfoList = productInfoService.findByProductStatus(ProductInfoEnums.UP.getCode());
        //查询所有商品的类目
        List<Integer> categoryTypeList =productInfoList.stream()
                                        .map(e -> e.getCategoryType())
                                        .collect(Collectors.toList());

        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        //数据的拼接
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory:productCategoryList){
            List<ProductInfoVo> productInfoVoList = new ArrayList<ProductInfoVo>();
            for (ProductInfo productInfo:productInfoList){
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())){
//                    productInfoVoList.add(new ProductInfoVo(productInfo.getProductId()
//                            ,productInfo.getProductName()
//                            ,productInfo.getProductPrice()
//                            ,productInfo.getProductDescription()
//                            ,productInfo.getProductIcon()));
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo , productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }
            }
            ProductVO pv = new ProductVO(productCategory.getCategoryName()
                    ,productCategory.getCategoryType()
                    ,productInfoVoList);
            productVOList.add(pv);
        }
        return ResultVoUtils.success(productVOList);
    }

}
