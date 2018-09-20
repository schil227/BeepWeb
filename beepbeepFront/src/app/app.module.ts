import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { OutputConfigurationStatusComponent } from './output-configuration-status/output-configuration-status.component';
import { OutputConfigurationApiService } from './services/output-configuration-api.service';
import { HttpModule } from '@angular/http'

@NgModule({
  declarations: [
    AppComponent,
    OutputConfigurationStatusComponent,
  ],
  imports: [
    BrowserModule,
    HttpModule
  ],
  providers: [OutputConfigurationApiService],
  bootstrap: [AppComponent]
})
export class AppModule { }
