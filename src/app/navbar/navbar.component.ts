import { Component } from '@angular/core';
import {AppStateService} from "../services/app-state.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  actions: Array<any> = [
    { title: 'Home', "route":"/admin/home", icon: "house"},
    { title: 'Products', "route":"/admin/products", icon: "search"},
    { title: 'New Products', "route":"/admin/newProduct", icon: "safe"},
  ];

  currentAction: any;

  constructor(public appState: AppStateService,
              private router: Router) {
  }

  setCurrentAction(action: any) {
    this.currentAction = action;
  }

  logout() {
    this.appState.authState = {}
    this.router.navigateByUrl("/login")
  }
}
