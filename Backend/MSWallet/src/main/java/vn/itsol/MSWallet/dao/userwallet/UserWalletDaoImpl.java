package vn.itsol.MSWallet.dao.userwallet;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.itsol.MSWallet.entities.UserWallet;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserWalletDaoImpl implements UserWalletDao
{
    private static final Logger log = LoggerFactory.getLogger(UserWalletDaoImpl.class);

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<UserWallet> getUserWallet(int wallet_id) {
        Session session = entityManager.unwrap(Session.class);
        String hql = "select u From UserWallet u Where u.wallet.walletId = " + wallet_id;

        Query<UserWallet> query = session.createQuery(hql);
        log.info("getUserWallet.query: " + query.toString());

        List<UserWallet> results = query.getResultList();
        log.info("getUserWallet.result: " + results.toString());
        return results;
    }

    @Override
    public List<UserWallet> getUserWalletByUseridWalletid(int wallet_id, int user_id) {
        Session session = entityManager.unwrap(Session.class);
        String hql = "select u From UserWallet u Where u.wallet.walletId = " + wallet_id + "And u.users.userId =" + user_id ;

        Query<UserWallet> query = session.createQuery(hql, UserWallet.class);
        log.info("getUserWallet.query: " + query.toString());

        List<UserWallet> results = query.getResultList();
        log.info("getUserWallet.result: " + results.toString());
        return results;
    }

    @Override
    public void save(UserWallet userWallet) {
        Session session = entityManager.unwrap(Session.class);
        String sql = "Insert Into user_wallet(user_wallet_id, user_id, wallet_id, role) " +
                     "values( ((SELECT max(user_wallet_id) from user_wallet)+1), " + userWallet.getUsers().getUserId() + ", " + userWallet.getWallet().getWalletId() + ", " + userWallet.getRole() + ")";

        Query<UserWallet> query = session.createSQLQuery(sql);
        log.info("save.query: " + query.toString());

        query.executeUpdate();
    }

    @Override
    public void delete(int wallet_id) {
        Session session = entityManager.unwrap(Session.class);
        String sql = "Delete From UserWallet u Where u.wallet.walletId = " + wallet_id;

        Query<UserWallet> query = session.createQuery(sql);
        log.info("delete.query: " + query.toString());

        query.executeUpdate();
    }

    @Override
    public void deleteSingle(int user_id, int wallet_id) {
        Session session = entityManager.unwrap(Session.class);
        String hql = "Delete From UserWallet u Where u.users.userId = " + user_id + " And u.wallet.walletId = " +wallet_id;
        Query<UserWallet> query = session.createQuery(hql);
        log.info("delete.query: " + query.toString());
        query.executeUpdate();
    }
}
