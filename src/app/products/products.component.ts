import { Component, OnInit } from '@angular/core';
import { ProductService } from '../services/product.service';
import { Product } from '../modele/product.model';
import {Router} from "@angular/router";

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrl: './products.component.css'
})
export class ProductsComponent implements OnInit {

  public products: Array<Product> = []
  public keyword: string = ""
  public totalPages: number = 0
  public pageSize: number = 5
  public currentPage: number = 1

  constructor(private productService: ProductService,
              private router: Router) {

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
          this.products = this.products.filter(p => p.id != product.id)
        }
      })
  }

  handleGoToPage(page: number) {
    this.currentPage= page;
    this.searchProducts();
  }

  public searchProducts() {
    this.productService.searchProducts(this.keyword, this.currentPage, this.pageSize).subscribe({
      next: res => {
        this.products = res.body as Product[];
        let totalProducts: number = parseInt(res.headers.get('X-Total-Count')!);
        this.totalPages = Math.floor(totalProducts / this.pageSize);
        if (totalProducts % this.pageSize != 0) this.totalPages++;
      },
      error: err => console.log(err)
    });
  }

    handleEditProduct(product: Product) {
      this.router.navigateByUrl('/editProduct/' + product.id)
    }
}
