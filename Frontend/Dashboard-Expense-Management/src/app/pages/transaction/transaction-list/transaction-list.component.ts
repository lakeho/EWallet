import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Transaction_display } from 'src/app/models/transaction_display';
import { TransService } from 'src/app/services/trans.service'
import { MatDialog, MatDialogConfig } from "@angular/material/dialog";
import { MatSnackBar } from "@angular/material/snack-bar";
import { TransactionAddComponent } from '../transaction-add/transaction-add.component';
import { TransactionUpdateComponent } from '../transaction-update/transaction-update.component'
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-transaction-list',
  templateUrl: './transaction-list.component.html',
  styleUrls: ['./transaction-list.component.scss']
})
export class TransactionListComponent implements OnInit {

  @ViewChild(MatPaginator) paginator: MatPaginator;

  displayedColumns: string[] = ['date', 'trans_name', 'category_name', 'amount', 'user_name', 'function'];

  datasource;

  transList: Transaction_display[];

  walletName: string;

  constructor(
    private ActivatedRoute: ActivatedRoute,
    public transService: TransService,
    private dialog: MatDialog,
    private snackBar: MatSnackBar
  ) { }

  getList() {
    this.ActivatedRoute.params.subscribe(param => {
      this.transService.getListByWalletId(param.wallet_id).subscribe(data => {
        console.log(data)
        if (data[0]) {
          this.walletName = data[0].wallet_name;
          this.transList = data;
          this.datasource = new MatTableDataSource<Transaction_display>(this.transList);
          this.datasource.paginator = this.paginator;
        }else{
          this.datasource = new MatTableDataSource<Transaction_display>(this.transList);
          this.datasource.paginator = this.paginator;
        }
      })
    })
  }

  newTransDialog() {
    this.ActivatedRoute.params.subscribe(param => {
      console.log('initiating new transaction dialogue');
      const dialogConfig = new MatDialogConfig();
      dialogConfig.autoFocus = true;
      dialogConfig.width = "35%";
      dialogConfig.data = {
        id: param.wallet_id
      }
      const dia = this.dialog.open(TransactionAddComponent, dialogConfig);
      dia.afterClosed().subscribe(result => {
        this.getList();
      })
    })
  }

  updateTransDialog(t: Transaction_display) {
    console.log('initiating update transaction dialogue');
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.width = "35%";
    dialogConfig.data = t;
    const dia = this.dialog.open(TransactionUpdateComponent, dialogConfig);
    dia.afterClosed().subscribe(result => {
      this.getList();
    })
  }

  deleteTransaction(id) {
    if (confirm("Delete transaction No." + id + "?")) {
      this.transService.deleteById(id).subscribe(delta => {
        this.snackBar.open("Delate Successfully!", "Close", {
          duration: 3000,
          verticalPosition: "bottom",
          horizontalPosition: "center"
        });
        this.getList();
      })
    }
  }

  ngOnInit() {
    this.getList();
  }

}
