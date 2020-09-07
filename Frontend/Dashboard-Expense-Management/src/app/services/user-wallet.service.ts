import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User_Wallet_display } from 'src/app/models/user_wallet_display';
import { User_Wallet } from 'src/app/models/user_wallet';

@Injectable({
  providedIn: 'root'
})
export class UserWalletService {

  private UWdisplay_Url: string;

  private UW_url: string;

  constructor(private http:HttpClient) { 
    this.UWdisplay_Url = 'http://localhost:8030/wallet';

    this.UW_url = 'http://localhost:8030/userwallet';
  }

  // find all wallet by current user id
  findAll(id:number): Observable<User_Wallet_display[]>{
    return this.http.get<User_Wallet_display[]>(`${this.UWdisplay_Url}/findbyuserid/${id}`);
  }

  //find all user(member) within a wallet; 'id' is wallet_id
  findAllUser(id:number): Observable<User_Wallet_display[]>{
    return this.http.get<User_Wallet_display[]>(`${this.UWdisplay_Url}/findbywalletid/${id}`);
  }

  // add wallet pt2 (insert into user_wallet)
  addUserWallet(uw: User_Wallet): Observable<User_Wallet>{
    return this.http.post<User_Wallet>(`${this.UW_url}/save`,uw);
  }

  //delete user_wallet by wallet_id (del all user in wallet)
  deleteUserByWalletId(id:number): Observable<User_Wallet>{
    return this.http.delete<User_Wallet>(`${this.UW_url}/delete/${id}`);
  }

  //delete a single user in a wallet
  deleteSingle(userid,walletid):Observable<User_Wallet>{
    return this.http.delete<User_Wallet>(`${this.UW_url}/deleteSingle?userid=${userid}&walletid=${walletid}`);
  }

  //get user_wallet by wallet_id
  getUserWallet(id): Observable<User_Wallet[]>{
    return this.http.get<User_Wallet[]>(`${this.UW_url}/findbyid/${id}`);
  }

  //get user_wallet by user_id and wallet_id
  getUserWalletBy2Id(user_id, wallet_id): Observable<User_Wallet>{
    return this.http.get<User_Wallet>(`${this.UW_url}/findbyuseridwalletid?walletid=${wallet_id}&userid=${user_id}`);
  }
}
