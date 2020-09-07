package vn.itsol.MSWallet.service.userwallet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.itsol.MSWallet.dao.userwallet.UserWalletDao;
import vn.itsol.MSWallet.dto.UserWalletDto;
import vn.itsol.MSWallet.entities.UserWallet;
import vn.itsol.MSWallet.entities.Users;
import vn.itsol.MSWallet.entities.Wallet;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserWalletServiceImpl implements UserWalletService
{
    private static final Logger log = LoggerFactory.getLogger(UserWalletServiceImpl.class);

    @Autowired
    private UserWalletDao userWalletDao;

    @Transactional
    @Override
    public List<UserWalletDto> getUserWallet(int wallet_id) {
        List<UserWallet> userWalletList = userWalletDao.getUserWallet(wallet_id);
        List<UserWalletDto> userWalletDtoList = new ArrayList<>();
        for (UserWallet userWallet: userWalletList) {
            UserWalletDto userWalletDto = new UserWalletDto();
            userWalletDto.setWalletId(userWallet.getWallet().getWalletId());
            userWalletDto.setUserWalletId(userWallet.getUserWalletId());
            userWalletDto.setUserId(userWallet.getUsers().getUserId());
            userWalletDto.setRole(userWallet.getRole());
            userWalletDtoList.add(userWalletDto);
        }
        return userWalletDtoList;
    }

    @Override
    public List<UserWalletDto> getUserWalletByUseridWalletid(int wallet_id, int user_id) {
        List<UserWallet> userWalletList = userWalletDao.getUserWalletByUseridWalletid(wallet_id, user_id);
        List<UserWalletDto> userWalletDtoList = new ArrayList<>();
        for (UserWallet userWallet: userWalletList) {
            UserWalletDto userWalletDto = new UserWalletDto();
            userWalletDto.setWalletId(userWallet.getWallet().getWalletId());
            userWalletDto.setUserWalletId(userWallet.getUserWalletId());
            userWalletDto.setUserId(userWallet.getUsers().getUserId());
            userWalletDto.setRole(userWallet.getRole());
            userWalletDtoList.add(userWalletDto);
        }
        return userWalletDtoList;
    }

    @Transactional
    @Override
    public void save(UserWalletDto userWalletDto) {
        UserWallet userWallet = new UserWallet();
        Wallet wallet = new Wallet();
        wallet.setWalletId(userWalletDto.getWalletId());
        Users users = new Users();
        users.setUserId(userWalletDto.getUserId());

        userWallet.setRole(userWalletDto.getRole());
        userWallet.setWallet(wallet);
        userWallet.setUserWalletId(userWalletDto.getUserWalletId());
        userWallet.setUsers(users);

        userWalletDao.save(userWallet);
    }

    @Transactional
    @Override
    public void delete(int wallet_id) {
        userWalletDao.delete(wallet_id);
    }

    @Transactional
    @Override
    public void deleteSingle(int user_id, int wallet_id) {
        userWalletDao.deleteSingle(user_id, wallet_id);
    }
}
