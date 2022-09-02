import { Component, OnInit } from '@angular/core';
import { UserDetails } from 'src/app/common/interface/user-details';
import { UserService } from 'src/app/services/userservice/user.service';

@Component({
  selector: 'app-searchusers',
  templateUrl: './searchusers.component.html',
  styleUrls: ['./searchusers.component.css']
})
export class SearchusersComponent implements OnInit {

  private _searchTerm:string;
  originalLists:UserDetails[];
  filteredList:UserDetails[];
get searchTerm():string{
  return this._searchTerm;
}

set searchTerm(value: string){
  this._searchTerm = value;
  this.filteredList= this.filterList(value);
}

filterList(searchString: string){
  return this.originalLists.filter(user=> user.userName.toLowerCase().indexOf(
    searchString.toLowerCase())!== -1);
}

  constructor(private userService:UserService) { }

  ngOnInit(): void {
   
    this.userService.getAllUser().subscribe((data)=>{
      this.originalLists=data;
      this.filteredList=this.originalLists;
      console.log("Filterd list initial ",this.filteredList);
  },(error)=>{})
  }
  // search(userName: any){
  //   console.info("Search ",this.userName)
  //   this.filteredList=this.originalLists.filter(user=>user.userName.toLocaleLowerCase().includes(this.userName.toLocaleLowerCase()));

  // }
}