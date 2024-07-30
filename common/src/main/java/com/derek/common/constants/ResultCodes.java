package com.derek.common.constants;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author Derek Zhu
 * @Description
 * @Date 2024/07/11:02
 */
@ApiModel(description = "Result codes for API responses")
public final class ResultCodes {

    @ApiModelProperty(value = "Success code")
    public static final String SUCCESS = "01";

    @ApiModelProperty(value = "Failure code")
    public static final String FAILURE = "02";

    private ResultCodes() {
        // Private constructor to prevent instantiation
    }
}
