import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable(/*{
  providedIn: 'root',
}*/)
export class ServerCommunicationService {

  authToken: string;
    serverUrl: string = "http://getcan.net/api"

    constructor( private htttpClient: HttpClient) { }

    getUser(): Promise<string> {

        return
    }

    getPickup(): Promise<number> {
        return 
    }

    putUser(name: string): Promise<boolean>{
        return
    }

    postUser(name: string): Promise<boolean> {
        return
    }

    getUserName(): Promise<boolean> {
        return
    }

    getUserId():Promise<boolean> {
        return
    }

}
