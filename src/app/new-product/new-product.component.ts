import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-new-product',
  templateUrl: './new-product.component.html',
  styleUrl: './new-product.component.css'
})
export class NewProductComponent implements OnInit{
  public productForm!: FormGroup;

  constructor(private fb: FormBuilder, private productService:ProductService){

  }
  ngOnInit(): void {
    this.productForm = this.fb.group({
      name: this.fb.control('', Validators.required),
      price: this.fb.control(0, Validators.required),
      checked: this.fb.control(false, Validators.required)
    })
  }

  handleSubmitProduct() {
    let product = this.productForm.value;
    this.productService.saveProduct(product).subscribe({
      next: val =>{
        alert(JSON.stringify(val))
      },
      error: err => console.log(err)
      
    });

  }
}
