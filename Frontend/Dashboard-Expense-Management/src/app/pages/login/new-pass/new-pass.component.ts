import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog'
import { MatSnackBar } from '@angular/material/snack-bar'
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-new-pass',
  templateUrl: './new-pass.component.html',
  styleUrls: ['./new-pass.component.scss']
})
export class NewPassComponent implements OnInit {


  newPass: string;
  rePass: string;
  hide: boolean = true;

  constructor(
    public dialog: MatDialogRef<NewPassComponent>,
    @Inject(MAT_DIALOG_DATA) public user,
    public uService: UserService,
    public snackBar: MatSnackBar
  ) { }

    setNewPass(){
      if(this.newPass == this.rePass){
        this.user.passWord = this.newPass;
        this.uService.userUpdate(this.user).subscribe(data => {
          this.snackBar.open("Change Password Successfully!","Close",{
            duration: 3000,
            verticalPosition: "bottom",
            horizontalPosition: "center"
          });
          this.onClose();
        })
      }else{
        console.log('lmao')
      }
    }

    onClose(){
      this.dialog.close();
    }

  ngOnInit() {
  }

}
