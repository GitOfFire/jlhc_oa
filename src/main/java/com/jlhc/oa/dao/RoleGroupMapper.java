package com.jlhc.oa.dao;

import com.jlhc.oa.dto.role.RoleGroup;
import com.jlhc.oa.dto.role.example.RoleGroupExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RoleGroupMapper {
    int countByExample(RoleGroupExample example);

    int deleteByExample(RoleGroupExample example);

    int deleteByPrimaryKey(Integer groupId);

    int insert(RoleGroup record);

    int insertSelective(RoleGroup record);

    List<RoleGroup> selectByExample(RoleGroupExample example);

    RoleGroup selectByPrimaryKey(Integer groupId);

    int updateByExampleSelective(@Param("record") RoleGroup record, @Param("example") RoleGroupExample example);

    int updateByExample(@Param("record") RoleGroup record, @Param("example") RoleGroupExample example);

    int updateByPrimaryKeySelective(RoleGroup record);

    int updateByPrimaryKey(RoleGroup record);
}