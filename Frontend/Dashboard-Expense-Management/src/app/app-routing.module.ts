import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { MapsComponent } from './pages/maps/maps.component';
import { NotificationsComponent } from './pages/notifications/notifications.component';
import { WalletComponent } from './pages/wallet/wallet.component';
import { WalletListComponent } from './pages/wallet/wallet-list/wallet-list.component';
import { WalletDetailsComponent } from './pages/wallet/wallet-details/wallet-details.component';
import { TransactionComponent } from './pages/transaction/transaction.component';
import { TransactionListComponent } from './pages/transaction/transaction-list/transaction-list.component';
import { ChartComponent } from './pages/chart/chart.component';
import { LoginComponent } from './pages/login/login.component';
import { SignupComponent } from './pages/signup/signup.component';
import { UserComponent } from './pages/user/user.component';
import { PageNotFoundComponent } from './pages/page-not-found/page-not-found.component';
import { TransListGuard } from './guard/trans-list.guard';
import { MainComponent } from './main/main.component';
import { LoginGuard } from './guard/login.guard';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  {
    path: '', component: MainComponent, canActivate: [LoginGuard], children:
      [
        { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
        { path: 'dashboard', component: DashboardComponent },
        { path: 'maps', component: MapsComponent },
        { path: 'notifications', component: NotificationsComponent },
        {
          path: 'wallet', component: WalletComponent, children:
            [
              { path: '', component: WalletListComponent },
              { path: ':wallet_id', component: WalletDetailsComponent, canActivate: [TransListGuard] }
            ]
        },
        {
          path: 'transaction', component: TransactionComponent, children:
            [
              { path: ':wallet_id', component: TransactionListComponent }
            ]
        },
        { path: 'chart', component: ChartComponent },
        { path: 'signup', component: SignupComponent },
        { path: 'user', component: UserComponent },
        { path: 'notfound', component: PageNotFoundComponent }
      ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
