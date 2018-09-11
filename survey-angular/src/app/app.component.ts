import { Component, OnInit } from '@angular/core';
import { map } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { AppService } from './app.service';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { switchMap } from 'rxjs/operators';

import { Survey } from './survey';
declare var $: any;



@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  restItems: any[];

 

  // constructor(private activatedRoute: ActivatedRoute) {}
    constructor(private appService: AppService) {}

   onNameKeyUp(event:any){
     console.log(event.target.value)
   }

  ngOnInit() {
	  
	    // this.activatedRoute.params.subscribe(paramsId => {
        // this.guid = paramsId.guid;
    // });
    // console.log(this.guid);
	  
    this.getRestItems();
  }

 // Read all REST Items
  getRestItems(): void {
    this.appService.getAll()
      .subscribe(
        restItems => {
          this.restItems = restItems;
          console.log(this.restItems);
        }
      )
  }

  
  // add(CAMPAIGN_GUID: string): void {
  //   this.restItems = restItems;
  //   CAMPAIGN_GUID = CAMPAIGN_GUID;
  //   if (!CAMPAIGN_GUID) { return; }

  //   // The server will generate the id for this new hero
  //   const newSurvey: any = { CAMPAIGN_GUID } as any;
  //   this.appService.addSurvey(newSurvey)
  //     .subscribe(survey => this.restItems.push(survey));
  // }

  // add(CAMPAIGN_GUID: string): void {
  //   this.restItems = undefined;
  //   CAMPAIGN_GUID = CAMPAIGN_GUID;
  //   if (!CAMPAIGN_GUID) { return; }

  //   // The server will generate the id for this new hero
  //   const newSurvey: any = { CAMPAIGN_GUID } as any;
  //   this.appService.addSurvey(newSurvey)
  //     .subscribe(restItem => this.restItems.push(restItem));
  // }

  add(CAMPAIGN_GUID: string, SURVEY_ID: number, ANSWER_VALUE: string): void {
    const newSurvey: Survey = { CAMPAIGN_GUID, SURVEY_ID, ANSWER_VALUE } as Survey;
    this.appService.addSurvey(newSurvey)
      .subscribe(
        question => {
          this.restItems.push(question);
          console.log(this.restItems);
        }
      )
  }

  selectedAnswer(question: any, answer: any) {
    question.ANSWER_VALUE = answer;
    console.log("selectedAnswer", question);
  }
}