package com.jlhc.oa.dao;

import com.jlhc.oa.dto.function.FuncRoleRelation;
import com.jlhc.oa.dto.function.example.FuncRoleRelationExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncRoleRelationMapper {
    int countByExample(FuncRoleRelationExample example);

    int deleteByExample(FuncRoleRelationExample example);

    int deleteByPrimaryKey(Integer funcRoleId);

    int insert(FuncRoleRelation record);

    int insertSelective(FuncRoleRelation record);

    List<FuncRoleRelation> selectByExample(FuncRoleRelationExample example);

    FuncRoleRelation selectByPrimaryKey(Integer funcRoleId);

    int updateByExampleSelective(@Param("record") FuncRoleRelation record, @Param("example") FuncRoleRelationExample example);

    int updateByExample(@Param("record") FuncRoleRelation record, @Param("example") FuncRoleRelationExample example);

    int updateByPrimaryKeySelective(FuncRoleRelation record);

    int updateByPrimaryKey(FuncRoleRelation record);

    List<Integer> selectFuncIdsByRoleId(Integer roleId);
}