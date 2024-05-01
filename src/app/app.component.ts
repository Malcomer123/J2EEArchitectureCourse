import { Component } from '@angular/core';
import { HomeComponent } from './home/home.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  actions: Array<any> = [
    { title: 'Home', "route":"/home", icon: "house"},
    { title: 'Products', "route":"/products", icon: "search"},
    { title: 'New Products', "route":"/newProduct", icon: "safe"},
  ];

  currentAction: any;

  setCurrentAction(action: any) {
    this.currentAction = action;
  }

}
