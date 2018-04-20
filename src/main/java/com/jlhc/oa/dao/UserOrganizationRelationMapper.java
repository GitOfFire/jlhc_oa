package com.jlhc.oa.dao;

import com.jlhc.oa.dto.user.UserOrganizationRelation;
import com.jlhc.oa.dto.user.example.UserOrganizationRelationExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserOrganizationRelationMapper {
    int countByExample(UserOrganizationRelationExample example);

    int deleteByExample(UserOrganizationRelationExample example);

    int deleteByPrimaryKey(Integer userOrgId);

    int insert(UserOrganizationRelation record);

    int insertSelective(UserOrganizationRelation record);

    List<UserOrganizationRelation> selectByExample(UserOrganizationRelationExample example);

    UserOrganizationRelation selectByPrimaryKey(Integer userOrgId);

    int updateByExampleSelective(@Param("record") UserOrganizationRelation record, @Param("example") UserOrganizationRelationExample example);

    int updateByExample(@Param("record") UserOrganizationRelation record, @Param("example") UserOrganizationRelationExample example);

    int updateByPrimaryKeySelective(UserOrganizationRelation record);

    int updateByPrimaryKey(UserOrganizationRelation record);
}