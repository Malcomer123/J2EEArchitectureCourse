import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {firstValueFrom} from "rxjs";
import {AppStateService} from "./app-state.service";
import {jwtDecode} from "jwt-decode";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http: HttpClient,
              private appState: AppStateService) { }

  async login(username:string, password:string){
    let user:any = await firstValueFrom(this.http.get("http://localhost:8089/users?username="+username));
    console.log(user)
    console.log(username, password)
    if(password === user[0].password){
      console.log(true)
      let decodedJwt:any = jwtDecode(user[0].token)
      this.appState.setAuthState({
        username: decodedJwt.sub,
        roles: decodedJwt.roles,
        isAuthenticated: true,
        token: user[0].token
      })
      return Promise.resolve(true);
    }
    return Promise.reject("Bad Credentials")
  }
}
