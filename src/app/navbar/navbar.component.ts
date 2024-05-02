import { Component } from '@angular/core';
import {AppStateService} from "../services/app-state.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  actions: Array<any> = [
    { title: 'Home', "route":"/home", icon: "house"},
    { title: 'Products', "route":"/products", icon: "search"},
    { title: 'New Products', "route":"/newProduct", icon: "safe"},
  ];

  currentAction: any;

  constructor(public appState: AppStateService) {
  }

  setCurrentAction(action: any) {
    this.currentAction = action;
  }

}
