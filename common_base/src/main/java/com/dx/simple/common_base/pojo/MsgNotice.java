package com.dx.simple.common_base.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Desc --
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2022/4/8
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MsgNotice implements Serializable {


    private boolean success;
    private LocalDateTime time;
    private String allNums;
    private String addNums;
}
