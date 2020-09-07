package vn.itsol.MSWallet.dto;

import java.util.Date;

public class TransactionsDisplay {
    //trans_id, trans_name, amount, trans_type, date, note, user_id, user_name, wallet_id
    //    wallet_name, role, category_id, category_name
    private long trans_id;
    private String trans_name;
    private long amount;
    private long trans_type;
    private String date;
    private String note;
    private long user_id;
    private String user_name;
    private long wallet_id;
    private String wallet_name;
    private long role;
    private long category_id;
    private String category_name;

    public TransactionsDisplay() {
    }

    public TransactionsDisplay(long trans_id, String trans_name, long amount, long trans_type, String date, String note, long user_id, String user_name, long wallet_id, String wallet_name, long role, long category_id, String category_name) {
        this.trans_id = trans_id;
        this.trans_name = trans_name;
        this.amount = amount;
        this.trans_type = trans_type;
        this.date = date;
        this.note = note;
        this.user_id = user_id;
        this.user_name = user_name;
        this.wallet_id = wallet_id;
        this.wallet_name = wallet_name;
        this.role = role;
        this.category_id = category_id;
        this.category_name = category_name;
    }

    public long getTrans_id() {
        return trans_id;
    }

    public void setTrans_id(long trans_id) {
        this.trans_id = trans_id;
    }

    public String getTrans_name() {
        return trans_name;
    }

    public void setTrans_name(String trans_name) {
        this.trans_name = trans_name;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getTrans_type() {
        return trans_type;
    }

    public void setTrans_type(long trans_type) {
        this.trans_type = trans_type;
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

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public long getWallet_id() {
        return wallet_id;
    }

    public void setWallet_id(long wallet_id) {
        this.wallet_id = wallet_id;
    }

    public String getWallet_name() {
        return wallet_name;
    }

    public void setWallet_name(String wallet_name) {
        this.wallet_name = wallet_name;
    }

    public long getRole() {
        return role;
    }

    public void setRole(long role) {
        this.role = role;
    }

    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
