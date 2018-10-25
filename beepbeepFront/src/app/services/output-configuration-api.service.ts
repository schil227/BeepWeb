import { OutputConfiguration } from '../models/outputConfiguration';
import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { interval } from 'rxjs/observable/interval';

@Injectable()
export class OutputConfigurationApiService {

  private outputConfigurationApiUrl = 'http://10.10.3.88:8080/status/';
  private http: Http;

  constructor(http: Http) {
    this.http = http;
    interval(10000).subscribe(() => this.getOutputConfigurations());
   }

  public getOutputConfigurations (): Promise<OutputConfiguration[]> {
      return this.http.get(this.outputConfigurationApiUrl)
        .toPromise()
        .then(response => response.json() as OutputConfiguration[]);
  }

}
