import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, CanActivate, Router } from '@angular/router';
import { UserWalletService } from 'src/app/services/user-wallet.service';

@Injectable({
  providedIn: 'root'
})
export class TransListGuard implements CanActivate {

  can: boolean;

  constructor(private service: UserWalletService,
    private router: Router
  ) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    this.service.getUserWalletBy2Id(+sessionStorage.getItem('id'), +route.paramMap.get('wallet_id')).subscribe(data => {
      console.log(data)
      if (data[0] != null) {
        return true;
      } else {
        this.router.navigate(['/notfound'])
        return false;
      }
    })
    
    return true;
  }
}
