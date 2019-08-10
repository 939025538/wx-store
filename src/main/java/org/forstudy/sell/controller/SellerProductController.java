package org.forstudy.sell.controller;

import org.forstudy.sell.dataobject.ProductCategory;
import org.forstudy.sell.dataobject.ProductInfo;
import org.forstudy.sell.exception.SellException;
import org.forstudy.sell.form.ProductForm;
import org.forstudy.sell.service.CategoryService;
import org.forstudy.sell.service.ProductInfoService;
import org.forstudy.sell.utils.KeyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size" , defaultValue = "5") Integer size,
                             Map<String, Object> map){
        PageRequest request = new PageRequest(page-1, size);
        Page<ProductInfo> productInfoPage = productInfoService.findAll(request);
        map.put("productInfoPage" , productInfoPage);
        map.put("currentPage" , page);
        map.put("size",size);

        return new ModelAndView("product/list",map);
    }

    @GetMapping("/onSale")
    public ModelAndView onSale(@RequestParam("productId") String productId){
        Map<String,Object> map = new HashMap<>();
        map.put("url","/sell/seller/product/list");
        try{
            ProductInfo productInfo =productInfoService.onSale(productId);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            return new ModelAndView("common/error",map);
        }
        return new ModelAndView("common/success",map);
    }

    @GetMapping("/offSale")
    public ModelAndView offSale(@RequestParam("productId") String productId){
        Map<String,Object> map = new HashMap<>();
        map.put("url","/sell/seller/product/list");
        try{
            ProductInfo productInfo =productInfoService.offSale(productId);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            return new ModelAndView("common/error",map);
        }
        return new ModelAndView("common/success",map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId" , required = false) String productId,
                              Map<String,Object> map){
        if (!StringUtils.isEmpty(productId)){
            ProductInfo productInfo = productInfoService.findOne(productId);
            map.put("productInfo",productInfo);
        }
        List<ProductCategory> productCategoryList =  categoryService.findAll();
        map.put("productCategoryList",productCategoryList);
        return new ModelAndView("product/index",map);
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm productForm,
                             BindingResult bindingResult,
                             Map<String,Object> map){
        if (bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("common/error",map);
        }
        ProductInfo productInfo = new ProductInfo();
        try{
            if (!StringUtils.isEmpty(productForm.getProductId())){
                productInfo = productInfoService.findOne(productForm.getProductId());
            }else {
                productForm.setProductId(KeyUtils.genUniqueKey());
            }
        BeanUtils.copyProperties(productForm , productInfo);
        productInfoService.save(productInfo);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("common/error",map);
        }
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }

}
