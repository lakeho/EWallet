package vn.itsol.MSWallet.dao.wallet;

import vn.itsol.MSWallet.entities.UserWallet;
import vn.itsol.MSWallet.entities.Wallet;

import java.util.List;

public interface WalletDao
{
    List<UserWallet> findWalletbyuserid(int user_id);
    List<UserWallet> findWalletbywalletid(int wallet_id);
    Wallet getWallet(int wallet_id);
    long save(Wallet wallet);
    void update(Wallet wallet);
    void delete(int wallet_id);
}
