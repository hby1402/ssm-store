package com.hby.mapper;

import com.hby.pojo.Articletype;
import com.hby.pojo.ArticletypeExample;
import com.hby.pojo.SecondArticleType;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ArticletypeMapper {
    long countByExample(ArticletypeExample example);

    int deleteByExample(ArticletypeExample example);

    int deleteByPrimaryKey(String code);

    int insert(Articletype record);

    int insertSelective(Articletype record);

    List<Articletype> selectByExample(ArticletypeExample example);
    
    //一级二级物品分类
    List<Articletype> selectByCodeLength(ArticletypeExample example);
    List<SecondArticleType> selectBySecondCodeLength(String code);

    Articletype selectByPrimaryKey(String code);

    int updateByExampleSelective(@Param("record") Articletype record, @Param("example") ArticletypeExample example);

    int updateByExample(@Param("record") Articletype record, @Param("example") ArticletypeExample example);

    int updateByPrimaryKeySelective(Articletype record);

    int updateByPrimaryKey(Articletype record);
}