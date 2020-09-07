package vn.itsol.MSWallet.dao.passwordresettoken;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.itsol.MSWallet.entities.PasswordResetToken;
import vn.itsol.MSWallet.entities.Users;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PasswordResetTokenDaoImpl implements PasswordResetTokenDao
{
    private static final Logger log = LoggerFactory.getLogger(PasswordResetTokenDaoImpl.class);

    @PersistenceContext
    @Autowired
    private EntityManager entityManager;

    @Override
    public PasswordResetToken getPasswordResetToken(int user_id) {
        Session session = entityManager.unwrap(Session.class);
        String hql = "select p From PasswordResetToken p Where p.users.userId = " + user_id;

        Query<PasswordResetToken> query = session.createQuery(hql);
        log.info("getPasswordResetToken.query: " + query.toString());

        PasswordResetToken passwordResetToken = query.getSingleResult();

        return passwordResetToken;
    }

    @Override
    public void save(PasswordResetToken passwordResetToken) {
        Session session = entityManager.unwrap(Session.class);
        String sql = "Insert Into password_reset_token(tokenID, token, expiry_date, user_id) " +
                     "values(((SELECT MAX(tokenID) FROM password_reset_token)+1), \'" + passwordResetToken.getToken() + "\', TO_DATE(\'" + passwordResetToken.getExpiryDate() + "\', \'dd/mm/yyyy\'), " + passwordResetToken.getUsers().getUserId() + ")";

        Query<PasswordResetToken> query = session.createSQLQuery(sql);
        log.info("save.query: " + query.toString());

        query.executeUpdate();
    }

    @Override
    public void update(PasswordResetToken passwordResetToken) {
        Session session = entityManager.unwrap(Session.class);
        String sql = "UPDATE password_reset_token SET token = \'" + passwordResetToken.getToken() + "\', expiry_date = TO_DATE(\'" + passwordResetToken.getExpiryDate() + "\', \'dd/mm/yyyy\'), user_id = " + passwordResetToken.getUsers().getUserId() + " WHERE tokenID = " + passwordResetToken.getTokenId() + "";

        Query<PasswordResetToken> query = session.createSQLQuery(sql);
        log.info("update.query: " + query.toString());

        query.executeUpdate();
    }
}
