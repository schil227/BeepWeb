import { OutputConfiguration } from '../models/outputConfiguration';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-output-configuration-status',
  templateUrl: './output-configuration-status.component.html',
  styleUrls: ['./output-configuration-status.component.css']
})
export class OutputConfigurationStatusComponent implements OnInit {

  @Input () outputConfigurations: OutputConfiguration[];

  constructor() { }

  ngOnInit() {
  }

}
