package com.jlhc.oa.dto.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.shiro.authc.HostAuthenticationToken;
import org.apache.shiro.authc.RememberMeAuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UsernameAndPasswdToken extends UsernamePasswordToken implements HostAuthenticationToken, RememberMeAuthenticationToken {

    @NotEmpty
    @NotBlank
    private String username;
    @NotEmpty
    @Size(min = 1)
    private char[] password;
    private boolean rememberMe;
    private String host;

    public UsernameAndPasswdToken() {
        this.rememberMe = false;
    }

    public UsernameAndPasswdToken(String username, char[] password) {
        this(username, (char[])password, false, (String)null);
    }

    public UsernameAndPasswdToken(String username, String password) {
        this(username, (char[])(password != null ? password.toCharArray() : null), false, (String)null);
    }

    public UsernameAndPasswdToken(String username, char[] password, String host) {
        this(username, password, false, host);
    }

    public UsernameAndPasswdToken(String username, String password, String host) {
        this(username, password != null ? password.toCharArray() : null, false, host);
    }

    public UsernameAndPasswdToken(String username, char[] password, boolean rememberMe) {
        this(username, (char[])password, rememberMe, (String)null);
    }

    public UsernameAndPasswdToken(String username, String password, boolean rememberMe) {
        this(username, (char[])(password != null ? password.toCharArray() : null), rememberMe, (String)null);
    }

    public UsernameAndPasswdToken(String username, char[] password, boolean rememberMe, String host) {
        this.rememberMe = false;
        this.username = username;
        this.password = password;
        this.rememberMe = rememberMe;
        this.host = host;
    }

    public UsernameAndPasswdToken(String username, String password, boolean rememberMe, String host) {
        this(username, password != null ? password.toCharArray() : null, rememberMe, host);
    }

    public void clear() {
        this.username = null;
        this.host = null;
        this.rememberMe = false;
        if (this.password != null) {
            for(int i = 0; i < this.password.length; ++i) {
                this.password[i] = 0;
            }

            this.password = null;
        }

    }


    @Override
    public Object getPrincipal() {
        return this.getUsername();
    }
    @Override
    public Object getCredentials() {
        return this.getPassword();
    }
}
