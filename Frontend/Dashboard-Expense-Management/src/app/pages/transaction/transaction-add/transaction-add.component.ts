import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Transaction } from 'src/app/models/transaction';
import { Category } from 'src/app/models/category';
import { TransService } from 'src/app/services/trans.service';
import { CategoryService } from 'src/app/services/category.service';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Wallet } from 'src/app/models/wallet';
import { WalletService } from 'src/app/services/wallet.service';

@Component({
  selector: 'app-transaction-add',
  templateUrl: './transaction-add.component.html',
  styleUrls: ['./transaction-add.component.scss']
})
export class TransactionAddComponent implements OnInit {

  transaction: Transaction;
  cateList: Category[];
  today = new Date();
  cwallet: Wallet;

  constructor(
    public dialog: MatDialogRef<TransactionAddComponent>,
    private snackBar: MatSnackBar,
    public walletService: WalletService,
    public transService: TransService,
    public cateService: CategoryService,
    @Inject(MAT_DIALOG_DATA) public wallet
  ) { }

  ngOnInit() {
    this.transaction = new Transaction;
    this.getCateList();
    this.getCurrentWallet();
  }

  onClose() {
    this.dialog.close();
  }

  getCateList() {
    this.cateService.getAll().subscribe(data => {
      console.log(data)
      return this.cateList = data;
    })
  }

  getCurrentWallet() {
    this.walletService.findById(this.wallet.id).subscribe(data => {
      return this.cwallet = data;
    })
  }

  addTransaction() {
    this.transaction.walletId = this.wallet.id;
    this.transaction.userId = +sessionStorage.getItem('id');
    this.transaction.date = new Date(this.transaction.date).toLocaleDateString('en-GB');
    console.log(this.transaction);
    this.transService.addTrans(this.transaction).subscribe(data => {
      this.cwallet.createDate = new Date(this.cwallet.createDate).toLocaleDateString('en-GB');
      if (this.transaction.transType == 1) {
        this.cwallet.currency = this.cwallet.currency - this.transaction.amount
      } else {
        this.cwallet.currency += this.transaction.amount
      }
      this.walletService.updateWallet(this.cwallet).subscribe(doto => {
        console.log('add transaction successfully');
        this.onClose();
        this.snackBar.open("Add Transaction Successfully!", "Close", {
          duration: 3000,
          verticalPosition: "bottom",
          horizontalPosition: "center"
        });
      })
    })
  }
}
