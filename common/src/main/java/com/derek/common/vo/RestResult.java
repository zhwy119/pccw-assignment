package com.derek.common.vo;


import com.derek.common.constants.ResultCodes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(
        description = "common return message of apis"
)
public class RestResult {
    @ApiModelProperty("api calling result, true or false")
    private boolean result;
    @ApiModelProperty("message of api calling")
    private String message;
    @ApiModelProperty("code of api calling")
    private String code;
    @ApiModelProperty("sub code of api calling")
    private String subCode;
    @ApiModelProperty("object of result")
    private Object object;

    public RestResult() {
    }

    public RestResult(boolean result) {
        this.result = result;
    }

    public RestResult(boolean result, String message) {
        this.result = result;
        this.message = message;
    }

    public RestResult(boolean result, String message, String code) {
        this.result = result;
        this.message = message;
        this.code = code;
    }

    public static RestResult error() {
        return new RestResult(false, "system error",ResultCodes.FAILURE);
    }

    public static RestResult success() {
        return new RestResult(true, "success", ResultCodes.SUCCESS);
    }

    public static RestResult success(String code) {
        RestResult restResult = success();
        restResult.setCode(code);
        return restResult;
    }

    public static RestResult fail(String message) {
        return new RestResult(false, message);
    }

    public static RestResult fail(String code, String message) {
        return new RestResult(false, message, code);
    }

    public boolean isResult() {
        return this.result;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return this.code;
    }

    public String getSubCode() {
        return this.subCode;
    }

    public Object getObject() {
        return this.object;
    }

    public void setResult(final boolean result) {
        this.result = result;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public void setSubCode(final String subCode) {
        this.subCode = subCode;
    }

    public void setObject(final Object object) {
        this.object = object;
    }


    public String toString() {
        return "RestResult(result=" + this.isResult() + ", message=" + this.getMessage() + ", code=" + this.getCode() + ", subCode=" + this.getSubCode() + ", object=" + this.getObject() + ")";
    }
}
