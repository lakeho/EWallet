package vn.itsol.MSWallet.repository;

import org.springframework.data.repository.CrudRepository;
import vn.itsol.MSWallet.entities.Wallet;

public interface WalletRepository extends CrudRepository<Wallet, Long> {
}
