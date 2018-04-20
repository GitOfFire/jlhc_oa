package com.jlhc.web.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

public class CredentialsMatcher extends SimpleCredentialsMatcher{
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {

        UsernamePasswordToken utoken=(UsernamePasswordToken) token;
        //获得用户输入的密码:(可以采用加盐(salt)的方式去检验)此处使用最简单的验证方法,加盐的方法放到logcontorller了,待改进

        String inPassword = new String(utoken.getPassword());
        //System.out.println(inPassword);
        String dbPassword=(String)info.getCredentials();//这里出现问题了,这里是获取第二个传入密码参数,
        //进行密码的比对
        return this.equals(inPassword, dbPassword);
    }
}
