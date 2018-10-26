import { OutputConfiguration } from '../models/outputConfiguration';
import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { _ } from 'lodash';

@Injectable()
export class OutputConfigurationApiService {

  private outputConfigurationApiUrl = 'http://10.10.3.88:4200/api/';
  private http: Http;
  private allWorking: boolean;

  constructor(http: Http) {
    this.http = http;
    // Belongs in OnInit, but it wasn't getting called.
    this.allWorking = false;
   }

  public getOutputConfigurations (): Promise<OutputConfiguration[]> {
      return this.http.get(this.outputConfigurationApiUrl)
        .toPromise()
        .then(response => {
          const result = response.json() as OutputConfiguration[];
          let tmpStatus = true;

          // lodash was being a jerk.
          for (let i = 0; i < result.length; i++) {
            if (!result[i].isWorking) {
              tmpStatus = false;
              break;
            }
          }

          if (this.allWorking && !tmpStatus) {
            this.allWorking = false;
            new Audio('/assets/car_wreck.mp3').play();
          } else if (!this.allWorking && tmpStatus) {
            this.allWorking = true;
            new Audio('/assets/tom-tom-club_genius-of-love_intro.mp3').play();
          }

          return result;
        });
  }

}
