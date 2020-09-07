package vn.itsol.MSWallet.service.wallet;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.itsol.MSWallet.dao.transactions.TransactionsDao;
import vn.itsol.MSWallet.dao.users.UsersDao;
import vn.itsol.MSWallet.dao.userwallet.UserWalletDao;
import vn.itsol.MSWallet.dao.wallet.WalletDao;
import vn.itsol.MSWallet.dto.UserWalletDisplay;
import vn.itsol.MSWallet.dto.WalletDto;
import vn.itsol.MSWallet.entities.UserWallet;
import vn.itsol.MSWallet.entities.Users;
import vn.itsol.MSWallet.entities.Wallet;
import vn.itsol.MSWallet.repository.WalletRepository;
import vn.itsol.MSWallet.service.userwallet.UserWalletService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WalletServiceImpl implements WalletService
{
    private static final Logger log = LoggerFactory.getLogger(WalletServiceImpl.class);

    @Autowired
    private TransactionsDao transactionsDao;

    @Autowired
    private WalletDao walletDao;

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private UserWalletDao userWalletDao;

    @Autowired
    private WalletRepository walletRepo;

    @Override
    public List<UserWalletDisplay> findWalletbyuserid(int user_id) {
        List<UserWallet> userWalletList = walletDao.findWalletbyuserid(user_id);
        List<UserWalletDisplay> userWalletDisplayList = new ArrayList<>();

        for (UserWallet userWallet : userWalletList){
            UserWalletDisplay userWalletDisplay = new UserWalletDisplay();
            Wallet wallet = walletDao.getWallet((int)userWallet.getWallet().getWalletId());
            Users user = usersDao.GetUser((int)userWallet.getUsers().getUserId());

            userWalletDisplay.setWalletId(wallet.getWalletId());
            userWalletDisplay.setWallertName(wallet.getWallertName());
            userWalletDisplay.setUserId(user.getUserId());
            userWalletDisplay.setCurrency(wallet.getCurrency());
            userWalletDisplay.setBalance(wallet.getBalance());
            userWalletDisplay.setName(user.getName());
            userWalletDisplay.setRole(userWallet.getRole());
            userWalletDisplay.setUserName(user.getUserName());
            userWalletDisplay.setDate(wallet.getCreateDate());

            userWalletDisplayList.add(userWalletDisplay);
        }

        return userWalletDisplayList;
    }

    @Override
    public List<UserWalletDisplay> findWalletbywalletid(int wallet_id) {
        List<UserWallet> userWalletList = walletDao.findWalletbywalletid(wallet_id);
        List<UserWalletDisplay> userWalletDisplayList = new ArrayList<>();

        for (UserWallet userWallet : userWalletList){
            UserWalletDisplay userWalletDisplay = new UserWalletDisplay();
            Wallet wallet = walletDao.getWallet((int)userWallet.getWallet().getWalletId());
            Users user = usersDao.GetUser((int)userWallet.getUsers().getUserId());

            userWalletDisplay.setWalletId(wallet.getWalletId());
            userWalletDisplay.setWallertName(wallet.getWallertName());
            userWalletDisplay.setUserId(user.getUserId());
            userWalletDisplay.setCurrency(wallet.getCurrency());
            userWalletDisplay.setBalance(wallet.getBalance());
            userWalletDisplay.setName(user.getName());
            userWalletDisplay.setRole(userWallet.getRole());
            userWalletDisplay.setUserName(user.getUserName());

            userWalletDisplayList.add(userWalletDisplay);
        }

        return userWalletDisplayList;
    }

    @Transactional
    @Override
    public WalletDto getWallet(int wallet_id) {
        Wallet wallet = walletDao.getWallet(wallet_id);

        WalletDto walletDto = new WalletDto();

        walletDto.setWallertName(wallet.getWallertName());
        walletDto.setBalance(wallet.getBalance());
        walletDto.setCreateDate(wallet.getCreateDate());
        walletDto.setCurrency(wallet.getCurrency());
        walletDto.setWalletId(wallet.getWalletId());
        log.info("getWallet.walletDto: " + walletDto.toString() );

        return walletDto;
    }

    @Transactional
    @Override
    public void save(WalletDto wallet) {
        Wallet wallet1 = new Wallet();
        if(wallet != null && wallet.toString() != "") {
            wallet1.setBalance(wallet.getBalance());
            wallet1.setCreateDate(wallet.getCreateDate());
            wallet1.setCurrency(wallet.getCurrency());
            wallet1.setWallertName(wallet.getWallertName());
            //wallet1.setWalletId(wallet.getWalletId());
            log.info("save.wallet1: " + wallet1.toString());
            walletDao.save(wallet1);
//            return "success";
        }
//        return "false";
    }

    @Transactional
    @Override
    public WalletDto save2(WalletDto wallet) {
        Wallet wallet1 = new Wallet();

            wallet1.setBalance(wallet.getBalance());
            wallet1.setCreateDate(wallet.getCreateDate());
            wallet1.setCurrency(wallet.getCurrency());
            wallet1.setWallertName(wallet.getWallertName());
            //wallet1.setWalletId(wallet.getWalletId());
            log.info("save.wallet1: " + wallet1.toString());
            long a = walletDao.save(wallet1);
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setAmbiguityIgnored(true);
            WalletDto wa = modelMapper.map(wallet1,WalletDto.class);
            wa.setWalletId(a);
            return wa;


//        walletRepo.save(w);
//        ModelMapper modelMapper = new ModelMapper();
//        modelMapper.getConfiguration().setAmbiguityIgnored(true);
//        WalletDto wa = modelMapper.map(w,WalletDto.class);
//        wa.setWalletId(w.getWalletId());

    }

    @Transactional
    @Override
    public void update(WalletDto wallet) {
        Wallet wallet1 = new Wallet();

        wallet1.setBalance(wallet.getBalance());
        wallet1.setCreateDate(wallet.getCreateDate());
        wallet1.setCurrency(wallet.getCurrency());
        wallet1.setWallertName(wallet.getWallertName());
        wallet1.setWalletId(wallet.getWalletId());
        log.info("update.wallet1: " + wallet1.toString());
        walletDao.update(wallet1);
    }

    @Transactional
    @Override
    public void delete(int wallet_id) {
        List<UserWallet> userWalletList = userWalletDao.getUserWallet(wallet_id);
        if (userWalletList.size() != 0)
        {
            userWalletDao.delete(wallet_id);
            transactionsDao.deleteByWalletID(wallet_id);
        }
        walletDao.delete(wallet_id);
    }
}
