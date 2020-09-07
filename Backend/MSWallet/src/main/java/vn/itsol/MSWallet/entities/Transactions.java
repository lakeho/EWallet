package vn.itsol.MSWallet.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transactions
{
    @Id
    @Column(name = "trans_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long transId;

    @Column(name = "trans_name")
    private String transName;

    @Column(name = "amount")
    private long amount;

    @Column(name = "trans_type")
    private long transType;

    @Column(name = "datetime")
    private String date;

    @Column(name = "note")
    private String note;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
