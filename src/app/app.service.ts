import { Component } from '@angular/core';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';

import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { HttpErrorHandler, HandleError } from './http-error-handler.service';
import { Router, ActivatedRoute, ParamMap, Params } from '@angular/router';
import { Survey } from './survey';

import { map } from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    'Authorization': 'my-auth-token'
  })
};

@Injectable()
export class AppService {

  protected url : string = './assets/data.json/';
    // protected url : string = 'http://localhost:9080/survey-application/api/surveyapp/new?guid=23RSAN0FOAKY0Y';
  protected url2 : string = 'http://localhost:9080/survey-application/api/surveyapp';

  private handleError: HandleError;

  constructor(
    private http: HttpClient,
    httpErrorHandler: HttpErrorHandler) {
      this.handleError = httpErrorHandler.createHandleError('AppService');}

  // Rest Items Service: Read all REST Items
  getAll() {
    return this.http
      .get<any[]>(this.url)
      .pipe(map(data => data));
  }

  addSurvey (question: Survey): Observable<Survey> {
    let questionString = JSON.stringify(question);
    return this.http.post<Survey>(this.url2, questionString, httpOptions)
      .pipe(
        catchError(this.handleError('addSurvey', question))
      );
  }


}