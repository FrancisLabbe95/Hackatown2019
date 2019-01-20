import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { ViewChild } from '@angular/core';
import { } from '@types/googlemaps';

@Component({
  selector: 'app-pickup',
  templateUrl: './pickup.component.html',
  styleUrls: ['./pickup.component.css']
})
export class PickupComponent implements OnInit {

  @ViewChild('gmap') gmapElement: any;
  map: google.maps.Map;

  constructor(private router: Router) { }

  username: string = "Francis"

  ngOnInit() {

    var mapProp = {
      center: new google.maps.LatLng(18.5793, 73.8143),
      zoom: 15,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    this.map = new google.maps.Map(this.gmapElement.nativeElement, mapProp);
  }

  giveBottles(): void { 
    this.router.navigateByUrl('/home');
  }

  setMapType(mapTypeId: string) {
    this.map.setMapTypeId(mapTypeId)    
}

}
