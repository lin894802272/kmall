package com.zwl.provider.mapper;


import java.util.List;

import com.kmall.pojo.TbContentCategory;
import com.kmall.pojo.TbContentCategoryExample;
import org.apache.ibatis.annotations.Param;

public interface TbContentCategoryMapper {
    int countByExample(TbContentCategoryExample example);

    int deleteByExample(TbContentCategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbContentCategory record);

    int insertSelective(TbContentCategory record);

    List<TbContentCategory> selectByExample(TbContentCategoryExample example);

    TbContentCategory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbContentCategory record, @Param("zwl") TbContentCategoryExample example);

    int updateByExample(@Param("record") TbContentCategory record, @Param("zwl") TbContentCategoryExample example);

    int updateByPrimaryKeySelective(TbContentCategory record);

    int updateByPrimaryKey(TbContentCategory record);
}