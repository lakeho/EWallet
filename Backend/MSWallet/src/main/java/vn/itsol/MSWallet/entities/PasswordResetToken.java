package vn.itsol.MSWallet.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "password_reset_token")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordResetToken
{
    @Id
    @Column(name = "tokenid")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long tokenId;

    @Column(name = "token")
    private String token;

    @Column(name = "expiry_date")
    private String expiryDate;

//    @Column(name = "user_id")
//    private long userId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Users users;
}
