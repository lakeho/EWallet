import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from 'src/app/services/user.service';
import { UserWalletService } from 'src/app/services/user-wallet.service';
import { User_Wallet } from 'src/app/models/user_wallet';
import { ActivatedRoute } from '@angular/router';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-wallet-user-add',
  templateUrl: './wallet-user-add.component.html',
  styleUrls: ['./wallet-user-add.component.scss']
})
export class WalletUserAddComponent implements OnInit {

  newMembers: string;

  names: string[];

  newWalletsMember: User_Wallet;

  constructor(
    public Dialog: MatDialogRef<WalletUserAddComponent>,
    private snackBar: MatSnackBar,
    private UserService: UserService,
    private uwService: UserWalletService,
    private ActivatedRoute: ActivatedRoute,
    @Inject(MAT_DIALOG_DATA) public currentWallet
  ) { }

  ngOnInit() {
    this.newMembers = null;
  }

  onClose() {
    this.Dialog.close();
    // this.service.filter('click');
  }

  addUser() {
    this.names = this.newMembers.split(",");
    this.names.forEach(user => {
      console.log(user.trim())
      this.UserService.getUserByUsername(user.trim()).subscribe(data => {
        console.log(data)
        this.newWalletsMember = {
          userWalletId: null,
          userId: data.userId,
          walletId: this.currentWallet.id,
          role: 0
        }
        this.uwService.addUserWallet(this.newWalletsMember).subscribe(adata => {
          console.log('Added 1 new member to wallet!');
          this.onClose();
          this.snackBar.open('Add New Members Successfully!','Close',{
            duration: 3000,
            verticalPosition: 'bottom',
            horizontalPosition: 'center'
          })
        })
        console.log(this.newWalletsMember)
      })
    })
  }

}
