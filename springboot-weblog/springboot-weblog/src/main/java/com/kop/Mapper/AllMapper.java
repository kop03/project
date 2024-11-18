package com.kop.Mapper;

import com.kop.pojo.*;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AllMapper {
    @Select("select * from average_visits")
    public List<com.kop.pojo.AverageVisits> average_visitsAll();

    @Select("select * from buonce_rate")
    public List<BuonceRate> buonce_rateAll();

    @Select("select * from consoildate_retention_rate")
    public List<com.kop.pojo.ConsoildateRetentionRate> consoildate_retention_rateAll();

    @Select("select * from fiveday_retenition_rate")
    public List<FivedayRetenitionRate> fiveday_retenition_rate();

    @Select("select * from nextday_retenition_rate")
    public List<NextdayRetenitionRate> nextday_retenition_rate();

    @Select("select * from overall_statistics")
    public List<OverallStatistics> overall_statistics();

    @Select("select * from repurchase_rate")
    public List<Repurchase_rate> repurchase_rate();

    @Select("select * from rfm_usertype")
    public List<rfm_usertype> rfm_usertype();

    @Select("select * from severnday_retenition_rate")
    public List<severnday_retenition_rate> severnday_retenition_rate();

    @Select("select * from statistics_four_behaviors_of_users")
    public List<statistics_four_behaviors_of_users> statistics_four_behaviors_of_users();

    @Select("select * from the_most_repeat_purchases")
    public List<the_most_repeat_purchases> the_most_repeat_purchases();

    @Select("select * from the_number_of_purchases_person")
    public List<the_number_of_purchases_person> the_number_of_purchases_person();

    @Select("select * from threeday_retenition_rate")
    public List<threeday_retenition_rate> threeday_retenition_rate();
// type_of_product_purchased_the_most_repeatedly


    @Select("select * from type_of_product_purchased_the_most_repeatedly")
    public List<type_of_product_purchased_the_most_repeatedly> type_of_product_purchased_the_most_repeatedly();

    @Select("select * from user_add_everyday")
    public List<UserAddEvery> searchAll();

    @Select("select * from user_behaviors_change_hour")
    public List<user_behaviors_change_hour> user_behaviors_change_hour();

    @Select("select * from user_rank")
    public List<user_rank> user_rank();

    @Select("select * from user_recenvy_frequency")
    public List<user_recenvy_frequency> user_recenvy_frequency();

    @Select("select * from user_score")
    public List<user_score> user_score();

    @Select("select * from users_with_most_repeat_purchases")
    public List<users_with_most_repeat_purchases> users_with_most_repeat_purchases();


}
