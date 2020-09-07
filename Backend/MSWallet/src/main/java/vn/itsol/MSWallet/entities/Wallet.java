package vn.itsol.MSWallet.entities;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "wallet")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Wallet
{
    @Id
    @Column(name = "wallet_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long walletId;

    @Column(name = "wallert_name")
    private String wallertName;

    @Column(name = "balance")
    private long balance;

    @Column(name = "currency")
    private long currency;

    @Column(name = "create_date")
    private String createDate;

    @OneToMany(mappedBy = "wallet")
    private Collection<Transactions> transactions;

    @OneToMany(mappedBy = "wallet")
    private Collection<UserWallet> userWallet;
}
