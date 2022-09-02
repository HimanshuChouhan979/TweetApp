import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { ResetComponent } from './components/reset/reset/reset.component';
import { AuthGuard } from './services/authguard/auth.guard';

const routes: Routes = [
  {path:'',component:LoginComponent,pathMatch:'full'},
  {path:'login',component:LoginComponent,pathMatch:'full'},
  {path:'register',component:RegisterComponent,pathMatch:'full'},
  {path:'reset',component:ResetComponent,pathMatch:'full'},
  {
    path: 'user',
    canActivate:[AuthGuard],
    loadChildren:()=>
    import('./modules/dashboard/dashboard.module').then((m)=>m.DashboardModule)
  }
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
