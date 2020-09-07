import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { User } from 'src/app/models/user';
import { JwtRequest } from 'src/app/models/jwtRequest';

export class JwtResponse {
  constructor(
    public jwttoken: string,
  ) { }
}

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private usersAPI: string;

  isLoggedIn() {
    let user = sessionStorage.getItem('username')
    console.log(!(user === null))
    return !(user === null)
  }

  constructor(private http: HttpClient) {
    this.usersAPI = 'http://localhost:8030/user';
  }

  //authenticate
  authenticate(req: JwtRequest){
    return this.http.post<any>('http://localhost:8030/authenticate', req).pipe(
      map(
        userData => {
          sessionStorage.setItem('username',req.userName);
          let tokenStr = 'Bearer ' + userData.token;
          console.log(tokenStr);
          sessionStorage.setItem('token',tokenStr);
          return userData;
        }
      )
    )
  }

  //get user by id. Classic.
  getUser(id: number): Observable<User> {
    return this.http.get<User>(`${this.usersAPI}/findbyid/${id}`);
  }

  //get user by username
  getUserByUsername(username: string): Observable<User> {
    return this.http.get<User>(`${this.usersAPI}/findbyusername?username=${username}`);
  }

  //get user by username, name, phone, sex -- WHAT IS THIS FOR???
  getUserByMany(username: string, name: string, tel: number, sex: boolean): Observable<User> {
    return this.http.get<User>(`${this.usersAPI}?user_name=${username}&name=${name}&phone_number=${tel}&sex=${sex}`)
  }

  //checkLogin (get by user and pass) -- NO LONGER USE
  checkLogin(user: string, pass: string): Observable<User> {
    return this.http.get<User>(`${this.usersAPI}?user_name=${user}&password=${pass}`);
  }

  //new registration (insert)
  register(user: User): Observable<User> {
    return this.http.post<User>(`${this.usersAPI}/save`, user);
  }

  //update user info
  userUpdate(user: User): Observable<User> {
    return this.http.put<User>(`${this.usersAPI}/update`, user);
  }

}
