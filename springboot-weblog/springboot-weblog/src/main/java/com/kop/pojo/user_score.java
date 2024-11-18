package com.kop.pojo;

import lombok.Data;

@Data
public class user_score {
    private Integer  user_id;
    private Integer recency;
    private Integer frequency;
    private Integer r_score;
    private Integer f_score;

}


