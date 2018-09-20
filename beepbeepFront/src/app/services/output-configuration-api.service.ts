import { OutputConfiguration } from '../models/outputConfiguration';
import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class OutputConfigurationApiService {

  private outputConfigurationApiUrl = 'http://localhost:8080/status/'
  private http: Http;

  constructor(http: Http) {
    this.http = http;
   }

  public getOutputConfigurations (): Promise<OutputConfiguration[]> {
      return this.http.get(this.outputConfigurationApiUrl)
        .toPromise()
        .then(response => response.json() as OutputConfiguration[]);
  }

}
