package org.forstudy.sell.VO;

import lombok.Data;
import org.forstudy.sell.enums.ResultVOEnums;

import java.io.Serializable;


@Data
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 4588816873239478302L;

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
