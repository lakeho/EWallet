import { BrowserModule } from '@angular/platform-browser';
import { NgModule, LOCALE_ID } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule, HTTP_INTERCEPTORS } from "@angular/common/http";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { CollapseModule } from 'ngx-bootstrap/collapse';
import { ToastrModule } from 'ngx-toastr';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { FooterComponent } from './components/footer/footer.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { MapsComponent } from './pages/maps/maps.component';
import { NotificationsComponent } from './pages/notifications/notifications.component';
import { MatDialogModule } from '@angular/material/dialog';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { WalletComponent } from './pages/wallet/wallet.component';
import { TransactionComponent } from './pages/transaction/transaction.component';
import { CategoryComponent } from './pages/category/category.component';
import { ChartComponent } from './pages/chart/chart.component';
import { WalletListComponent } from './pages/wallet/wallet-list/wallet-list.component';
import { WalletDetailsComponent } from './pages/wallet/wallet-details/wallet-details.component';
import { WalletAddComponent } from './pages/wallet/wallet-add/wallet-add.component';
import { WalletUpdateComponent } from './pages/wallet/wallet-update/wallet-update.component';
import { WalletUserAddComponent } from './pages/wallet/wallet-details/wallet-user-add/wallet-user-add.component';
import { TransactionListComponent } from './pages/transaction/transaction-list/transaction-list.component';
import { TransactionAddComponent } from './pages/transaction/transaction-add/transaction-add.component';
import { TransactionUpdateComponent } from './pages/transaction/transaction-update/transaction-update.component';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatRadioModule } from '@angular/material/radio';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { ChartFilterComponent } from './pages/chart/chart-filter/chart-filter.component'
import { LoginComponent } from './pages/login/login.component';
import { SignupComponent } from './pages/signup/signup.component';
import { UserComponent } from './pages/user/user.component';
import { PageNotFoundComponent } from './pages/page-not-found/page-not-found.component'
import { TransListGuard } from './guard/trans-list.guard';
import { MainComponent } from './main/main.component';
import { LoginGuard } from './guard/login.guard';
import { ChangePassComponent } from './pages/user/change-pass/change-pass.component';
import { ForgetPassComponent } from './pages/login/forget-pass/forget-pass.component';
import { NewPassComponent } from './pages/login/new-pass/new-pass.component';
import { InterceptorsService } from './services/interceptors.service';
@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    SidebarComponent,
    FooterComponent,
    DashboardComponent,
    MapsComponent,
    NotificationsComponent,
    WalletComponent,
    TransactionComponent,
    CategoryComponent,
    ChartComponent,
    WalletListComponent,
    WalletDetailsComponent,
    WalletAddComponent,
    WalletUpdateComponent,
    WalletUserAddComponent,
    TransactionListComponent,
    TransactionAddComponent,
    TransactionUpdateComponent,
    ChartFilterComponent,
    LoginComponent,
    SignupComponent,
    UserComponent,
    PageNotFoundComponent,
    MainComponent,
    ChangePassComponent,
    ForgetPassComponent,
    NewPassComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CommonModule,
    BrowserAnimationsModule,
    CollapseModule.forRoot(),
    ToastrModule.forRoot(),
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatSnackBarModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatRadioModule,
    MatIconModule,
    MatTableModule,
    MatPaginatorModule
  ],
  providers:
    [
      TransListGuard,
      LoginGuard,
      { provide: LOCALE_ID, useValue: "en-GB" },
      { provide: HTTP_INTERCEPTORS, useClass:InterceptorsService, multi:true  }
    ],
  bootstrap: [AppComponent],
  entryComponents:
    [
      WalletAddComponent,
      WalletUpdateComponent,
      WalletUserAddComponent,
      TransactionAddComponent,
      TransactionUpdateComponent,
      ChangePassComponent,
      ForgetPassComponent,
      NewPassComponent
    ]
})
export class AppModule { }
