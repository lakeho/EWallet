package vn.itsol.MSWallet.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "category")
@Data
public class Category
{
    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long categoryid;

    @Column(name = "categoryname")
    private String categoryname;

    @OneToMany(mappedBy = "category")
    private Collection<Transactions> transactions;
}
