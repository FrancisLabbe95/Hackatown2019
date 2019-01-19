import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  title = "Welcome to PolyPaintPro.";
  loginText = 'Please enter your credentials';
  errorMessage = "Login was not possible";
  showErrorMessage = false;

  username = "";
  password = "";

  constructor(private router: Router) { }

  ngOnInit() {
  }

  onSubmit(): void{
    this.router.navigateByUrl('/home');
  }
  
}
