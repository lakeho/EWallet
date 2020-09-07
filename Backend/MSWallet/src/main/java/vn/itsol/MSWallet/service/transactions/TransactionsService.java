package vn.itsol.MSWallet.service.transactions;

import vn.itsol.MSWallet.dto.TransactionsDisplay;
import vn.itsol.MSWallet.dto.TransactionsDto;

import java.util.List;

public interface TransactionsService
{
    List<TransactionsDisplay> getTransactionsDisplay(int wallet_id);
    List<TransactionsDto> getTransactionsCategory(int category_id);
    List<TransactionsDto> getTransactionsWallet(int wallet_id);
    TransactionsDto gettran(int tran_id);
    void save(TransactionsDto tran);
    void update(TransactionsDto tran);
    void delete(long tran_id);
    void deleteByWalletId(long wallet_id);
}
