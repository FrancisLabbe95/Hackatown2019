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

  username: string = "Francis";
  currentLat: number;
  currentLong: number;
  marker: any;
  myMarker: any;
  directionsService: any;
  directionsDisplay: any;
  showAddress: boolean = false;

  ngOnInit() {

    this.showAddress = false;
    this.directionsService = new google.maps.DirectionsService;
    this.directionsDisplay = new google.maps.DirectionsRenderer;

    var mapProp = {
      center: new google.maps.LatLng(45.5017, -73.5673),
      zoom: 12,
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

  findMe() {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition((position) => {
        this.showPosition(position);
      });
    } else {
      alert("Geolocation is not supported by this browser.");
    }

    this.addPositions();
    this.showAddress = true;
  }

  addPositions() {
    let location = new google.maps.LatLng(45.5017, -73.5673);

    this.showMarker(location);

    this.directionsDisplay.setMap(this.map);
    this.calculateAndDisplayRoute(this.directionsService, this.directionsDisplay);

  }

  showMarker(location){
    this.map.panTo(location);
    if (!this.myMarker) {
      this.myMarker = new google.maps.Marker({
        position: location,
        map: this.map,
        title: 'Got you!'
      });
    }
    else {
      this.myMarker.setPosition(location);
    }
  }

  calculateAndDisplayRoute(directionsService, directionsDisplay) {

    var waypts = [];
    var checkboxArray:any[] = [
        'winnipeg', 'regina','calgary'
];
for (var i = 0; i < checkboxArray.length; i++) {

      waypts.push({
        location: checkboxArray[i],
        stopover: true
      });

  }

  directionsService.route({
    origin: {lat: 41.85, lng: -87.65},
    destination: {lat: 49.3, lng: -123.12},
    waypoints: waypts,
    optimizeWaypoints: true,
    travelMode: 'DRIVING'
  }, function(response, status) {
    if (status === 'OK') {
      directionsDisplay.setDirections(response);
    } else {
      window.alert('Directions request failed due to ' + status);
    }
  });
}
  

  showPosition(position) {
    this.currentLat = position.coords.latitude;
    this.currentLong = position.coords.longitude;

    let location = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
    this.map.panTo(location);

    if (!this.marker) {
      this.marker = new google.maps.Marker({
        position: location,
        map: this.map,
        title: 'Got you!'
      });
    }
    else {
      this.marker.setPosition(location);
    }
  }
/*
  trackMe() {
    if (navigator.geolocation) {
      this.isTracking = true;
      navigator.geolocation.watchPosition((position) => {
        this.showTrackingPosition(position);
      });
    } else {
      alert("Geolocation is not supported by this browser.");
    }
  }
*/
}
