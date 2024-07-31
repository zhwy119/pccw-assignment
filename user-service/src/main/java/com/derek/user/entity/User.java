package com.derek.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.derek.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@TableName("user")
@ApiModel(description = "User entity")
public class User extends BaseEntity {
    @NotBlank(message = "User name cannot be blank")
    @ApiModelProperty(value = "User name")
    private String userName;

    @NotBlank(message = "Mobile number cannot be blank")
    @Pattern(regexp = "^\\d{11}$", message = "Mobile number must be 11 digits")
    @ApiModelProperty(value = "Mobile number")
    private String mobile;

    @NotBlank(message = "Email address cannot be blank")
    @Email(message = "Invalid email format")
    @ApiModelProperty(value = "Email address")
    private String email;
}

