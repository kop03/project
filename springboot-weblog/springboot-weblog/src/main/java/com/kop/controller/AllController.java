package com.kop.controller;


import com.kop.Mapper.AllMapper;

import com.kop.pojo.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AllController {

    @Autowired
    private AllMapper averageVisitsMapper;

    @GetMapping("/data1")
    @CrossOrigin
    public List<AverageVisits> average_visitsAll(){
        return averageVisitsMapper.average_visitsAll();
    }

    @Autowired
    private AllMapper buonceRateMapper;
    @GetMapping("/data2")
    @CrossOrigin
    public List<BuonceRate> buonce_rateAll(){
        return buonceRateMapper.buonce_rateAll();
    }

    @Autowired
    private AllMapper consoildateRetentionRateMapper;

    @GetMapping("/data3")
    @CrossOrigin
    public List<ConsoildateRetentionRate> consoildate_retention_rate(){
        return consoildateRetentionRateMapper.consoildate_retention_rateAll();
    }

    @Autowired
    private AllMapper fiveday_retenition_rate;

    @GetMapping("/data4")
    @CrossOrigin
    public List<FivedayRetenitionRate> fiveday_retenition_rate(){
        return fiveday_retenition_rate.fiveday_retenition_rate();
    }

    @Autowired
    private AllMapper nextday;

    @GetMapping("/data5")
    @CrossOrigin
    public List<NextdayRetenitionRate> nextday_retenition_rate(){
        return nextday.nextday_retenition_rate();
    }

    @Autowired
    private AllMapper overall_statistics;

    @GetMapping("/data6")
    @CrossOrigin
    public List<OverallStatistics> overall_statistics(){
        return overall_statistics.overall_statistics();
    }

    @Autowired
    private AllMapper repurchase_rate;

    @GetMapping("/data7")
    @CrossOrigin
    public List<Repurchase_rate> repurchase_rate(){
        return repurchase_rate.repurchase_rate();
    }

    @Autowired
    private AllMapper rfm_usertype;

    @GetMapping("/data8")
    @CrossOrigin
    public List<rfm_usertype> rfm_usertype(){
        return rfm_usertype.rfm_usertype();
    }

    @Autowired
    private AllMapper severnday_retenition_rate;

    @GetMapping("/data9")
    @CrossOrigin
    public List<severnday_retenition_rate> severnday_retenition_rate(){
        return severnday_retenition_rate.severnday_retenition_rate();
    }

    @Autowired
    private AllMapper statistics_four_behaviors_of_users;

    @GetMapping("/data10")
    @CrossOrigin
    public List<statistics_four_behaviors_of_users> statistics_four_behaviors_of_users(){
        return statistics_four_behaviors_of_users.statistics_four_behaviors_of_users();
    }

    @Autowired
    private AllMapper the_most_repeat_purchases;

    @GetMapping("/data11")
    @CrossOrigin
    public List<the_most_repeat_purchases> the_most_repeat_purchases(){
        return the_most_repeat_purchases.the_most_repeat_purchases();
    }

    @Autowired
    private AllMapper the_number_of_purchases_person;

    @GetMapping("/data12")
    @CrossOrigin
    public List<the_number_of_purchases_person> the_number_of_purchases_person(){
        return the_number_of_purchases_person.the_number_of_purchases_person();
    }

    @Autowired
    private AllMapper threeday_retenition_rate;

    @GetMapping("/data13")
    @CrossOrigin
    public List<threeday_retenition_rate> threeday_retenition_rate(){
        return threeday_retenition_rate.threeday_retenition_rate();
    }

    @Autowired
    private AllMapper type_of_product_purchased_the_most_repeatedly;

    @GetMapping("/data14")
    @CrossOrigin
    public List<type_of_product_purchased_the_most_repeatedly> type_of_product_purchased_the_most_repeatedly(){
        return type_of_product_purchased_the_most_repeatedly.type_of_product_purchased_the_most_repeatedly();
    }

    @Autowired
    private AllMapper userAddEveryDayMapper;

    @GetMapping("/data15")
    @CrossOrigin
    public List<UserAddEvery> searchAll(){
        return userAddEveryDayMapper.searchAll();
    }

    @Autowired
    private AllMapper user_behaviors_change_hour;

    @GetMapping("/data16")
    @CrossOrigin
    public List<user_behaviors_change_hour> user_behaviors_change_hour(){
        return user_behaviors_change_hour.user_behaviors_change_hour();
    }

    @Autowired
    private AllMapper user_rank;

    @GetMapping("/data17")
    @CrossOrigin
    public List<user_rank> user_rank(){
        return user_rank.user_rank();
    }

    @Autowired
    private AllMapper user_recenvy_frequency;

    @GetMapping("/data18")
    @CrossOrigin
    public List<user_recenvy_frequency> user_recenvy_frequency(){
        return user_recenvy_frequency.user_recenvy_frequency();
    }

    @Autowired
    private AllMapper user_score;

    @GetMapping("/data19")
    @CrossOrigin
    public List<user_score> user_score(){
        return user_score.user_score();
    }

    @Autowired
    private AllMapper users_with_most_repeat_purchases;

    @GetMapping("/data20")
    @CrossOrigin
    public List<users_with_most_repeat_purchases> users_with_most_repeat_purchases(){
        return users_with_most_repeat_purchases.users_with_most_repeat_purchases();
    }
}
