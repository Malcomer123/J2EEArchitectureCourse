import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ProductService} from "../services/product.service";
import {log} from "@angular-devkit/build-angular/src/builders/ssr-dev-server";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Product} from "../modele/product.model";

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrl: './edit-product.component.css'
})
export class EditProductComponent implements OnInit{
  productId!:number
  productFormGroup!: FormGroup

  constructor(private activatedRoute: ActivatedRoute,
              private productService: ProductService,
              private fb: FormBuilder){
  }

  ngOnInit(): void {
    this.productId= this.activatedRoute.snapshot.params['id']
    this.productService.getProductById(this.productId).subscribe({
      next: data =>{
        this.productFormGroup=this.fb.group({
          id: this.fb.control(data.id),
          name: this.fb.control(data.name, [Validators.required]),
          price: this.fb.control(data.price, [Validators.min(100)]),
          checked: this.fb.control(data.checked)
        })
      },
      error: err => console.log(err)
    })
  }


  handleUpdateProduct() {
    let product: Product = this.productFormGroup.value;
    this.productService.updateProduct(product).subscribe({
      next: data =>{
        alert(JSON.stringify(data))
      }
    })
  }
}
