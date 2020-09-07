import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Wallet } from 'src/app/models/wallet';
import { WalletService } from 'src/app/services/wallet.service';

@Component({
  selector: 'app-wallet-update',
  templateUrl: './wallet-update.component.html',
  styleUrls: ['./wallet-update.component.scss']
})
export class WalletUpdateComponent implements OnInit {

  wallet: Wallet;

  constructor(
    public dialog: MatDialogRef<WalletUpdateComponent>,
    private snackBar: MatSnackBar,
    public service: WalletService,
    @Inject(MAT_DIALOG_DATA) public updatingWallet
  ) { }

  ngOnInit() {
    this.wallet = {
      walletId: this.updatingWallet.walletId,
      wallertName: this.updatingWallet.wallertName,
      balance: this.updatingWallet.balance,
      currency: this.updatingWallet.currency,
      createDate: new Date(this.updatingWallet.date).toLocaleDateString('en-GB')
    }
  }

  onClose(){
    this.dialog.close();
  }

  updateWallet(){
    this.service.updateWallet(this.wallet).subscribe(data => {
      console.log('Update Wallet Successfully!');
      this.onClose();
      this.snackBar.open("Update Wallet Successfully!","Close",{
        duration: 3000,
        verticalPosition: "bottom",
        horizontalPosition: "center"
      });
    })
  }

}
