package vn.itsol.MSWallet.service.transactions;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.itsol.MSWallet.dao.category.CategoryDao;
import vn.itsol.MSWallet.dao.transactions.TransactionsDao;
import vn.itsol.MSWallet.dao.users.UsersDao;
import vn.itsol.MSWallet.dao.userwallet.UserWalletDao;
import vn.itsol.MSWallet.dao.wallet.WalletDao;
import vn.itsol.MSWallet.dto.TransactionsDisplay;
import vn.itsol.MSWallet.dto.TransactionsDto;
import vn.itsol.MSWallet.entities.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionsServiceImpl implements TransactionsService
{
    private static final Logger log = LoggerFactory.getLogger(TransactionsServiceImpl.class);

    @Autowired
    private TransactionsDao transactionsDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private WalletDao walletDao;

    @Autowired
    private UserWalletDao userWalletDao;

    //trans_id, trans_name, amount, trans_type, date, note, user_id, user_name, wallet_id
    //    wallet_name, role, category_id, category_name

    @Transactional
    @Override
    public List<TransactionsDisplay> getTransactionsDisplay(int wallet_id) {
        List<Transactions> transactionsList = transactionsDao.getTransactionsWallet(wallet_id);
        List<TransactionsDisplay> transactionsDtoList = new ArrayList<>();
        for(Transactions transactions : transactionsList){
            TransactionsDisplay  transactionsDisplay = new TransactionsDisplay();
            Category category = categoryDao.getCatetgory((int)transactions.getCategory().getCategoryid());
            Users user = usersDao.GetUser((int) transactions.getUsers().getUserId());
            Wallet wallet = walletDao.getWallet((int) transactions.getWallet().getWalletId());
            List<UserWallet> userWalletList = userWalletDao.getUserWallet((int) transactions.getWallet().getWalletId());

            for (UserWallet userWallet : userWalletList) {
                transactionsDisplay.setTrans_id(transactions.getTransId());
                transactionsDisplay.setTrans_name(transactions.getTransName());
                transactionsDisplay.setAmount(transactions.getAmount());
                transactionsDisplay.setCategory_id(category.getCategoryid());
                transactionsDisplay.setCategory_name(category.getCategoryname());
                transactionsDisplay.setDate(transactions.getDate());
                transactionsDisplay.setNote(transactions.getNote());
                transactionsDisplay.setRole(userWallet.getRole());
                transactionsDisplay.setTrans_type(transactions.getTransType());
                transactionsDisplay.setUser_id(user.getUserId());
                transactionsDisplay.setUser_name(user.getUserName());
                transactionsDisplay.setWallet_id(wallet.getWalletId());
                transactionsDisplay.setWallet_name(wallet.getWallertName());
            }
            transactionsDtoList.add(transactionsDisplay);
        }
        return transactionsDtoList;
    }

    @Transactional
    @Override
    public List<TransactionsDto> getTransactionsCategory(int category_id) {
        List<Transactions> transactionsList = transactionsDao.getTransactionsCategory(category_id);
        List<TransactionsDto> transactionsDtoList = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        for(Transactions transactions : transactionsList){
            TransactionsDto transactionsDto = modelMapper.map(transactions, TransactionsDto.class);
            transactionsDto.setUserId(transactions.getUsers().getUserId());
            transactionsDtoList.add(transactionsDto);
        }
        return transactionsDtoList;
    }

    @Transactional
    @Override
    public List<TransactionsDto> getTransactionsWallet(int wallet_id) {
        List<Transactions> transactionsList = transactionsDao.getTransactionsWallet(wallet_id);
        List<TransactionsDto> transactionsDtoList = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        for (Transactions transactions : transactionsList)
        {
            TransactionsDto transactionsDto = modelMapper.map(transactions, TransactionsDto.class);
            transactionsDto.setUserId(transactions.getUsers().getUserId());
            transactionsDtoList.add(transactionsDto);
        }
        return transactionsDtoList;
    }

    @Transactional
    @Override
    public TransactionsDto gettran(int tran_id) {
        Transactions transactions = transactionsDao.getTransaction(tran_id);
        TransactionsDto transactionsDto = new TransactionsDto();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        transactionsDto = modelMapper.map(transactions, TransactionsDto.class);
        transactionsDto.setUserId(transactions.getUsers().getUserId());
        return transactionsDto;
    }

    @Transactional
    @Override
    public void save(TransactionsDto tran) {
        Transactions transactions = new Transactions();

        Category category = new Category();
        category.setCategoryid(tran.getCategoryId());

        Users users = new Users();
        users.setUserId(tran.getUserId());

        Wallet wallet = new Wallet();
        wallet.setWalletId(tran.getWalletId());

        transactions.setTransName(tran.getTransName());
        transactions.setAmount(tran.getAmount());
        transactions.setCategory(category);
        transactions.setDate(tran.getDate());
        transactions.setNote(tran.getNote());
        transactions.setTransId(tran.getTransId());
        transactions.setTransType(tran.getTransType());
        transactions.setUsers(users);
        transactions.setWallet(wallet);

        transactionsDao.save(transactions);
    }

    @Transactional
    @Override
    public void update(TransactionsDto tran) {
        Transactions transactions = new Transactions();

        Category category = new Category();
        category.setCategoryid(tran.getCategoryId());

        Users users = new Users();
        users.setUserId(tran.getUserId());

        Wallet wallet = new Wallet();
        wallet.setWalletId(tran.getWalletId());

        transactions.setTransName(tran.getTransName());
        transactions.setAmount(tran.getAmount());
        transactions.setCategory(category);
        transactions.setDate(tran.getDate());
        transactions.setNote(tran.getNote());
        transactions.setTransId(tran.getTransId());
        transactions.setTransType(tran.getTransType());
        transactions.setUsers(users);
        transactions.setWallet(wallet);

        transactionsDao.update(transactions);
    }

    @Transactional
    @Override
    public void delete(long tran_id) {
        transactionsDao.delete(tran_id);
    }

    @Transactional
    @Override
    public void deleteByWalletId(long wallet_id) {
        transactionsDao.deleteByWalletID(wallet_id);
    }
}
