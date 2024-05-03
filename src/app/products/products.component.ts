import { Component, OnInit } from '@angular/core';
import { ProductService } from '../services/product.service';
import { Product } from '../modele/product.model';
import {Router} from "@angular/router";
import {AppStateService} from "../services/app-state.service";

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrl: './products.component.css'
})
export class ProductsComponent implements OnInit {


  constructor(private productService: ProductService,
              private router: Router,
              public appStateService: AppStateService){
  }

  ngOnInit(): void {
    this.searchProducts();
  }


  handleCheckProduct(product: Product) {
    this.productService.checkProduct(product)
      .subscribe({
        next: updatedProduct => {
          product.checked != product.checked
        }
      })
    product.checked = !product.checked;
  }

  handleDeleteProduct(product: Product) {
    if (confirm("u sure?"))
      this.productService.deleteProduct(product).subscribe({
        next: val => {
          //this.appStateService.productState.products = this.appStateService.productState.products.filter((p:any) => p.id != product.id)
          this.searchProducts();
        }
      })
  }

  handleGoToPage(page: number) {
    this.appStateService.productState.currentPage= page;
    this.searchProducts();
  }

  public searchProducts() {
    // this.appStateService.setProductState({status: "LOADING"});
    this.productService.searchProducts(this.appStateService.productState.keyword, this.appStateService.productState.currentPage, this.appStateService.productState.pageSize).subscribe({
      next: res => {
        let products = res.body as Product[];
        let totalProducts: number = parseInt(res.headers.get('X-Total-Count')!);
        //this.appStateService.productState.totalProducts = totalProducts;
        let totalPages = Math.floor(totalProducts / this.appStateService.productState.pageSize);
        if (totalProducts % this.appStateService.productState.pageSize != 0) this.appStateService.productState.totalPages++;
        this.appStateService.setProductState({
          products: products,
          totalProducts: totalProducts,
          totalPages: totalPages
        })
        },
      error: err => this.appStateService.setProductState({status: "ERROR", errorMessage: err})
    });
  }

    handleEditProduct(product: Product) {
      this.router.navigateByUrl('/admin/editProduct/' + product.id)
    }
}
