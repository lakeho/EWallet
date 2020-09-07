package vn.itsol.MSWallet.service.passwordresettoken;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.itsol.MSWallet.dao.passwordresettoken.PasswordResetTokenDao;
import vn.itsol.MSWallet.dto.CategoryDto;
import vn.itsol.MSWallet.dto.PasswordResetTokenDto;
import vn.itsol.MSWallet.entities.PasswordResetToken;
import vn.itsol.MSWallet.entities.Users;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService
{
    private static final Logger log = LoggerFactory.getLogger(PasswordResetTokenServiceImpl.class);

    @Autowired
    private PasswordResetTokenDao passwordResetTokenDao;

    @Transactional
    @Override
    public PasswordResetTokenDto getPasswordResetToken(int user_id) {
        PasswordResetToken passwordResetToken = passwordResetTokenDao.getPasswordResetToken(user_id);
//        PasswordResetTokenDto passwordResetTokenDto = new PasswordResetTokenDto();
//        passwordResetTokenDto.setTokenId(passwordResetToken.getTokenId());
//        passwordResetTokenDto.setToken(passwordResetToken.getToken());
//        passwordResetTokenDto.setExpiryDate(passwordResetToken.getExpiryDate());
//        return passwordResetTokenDto;
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper.map(passwordResetToken, PasswordResetTokenDto.class);
    }

    @Transactional
    @Override
    public void save(PasswordResetTokenDto passwordResetTokenDto) {
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        Users users = new Users();
        users.setUserId(passwordResetToken.getUsers().getUserId());

        passwordResetToken.setExpiryDate(passwordResetTokenDto.getExpiryDate());
        passwordResetToken.setToken(passwordResetTokenDto.getToken());
        //passwordResetToken.setTokenId(passwordResetTokenDto.getTokenId());
        passwordResetToken.setUsers(users);
        passwordResetTokenDao.save(passwordResetToken);
    }

    @Transactional
    @Override
    public void update(PasswordResetTokenDto passwordResetTokenDto) {
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        Users users = new Users();
//        users.setUserId(passwordResetTokenDto.getUser().getUserId());
        passwordResetToken.setExpiryDate(passwordResetTokenDto.getExpiryDate());
        passwordResetToken.setToken(passwordResetTokenDto.getToken());
        passwordResetToken.setTokenId(passwordResetTokenDto.getTokenId());
        passwordResetToken.setUsers(users);
        passwordResetTokenDao.update(passwordResetToken);
    }
}
