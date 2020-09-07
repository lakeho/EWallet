import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';
import { MatDialogRef, MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { NewPassComponent } from 'src/app/pages/login/new-pass/new-pass.component';

@Component({
  selector: 'app-forget-pass',
  templateUrl: './forget-pass.component.html',
  styleUrls: ['./forget-pass.component.scss']
})
export class ForgetPassComponent implements OnInit {

  username: string;
  fullname: string;
  phone: number;
  sex: boolean;
  wrong: boolean = false;

  constructor(
    public dialog: MatDialogRef<ForgetPassComponent>,
    private newDialog: MatDialog,
    private snackBar: MatSnackBar,
    private uService: UserService
  ) { }

  onClose() {
    this.dialog.close();
  }

  infoConfirm() {
    this.uService.getUserByUsername(this.username).subscribe(
      data => {
        console.log(this.phone)
        if (this.fullname == data.name && this.phone == data.phoneNumber && this.sex == data.gender) {
          this.wrong = false;
          //mở dialog mới, đóng dialog hiện tại
          console.log('initiating new password dialog');
          const dialogConfig = new MatDialogConfig();
          dialogConfig.autoFocus = true;
          dialogConfig.width = "30%";
          dialogConfig.data = data;
          this.newDialog.open(NewPassComponent, dialogConfig);
          this.onClose();
        }else{
          console.log(':v')
          this.wrong = true;
        }
      },
      error => {
        this.wrong = true;
        console.log('vkl')
      }
    )




    // this.uService.getUserByMany(this.username,this.fullname,this.phone,this.sex).subscribe(data => {
    //   if(data[0]!=null){
    //     this.wrong = false;
    //     //mở dialog mới, đóng dialog hiện tại
    // console.log('initiating new password dialog');
    // const dialogConfig = new MatDialogConfig();
    // dialogConfig.autoFocus = true;
    // dialogConfig.width = "30%";
    // dialogConfig.data = data[0];
    // this.newDialog.open(NewPassComponent,dialogConfig);
    // this.onClose();
    //   }else{
    //     this.wrong = true;
    //   }
    // })
  }

  ngOnInit() {
  }

}
