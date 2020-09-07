package vn.itsol.MSWallet.dto;

import javax.persistence.Column;
import java.util.Date;

public class TransactionsDto
{
    private long transId;
    private String transName;
    private long amount;
    private long transType;
    private String date;
    private String note;
    private long userId;
    private long walletId;
    private long categoryId;

    public long getTransId() {
        return transId;
    }

    public void setTransId(long transId) {
        this.transId = transId;
    }

    public String getTransName(){
        return transName;
    }

    public void setTransName(String transName){
        this.transName = transName;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getTransType() {
        return transType;
    }

    public void setTransType(long transType) {
        this.transType = transType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getWalletId() {
        return walletId;
    }

    public void setWalletId(long walletId) {
        this.walletId = walletId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public TransactionsDto(long transId, String transName, long amount, long transType, String date, String note, long userId, long walletId, long categoryId) {
        this.transId = transId;
        this.transName = transName;
        this.amount = amount;
        this.transType = transType;
        this.date = date;
        this.note = note;
        this.userId = userId;
        this.walletId = walletId;
        this.categoryId = categoryId;
    }

    public TransactionsDto() {
    }
}
