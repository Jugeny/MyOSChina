package com.example.administrator.myoschina.bean;

/**
 * Created by Administrator on 2017/4/27.
 */

public class TokenResponse {

    /**
     * access_token : 5250acea-01c9-4e65-a363-c81bd1e493c0
     * refresh_token : dc54efb5-0193-4845-83d8-2d877d29a9b0
     * uid : 3453719
     * token_type : bearer
     * expires_in : 604799
     */

    private String access_token;
    private String refresh_token;
    private int uid;
    private String token_type;
    private int expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }
}
