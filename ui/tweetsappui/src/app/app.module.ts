import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import {MatButtonModule} from '@angular/material/button';
import { LoginComponent } from './components/login/login.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { RegisterComponent } from './components/register/register.component';
import { RouterModule} from '@angular/router';
import { ResetComponent } from './components/reset/reset/reset.component';
import { LoginService } from './services/loginservice/login.service';
import { AuthGuard } from './services/authguard/auth.guard';
import { AuthInterceptor } from './services/auth.interceptor';
import { UserDashboardComponent } from './modules/components/user-dashboard/user-dashboard.component';
import { ProfileComponent } from './modules/components/profile/profile.component';
import { TweetsComponent } from './modules/components/tweets/tweets.component';
import { SearchusersComponent } from './modules/components/searchusers/searchusers.component';
import { HomeComponent } from './modules/components/home/home.component';
import { NavbarComponent } from './modules/components/navbar/navbar.component';
import { MatCardModule } from '@angular/material/card';
import {MatIconModule} from '@angular/material/icon';
import {MatSelectModule} from '@angular/material/select';
import { HashtagsComponent } from './modules/components/hashtags/hashtags.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,    
    HomeComponent,    
    RegisterComponent,
    ResetComponent,
    UserDashboardComponent,
    ProfileComponent,
    TweetsComponent,
    SearchusersComponent,
    NavbarComponent,
    HashtagsComponent,
    

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,    
    ReactiveFormsModule,
    HttpClientModule,
    MatButtonModule,
    MatToolbarModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,        
    RouterModule,
    MatCardModule,
    MatIconModule,
    MatSelectModule
  ],
  providers: [LoginService,AuthGuard,[{ provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }]],
  bootstrap: [AppComponent]
})
export class AppModule { }
