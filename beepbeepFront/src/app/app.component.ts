import { OutputConfiguration } from './models/outputConfiguration';
import { OutputConfigurationApiService } from './services/output-configuration-api.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'Status App';
  config1: OutputConfiguration = new OutputConfiguration('Aero Platform Build', true);
  config2: OutputConfiguration = new OutputConfiguration('Suite Build', true);
  configurations: OutputConfiguration[]

  constructor(private outputConfigurationApiService: OutputConfigurationApiService){}

  ngOnInit(): void {
    this.outputConfigurationApiService.getOutputConfigurations().then(result =>
      this.configurations = result
    );
  }

}
