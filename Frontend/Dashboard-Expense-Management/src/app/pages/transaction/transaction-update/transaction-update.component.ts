import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Transaction } from 'src/app/models/transaction';
import { Category } from 'src/app/models/category';
import { TransService } from 'src/app/services/trans.service';
import { CategoryService } from 'src/app/services/category.service';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-transaction-update',
  templateUrl: './transaction-update.component.html',
  styleUrls: ['./transaction-update.component.scss']
})
export class TransactionUpdateComponent implements OnInit {

  transaction: Transaction;
  cateList: Category[];
  today = new Date();

  constructor(
    public dialog: MatDialogRef<TransactionUpdateComponent>,
    private snackBar: MatSnackBar,
    public transService: TransService,
    public cateService: CategoryService,
    @Inject(MAT_DIALOG_DATA) public trans
  ) { }

  ngOnInit() {
    this.transaction = {
      amount: this.trans.amount,
      categoryId: this.trans.category_id,
      date: this.trans.date,
      transId: this.trans.trans_id,
      note: this.trans.note,
      transName: this.trans.trans_name,
      transType: this.trans.trans_type,
      userId: this.trans.user_id,
      walletId: this.trans.wallet_id
    };
    this.getCateList();
  }

  onClose(){
    this.dialog.close();
  }

  getCateList(){
    this.cateService.getAll().subscribe(data => {
      return this.cateList = data;
    })
  }

  updateTransaction(){
    this.transaction.date = new Date(this.transaction.date).toLocaleDateString('en-GB');
    console.log(this.transaction)
    this.transService.updateTrans(this.transaction).subscribe(data => {
      console.log('update transaction successfully');
      this.onClose();
      this.snackBar.open("Update Transaction Successfully!","Close",{
        duration: 3000,
        verticalPosition: "bottom",
        horizontalPosition: "center"
      });
    })
  }

}
