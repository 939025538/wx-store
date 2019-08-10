package org.forstudy.sell.utils;

import org.forstudy.sell.VO.ResultVO;
import org.forstudy.sell.enums.ResultVOEnums;

public class ResultVoUtils {

    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO(ResultVOEnums.SUCCESS,object);
        return resultVO;
    }

    public static ResultVO success(){
        ResultVO resultVO = new ResultVO(ResultVOEnums.SUCCESS,null);
        return resultVO;
    }

    public static ResultVO error(Integer code , String msg){
        ResultVO resultVO = new ResultVO(code,msg);
        return resultVO;
    }
}
