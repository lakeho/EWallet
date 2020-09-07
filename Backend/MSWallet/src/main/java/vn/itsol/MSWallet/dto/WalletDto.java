package vn.itsol.MSWallet.dto;

import javax.persistence.Column;
import java.util.Date;

public class WalletDto
{
    private long walletId;
    private String wallertName;
    private long balance;
    private long currency;
    private String createDate;

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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public WalletDto(long walletId, String wallertName, long balance, long currency, String createDate) {
        this.walletId = walletId;
        this.wallertName = wallertName;
        this.balance = balance;
        this.currency = currency;
        this.createDate = createDate;
    }

    public WalletDto() {
    }
}
