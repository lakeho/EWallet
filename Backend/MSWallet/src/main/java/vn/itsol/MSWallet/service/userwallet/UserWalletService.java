package vn.itsol.MSWallet.service.userwallet;

import vn.itsol.MSWallet.dto.UserWalletDto;
import vn.itsol.MSWallet.dto.WalletDto;

import java.util.List;

public interface UserWalletService {
    List<UserWalletDto> getUserWallet(int wallet_id);
    List<UserWalletDto> getUserWalletByUseridWalletid(int wallet_id, int user_id);
    void save(UserWalletDto userWalletDto);
    void delete(int wallet_id);
    void deleteSingle(int user_id, int wallet_id);
}
