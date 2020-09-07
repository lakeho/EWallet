import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Transaction } from 'src/app/models/transaction';
import { Transaction_display } from 'src/app/models/transaction_display';
// import { forEach } from '@angular/router/src/utils/collection';
 
@Injectable({
  providedIn: 'root'
})
export class TransService {

  private transUrl: string;

  constructor(private http:HttpClient) {
    this.transUrl = 'http://localhost:8030/trans';
  }

  //get all transactions by id(wallet_id) - BASE TRANSACTION IN DATABASE
  findAllByWallet(id:number): Observable<Transaction[]>{
    console.log('finding qualified records...')
    return this.http.get<Transaction[]>(`${this.transUrl}/findbywalletid/${id}`);
  }

  //get all transaction_display by wallet_id - TRANSACTION DTO
  getListByWalletId(id:number): Observable<Transaction_display[]>{
    return this.http.get<Transaction_display[]>(`${this.transUrl}/display/${id}`);
  }

  //add transaction
  addTrans(t:Transaction): Observable<Transaction>{
    return this.http.post<Transaction>(`${this.transUrl}/save`,t);
  }

  //update transaction
  updateTrans(t:Transaction): Observable<Transaction>{
    return this.http.put<Transaction>(`${this.transUrl}/update`,t)
  }

  //delete a transaction by id
  deleteById(id:number): Observable<Transaction>{
    return this.http.delete<Transaction>(`${this.transUrl}/delete/${id}`);
  }

  //delete all transactions by wallet_id
  deleteByWalletId(id:number): Observable<Transaction>{
    return this.http.delete<Transaction>(`${this.transUrl}/delByWId/${id}`);
  }
}
