import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { AppService } from './app.service';

import { HttpErrorHandler }     from './http-error-handler.service';
import { MessageService }       from './message.service';

import { Observable } from 'rxjs/Observable';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpResponse} from '@angular/common/http';
import 'rxjs/add/operator/do';

import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material';
import {MatFormFieldModule} from '@angular/material/form-field';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { RouterModule, Routes } from '@angular/router';
import { routing } from './app.routing';



@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
	RouterModule,
	MatDatepickerModule,
	MatNativeDateModule,
	MatFormFieldModule,
	FormsModule,
	routing,
	BrowserAnimationsModule
  ],
  exports : [
  RouterModule,
  ],
  providers: [
  AppService,
  HttpErrorHandler,
  MessageService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }