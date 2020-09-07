package vn.itsol.MSWallet.dao.wallet;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.itsol.MSWallet.entities.UserWallet;
import vn.itsol.MSWallet.entities.Users;
import vn.itsol.MSWallet.entities.Wallet;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class WalletDaoImpl implements WalletDao
{
    private static final Logger log = LoggerFactory.getLogger(WalletDaoImpl.class);

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<UserWallet> findWalletbyuserid(int user_id) {
        Session session = entityManager.unwrap(Session.class);
        String hql = "SELECT uw FROM UserWallet uw where uw.users.userId = " + user_id;

        Query<UserWallet> query = session.createQuery(hql, UserWallet.class);
        log.info("getWallet.query: " + query.toString());

        List<UserWallet> results = query.getResultList();
        log.info("getWallet.result: " + results);
        return results;
    }

    @Override
    public List<UserWallet> findWalletbywalletid(int wallet_id) {
        Session session = entityManager.unwrap(Session.class);
        String hql = "SELECT uw FROM UserWallet uw where uw.wallet.walletId = " + wallet_id;

        Query<UserWallet> query = session.createQuery(hql, UserWallet.class);
        log.info("getWallet.query: " + query.toString());

        List<UserWallet> results = query.getResultList();
        log.info("getWallet.result: " + results);
        return results;
    }

    @Override
    public Wallet getWallet(int wallet_id) {
        Session session = entityManager.unwrap(Session.class);
        String hql = "Select w From Wallet w Where w.walletId = " + wallet_id;

        Query<Wallet> query = session.createQuery(hql);
        log.info("getWallet.query: " + query.toString());

        Wallet result = query.getSingleResult();
        log.info("getWallet.result: " + result);
        return result;
    }

    @Override
    public long save(Wallet wallet) {
        Session session = entityManager.unwrap(Session.class);
        String sql = "INSERT INTO Wallet(wallet_id, wallert_name, balance, currency, create_date) " +
                "VALUES(((select max(wallet_id) from Wallet)+1), \'" + wallet.getWallertName() + "\'," + wallet.getBalance()
                + ","+ wallet.getCurrency() +", TO_DATE(\'"+ wallet.getCreateDate() +"\', \'dd/mm/yyyy\'))";

        Query<Wallet> query = session.createSQLQuery(sql);
        log.info("save.query: " + query.toString());
        query.executeUpdate();

        String id = "select max(walletId) from Wallet";
        Query<Long> q = session.createQuery(id);
        long result = q.getSingleResult();
        return result;
    }

    @Override
    public void update(Wallet wallet) {
        Session session = entityManager.unwrap(Session.class);
        String sql = "UPDATE Wallet SET wallert_name = \'" + wallet.getWallertName()
                        + "\', balance = " + wallet.getBalance()
                        + ", currency = " + wallet.getCurrency()
                        + ", create_date = TO_DATE(\'"+ wallet.getCreateDate() +"\', \'dd/mm/yyyy\') "
                        + "WHERE wallet_id = " + wallet.getWalletId();

        Query<Wallet> query = session.createSQLQuery(sql);
        log.info("update.query: " + query.toString());

        query.executeUpdate();
    }

    @Override
    public void delete(int wallet_id) {
        Session session = entityManager.unwrap(Session.class);
        String hql = "DELETE FROM Wallet w Where w.walletId = " + wallet_id;

        Query<Wallet> query = session.createQuery(hql);
        log.info("delete.query: " + query.toString());

        query.executeUpdate();
    }
}
