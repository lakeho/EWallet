package vn.itsol.MSWallet.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users
{
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String passWord;

    @Column(name = "phone_number")
    private long phoneNumber;

    @Column(name = "gender")
    private String gender;

    @Column(name = "Path_ava")
    private String pathAva;

    @OneToOne(mappedBy = "users")
    private PasswordResetToken passwordResetToken;

    @OneToMany(mappedBy = "users")
    private Collection<UserWallet> userWallet;

    @OneToMany(mappedBy = "users")
    private Collection<Transactions> transactions;
}
