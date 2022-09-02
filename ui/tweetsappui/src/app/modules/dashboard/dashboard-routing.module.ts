import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from '../components/home/home.component';


import { ProfileComponent } from '../components/profile/profile.component';
import { UserDashboardComponent } from '../components/user-dashboard/user-dashboard.component';

const routes: Routes = [
  {path : '', component: UserDashboardComponent,children: [
    {path:'home',component: HomeComponent},
    {path:'profile',component:ProfileComponent},
    {path:'',redirectTo:'/user/home',pathMatch:'full'}

],
  },
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardRoutingModule { }
