package org.forstudy.sell.VO;

import lombok.Data;
import org.forstudy.sell.enums.ResultVOEnums;


@Data
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T data;

    public ResultVO() {
    }

    public ResultVO(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultVO(ResultVOEnums resultVOEnums, T data) {
        this.code = resultVOEnums.getCode();
        this.msg = resultVOEnums.getMsg();
        this.data = data;
    }
}
