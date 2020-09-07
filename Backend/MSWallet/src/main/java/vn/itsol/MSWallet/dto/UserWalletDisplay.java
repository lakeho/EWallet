package vn.itsol.MSWallet.dto;

public class UserWalletDisplay {
//user_id, user_name, name, wallet_id, wallet_name, balance, currency và role
    private long userId;
    private String userName;
    private String name;
    private long walletId;
    private String wallertName;
    private long balance;
    private long currency;
    private long role;
    private String date;

    public UserWalletDisplay() {
    }

    public UserWalletDisplay(long userId, String userName, String name, long walletId, String wallertName, long balance, long currency, long role, String date) {
        this.userId = userId;
        this.userName = userName;
        this.name = name;
        this.walletId = walletId;
        this.wallertName = wallertName;
        this.balance = balance;
        this.currency = currency;
        this.role = role;
        this.date = date;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getWalletId() {
        return walletId;
    }

    public void setWalletId(long walletId) {
        this.walletId = walletId;
    }

    public String getWallertName() {
        return wallertName;
    }

    public void setWallertName(String wallertName) {
        this.wallertName = wallertName;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public long getCurrency() {
        return currency;
    }

    public void setCurrency(long currency) {
        this.currency = currency;
    }

    public long getRole() {
        return role;
    }

    public void setRole(long role) {
        this.role = role;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }
}
