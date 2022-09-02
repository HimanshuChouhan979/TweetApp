import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/loginservice/login.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private loginService:LoginService) { }

  ngOnInit(): void {
  }
  logoutUser(){
    this.loginService.logout();
  }

}
