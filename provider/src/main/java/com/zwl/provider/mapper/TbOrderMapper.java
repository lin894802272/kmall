package com.zwl.provider.mapper;


import java.util.List;

import com.kmall.pojo.TbOrder;
import com.kmall.pojo.TbOrderExample;
import org.apache.ibatis.annotations.Param;

public interface TbOrderMapper {
    int countByExample(TbOrderExample example);

    int deleteByExample(TbOrderExample example);

    int deleteByPrimaryKey(String orderId);

    int insert(TbOrder record);

    int insertSelective(TbOrder record);

    List<TbOrder> selectByExample(TbOrderExample example);

    List<TbOrder> selectByExampleByPage(@Param("id") long id);

    TbOrder selectByPrimaryKey(String orderId);

    int updateByExampleSelective(@Param("record") TbOrder record, @Param("zwl") TbOrderExample example);

    int updateByExample(@Param("record") TbOrder record, @Param("zwl") TbOrderExample example);

    int updateByPrimaryKeySelective(TbOrder record);

    int updateByPrimaryKey(TbOrder record);


}