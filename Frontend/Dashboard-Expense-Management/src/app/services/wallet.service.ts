import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Wallet } from 'src/app/models/wallet';

@Injectable({
  providedIn: 'root'
})
export class WalletService {

  private justWalletUrl: string;

  formWallet: Wallet;

  //user_wallet_display json
  constructor(private http:HttpClient) {
    this.justWalletUrl = 'http://localhost:8030/wallet';
  }

  //get wallet by id
  findById(id:number): Observable<Wallet>{
    return this.http.get<Wallet>(`${this.justWalletUrl}/findbyid/${id}`);
  }

  // add wallet pt1 (insert into wallet)
  addWallet(w: Wallet): Observable<Wallet>{
    return this.http.post<Wallet>(`${this.justWalletUrl}/save2`,w);
  }
  
  //update wallet
  updateWallet(w: Wallet): Observable<Wallet>{
    console.log('updating wallet...')
    return this.http.put<Wallet>(`${this.justWalletUrl}/update`,w);
  }

  //delete wallet
  deleteWallet(id:number): Observable<Wallet>{
    return this.http.delete<Wallet>(`${this.justWalletUrl}/delete/${id}`);
  }

}
