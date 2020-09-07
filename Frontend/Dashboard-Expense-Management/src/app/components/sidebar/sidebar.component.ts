import { Component, OnInit } from '@angular/core';
import { User_Wallet_display } from 'src/app/models/user_wallet_display';
import { WalletService } from 'src/app/services/wallet.service';
import { UserService } from 'src/app/services/user.service';
import { UserWalletService } from 'src/app/services/user-wallet.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {

  walletList: User_Wallet_display[];
  username: string;
  fullname: string;

  constructor(
    public WalletService: WalletService,
    public userService: UserService,
    public uwService: UserWalletService
  ) { }

  getWalletList(){
    this.uwService.findAll(+sessionStorage.getItem('id')).subscribe(data => {
      return this.walletList = data;
    })
  }

  getInfo(){
      this.fullname = sessionStorage.getItem('name')
      this.username = sessionStorage.getItem('username')
  }

  ngOnInit() {
    this.getWalletList();
    this.getInfo();
  }

  

}
