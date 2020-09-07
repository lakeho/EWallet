import { Component, OnInit } from '@angular/core';
import { User_Wallet_display } from 'src/app/models/user_wallet_display';
import { User_Wallet } from 'src/app/models/user_wallet'
import { UserWalletService } from 'src/app/services/user-wallet.service';
import { ActivatedRoute } from '@angular/router';
import { MatDialog, MatDialogConfig } from "@angular/material/dialog";
import { MatSnackBar } from "@angular/material/snack-bar";
import { WalletUserAddComponent } from './wallet-user-add/wallet-user-add.component'

@Component({
  selector: 'app-wallet-details',
  templateUrl: './wallet-details.component.html',
  styleUrls: ['./wallet-details.component.scss']
})
export class WalletDetailsComponent implements OnInit {

  walletName: string;

  users: User_Wallet_display[];

  user_wallet: User_Wallet;

  owned: boolean;

  constructor(
    public uwService: UserWalletService,
    private ActivatedRoute: ActivatedRoute,
    private dialog: MatDialog,
    private snackBar: MatSnackBar,
  ) { }

  getRole() {
    this.ActivatedRoute.params.subscribe(param => {
      this.uwService.getUserWalletBy2Id(+sessionStorage.getItem('id'), param.wallet_id).subscribe(data => {
        console.log(data)
        if (data[0].role == true) {
          this.owned = true;
        } else {
          this.owned = false;
        }
      })
    })
  }

  getUsers() {
    this.ActivatedRoute.params.subscribe(param => {
      this.uwService.findAllUser(param.wallet_id).subscribe(data => {
        this.walletName = data[0].wallertName;
        return this.users = data;
      })
    })
  }

  addUserDialog() {
    this.ActivatedRoute.params.subscribe(param => {
      console.log('initiating add-user dialogue');
      const dialogConfig = new MatDialogConfig();
      dialogConfig.disableClose = true;
      dialogConfig.autoFocus = true;
      dialogConfig.width = "25%";
      dialogConfig.data = {
        id: param.wallet_id
      }
      const dia = this.dialog.open(WalletUserAddComponent, dialogConfig);
      dia.afterClosed().subscribe(result => {
        this.getUsers();
      })
    })
  }

  deleteUser(user_id) {
    if (confirm("Delete User No." + user_id + " from this wallet?")) {
      // get user_wallet with current wallet_id and chosen user_id
      console.log('Initiating delete process...');
      this.ActivatedRoute.params.subscribe(param => {
        //Delete user_wallet 
        this.uwService.deleteSingle(user_id, param.wallet_id).subscribe(delta => {
          this.getUsers();
          console.log('Deleted user from wallet!')
          this.snackBar.open('Deleted User From Wallet', 'Close', {
            duration: 3000,
            verticalPosition: 'bottom',
            horizontalPosition: 'center'
          })
        })
      })
    }
  }


  ngOnInit() {
    this.getUsers();
    this.getRole();
  }

}
