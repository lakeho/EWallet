package vn.itsol.MSWallet.dao.userwallet;

import vn.itsol.MSWallet.entities.UserWallet;

import java.util.List;

public interface UserWalletDao
{
    List<UserWallet> getUserWallet(int wallet_id);
    List<UserWallet> getUserWalletByUseridWalletid(int wallet_id, int user_id);
    void save(UserWallet userWallet);
    void delete(int wallet_id);
    void deleteSingle(int user_id, int wallet_id);
}
