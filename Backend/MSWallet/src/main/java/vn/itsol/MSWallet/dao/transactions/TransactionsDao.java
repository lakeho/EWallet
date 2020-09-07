package vn.itsol.MSWallet.dao.transactions;

import vn.itsol.MSWallet.entities.Transactions;

import java.util.List;

public interface TransactionsDao {
    List<Transactions> getTransactionsUser(int user_id);
    List<Transactions> getTransactionsCategory(int category_id);
    List<Transactions> getTransactionsWallet(int wallet_id);
    Transactions getTransaction(int tran_id);
    void save(Transactions transactions);
    void update(Transactions transactions);
    void delete(long tran_id);
    void deleteByWalletID(long wallet_id);
}
