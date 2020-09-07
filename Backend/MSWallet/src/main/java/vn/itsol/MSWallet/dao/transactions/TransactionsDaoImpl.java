package vn.itsol.MSWallet.dao.transactions;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.itsol.MSWallet.entities.Transactions;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class TransactionsDaoImpl implements TransactionsDao
{
    private static final Logger log = LoggerFactory.getLogger(TransactionsDaoImpl.class);

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Transactions> getTransactionsUser(int user_id) {
        Session session = entityManager.unwrap(Session.class);
        String hql = "select t From Transactions t Where t.users.userId = " + user_id;

        Query<Transactions> query = session.createQuery(hql);
        log.info("getTransactionsUser.query: " + query.toString());

        List<Transactions> results = query.getResultList();
        log.info("getTransactionsUser.results: " + results.toString());
        return results;
    }

    @Override
    public List<Transactions> getTransactionsCategory(int category_id) {
        Session session = entityManager.unwrap(Session.class);
        String hql = "select t From Transactions t Where t.category.categoryid = " + category_id;

        Query<Transactions> query = session.createQuery(hql);
        log.info("getTransactionsCategory.query: " + query.toString());

        List<Transactions> results = query.getResultList();
        log.info("getTransactionsCategory.results: " + results.toString());
        return results;
    }

    @Override
    public List<Transactions> getTransactionsWallet(int wallet_id) {
        Session session = entityManager.unwrap(Session.class);
        String hql = "select t From Transactions t Where t.wallet.walletId = " + wallet_id;

        Query<Transactions> query = session.createQuery(hql);
        log.info("getTransactionsWallet.query: " + query.toString());

        List<Transactions> results = query.getResultList();
        log.info("getTransactionsWallet.results: " + results.toString());
        return results;
    }

    @Override
    public Transactions getTransaction(int tran_id) {
        Session session = entityManager.unwrap(Session.class);
        String sql = "Select t From Transactions t Where t.transId = " + tran_id;

        Query<Transactions> query = session.createQuery(sql, Transactions.class);
        log.info("getTransaction.query: " + query.toString());

        Transactions result = query.getSingleResult();
        log.info("getTransaction.result: " + result.toString());
        return result;
    }

    @Override
    public void save(Transactions transactions) {
        Session session = entityManager.unwrap(Session.class);
        String sql = "Insert Into transactions(trans_id, trans_name, amount, trans_type, datetime, note, user_id, wallet_id, category_id)\n" +
                     "values(((SELECT MAX(trans_id) FROM transactions)+1), '" + transactions.getTransName() + "', " + transactions.getAmount() + ", " + transactions.getTransType()
                     + ", TO_DATE(\'" + transactions.getDate() + "\', \'dd/mm/yyyy\'), '" + transactions.getNote() + "', " + transactions.getUsers().getUserId()
                     + ", " + transactions.getWallet().getWalletId() + ", " + transactions.getCategory().getCategoryid() + ")";

        Query<Transactions> query = session.createSQLQuery(sql);
        log.info("save.query: " + query.toString());

        query.executeUpdate();
    }

    @Override
    public void update(Transactions transactions) {
        Session session = entityManager.unwrap(Session.class);
        String sql = "update transactions SET amount = " + transactions.getAmount() + ", trans_type = " + transactions.getTransType() +
                ", datetime = TO_DATE('" + transactions.getDate() + "','dd/mm/yyyy'), note = '" + transactions.getNote() + "', user_id = " + transactions.getUsers().getUserId() +
                ", wallet_id = " + transactions.getWallet().getWalletId() + ", category_id = "+ transactions.getCategory().getCategoryid() + ", trans_name = '" + transactions.getTransName() +
                "' WHERE trans_id = " + transactions.getTransId();

        Query<Transactions> query = session.createSQLQuery(sql);
        log.info("update.query: " + query.toString());

        query.executeUpdate();
    }

    @Override
    public void delete(long tran_id) {
        Session session = entityManager.unwrap(Session.class);
        String hql = "delete From Transactions t Where t.transId = :tran_id";

        Query query = session.createQuery(hql).setParameter("tran_id",tran_id);
        log.info("delete.query: " + query.toString());

        query.executeUpdate();
    }

    @Override
    public void deleteByWalletID(long wallet_id) {
        Session session = entityManager.unwrap(Session.class);
        String hql = "delete From Transactions t Where t.wallet.walletId = :wallet_id";

        Query<Transactions> query = session.createQuery(hql).setParameter("wallet_id",wallet_id);
        log.info("deleteByWalletID.query: " + query.toString());

        query.executeUpdate();
    }
}
