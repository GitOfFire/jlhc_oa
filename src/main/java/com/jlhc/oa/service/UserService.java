package com.jlhc.oa.service;

import com.jlhc.common.exception.NullEntityInDatabaseException;
import com.jlhc.oa.dto.user.User;
import com.jlhc.oa.dto.user.UserAndOrgId;
import com.jlhc.oa.dto.user.UserBase;
import com.jlhc.sell.dto.Task;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserService {

    User queryUserWithRolesAndRightByName(String username);

    User getAllRolesAndRights();

    User getUserById(Integer userId);

    Integer createUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException, NullEntityInDatabaseException;

    List<User> queryUsersByOrgId(Integer orgId);

    List<User> queryUsersNearUserTrueName(String userTrueName);

    Integer reworkBaseUser(UserBase userBase) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    Integer reworkBaseUserAndOrg(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException, NullEntityInDatabaseException;

    Integer dropUser(Integer userId);

    List<User> queryUsersByRoleId(Integer roleId);

    Integer reworkPasswords(Integer userId, String oldPasswords,String newPasswords) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    Integer reworkOrgIdOfUser(Integer userId, Integer orgId) throws NullEntityInDatabaseException;

    User queryUserByUsername(String username);

    List<User> queryAllUsersByOrgId(Integer orgId);

    Integer createAdminUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    List<User> queryAdminUserOfOrg(Integer orgId);

    List<User> queryUsersByTaskIdInTaskUserRelation(String taskId);

    List<User> getUsersOfOrg(Integer orgId) throws NullEntityInDatabaseException;
}
