package com.uol.client.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequestVO {

    @ApiModelProperty(notes = "Name's client", example = "Jose Silva", required = true, position = 1)
    private String name;

    @ApiModelProperty(notes = "Age's client", example = "25", required = true, position = 2)
    private Integer age;
}
