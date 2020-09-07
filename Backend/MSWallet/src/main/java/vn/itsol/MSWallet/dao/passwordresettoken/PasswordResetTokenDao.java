package vn.itsol.MSWallet.dao.passwordresettoken;

import vn.itsol.MSWallet.entities.PasswordResetToken;

import java.util.List;

public interface PasswordResetTokenDao {
    PasswordResetToken getPasswordResetToken(int user_id);
    void save(PasswordResetToken passwordResetToken);
    void update(PasswordResetToken passwordResetToken);
}
