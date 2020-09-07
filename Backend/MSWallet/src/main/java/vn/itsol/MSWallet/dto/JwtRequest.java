package vn.itsol.MSWallet.dto;

import java.io.Serializable;

public class JwtRequest implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    private String userName;
    private String passWord;

    //need default constructor for JSON Parsing
    public JwtRequest()
    {

    }

    public JwtRequest(String userName, String passWord) {
        this.setUserName(userName);
        this.setPassWord(passWord);
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
