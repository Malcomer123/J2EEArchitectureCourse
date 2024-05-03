import { Injectable } from '@angular/core';
import {Product} from "../modele/product.model";

@Injectable({
  providedIn: 'root'
})
export class AppStateService {
  public productState : any = {
    products: [] as Product[],
    keyword: "" as string,
    totalPages: 0 as number,
    totalProducts: 0 as number,
    pageSize: 5 as number,
    currentPage: 1 as number,
    status: "",
    errorMessage: "",
  }

  public authState: any={
    username: undefined,
    isAuthenticated: false,
    roles: undefined,
    token: undefined
  }
  constructor() { }

  public setProductState(state: any){
    this.productState = {...this.productState, ...state}
  }

  public setAuthState(state: any){
    this.authState = {...this.authState, ...state}
  }
}
