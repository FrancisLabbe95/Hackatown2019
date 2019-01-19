import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pickup',
  templateUrl: './pickup.component.html',
  styleUrls: ['./pickup.component.css']
})
export class PickupComponent implements OnInit {

  constructor(private router: Router) { }

  username: string = "Francis"

  ngOnInit() {
  }

  giveBottles(): void { 
    this.router.navigateByUrl('/home');
  }

}
