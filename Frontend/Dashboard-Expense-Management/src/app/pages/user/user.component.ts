import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog, MatDialogConfig } from "@angular/material/dialog";
import { ChangePassComponent } from 'src/app/pages/user/change-pass/change-pass.component';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {

  currentUser: User;

  constructor(
    public uService: UserService, 
    private snackBar: MatSnackBar,
    private dialog: MatDialog ) { }

  getUser(){
    this.uService.getUser(+sessionStorage.getItem('id')).subscribe(data => {
      console.log(data);
      this.currentUser = data;
    })
  }

  userUpdate(){
    this.uService.userUpdate(this.currentUser).subscribe(data => {
      sessionStorage.setItem('name',data.name);
      this.snackBar.open("Update Successfully!","Close",{
        duration: 3000,
        verticalPosition: "bottom",
        horizontalPosition: "center"
      });
    })
  }

  changePass(){
    console.log('initiating change password dialogue');
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.width = "40%";
    dialogConfig.data = this.currentUser;
    this.dialog.open(ChangePassComponent, dialogConfig);
  }

  ngOnInit() {
    this.getUser()
  }

}
