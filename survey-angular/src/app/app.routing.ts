import { RouterModule, Routes } from '@angular/router';


import { AppComponent } from './app.component';

export const routes: Routes = [

   { path: 'survey/:guid', component: AppComponent }
];

export const routing = RouterModule.forRoot(routes);