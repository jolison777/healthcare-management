import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs/operators';
@Injectable({providedIn:'root'}) export class AuthService{
 private base='http://localhost:8080/api/auth'; private current:any=null;
 constructor(private http:HttpClient){}
 login(email:string,password:string){return this.http.post<any>(`${this.base}/login`,{email,password}).pipe(tap(r=>this.current=r));}
 register(data:any){return this.http.post(`${this.base}/register`,data);}
 session(){return this.current} token(){return this.current?.token||''} isLoggedIn(){return !!this.current} logout(){this.current=null;}
}
