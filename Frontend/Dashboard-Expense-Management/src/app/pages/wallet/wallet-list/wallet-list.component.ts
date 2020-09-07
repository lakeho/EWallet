import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from "@angular/material/dialog";
import { MatSnackBar } from "@angular/material/snack-bar";

import { User_Wallet_display } from 'src/app/models/user_wallet_display';
import { WalletAddComponent } from "../wallet-add/wallet-add.component";
import { WalletUpdateComponent } from "../wallet-update/wallet-update.component";
import { WalletService } from "src/app/services/wallet.service";
import { UserWalletService } from 'src/app/services/user-wallet.service';
import { TransService } from 'src/app/services/trans.service';
import { Wallet } from 'src/app/models/wallet';
// import { UserService } from "src/app/services/user.service";

@Component({
  selector: 'app-wallet-list',
  templateUrl: './wallet-list.component.html',
  styleUrls: ['./wallet-list.component.scss']
})
export class WalletListComponent implements OnInit {

  public wallets: User_Wallet_display[];
  walletName: string;

  constructor(
    private dialog: MatDialog,
    private snackBar: MatSnackBar,
    public service: WalletService,
    public transService: TransService,
    public uwService: UserWalletService
  ) { }

  ngOnInit() {
    this.getGrid();
  }

  searchWallet() {
    let a = this.walletName.trim();
    console.log(a)
    if (a == "" || a == undefined) {
      this.getGrid();
    } else {
      this.wallets = this.wallets.filter(w => {
        w.wallertName == a;
      })
      console.log(this.wallets)
    }
  }

  getGrid() {
    this.uwService.findAll(+sessionStorage.getItem('id')).subscribe(data => {
      console.log(data)
      return this.wallets = data;
    })
  }

  addWalletDialog() {
    console.log('initiating add-wallet dialogue');
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.width = "30%";
    const dia = this.dialog.open(WalletAddComponent, dialogConfig);
    dia.afterClosed().subscribe(result => {
      this.getGrid();
    })
  }

  updateWalletDialog(w) {
    console.log('initiating update-wallet dialogue');
    this.service.formWallet = w;
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.width = "30%";
    dialogConfig.data = w;
    const dia = this.dialog.open(WalletUpdateComponent, dialogConfig);
    dia.afterClosed().subscribe(result => {
      this.getGrid();
    })
  }

  deleteWallet(wallet_id) { 
    if (confirm("Delete Wallet No." + wallet_id + "?")) {
      console.log('initiating delete process...')
      console.log('deleting all transactions...')
      //delete all transaction in wallet
      this.transService.deleteByWalletId(wallet_id).subscribe(data => {
        //delete all user in wallet
        this.uwService.deleteUserByWalletId(wallet_id).subscribe(dta => {
          //delete the wallet itself
          this.service.deleteWallet(wallet_id).subscribe(data => {
            this.getGrid();
            console.log('delete wallet successfully!')
            this.snackBar.open('Delete Wallet Successfully!','Close',{
              duration: 3000,
              horizontalPosition: 'center',
              verticalPosition: 'bottom'
            })
          })
        })
      })
    }
  }

}
