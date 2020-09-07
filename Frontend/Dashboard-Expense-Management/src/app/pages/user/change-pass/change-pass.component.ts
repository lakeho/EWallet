import { Component, OnInit, Inject } from '@angular/core';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-change-pass',
  templateUrl: './change-pass.component.html',
  styleUrls: ['./change-pass.component.scss']
})
export class ChangePassComponent implements OnInit {

  changedUser: User;
  oldPass: string;
  newPass: string;
  rePass: string;
  wrong: boolean = false;
  hide: boolean = true;

  constructor(
    public dialog: MatDialogRef<ChangePassComponent>,
    private snackBar: MatSnackBar,
    private uService: UserService,
    @Inject(MAT_DIALOG_DATA) public user
  ) { }

  onClose(){
    this.dialog.close();
  }

  changePass(){
    if(this.oldPass != this.user.password){
      this.wrong = true;
    }else if(this.newPass != this.rePass){
      alert('nhap lai mk')
    }else{
      this.user.password = this.newPass;
      this.uService.userUpdate(this.user).subscribe(data => {
        this.wrong = false;
        this.onClose();
        this.snackBar.open("Change Password Successfully!","Close",{
          duration: 3000,
          verticalPosition: "bottom",
          horizontalPosition: "center"
        });
      })
    }
  }

  ngOnInit() {
  }

}
