package com.jlhc.oa.dao;

import com.jlhc.oa.dto.function.Function;
import com.jlhc.oa.dto.function.example.FunctionExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FunctionMapper {
    int countByExample(FunctionExample example);

    int deleteByExample(FunctionExample example);

    int deleteByPrimaryKey(Integer funcId);

    int insert(Function record);

    int insertSelective(Function record);

    List<Function> selectByExample(FunctionExample example);

    Function selectByPrimaryKey(Integer funcId);

    int updateByExampleSelective(@Param("record") Function record, @Param("example") FunctionExample example);

    int updateByExample(@Param("record") Function record, @Param("example") FunctionExample example);

    int updateByPrimaryKeySelective(Function record);

    int updateByPrimaryKey(Function record);

    List<Function> selectByRoleId(Integer roleId);

    List<Integer> getFunctionIdsByIdListParams(List<Integer> funcIds);
    List<Integer> getFunctionModuleIdsByByIdListParams(List<Integer> funcIds);

    Integer insertNotExistTheSameName(Function function);
}