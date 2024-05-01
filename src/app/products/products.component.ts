import { Component, OnInit } from '@angular/core';
import { ProductService } from '../services/product.service';
import { Product } from '../product.model';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrl: './products.component.css'
})
export class ProductsComponent implements OnInit{
  public products: Array<Product> = []
  public keyword: string = ""

  constructor(private productService:ProductService){

  }

  ngOnInit(): void {
    this.productService.getProducts().subscribe({
      next: val => { this.products = val },
      error: err => console.log(err)
    });
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
    if(confirm("u sure?"))
    this.productService.deleteProduct(product).subscribe({
      next: val => {
        this.products = this.products.filter(p => p.id != product.id)
      }
    })
  }

  handleSearchProduct(){
    this.products = this.products.filter(p=> p.name.includes(this.keyword))
    this.productService.searchProduct(this.keyword).subscribe({
      next: data =>{
        this.products = data;
      },
      error: err => console.log(err)
    })
  }
}
