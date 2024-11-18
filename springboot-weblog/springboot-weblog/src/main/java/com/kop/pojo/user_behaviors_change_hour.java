package com.kop.pojo;

import lombok.Data;

@Data
public class user_behaviors_change_hour {
    private String  dates;
    private Integer  user_cnt;
    private Integer behavior_cnt;
    private Integer pv_sum;
    private Integer fav_sum;
    private Integer cart_sum;
    private Integer buy_sum;
}


