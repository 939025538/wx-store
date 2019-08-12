package org.forstudy.sell.Handler;

import org.forstudy.sell.Config.ProjectUrlConfig;
import org.forstudy.sell.VO.ResultVO;
import org.forstudy.sell.exception.SellException;
import org.forstudy.sell.exception.SellerAuthorizeException;
import org.forstudy.sell.utils.ResultVoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SellExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    //拦截登录异常
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException(){
//        return new ModelAndView("redirect:"
//                .concat(projectUrlConfig.getWechatOpenAuthorize())
//                .concat("/sell/wechat/qrAuthorize?")
//                .concat("returnUrl=")
//                .concat(projectUrlConfig.getSell())
//                .concat("/sell/seller/login"));
        return new  ModelAndView("redirect:"
        .concat(projectUrlConfig.getSell())
        .concat("/sell/seller/login?")
        .concat("openid=admin"));
    }

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handlerSellException(SellException e){
        return ResultVoUtils.error(e.getCode(),e.getMessage());
    }

}
